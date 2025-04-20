<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="记录ID" prop="recordId">
        <el-input
          v-model="queryParams.recordId"
          placeholder="请输入记录ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联络记录" prop="contactId">
        <el-input
          v-model="queryParams.contactId"
          placeholder="请输入联络记录ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考生" prop="stuEnrollId">
        <el-select
          v-model="queryParams.stuEnrollId"
          filterable
          remote
          reserve-keyword
          placeholder="请输入考生姓名"
          :remote-method="selectEnrollInfoList"
          :loading="enrollLoading"
        >
          <el-option
            v-for="item in enrollInfoList"
            :key="item.stuEnrollId"
            :label="`${item.stuName}--${item.examNum}`"
            :value="item.stuEnrollId"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="接听人" prop="answerPeopleName">
        <el-input
          v-model="queryParams.answerPeopleName"
          placeholder="请输入接听人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建者" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="更新者" prop="updateBy">
        <el-input
          v-model="queryParams.updateBy"
          placeholder="请输入更新者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新时间">
        <el-date-picker
          v-model="daterangeUpdateTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['manage:enrollContactRecord:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['manage:enrollContactRecord:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['manage:enrollContactRecord:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['manage:enrollContactRecord:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="enrollContactRecordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="记录ID" align="center" v-if="columns[0].visible" prop="recordId"/>
      <el-table-column label="联络记录ID" align="center" v-if="columns[1].visible" prop="contactId"/>
      <el-table-column label="考生" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible"
                       prop="stuEnrollName"
      />
      <el-table-column label="开始时间" align="center" v-if="columns[3].visible" prop="contactStartTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.contactStartTime, '{y}-{m}-{d} {h}:{m}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" v-if="columns[4].visible" prop="contactEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.contactEndTime, '{y}-{m}-{d} {h}:{m}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="接听人" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible"
                       prop="answerPeopleName"
      />
      <el-table-column label="与考生关系" :show-overflow-tooltip="true" align="center" v-if="columns[6].visible"
                       prop="answerPeopleRelation"
      />
      <el-table-column label="关系说明" :show-overflow-tooltip="true" align="center" v-if="columns[7].visible"
                       prop="answerPeopleRemark"
      />
      <el-table-column label="回复内容" :show-overflow-tooltip="true" align="center" v-if="columns[8].visible"
                       prop="replyContent"
      />
      <el-table-column label="回复说明" :show-overflow-tooltip="true" align="center" v-if="columns[9].visible"
                       prop="replyContentRemark"
      />
      <el-table-column label="附件名称" :show-overflow-tooltip="true" align="center" v-if="columns[10].visible"
                       prop="fileName"
      />
      <el-table-column label="附件后缀名" :show-overflow-tooltip="true" align="center" v-if="columns[11].visible"
                       prop="fileSuffix"
      />
      <el-table-column label="附件路径" align="center" v-if="columns[12].visible" prop="fileUrl" width="100">
        <template slot-scope="scope">
          <el-link v-show="scope.row.fileUrl!=null"
                   :download="getFileName(scope.row.fileUrl)"
                   :href="baseUrl+scope.row.fileUrl"
                   :underline="false"
                   target="_blank"
          >
            <span style="cursor: pointer;"> 下载 </span>
          </el-link>
        </template>
      </el-table-column>
      <el-table-column label="创建者" :show-overflow-tooltip="true" align="center" v-if="columns[13].visible"
                       prop="createBy"
      />
      <el-table-column label="创建时间" align="center" v-if="columns[14].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新者" :show-overflow-tooltip="true" align="center" v-if="columns[15].visible"
                       prop="updateBy"
      />
      <el-table-column label="更新时间" align="center" v-if="columns[16].visible" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[17].visible"
                       prop="remark"
      />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:enrollContactRecord:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:enrollContactRecord:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改考生联络记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
<!--        <el-form-item label="联络ID" prop="stuEnrollId">-->
<!--          <el-input v-model="form.contactId" placeholder="请输入联络ID"/>-->
<!--        </el-form-item>-->
        <el-form-item label="考生" prop="stuEnrollId">
          <el-select
            v-model="form.stuEnrollId"
            filterable
            remote
            reserve-keyword
            placeholder="请输入考生姓名"
            :remote-method="selectEnrollInfoList"
            :loading="enrollLoading"
          >
            <el-option
              v-for="item in enrollInfoList"
              :key="item.stuEnrollId"
              :label="`${item.stuName}--${item.examNum}`"
              :value="item.stuEnrollId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开始时间" prop="contactStartTime">
          <el-date-picker clearable
                          v-model="form.contactStartTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="请选择开始时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="contactEndTime">
          <el-date-picker clearable
                          v-model="form.contactEndTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="请选择结束时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="接听人" prop="answerPeopleName">
          <el-input v-model="form.answerPeopleName" placeholder="请输入接听人"/>
        </el-form-item>
        <el-form-item label="考生关系" prop="answerPeopleRelation">
          <el-input v-model="form.answerPeopleRelation" placeholder="请输入与考生关系"/>
        </el-form-item>
        <el-form-item label="关系说明" prop="answerPeopleRemark">
          <el-input v-model="form.answerPeopleRemark" placeholder="请输入关系说明"/>
        </el-form-item>
        <el-form-item label="回复内容">
          <el-input type="textarea" placeholder="请输入回复内容" v-model="form.replyContent"/>
        </el-form-item>
        <el-form-item label="回复说明" prop="replyContentRemark">
          <el-input v-model="form.replyContentRemark" placeholder="请输入回复说明"/>
        </el-form-item>
        <!--        <el-form-item label="附件名称" prop="fileName">-->
        <!--          <el-input v-model="form.fileName" placeholder="请输入附件名称"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="附件后缀名" prop="fileSuffix">-->
        <!--          <el-input v-model="form.fileSuffix" placeholder="请输入附件后缀名"/>-->
        <!--        </el-form-item>-->
        <el-form-item label="附件路径" prop="fileUrl">
          <file-upload v-model="form.fileUrl"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listEnrollContactRecord,
  getEnrollContactRecord,
  delEnrollContactRecord,
  addEnrollContactRecord,
  updateEnrollContactRecord
} from '@/api/manage/enrollContactRecord'
import { listEnrollBasic } from '@/api/manage/enrollBasic'

