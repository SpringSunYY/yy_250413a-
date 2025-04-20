<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="考生" prop="stuEnrollId">
        <el-input
          v-model="queryParams.stuEnrollId"
          placeholder="请输入考生"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="招生年度" prop="enrollYear">
        <el-input
          v-model="queryParams.enrollYear"
          placeholder="请输入招生年度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考生号" prop="examNum">
        <el-input
          v-model="queryParams.examNum"
          placeholder="请输入考生号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--      <el-form-item label="准考证号" prop="examTicketNum">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.examTicketNum"-->
      <!--          placeholder="请输入准考证号"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item label="姓名" prop="stuName">
        <el-input
          v-model="queryParams.stuName"
          placeholder="请输入姓名"
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
      <el-form-item label="身份证号" prop="idCard">
        <el-input
          v-model="queryParams.idCard"
          placeholder="请输入身份证号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--      <el-form-item label="民族" prop="nationId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.nationId"-->
      <!--          placeholder="请输入民族"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="政治面貌" prop="politicsId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.politicsId"-->
      <!--          placeholder="请输入政治面貌"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="考生类别" prop="stuExamSort">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.stuExamSort"-->
      <!--          placeholder="请输入考生类别"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="家庭电话" prop="homePhone">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.homePhone"-->
      <!--          placeholder="请输入家庭电话"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="外语语种" prop="foreignLanguage">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.foreignLanguage"-->
      <!--          placeholder="请输入外语语种"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="录取专业名称" prop="enrollSpName">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.enrollSpName"-->
      <!--          placeholder="请输入录取专业名称"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="录取学院名称" prop="enrollDeptName">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.enrollDeptName"-->
      <!--          placeholder="请输入录取学院名称"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="投档志愿" prop="enrollNum">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.enrollNum"-->
      <!--          placeholder="请输入投档志愿"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="服从调剂" prop="enrollAdjust">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.enrollAdjust"-->
      <!--          placeholder="请输入服从调剂"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="录取批次" prop="enrollBatch">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.enrollBatch"-->
      <!--          placeholder="请输入录取批次"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item label="档案状态" prop="docStatus">
        <el-select v-model="queryParams.docStatus" placeholder="请选择档案状态" clearable>
          <el-option
            v-for="dict in dict.type.is_enroll"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <!--      <el-form-item label="学历等级" prop="eduLevel">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.eduLevel"-->
      <!--          placeholder="请输入学历等级"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="导入数据标记" prop="dataBatchFlag">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.dataBatchFlag"-->
      <!--          placeholder="请输入导入数据标记"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
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
          v-hasPermi="['manage:enrollBasic:add']"
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
          v-hasPermi="['manage:enrollBasic:edit']"
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
          v-hasPermi="['manage:enrollBasic:remove']"
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
          v-hasPermi="['manage:enrollBasic:export']"
        >导出
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['manage:enrollBasic:import']"
        >导入
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="enrollBasicList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="考生" align="center" v-if="columns[0].visible" prop="stuEnrollId"/>
      <el-table-column label="招生年度" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible"
                       prop="enrollYear"
      />
      <el-table-column label="考生号" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible"
                       prop="examNum"
      />
      <el-table-column label="准考证号" :show-overflow-tooltip="true" align="center" v-if="columns[3].visible"
                       prop="examTicketNum"
      />
      <el-table-column label="姓名" :show-overflow-tooltip="true" align="center" v-if="columns[4].visible"
                       prop="stuName"
      />
      <el-table-column label="科类" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible"
                       prop="subjectSort"
      />
      <el-table-column label="科类名称" :show-overflow-tooltip="true" align="center" v-if="columns[6].visible"
                       prop="subjectSortName"
      />
      <el-table-column label="省份编码" :show-overflow-tooltip="true" align="center" v-if="columns[7].visible"
                       prop="provinceCode"
      />
      <el-table-column label="省份名称" :show-overflow-tooltip="true" align="center" v-if="columns[8].visible"
                       prop="provinceName"
      />
      <el-table-column label="地区代码" :show-overflow-tooltip="true" align="center" v-if="columns[9].visible"
                       prop="areaCode"
      />
      <el-table-column label="地区名称" :show-overflow-tooltip="true" align="center" v-if="columns[10].visible"
                       prop="areaName"
      />
      <el-table-column label="身份证号" :show-overflow-tooltip="true" align="center" v-if="columns[11].visible"
                       prop="idCard"
      />
      <el-table-column label="性别" :show-overflow-tooltip="true" align="center" v-if="columns[12].visible"
                       prop="gender"
      />
      <el-table-column label="出生年月" :show-overflow-tooltip="true" align="center" v-if="columns[13].visible"
                       prop="birthDate"
      />
      <el-table-column label="民族" :show-overflow-tooltip="true" align="center" v-if="columns[14].visible"
                       prop="nationId"
      />
      <el-table-column label="政治面貌" :show-overflow-tooltip="true" align="center" v-if="columns[15].visible"
                       prop="politicsId"
      />
      <el-table-column label="考生类别" :show-overflow-tooltip="true" align="center" v-if="columns[16].visible"
                       prop="stuExamSort"
      />
      <el-table-column label="中学名称" :show-overflow-tooltip="true" align="center" v-if="columns[17].visible"
                       prop="middleSchool"
      />
      <el-table-column label="家庭电话" :show-overflow-tooltip="true" align="center" v-if="columns[18].visible"
                       prop="homePhone"
      />
      <el-table-column label="家庭备用电话" :show-overflow-tooltip="true" align="center" v-if="columns[19].visible"
                       prop="homePhoneRes"
      />
      <el-table-column label="家庭联系人" :show-overflow-tooltip="true" align="center" v-if="columns[20].visible"
                       prop="homeLink"
      />
      <el-table-column label="家庭行政区划" :show-overflow-tooltip="true" align="center" v-if="columns[21].visible"
                       prop="homeAreaCode"
      />
      <el-table-column label="家庭地址" :show-overflow-tooltip="true" align="center" v-if="columns[22].visible"
                       prop="homeAddr"
      />
      <el-table-column label="家庭邮编" :show-overflow-tooltip="true" align="center" v-if="columns[23].visible"
                       prop="homeZip"
      />
      <el-table-column label="考生特征" :show-overflow-tooltip="true" align="center" v-if="columns[24].visible"
                       prop="stuFeature"
      />
      <el-table-column label="获奖情况" :show-overflow-tooltip="true" align="center" v-if="columns[25].visible"
                       prop="stuHonor"
      />
      <el-table-column label="外语语种" :show-overflow-tooltip="true" align="center" v-if="columns[26].visible"
                       prop="foreignLanguage"
      />
      <el-table-column label="语文成绩" :show-overflow-tooltip="true" align="center" v-if="columns[27].visible"
                       prop="coreChinese"
      />
      <el-table-column label="数学成绩" :show-overflow-tooltip="true" align="center" v-if="columns[28].visible"
                       prop="coreMath"
      />
      <el-table-column label="外语成绩" :show-overflow-tooltip="true" align="center" v-if="columns[29].visible"
                       prop="coreForeign"
      />
      <el-table-column label="综合成绩" :show-overflow-tooltip="true" align="center" v-if="columns[30].visible"
                       prop="coreSyn"
      />
      <el-table-column label="总成绩" :show-overflow-tooltip="true" align="center" v-if="columns[31].visible"
                       prop="coreSum"
      />
      <el-table-column label="加分" :show-overflow-tooltip="true" align="center" v-if="columns[32].visible"
                       prop="coreAward"
      />
      <el-table-column label="专业成绩" :show-overflow-tooltip="true" align="center" v-if="columns[33].visible"
                       prop="coreSpecialty"
      />
      <el-table-column label="投档成绩" :show-overflow-tooltip="true" align="center" v-if="columns[34].visible"
                       prop="coreArchive"
      />
      <el-table-column label="位次" :show-overflow-tooltip="true" align="center" v-if="columns[35].visible"
                       prop="coreSortNum"
      />
      <el-table-column label="录取专业ID" :show-overflow-tooltip="true" align="center" v-if="columns[36].visible"
                       prop="enrollSpId"
      />
      <el-table-column label="录取专业名称" :show-overflow-tooltip="true" align="center" v-if="columns[37].visible"
                       prop="enrollSpName"
      />
      <el-table-column label="录取学院ID" :show-overflow-tooltip="true" align="center" v-if="columns[38].visible"
                       prop="enrollDeptId"
      />
      <el-table-column label="录取学院名称" :show-overflow-tooltip="true" align="center" v-if="columns[39].visible"
                       prop="enrollDeptName"
      />
      <el-table-column label="投档志愿" :show-overflow-tooltip="true" align="center" v-if="columns[40].visible"
                       prop="enrollNum"
      />
      <el-table-column label="服从调剂" :show-overflow-tooltip="true" align="center" v-if="columns[41].visible"
                       prop="enrollAdjust"
      />
      <el-table-column label="录取批次" :show-overflow-tooltip="true" align="center" v-if="columns[42].visible"
                       prop="enrollBatch"
      />
      <el-table-column label="档案状态" align="center" v-if="columns[43].visible" prop="docStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.is_enroll" :value="scope.row.docStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="学历等级" :show-overflow-tooltip="true" align="center" v-if="columns[44].visible"
                       prop="eduLevel"
      />
      <el-table-column label="第一志愿编码" :show-overflow-tooltip="true" align="center" v-if="columns[45].visible"
                       prop="applySp1Id"
      />
      <el-table-column label="第一志愿名称" :show-overflow-tooltip="true" align="center" v-if="columns[46].visible"
                       prop="applySp1Name"
      />
      <el-table-column label="第二志愿编码" :show-overflow-tooltip="true" align="center" v-if="columns[47].visible"
                       prop="applySp2Id"
      />
      <el-table-column label="第二志愿名称" :show-overflow-tooltip="true" align="center" v-if="columns[48].visible"
                       prop="applySp2Name"
      />
      <el-table-column label="第三志愿编码" :show-overflow-tooltip="true" align="center" v-if="columns[49].visible"
                       prop="applySp3Id"
      />
      <el-table-column label="第三志愿名称" :show-overflow-tooltip="true" align="center" v-if="columns[50].visible"
                       prop="applySp3Name"
      />
      <el-table-column label="第四志愿编码" :show-overflow-tooltip="true" align="center" v-if="columns[51].visible"
                       prop="applySp4Id"
      />
      <el-table-column label="第四志愿名称" :show-overflow-tooltip="true" align="center" v-if="columns[52].visible"
                       prop="applySp4Name"
      />
      <el-table-column label="第五志愿编码" :show-overflow-tooltip="true" align="center" v-if="columns[53].visible"
                       prop="applySp5Id"
      />
      <el-table-column label="第五志愿名称" :show-overflow-tooltip="true" align="center" v-if="columns[54].visible"
                       prop="applySp5Name"
      />
      <el-table-column label="第六志愿编码" :show-overflow-tooltip="true" align="center" v-if="columns[55].visible"
                       prop="applySp6Id"
      />
      <el-table-column label="第六志愿名称" :show-overflow-tooltip="true" align="center" v-if="columns[56].visible"
                       prop="applySp6Name"
      />
      <el-table-column label="录取备注" :show-overflow-tooltip="true" align="center" v-if="columns[57].visible"
                       prop="enrollRemarks"
      />
      <el-table-column label="导入数据标记" :show-overflow-tooltip="true" align="center" v-if="columns[58].visible"
                       prop="dataBatchFlag"
      />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:enrollBasic:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:enrollBasic:remove']"
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

    <!-- 添加或修改录取信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="招生年度" prop="enrollYear">
          <el-input v-model="form.enrollYear" placeholder="请输入招生年度"/>
        </el-form-item>
        <el-form-item label="考生号" prop="examNum">
          <el-input v-model="form.examNum" placeholder="请输入考生号"/>
        </el-form-item>
        <el-form-item label="准考证号" prop="examTicketNum">
          <el-input v-model="form.examTicketNum" placeholder="请输入准考证号"/>
        </el-form-item>
        <el-form-item label="姓名" prop="stuName">
          <el-input v-model="form.stuName" placeholder="请输入姓名"/>
        </el-form-item>
        <el-form-item label="录取专业" prop="enrollSpId">
          <el-select
            style="width: 100%"
            v-model="form.enrollSpId"
            filterable
            remote
            :remote-method="selectPlanInfoList"
            reserve-keyword
            placeholder="请选择专业"
            :loading="planLoading"
          >
            <el-option
              v-for="item in planInfoList"
              :key="item.planId"
              :label="`${item.spName}-${item.stuDeptName}-${item.provinceName}`"
              :value="item.planId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="地区代码" prop="areaCode">
          <el-input v-model="form.areaCode" placeholder="请输入地区代码"/>
        </el-form-item>
        <el-form-item label="地区名称" prop="areaName">
          <el-input v-model="form.areaName" placeholder="请输入地区名称"/>
        </el-form-item>
        <el-form-item label="身份证号" prop="idCard">
          <el-input v-model="form.idCard" placeholder="请输入身份证号"/>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-input v-model="form.gender" placeholder="请输入性别"/>
        </el-form-item>
        <el-form-item label="出生年月" prop="birthDate">
          <el-input v-model="form.birthDate" placeholder="请输入出生年月"/>
        </el-form-item>
        <el-form-item label="民族" prop="nationId">
          <el-input v-model="form.nationId" placeholder="请输入民族"/>
        </el-form-item>
        <el-form-item label="政治面貌" prop="politicsId">
          <el-input v-model="form.politicsId" placeholder="请输入政治面貌"/>
        </el-form-item>
        <el-form-item label="考生类别" prop="stuExamSort">
          <el-input v-model="form.stuExamSort" placeholder="请输入考生类别"/>
        </el-form-item>
        <el-form-item label="中学名称" prop="middleSchool">
          <el-input v-model="form.middleSchool" placeholder="请输入中学名称"/>
        </el-form-item>
        <el-form-item label="家庭电话" prop="homePhone">
          <el-input v-model="form.homePhone" placeholder="请输入家庭电话"/>
        </el-form-item>
        <el-form-item label="家庭备用电话" prop="homePhoneRes">
          <el-input v-model="form.homePhoneRes" placeholder="请输入家庭备用电话"/>
        </el-form-item>
        <el-form-item label="家庭联系人" prop="homeLink">
          <el-input v-model="form.homeLink" placeholder="请输入家庭联系人"/>
        </el-form-item>
        <el-form-item label="家庭行政区划" prop="homeAreaCode">
          <el-input v-model="form.homeAreaCode" placeholder="请输入家庭行政区划"/>
        </el-form-item>
        <el-form-item label="家庭地址" prop="homeAddr">
          <el-input v-model="form.homeAddr" placeholder="请输入家庭地址"/>
        </el-form-item>
        <el-form-item label="家庭邮编" prop="homeZip">
          <el-input v-model="form.homeZip" placeholder="请输入家庭邮编"/>
        </el-form-item>
        <el-form-item label="考生特征" prop="stuFeature">
          <el-input v-model="form.stuFeature" placeholder="请输入考生特征"/>
        </el-form-item>
        <el-form-item label="获奖情况" prop="stuHonor">
          <el-input v-model="form.stuHonor" placeholder="请输入获奖情况"/>
        </el-form-item>
        <el-form-item label="外语语种" prop="foreignLanguage">
          <el-input v-model="form.foreignLanguage" placeholder="请输入外语语种"/>
        </el-form-item>
        <el-form-item label="语文成绩" prop="coreChinese">
          <el-input-number :min="0" :max="150" :precision="2" v-model="form.coreChinese" placeholder="请输入语文成绩"/>
        </el-form-item>
        <el-form-item label="数学成绩" prop="coreMath">
          <el-input-number :min="0" :max="150" :precision="2" v-model="form.coreMath" placeholder="请输入数学成绩"/>
        </el-form-item>
        <el-form-item label="外语成绩" prop="coreForeign">
          <el-input-number :min="0" :max="150" :precision="2" v-model="form.coreForeign" placeholder="请输入外语成绩"/>
        </el-form-item>
        <el-form-item label="综合成绩" prop="coreSyn">
          <el-input-number :min="0" :precision="2" v-model="form.coreSyn" placeholder="请输入综合成绩"/>
        </el-form-item>
        <el-form-item label="总成绩" prop="coreSum">
          <el-input-number :min="0" :precision="2" v-model="form.coreSum" placeholder="请输入总成绩"/>
        </el-form-item>
        <el-form-item label="加分" prop="coreAward">
          <el-input-number :min="0" :precision="2" v-model="form.coreAward" placeholder="请输入加分"/>
        </el-form-item>
        <el-form-item label="专业成绩" prop="coreSpecialty">
          <el-input-number :min="0" :precision="2" v-model="form.coreSpecialty" placeholder="请输入专业成绩"/>
        </el-form-item>
        <el-form-item label="投档成绩" prop="coreArchive">
          <el-input-number :min="0" :precision="2" v-model="form.coreArchive" placeholder="请输入投档成绩"/>
        </el-form-item>
        <el-form-item label="位次" prop="coreSortNum">
          <el-input v-model="form.coreSortNum" placeholder="请输入位次"/>
        </el-form-item>
        <el-form-item label="投档志愿" prop="enrollNum">
          <el-input v-model="form.enrollNum" placeholder="请输入投档志愿"/>
        </el-form-item>
        <el-form-item label="服从调剂" prop="enrollAdjust">
          <el-input v-model="form.enrollAdjust" placeholder="请输入服从调剂"/>
        </el-form-item>
        <el-form-item label="录取批次" prop="enrollBatch">
          <el-input v-model="form.enrollBatch" placeholder="请输入录取批次"/>
        </el-form-item>
        <el-form-item label="档案状态" prop="docStatus">
          <el-radio-group v-model="form.docStatus">
            <el-radio
              v-for="dict in dict.type.is_enroll"
              :key="dict.value"
              :label="dict.value"
            >{{ dict.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="学历等级" prop="eduLevel">
          <el-input v-model="form.eduLevel" placeholder="请输入学历等级"/>
        </el-form-item>
        <el-form-item label="第一志愿编码" prop="applySp1Id">
          <el-select
            style="width: 100%"
            v-model="form.applySp1Id"
            filterable
            remote
            :remote-method="selectPlanInfoList"
            reserve-keyword
            placeholder="请选择专业"
            :loading="planLoading"
          >
            <el-option
              v-for="item in planInfoList"
              :key="item.planId"
              :label="`${item.spName}-${item.stuDeptName}-${item.provinceName}`"
              :value="item.planId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="第二志愿编码" prop="applySp2Id">
          <el-select
            style="width: 100%"
            v-model="form.applySp2Id"
            filterable
            remote
            :remote-method="selectPlanInfoList"
            reserve-keyword
            placeholder="请选择专业"
            :loading="planLoading"
          >
            <el-option
              v-for="item in planInfoList"
              :key="item.planId"
              :label="`${item.spName}-${item.stuDeptName}-${item.provinceName}`"
              :value="item.planId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="第三志愿编码" prop="applySp3Id">
          <el-select
            style="width: 100%"
            v-model="form.applySp3Id"
            filterable
            remote
            :remote-method="selectPlanInfoList"
            reserve-keyword
            placeholder="请选择专业"
            :loading="planLoading"
          >
            <el-option
              v-for="item in planInfoList"
              :key="item.planId"
              :label="`${item.spName}-${item.stuDeptName}-${item.provinceName}`"
              :value="item.planId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="第四志愿编码" prop="applySp4Id">
          <el-select
            style="width: 100%"
            v-model="form.applySp4Id"
            filterable
            remote
            :remote-method="selectPlanInfoList"
            reserve-keyword
            placeholder="请选择专业"
            :loading="planLoading"
          >
            <el-option
              v-for="item in planInfoList"
              :key="item.planId"
              :label="`${item.spName}-${item.stuDeptName}-${item.provinceName}`"
              :value="item.planId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="第五志愿编码" prop="applySp5Id">
          <el-select
            style="width: 100%"
            v-model="form.applySp5Id"
            filterable
            remote
            :remote-method="selectPlanInfoList"
            reserve-keyword
            placeholder="请选择专业"
            :loading="planLoading"
          >
            <el-option
              v-for="item in planInfoList"
              :key="item.planId"
              :label="`${item.spName}-${item.stuDeptName}-${item.provinceName}`"
              :value="item.planId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="第六志愿编码" prop="applySp6Id">
          <el-select
            style="width: 100%"
            v-model="form.applySp6Id"
            filterable
            remote
            :remote-method="selectPlanInfoList"
            reserve-keyword
            placeholder="请选择专业"
            :loading="planLoading"
          >
            <el-option
              v-for="item in planInfoList"
              :key="item.planId"
              :label="`${item.spName}-${item.stuDeptName}-${item.provinceName}`"
              :value="item.planId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="录取备注" prop="enrollRemarks">
          <el-input v-model="form.enrollRemarks" placeholder="请输入录取备注"/>
        </el-form-item>
        <el-form-item label="导入数据标记" prop="dataBatchFlag">
          <el-input v-model="form.dataBatchFlag" placeholder="请输入导入数据标记"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px">
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listEnrollBasic,
  getEnrollBasic,
  delEnrollBasic,
  addEnrollBasic,
  updateEnrollBasic
} from '@/api/manage/enrollBasic'
import { getToken } from '@/utils/auth'
import { listEnrollPlan } from '@/api/manage/enrollPlan'

export default {
  name: 'EnrollBasic',
  dicts: ['is_enroll'],
  data() {
    return {
      //招生计划相关信息
      planInfoList: [],
      planLoading: false,
      planQueryParams: {
        spName: '',
        pageNum: 1,
        pageSize: 100
      },
      //表格展示列
      columns: [
        { key: 0, label: '考生', visible: false },
        { key: 1, label: '招生年度', visible: true },
        { key: 2, label: '考生号', visible: true },
        { key: 3, label: '准考证号', visible: false },
        { key: 4, label: '姓名', visible: true },
        { key: 5, label: '科类', visible: false },
        { key: 6, label: '科类名称', visible: true },
        { key: 7, label: '省份编码', visible: false },
        { key: 8, label: '省份名称', visible: true },
        { key: 9, label: '地区代码', visible: false },
        { key: 10, label: '地区名称', visible: false },
        { key: 11, label: '身份证号', visible: true },
        { key: 12, label: '性别', visible: false },
        { key: 13, label: '出生年月', visible: false },
        { key: 14, label: '民族', visible: false },
        { key: 15, label: '政治面貌', visible: false },
        { key: 16, label: '考生类别', visible: false },
        { key: 17, label: '中学名称', visible: false },
        { key: 18, label: '家庭电话', visible: false },
        { key: 19, label: '家庭备用电话', visible: false },
        { key: 20, label: '家庭联系人', visible: false },
        { key: 21, label: '家庭行政区划', visible: false },
        { key: 22, label: '家庭地址', visible: false },
        { key: 23, label: '家庭邮编', visible: false },
        { key: 24, label: '考生特征', visible: false },
        { key: 25, label: '获奖情况', visible: false },
        { key: 26, label: '外语语种', visible: false },
        { key: 27, label: '语文成绩', visible: false },
        { key: 28, label: '数学成绩', visible: false },
        { key: 29, label: '外语成绩', visible: false },
        { key: 30, label: '综合成绩', visible: false },
        { key: 31, label: '总成绩', visible: true },
        { key: 32, label: '加分', visible: false },
        { key: 33, label: '专业成绩', visible: false },
        { key: 34, label: '投档成绩', visible: false },
        { key: 35, label: '位次', visible: false },
        { key: 36, label: '录取专业ID', visible: false },
        { key: 37, label: '录取专业名称', visible: true },
        { key: 38, label: '录取学院ID', visible: false },
        { key: 39, label: '录取学院名称', visible: true },
        { key: 40, label: '投档志愿', visible: false },
        { key: 41, label: '服从调剂', visible: false },
        { key: 42, label: '录取批次', visible: false },
        { key: 43, label: '档案状态', visible: true },
        { key: 44, label: '学历等级', visible: false },
        { key: 45, label: '第一志愿编码', visible: false },
        { key: 46, label: '第一志愿名称', visible: false },
        { key: 47, label: '第二志愿编码', visible: false },
        { key: 48, label: '第二志愿名称', visible: false },
        { key: 49, label: '第三志愿编码', visible: false },
        { key: 50, label: '第三志愿名称', visible: false },
        { key: 51, label: '第四志愿编码', visible: false },
        { key: 52, label: '第四志愿名称', visible: false },
        { key: 53, label: '第五志愿编码', visible: false },
        { key: 54, label: '第五志愿名称', visible: false },
        { key: 55, label: '第六志愿编码', visible: false },
        { key: 56, label: '第六志愿名称', visible: false },
        { key: 57, label: '录取备注', visible: false },
        { key: 58, label: '导入数据标记', visible: false }
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
      // 录取信息表格数据
      enrollBasicList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        stuEnrollId: null,
        enrollYear: null,
        examNum: null,
        examTicketNum: null,
        stuName: null,
        provinceName: null,
        idCard: null,
        nationId: null,
        politicsId: null,
        stuExamSort: null,
        homePhone: null,
        foreignLanguage: null,
        enrollSpName: null,
        enrollDeptName: null,
        enrollNum: null,
        enrollAdjust: null,
        enrollBatch: null,
        docStatus: null,
        eduLevel: null,
        dataBatchFlag: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        stuEnrollId: [
          { required: true, message: '考生不能为空', trigger: 'blur' }
        ],
        enrollYear: [
          { required: true, message: '招生年度不能为空', trigger: 'blur' }
        ],
        examNum: [
          { required: true, message: '考生号不能为空', trigger: 'blur' }
        ],
        examTicketNum: [
          { required: true, message: '准考证号不能为空', trigger: 'blur' }
        ],
        stuName: [
          { required: true, message: '姓名不能为空', trigger: 'blur' }
        ],
        subjectSort: [
          { required: true, message: '科类不能为空', trigger: 'blur' }
        ],
        provinceCode: [
          { required: true, message: '省份名称不能为空', trigger: 'blur' }
        ],
        idCard: [
          { required: true, message: '身份证号不能为空', trigger: 'blur' },
          {
            pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
            message: '请输入正确的身份证号',
            trigger: 'blur'
          }
        ],
        homePhone: [
          { required: true, message: '家庭电话不能为空', trigger: 'blur' },
          {
            pattern: /^1[3|4|5|7|8][0-9]\d{8}$/,
            message: '请输入正确的家庭电话',
            trigger: 'blur'
          }
        ],
        enrollSpId: [
          { required: true, message: '录取专业不能为空', trigger: 'blur' }
        ],
        enrollDeptId: [
          { required: true, message: '录取学院不能为空', trigger: 'blur' }
        ],
        docStatus: [
          { required: true, message: '档案状态不能为空', trigger: 'blur' }
        ]
      },
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title:
          '',
        // 是否禁用上传
        isUploading:
          false,
        // 设置上传的请求头部
        headers:
          {
            Authorization: 'Bearer ' + getToken()
          }
        ,
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + '/manage/enrollBasic/importData'
      }
    }
  },
  created() {
    this.getList()
    this.getPlanInfoList()
  },
  methods: {
    /**
     * 获取考生列表推荐
     * @param query
     */
    selectPlanInfoList(query) {
      if (query !== '') {
        this.planLoading = true
        this.planQueryParams.spName = query
        setTimeout(() => {
          this.getPlanInfoList()
        }, 200)
      } else {
        this.planInfoList = []
        this.planQueryParams.spName = null
      }
    },
    /**
     * 获取考生信息列表
     */
    getPlanInfoList() {
      //添加查询参数
      this.planInfoList = []
      listEnrollPlan(this.planQueryParams).then(res => {
        this.planInfoList = res?.rows
        this.planLoading = false
      })
    },
    /** 查询录取信息列表 */
    getList() {
      this.loading = true
      listEnrollBasic(this.queryParams).then(response => {
        this.enrollBasicList = response.rows
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
        enrollYear: null,
        examNum: null,
        examTicketNum: null,
        stuName: null,
        subjectSort: null,
        subjectSortName: null,
        provinceCode: null,
        provinceName: null,
        areaCode: null,
        areaName: null,
        idCard: null,
        gender: null,
        birthDate: null,
        nationId: null,
        politicsId: null,
        stuExamSort: null,
        middleSchool: null,
        homePhone: null,
        homePhoneRes: null,
        homeLink: null,
        homeAreaCode: null,
        homeAddr: null,
        homeZip: null,
        stuFeature: null,
        stuHonor: null,
        foreignLanguage: null,
        coreChinese: null,
        coreMath: null,
        coreForeign: null,
        coreSyn: null,
        coreSum: null,
        coreAward: null,
        coreSpecialty: null,
        coreArchive: null,
        coreSortNum: null,
        enrollSpId: null,
        enrollSpName: null,
        enrollDeptId: null,
        enrollDeptName: null,
        enrollNum: null,
        enrollAdjust: null,
        enrollBatch: null,
        docStatus: null,
        eduLevel: null,
        applySp1Id: null,
        applySp1Name: null,
        applySp2Id: null,
        applySp2Name: null,
        applySp3Id: null,
        applySp3Name: null,
        applySp4Id: null,
        applySp4Name: null,
        applySp5Id: null,
        applySp5Name: null,
        applySp6Id: null,
        applySp6Name: null,
        enrollRemarks: null,
        dataBatchFlag: null
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
      this.title = '添加录取信息'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const stuEnrollId = row.stuEnrollId || this.ids
      getEnrollBasic(stuEnrollId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改录取信息'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.stuEnrollId != null) {
            updateEnrollBasic(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addEnrollBasic(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除录取信息编号为"' + stuEnrollIds + '"的数据项？').then(function() {
        return delEnrollBasic(stuEnrollIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/enrollBasic/export', {
        ...this.queryParams
      }, `enrollBasic_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = '录取信息导入'
      this.upload.open = true
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('manage/enrollBasic/importTemplate', {}, `enrollBasic_template_${new Date().getTime()}.xlsx`)
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false
      this.upload.isUploading = false
      this.$refs.upload.clearFiles()
      this.$alert(response.msg, '导入结果', { dangerouslyUseHTMLString: true })
      this.getList()
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit()
    }
  }
}
</script>
