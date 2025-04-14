<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="计划ID" prop="planId">
        <el-input
          v-model="queryParams.planId"
          placeholder="请输入计划ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="计划年度" prop="planYear">
        <el-input
          v-model="queryParams.planYear"
          placeholder="请输入计划年度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专业代码" prop="spId">
        <el-input
          v-model="queryParams.spId"
          placeholder="请输入专业代码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专业名称" prop="spName">
        <el-input
          v-model="queryParams.spName"
          placeholder="请输入专业名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="系部名称" prop="stuDeptName">
        <el-input
          v-model="queryParams.stuDeptName"
          placeholder="请输入系部名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="科类名称" prop="subjectSortName">
        <el-input
          v-model="queryParams.subjectSortName"
          placeholder="请输入科类名称"
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
      <el-form-item label="招生人数" prop="planNum">
        <el-input
          v-model="queryParams.planNum"
          placeholder="请输入招生人数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学费" prop="tuitionFee">
        <el-input
          v-model="queryParams.tuitionFee"
          placeholder="请输入学费"
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
          v-hasPermi="['manage:enrollPlan:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['manage:enrollPlan:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['manage:enrollPlan:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['manage:enrollPlan:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="enrollPlanList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="计划ID" align="center" v-if="columns[0].visible" prop="planId" />
        <el-table-column label="计划年度" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible" prop="planYear" />
        <el-table-column label="专业代码" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible" prop="spId" />
        <el-table-column label="专业名称" :show-overflow-tooltip="true" align="center" v-if="columns[3].visible" prop="spName" />
        <el-table-column label="系部id" :show-overflow-tooltip="true" align="center" v-if="columns[4].visible" prop="stuDeptId" />
        <el-table-column label="系部名称" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible" prop="stuDeptName" />
        <el-table-column label="学制" :show-overflow-tooltip="true" align="center" v-if="columns[6].visible" prop="spLength" />
        <el-table-column label="科类ID" :show-overflow-tooltip="true" align="center" v-if="columns[7].visible" prop="subjectSortId" />
        <el-table-column label="科类名称" :show-overflow-tooltip="true" align="center" v-if="columns[8].visible" prop="subjectSortName" />
        <el-table-column label="省份编码" :show-overflow-tooltip="true" align="center" v-if="columns[9].visible" prop="provinceCode" />
        <el-table-column label="省份名称" :show-overflow-tooltip="true" align="center" v-if="columns[10].visible" prop="provinceName" />
        <el-table-column label="省份简称" :show-overflow-tooltip="true" align="center" v-if="columns[11].visible" prop="provinceNameShort" />
        <el-table-column label="招生人数" :show-overflow-tooltip="true" align="center" v-if="columns[12].visible" prop="planNum" />
        <el-table-column label="学费" :show-overflow-tooltip="true" align="center" v-if="columns[13].visible" prop="tuitionFee" />
        <el-table-column label="创建者" :show-overflow-tooltip="true" align="center" v-if="columns[14].visible" prop="createBy" />
        <el-table-column label="创建时间" align="center" v-if="columns[15].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
        <el-table-column label="更新者" :show-overflow-tooltip="true" align="center" v-if="columns[16].visible" prop="updateBy" />
        <el-table-column label="更新时间" align="center" v-if="columns[17].visible" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
        <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[18].visible" prop="remark" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:enrollPlan:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:enrollPlan:remove']"
          >删除</el-button>
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

    <!-- 添加或修改招生计划对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="计划年度" prop="planYear">
          <el-input v-model="form.planYear" placeholder="请输入计划年度" />
        </el-form-item>
        <el-form-item label="专业代码" prop="spId">
          <el-input v-model="form.spId" placeholder="请输入专业代码" />
        </el-form-item>
        <el-form-item label="专业名称" prop="spName">
          <el-input v-model="form.spName" placeholder="请输入专业名称" />
        </el-form-item>
        <el-form-item label="系部id" prop="stuDeptId">
          <el-input v-model="form.stuDeptId" placeholder="请输入系部id" />
        </el-form-item>
        <el-form-item label="系部名称" prop="stuDeptName">
          <el-input v-model="form.stuDeptName" placeholder="请输入系部名称" />
        </el-form-item>
        <el-form-item label="学制" prop="spLength">
          <el-input v-model="form.spLength" placeholder="请输入学制" />
        </el-form-item>
        <el-form-item label="科类ID" prop="subjectSortId">
          <el-input v-model="form.subjectSortId" placeholder="请输入科类ID" />
        </el-form-item>
        <el-form-item label="科类名称" prop="subjectSortName">
          <el-input v-model="form.subjectSortName" placeholder="请输入科类名称" />
        </el-form-item>
        <el-form-item label="省份编码" prop="provinceCode">
          <el-input v-model="form.provinceCode" placeholder="请输入省份编码" />
        </el-form-item>
        <el-form-item label="省份名称" prop="provinceName">
          <el-input v-model="form.provinceName" placeholder="请输入省份名称" />
        </el-form-item>
        <el-form-item label="省份简称" prop="provinceNameShort">
          <el-input v-model="form.provinceNameShort" placeholder="请输入省份简称" />
        </el-form-item>
        <el-form-item label="招生人数" prop="planNum">
          <el-input v-model="form.planNum" placeholder="请输入招生人数" />
        </el-form-item>
        <el-form-item label="学费" prop="tuitionFee">
          <el-input v-model="form.tuitionFee" placeholder="请输入学费" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listEnrollPlan, getEnrollPlan, delEnrollPlan, addEnrollPlan, updateEnrollPlan } from "@/api/manage/enrollPlan";

