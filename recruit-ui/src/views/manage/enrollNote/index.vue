<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="考生ID" prop="stuEnrollId">
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
      <el-form-item label="通知书" prop="noteCode">
        <el-input
          v-model="queryParams.noteCode"
          placeholder="请输入通知书编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="招生年度" prop="planYear">
        <el-input
          v-model="queryParams.planYear"
          placeholder="请输入招生年度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="省份名称" prop="provinceName">
        <el-input
          v-model="queryParams.provinceName"
          placeholder="请输入省份名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="录取院校" prop="stuDeptName">
        <el-input
          v-model="queryParams.stuDeptName"
          placeholder="请输入录取院校"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="录取专业" prop="stuMajor">
        <el-input
          v-model="queryParams.stuMajor"
          placeholder="请输入录取专业"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生姓名" prop="stuName">
        <el-input
          v-model="queryParams.stuName"
          placeholder="请输入学生姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否录取" prop="isEnroll">
        <el-select v-model="queryParams.isEnroll" placeholder="请选择是否录取" clearable>
          <el-option
            v-for="dict in dict.type.is_enroll"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="通知书" prop="notePrintStatus">
        <el-select v-model="queryParams.notePrintStatus" placeholder="请选择通知书打印" clearable>
          <el-option
            v-for="dict in dict.type.is_common_whether"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <!--      <el-form-item label="通知书打印信息" prop="notePrintInfo">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.notePrintInfo"-->
      <!--          placeholder="请输入通知书打印信息"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
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
      <!--      <el-form-item label="更新者" prop="updateBy">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.updateBy"-->
      <!--          placeholder="请输入更新者"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="更新时间">-->
      <!--        <el-date-picker-->
      <!--          v-model="daterangeUpdateTime"-->
      <!--          style="width: 240px"-->
      <!--          value-format="yyyy-MM-dd"-->
      <!--          type="daterange"-->
      <!--          range-separator="-"-->
      <!--          start-placeholder="开始日期"-->
      <!--          end-placeholder="结束日期"-->
      <!--        ></el-date-picker>-->
      <!--      </el-form-item>-->
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
          v-hasPermi="['manage:enrollNote:add']"
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
          v-hasPermi="['manage:enrollNote:edit']"
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
          v-hasPermi="['manage:enrollNote:remove']"
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
          v-hasPermi="['manage:enrollNote:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="enrollNoteList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="考生ID" align="center" v-if="columns[0].visible" prop="stuEnrollName"/>
      <el-table-column label="通知书编号" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible"
                       prop="noteCode"
      />
      <el-table-column label="前缀" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible"
                       prop="noteCodePrefix"
      />
      <el-table-column label="后缀" :show-overflow-tooltip="true" align="center" v-if="columns[3].visible"
                       prop="noteCodeSuffix"
      />
      <el-table-column label="招生年度" :show-overflow-tooltip="true" align="center" v-if="columns[4].visible"
                       prop="planYear"
      />
      <el-table-column label="省份名称" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible"
                       prop="provinceName"
      />
      <el-table-column label="录取院校" :show-overflow-tooltip="true" align="center" v-if="columns[6].visible"
                       prop="stuDeptName"
      />
      <el-table-column label="录取专业" :show-overflow-tooltip="true" align="center" v-if="columns[7].visible"
                       prop="stuMajor"
      />
      <el-table-column label="学生姓名" :show-overflow-tooltip="true" align="center" v-if="columns[8].visible"
                       prop="stuName"
      />
      <el-table-column label="是否录取" align="center" v-if="columns[9].visible" prop="isEnroll">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.is_enroll" :value="scope.row.isEnroll"/>
        </template>
      </el-table-column>
      <el-table-column label="通知书防伪码" :show-overflow-tooltip="true" align="center" v-if="columns[10].visible"
                       prop="noteSecurityCode"
      />
      <el-table-column label="通知书快递号" :show-overflow-tooltip="true" align="center" v-if="columns[11].visible"
                       prop="noteExpCode"
      />
      <el-table-column label="通知书打印" align="center" v-if="columns[12].visible" prop="notePrintStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.is_common_whether" :value="scope.row.notePrintStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="通知书打印信息" :show-overflow-tooltip="true" align="center" v-if="columns[13].visible"
                       prop="notePrintInfo"
      />
      <el-table-column label="通知书打印时间" align="center" v-if="columns[14].visible" prop="notePrintTime"
                       width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.notePrintTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建者" :show-overflow-tooltip="true" align="center" v-if="columns[15].visible"
                       prop="createBy"
      />
      <el-table-column label="创建时间" align="center" v-if="columns[16].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新者" :show-overflow-tooltip="true" align="center" v-if="columns[17].visible"
                       prop="updateBy"
      />
      <el-table-column label="更新时间" align="center" v-if="columns[18].visible" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[19].visible"
                       prop="remark"
      />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:enrollNote:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:enrollNote:remove']"
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

    <!-- 添加或修改通知书信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="考生ID" prop="stuEnrollId">
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
        <el-form-item label="通知书编号" prop="noteCode">
          <el-input v-model="form.noteCode" placeholder="请输入通知书编号"/>
        </el-form-item>
        <el-form-item label="前缀" prop="noteCodePrefix">
          <el-input v-model="form.noteCodePrefix" placeholder="请输入通知书编号-前缀"/>
        </el-form-item>
        <el-form-item label="后缀" prop="noteCodeSuffix">
          <el-input v-model="form.noteCodeSuffix" placeholder="请输入通知书编号-后缀"/>
        </el-form-item>
        <el-form-item label="招生年度" prop="planYear">
          <el-input-number  :min="0" :max="9999" v-model="form.planYear" placeholder="请输入招生年度"/>
        </el-form-item>
        <el-form-item label="省份名称" prop="provinceName">
          <el-input v-model="form.provinceName" placeholder="请输入省份名称"/>
        </el-form-item>
        <el-form-item label="录取院校" prop="stuDeptName">
          <el-input v-model="form.stuDeptName" placeholder="请输入录取院校"/>
        </el-form-item>
        <el-form-item label="录取专业" prop="stuMajor">
          <el-input v-model="form.stuMajor" placeholder="请输入录取专业"/>
        </el-form-item>
        <el-form-item label="学生姓名" prop="stuName">
          <el-input v-model="form.stuName" placeholder="请输入学生姓名"/>
        </el-form-item>
        <el-form-item label="是否录取" prop="isEnroll">
          <el-radio-group v-model="form.isEnroll">
            <el-radio
              v-for="dict in dict.type.is_enroll"
              :key="dict.value"
              :label="dict.value"
            >{{ dict.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="通知书防伪码" prop="noteSecurityCode">
          <el-input v-model="form.noteSecurityCode" placeholder="请输入通知书防伪码"/>
        </el-form-item>
        <el-form-item label="通知书快递号" prop="noteExpCode">
          <el-input v-model="form.noteExpCode" placeholder="请输入通知书快递号"/>
        </el-form-item>
        <el-form-item label="通知书打印" prop="notePrintStatus">
          <el-radio-group v-model="form.notePrintStatus">
            <el-radio
              v-for="dict in dict.type.is_common_whether"
              :key="dict.value"
              :label="dict.value"
            >{{ dict.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="打印信息" prop="notePrintInfo">
          <el-input v-model="form.notePrintInfo" placeholder="请输入通知书打印信息"/>
        </el-form-item>
        <el-form-item label="打印时间" prop="notePrintTime">
          <el-date-picker clearable
                          v-model="form.notePrintTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择通知书打印时间"
          >
          </el-date-picker>
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
import { listEnrollNote, getEnrollNote, delEnrollNote, addEnrollNote, updateEnrollNote } from '@/api/manage/enrollNote'
import { listEnrollBasic } from '@/api/manage/enrollBasic'

export default {
  name: 'EnrollNote',
  dicts: ['is_common_whether', 'is_enroll'],
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
      //表格展示列
      columns: [
        { key: 0, label: '考生ID', visible: true },
        { key: 1, label: '通知书编号', visible: true },
        { key: 2, label: '通知书编号-前缀', visible: false },
        { key: 3, label: '通知书编号-后缀', visible: false },
        { key: 4, label: '招生年度', visible: true },
        { key: 5, label: '省份名称', visible: true },
        { key: 6, label: '录取院校', visible: true },
        { key: 7, label: '录取专业', visible: true },
        { key: 8, label: '学生姓名', visible: true },
        { key: 9, label: '是否录取', visible: true },
        { key: 10, label: '通知书防伪码', visible: false },
        { key: 11, label: '通知书快递号', visible: true },
        { key: 12, label: '通知书打印', visible: true },
        { key: 13, label: '通知书打印信息', visible: false },
        { key: 14, label: '通知书打印时间', visible: true },
        { key: 15, label: '创建者', visible: true },
        { key: 16, label: '创建时间', visible: false },
        { key: 17, label: '更新者', visible: false },
        { key: 18, label: '更新时间', visible: false },
        { key: 19, label: '备注', visible: false }
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
      // 通知书信息表格数据
      enrollNoteList: [],
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
        stuEnrollId: null,
        noteCode: null,
        planYear: null,
        provinceName: null,
        stuDeptName: null,
        stuMajor: null,
        stuName: null,
        isEnroll: null,
        notePrintStatus: null,
        notePrintInfo: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
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
    /** 查询通知书信息列表 */
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
      listEnrollNote(this.queryParams).then(response => {
        this.enrollNoteList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        stuEnrollId: null,
        noteCode: null,
        noteCodePrefix: null,
        noteCodeSuffix: null,
        planYear: null,
        provinceName: null,
        stuDeptName: null,
        stuMajor: null,
        stuName: null,
        isEnroll: null,
        noteSecurityCode: null,
        noteExpCode: null,
        notePrintStatus: null,
        notePrintInfo: null,
        notePrintTime: null,
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
      this.ids = selection.map(item => item.stuEnrollId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加通知书信息'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const stuEnrollId = row.stuEnrollId || this.ids
      getEnrollNote(stuEnrollId).then(response => {
        this.form = response.data
        this.getEnrollInfoList()
        this.open = true
        this.title = '修改通知书信息'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.stuEnrollId != null) {
            updateEnrollNote(this.form).then(response => {
              this.$modal.msgSuccess('操作成功')
              this.open = false
              this.getList()
            })
          } else {
            addEnrollNote(this.form).then(response => {
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
      const stuEnrollIds = row.stuEnrollId || this.ids
      this.$modal.confirm('是否确认删除通知书信息编号为"' + stuEnrollIds + '"的数据项？').then(function() {
        return delEnrollNote(stuEnrollIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/enrollNote/export', {
        ...this.queryParams
      }, `enrollNote_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
