<template>
  <div :class="className" :style="{height:height,width:width}"/>
</template>

<script>
import * as echarts from 'echarts'

require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'

const animationDuration = 6000

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    },
    chartData: {
      type: Object,
      required: true,
      default:{}
    },
    names: {
      require: true,
      type: Array,
      default: []
    }
  },
  data() {
    return {
      chart: null,
      series: []
    }
  },
  watch: {
    chartData: {
      deep: true,
      handler(val) {
        // console.log(val)
        console.log('val', val)
        this.initSeries(val)
        this.initChart()
      }
    }
  },
  mounted() {
    this.$nextTick(() => {
      // console.log('111')
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initSeries(data) {
      if (this.chartData == undefined) {
        return
      }
      this.chartData.totals.forEach((data, index) => {
        let seriesItem = {
          name: this.names[index],
          type: 'bar',
          stack: 'vistors',
          barWidth: '60%',
          data: data,
          animationDuration: 1000
        }
        this.series.push(seriesItem)
      })
    },
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')

      this.chart.setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          top: 10,
          left: '2%',
          right: '2%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [{
          type: 'category',
          data: this.chartData.names,
          axisTick: {
            alignWithLabel: true
          }
        }],
        yAxis: [{
          type: 'value',
          axisTick: {
            show: false
          }
        }],
        series: this.series
      })
    }
  }
}
</script>