export default {
  name: "EnrollPlan",
  data() {
    return {
      //表格展示列
      columns: [
        { key: 0, label: '计划ID', visible: true },
          { key: 1, label: '计划年度', visible: true },
          { key: 2, label: '专业代码', visible: true },
          { key: 3, label: '专业名称', visible: true },
          { key: 4, label: '系部id', visible: true },
          { key: 5, label: '系部名称', visible: true },
          { key: 6, label: '学制', visible: true },
          { key: 7, label: '科类ID', visible: true },
          { key: 8, label: '科类名称', visible: true },
          { key: 9, label: '省份编码', visible: true },
          { key: 10, label: '省份名称', visible: true },
          { key: 11, label: '省份简称', visible: true },
          { key: 12, label: '招生人数', visible: true },
          { key: 13, label: '学费', visible: true },
          { key: 14, label: '创建者', visible: true },
          { key: 15, label: '创建时间', visible: true },
          { key: 16, label: '更新者', visible: true },
          { key: 17, label: '更新时间', visible: true },
          { key: 18, label: '备注', visible: true },
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
      // 招生计划表格数据
      enrollPlanList: [],
      // 弹出层标题
      title: "",
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
        planId: null,
        planYear: null,
        spId: null,
        spName: null,
        stuDeptName: null,
        subjectSortName: null,
        provinceName: null,
        planNum: null,
        tuitionFee: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        spId: [
          { required: true, message: "专业代码不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询招生计划列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params["beginUpdateTime"] = this.daterangeUpdateTime[0];
        this.queryParams.params["endUpdateTime"] = this.daterangeUpdateTime[1];
      }
      listEnrollPlan(this.queryParams).then(response => {
        this.enrollPlanList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        planId: null,
        planYear: null,
        spId: null,
        spName: null,
        stuDeptId: null,
        stuDeptName: null,
        spLength: null,
        subjectSortId: null,
        subjectSortName: null,
        provinceCode: null,
        provinceName: null,
        provinceNameShort: null,
        planNum: null,
        tuitionFee: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreateTime = [];
      this.daterangeUpdateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.planId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加招生计划";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const planId = row.planId || this.ids
      getEnrollPlan(planId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改招生计划";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.planId != null) {
            updateEnrollPlan(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEnrollPlan(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const planIds = row.planId || this.ids;
      this.$modal.confirm('是否确认删除招生计划编号为"' + planIds + '"的数据项？').then(function() {
        return delEnrollPlan(planIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/enrollPlan/export', {
        ...this.queryParams
      }, `enrollPlan_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
