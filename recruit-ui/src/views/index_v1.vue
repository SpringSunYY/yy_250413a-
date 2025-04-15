<template>
  <div class="dashboard-editor-container">

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <my-line-chart :chart-data="lineDate" name="每日联系"/>
    </el-row>

    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart chart-name="录取省份统计" :chart-data="provinceData.dates"/>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart :chart-data="schoolData.dates" chart-name="录取院校统计"/>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart :chart-data="majorData.dates" chart-name="录取专业统计"/>
        </div>
      </el-col>
    </el-row>


  </div>
</template>

<script>
import PanelGroup from './dashboard/PanelGroup'
import LineChart from './dashboard/LineChart'
import RaddarChart from './dashboard/RaddarChart'
import PieChart from './dashboard/PieChart'
import BarChart from './dashboard/BarChart'
import MyLineChart from '@/views/dashboard/MyLineChart.vue'
import { getStaticsLine, getStaticsPie } from '@/api/manage/statics'

export default {
  name: 'Index',
  components: {
    MyLineChart,
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart
  },
  data() {
    return {
      provinceData: {},
      schoolData: {},
      majorData: {},
      lineDate: {}
    }
  },
  created() {
    this.getProvinceData()
    this.getSchoolData()
    this.getMajorData()
    this.getLineData()
  },
  methods: {
    getProvinceData() {
      getStaticsPie({ staticFiled: 'province_name' }).then(res => {
        this.provinceData = res.data
      })
    },
    getSchoolData() {
      getStaticsPie({ staticFiled: 'stu_dept_name' }).then(res => {
        this.schoolData = res.data
      })
    },
    getMajorData() {
      getStaticsPie({ staticFiled: 'stu_major' }).then(res => {
        this.majorData = res.data
      })
    },
    getLineData() {
      getStaticsLine({}).then(res => {
        this.lineDate = res.data
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
