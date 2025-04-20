<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
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
      <el-form-item label="学号" prop="stuNo">
        <el-input
          v-model="queryParams.stuNo"
          placeholder="请输入学号"
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
      <el-form-item label="班级" prop="classId">
        <el-input
          v-model="queryParams.classId"
          placeholder="请输入班级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宿舍号" prop="dormId">
        <el-input
          v-model="queryParams.dormId"
          placeholder="请输入宿舍号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="辅导员" prop="classTeacher">
        <el-input
          v-model="queryParams.classTeacher"
          placeholder="请输入辅导员"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系方式" prop="classTeacherContact">
        <el-input
          v-model="queryParams.classTeacherContact"
          placeholder="请输入辅导员联系方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="QQ群" prop="classQq">
        <el-input
          v-model="queryParams.classQq"
          placeholder="请输入QQ群"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['manage:enrollSearch:add']"-->
<!--        >新增-->
<!--        </el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['manage:enrollSearch:edit']"
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
          v-hasPermi="['manage:enrollSearch:remove']"
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
          v-hasPermi="['manage:enrollSearch:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="enrollSearchList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="考生" align="center" v-if="columns[0].visible" prop="stuEnrollName"/>
      <el-table-column label="招生年度" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible"
                       prop="planYear"
      />
      <el-table-column label="省份名称" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible"
                       prop="provinceName"
      />
      <el-table-column label="录取院校" :show-overflow-tooltip="true" align="center" v-if="columns[3].visible"
                       prop="stuDeptName"
      />
      <el-table-column label="录取专业" :show-overflow-tooltip="true" align="center" v-if="columns[4].visible"
                       prop="stuMajor"
      />
      <el-table-column label="学生姓名" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible"
                       prop="stuName"
      />
      <el-table-column label="是否录取" align="center" v-if="columns[6].visible" prop="isEnroll">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.is_enroll" :value="scope.row.isEnroll"/>
        </template>
      </el-table-column>
      <el-table-column label="学号" :show-overflow-tooltip="true" align="center" v-if="columns[7].visible"
                       prop="stuNo"
      />
      <el-table-column label="班级" :show-overflow-tooltip="true" align="center" v-if="columns[8].visible"
                       prop="classId"
      />
      <el-table-column label="宿舍号" :show-overflow-tooltip="true" align="center" v-if="columns[9].visible"
                       prop="dormId"
      />
      <el-table-column label="辅导员" :show-overflow-tooltip="true" align="center" v-if="columns[10].visible"
                       prop="classTeacher"
      />
      <el-table-column label="辅导员联系方式" :show-overflow-tooltip="true" align="center" v-if="columns[11].visible"
                       prop="classTeacherContact"
      />
      <el-table-column label="QQ群" :show-overflow-tooltip="true" align="center" v-if="columns[12].visible"
                       prop="classQq"
      />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:enrollSearch:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:enrollSearch:remove']"
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

    <!-- 添加或修改考生查询对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
<!--        <el-form-item v-if="form.stuEnrollId ===null" label="考生" prop="stuEnrollId">-->
<!--          <el-select-->
<!--            v-model="form.stuEnrollId"-->
<!--            filterable-->
<!--            remote-->
<!--            reserve-keyword-->
<!--            placeholder="请输入考生姓名"-->
<!--            :remote-method="selectEnrollInfoList"-->
<!--            :loading="enrollLoading"-->
<!--          >-->
<!--            <el-option-->
<!--              v-for="item in enrollInfoList"-->
<!--              :key="item.stuEnrollId"-->
<!--              :label="`${item.stuName}&#45;&#45;${item.examNum}`"-->
<!--              :value="item.stuEnrollId"-->
<!--            >-->
<!--            </el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
        <el-form-item label="学号" prop="stuNo">
          <el-input v-model="form.stuNo" placeholder="请输入学号"/>
        </el-form-item>
        <el-form-item label="班级" prop="classId">
          <el-input v-model="form.classId" placeholder="请输入班级"/>
        </el-form-item>
        <el-form-item label="宿舍号" prop="dormId">
          <el-input v-model="form.dormId" placeholder="请输入宿舍号"/>
        </el-form-item>
        <el-form-item label="辅导员" prop="classTeacher">
          <el-input v-model="form.classTeacher" placeholder="请输入辅导员"/>
        </el-form-item>
        <el-form-item label="联系方式" prop="classTeacherContact">
          <el-input v-model="form.classTeacherContact" placeholder="请输入辅导员联系方式"/>
        </el-form-item>
        <el-form-item label="QQ群" prop="classQq">
          <el-input v-model="form.classQq" placeholder="请输入QQ群"/>
        </el-form-item>
        <el-form-item label="其他说明" prop="searchRemarks">
          <el-input v-model="form.searchRemarks" type="textarea" placeholder="请输入内容"/>
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
  listEnrollSearch,
  getEnrollSearch,
  delEnrollSearch,
  addEnrollSearch,
  updateEnrollSearch
} from '@/api/manage/enrollSearch'
import { listEnrollBasic } from '@/api/manage/enrollBasic'

export default {
  name: 'EnrollSearch',
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
        { key: 0, label: '考生', visible: true },
        { key: 1, label: '招生年度', visible: true },
        { key: 2, label: '省份名称', visible: true },
        { key: 3, label: '录取院校', visible: true },
        { key: 4, label: '录取专业', visible: true },
        { key: 5, label: '学生姓名', visible: true },
        { key: 6, label: '是否录取', visible: true },
        { key: 7, label: '学号', visible: true },
        { key: 8, label: '班级', visible: true },
        { key: 9, label: '宿舍号', visible: true },
        { key: 10, label: '辅导员', visible: true },
        { key: 11, label: '辅导员联系方式', visible: true },
        { key: 12, label: 'QQ群', visible: true }
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
      // 考生查询表格数据
      enrollSearchList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
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
        stuNo: null,
        isEnroll: null,
        classId: null,
        dormId: null,
        classTeacher: null,
        classTeacherContact: null,
        classQq: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        classTeacherContact:[
          { required: false, message: '辅导员联系方式不能为空', trigger: 'blur' },
          {
            pattern: /^1[3|4|5|7|8][0-9]\d{8}$/,
            message: '请输入正确的手机号码',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  created() {
    this.getEnrollInfoList()
    this.getList()
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
    /** 查询考生查询列表 */
    getList() {
      this.loading = true
      listEnrollSearch(this.queryParams).then(response => {
        this.enrollSearchList = response.rows
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
        stuNo: null,
        classId: null,
        dormId: null,
        classTeacher: null,
        classTeacherContact: null,
        classQq: null,
        searchRemarks: null
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
      this.title = '添加考生查询'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const stuEnrollId = row.stuEnrollId || this.ids
      getEnrollSearch(stuEnrollId).then(response => {
        this.form = response.data
        if (!this.form) {
          this.reset()
          this.form.stuEnrollId = stuEnrollId
        }
        this.open = true
        this.title = '修改考生查询'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.stuEnrollId != null) {
            updateEnrollSearch(this.form).then(response => {
              this.$modal.msgSuccess('操作成功')
              this.open = false
              this.getList()
            })
          } else {
            addEnrollSearch(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除考生查询编号为"' + stuEnrollIds + '"的数据项？').then(function() {
        return delEnrollSearch(stuEnrollIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/enrollSearch/export', {
        ...this.queryParams
      }, `enrollSearch_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