export default {
  name: 'EnrollContactRecord',
  data() {
    return {
      //考生相关信息
      enrollInfoList: [],
      enrollLoading: false,
      enrollQueryParams: {
        stuName: '',
        pageNum: 1,
        pageSize: 100
      },
      baseUrl: process.env.VUE_APP_BASE_API,
      //表格展示列
      columns: [
        { key: 0, label: '记录ID', visible: false },
        { key: 1, label: '联络记录ID', visible: false },
        { key: 2, label: '考生', visible: true },
        { key: 3, label: '开始时间', visible: true },
        { key: 4, label: '结束时间', visible: true },
        { key: 5, label: '接听人', visible: true },
        { key: 6, label: '与考生关系', visible: false },
        { key: 7, label: '关系说明', visible: false },
        { key: 8, label: '回复内容', visible: true },
        { key: 9, label: '回复说明', visible: false },
        { key: 10, label: '附件名称', visible: false },
        { key: 11, label: '附件后缀名', visible: false },
        { key: 12, label: '附件路径', visible: true },
        { key: 13, label: '创建者', visible: false },
        { key: 14, label: '创建时间', visible: false },
        { key: 15, label: '更新者', visible: false },
        { key: 16, label: '更新时间', visible: false },
        { key: 17, label: '备注', visible: false }
      ],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 考生联络记录表格数据
      enrollContactRecordList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangeCreateTime: [],
      // 备注时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        recordId: null,
        contactId: null,
        stuEnrollId: null,
        answerPeopleName: null,
        fileUrl: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        stuEnrollId: [
          { required: true, message: '考生不能为空', trigger: 'blur' }
        ],
        answerPeopleName: [
          { required: true, message: '接听人不能为空',trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getList()
    this.getEnrollInfoList()
  },
  methods: {
    /**
     * 获取考生列表推荐
     * @param query
     */
    selectEnrollInfoList(query) {
      if (query !== '') {
        this.enrollLoading = true
        this.enrollQueryParams.stuName = query
        setTimeout(() => {
          this.getEnrollInfoList()
        }, 200)
      } else {
        this.enrollInfoList = []
        this.enrollQueryParams.stuName = null
      }
    }
    ,
    /**
     * 获取考生信息列表
     */
    getEnrollInfoList() {
      //添加查询参数
      if (this.form.stuEnrollId != null) {
        this.enrollQueryParams.enrollId = this.form.stuEnrollId
      } else {
        this.enrollQueryParams.enrollId = null
      }
      if (this.enrollQueryParams.stuName !== '') {
        this.enrollQueryParams.enrollId = null
      }
      this.enrollInfoList = []
      listEnrollBasic(this.enrollQueryParams).then(res => {
        this.enrollInfoList = res?.rows
        this.enrollLoading = false
      })
    },
    /** 查询考生联络记录列表 */
    getList() {
      this.loading = true
      this.queryParams.params = {}
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params['beginCreateTime'] = this.daterangeCreateTime[0]
        this.queryParams.params['endCreateTime'] = this.daterangeCreateTime[1]
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params['beginUpdateTime'] = this.daterangeUpdateTime[0]
        this.queryParams.params['endUpdateTime'] = this.daterangeUpdateTime[1]
      }
      listEnrollContactRecord(this.queryParams).then(response => {
        this.enrollContactRecordList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    //获取文件名 此功能只可以下载只有一个文件的
    getFileName(filePath) {
      if (filePath == null) {
        return
      }
      // 提取文件名或根据需求生成文件名
      const fileName = filePath.substring(filePath.lastIndexOf('/') + 1)
      return fileName
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        recordId: null,
        contactId: null,
        stuEnrollId: null,
        contactStartTime: null,
        contactEndTime: null,
        answerPeopleName: null,
        answerPeopleRelation: null,
        answerPeopleRemark: null,
        replyContent: null,
        replyContentRemark: null,
        fileName: null,
        fileSuffix: null,
        fileUrl: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreateTime = []
      this.daterangeUpdateTime = []
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.recordId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加考生联络记录'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const recordId = row.recordId || this.ids
      getEnrollContactRecord(recordId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改考生联络记录'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.recordId != null) {
            updateEnrollContactRecord(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addEnrollContactRecord(this.form).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const recordIds = row.recordId || this.ids
      this.$modal.confirm('是否确认删除考生联络记录编号为"' + recordIds + '"的数据项？').then(function() {
        return delEnrollContactRecord(recordIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/enrollContactRecord/export', {
        ...this.queryParams
      }, `enrollContactRecord_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
