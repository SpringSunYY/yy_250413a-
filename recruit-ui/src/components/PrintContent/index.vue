<template>
  <div id="printContent">
    <!-- 打印区域 -->
    <div id="printContent" class="notice-content">
      <!-- 校徽和标题 -->
      <div class="header">
        <h1><img class="logo" src="@/assets/school-logo.png"></h1>
        <div class="red-title">录取通知书</div>
      </div>

      <!-- 正文内容 -->
      <div class="content">
        <p class="greeting">{{ student.stuName }} 同学：</p>

        <div class="text-block">
          <span >经录取你到我校</span>
          <div class="highlight-box">{{ student.stuMajor }}</div>
          <span>专业学习。请凭本通知书于</span>
          <div class="highlight-box">{{ reportDate }}</div>
          <span>到我校贵阳信息科技学院报到。</span>
        </div>
        <!-- 校长签名 -->
        <div class="signature">
          <p>校长：</p>
        </div>
        <div class="data">
          <p>贵阳信息科技学院</p>
          <p class="date">{{ issueDate }}</p>
        </div>
        <!-- 建筑线条图 -->
        <img class="building" src="@/assets/building-line.png">
      </div>
    </div>
  </div>
</template>

<script>
import print from 'vue-print-nb'

export default {
  name: 'PrintContent',
  directives: { print },
  props: {
    student: {
      type: Object,
      default: () => ({
        name: '陈钢',
        major: '土木工程',
        noticeNumber: '4289-4782'
      })
    },
    issueDate: {
      type:String,
      default: '2023年8月22日'
    },
    reportDate: {
      type:String,
      default: '8月22日至23日'
    },
  },
  data() {
    return {
      printConfig: {
        id: 'printContent',
        popTitle: ' ',
        extraCss: `
          @media print {
            @page { margin: 0; }
            body { -webkit-print-color-adjust: exact; }
            button { display: none; }
          }
        `
      }
    }
  }
}
</script>

<style scoped>
.notice-content {
  width: 794px;
  height: 1123px;
  margin: 0 auto;
  padding: 60px;
  background: white;
  position: relative;
}

.header {
  text-align: center;
  padding-bottom: 20px;
  border-bottom: 2px solid #c00;
}

.logo {
  width: 200px;
  left: 60px;
  top: 60px;
}

.red-title {
  color: #c00;
  font-size: 36px;
  font-weight: bold;
  margin: 10px 0;
}

.content {
  font-size: 20px;
  line-height: 1.8;
  margin-top: 40px;
}

.greeting {
  font-size: 22px;
}

.text-block {
  text-indent: 2em;
  font-size: 16px;
}

.highlight-box {
  display: inline-block;
  padding: 8px 0 8px 0;
  font-weight: bold;
  color: #c00;
}

.signature {
  margin-top: 80px;
  font-size: 22px;
}

.data {
  float: right;
}

.building {
  width: 100%;
  margin-top: 50px;
  opacity: 0.9;
}

button {
  display: block;
  margin: 20px auto;
  padding: 12px 40px;
  background: #c00;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>
