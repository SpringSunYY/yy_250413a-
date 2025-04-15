<template>
  <div class="login">
    <el-form ref="loginForm" :model="form" :rules="rules" label-width="100px" class="login-form">
      <h3 class="title">高校招生管理系统</h3>
      <el-form-item label="姓名" prop="stuName">
        <el-input v-model="form.stuName" placeholder="请输入姓名"/>
      </el-form-item>
      <el-form-item label="考生号" prop="examNum">
        <el-input v-model="form.examNum" placeholder="请输入考生号"/>
      </el-form-item>
      <el-form-item label="身份证号" prop="idCard">
        <el-input v-model="form.idCard" placeholder="请输入身份证号"/>
      </el-form-item>
      <el-form-item prop="code" label="验证码" v-if="captchaEnabled">
        <el-input
          v-model="form.code"
          auto-complete="off"
          placeholder="验证码"
          style="width: 63%"
        >
          <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon"/>
        </el-input>
        <div class="login-code">
          <img :src="codeUrl" @click="getCode" class="login-code-img"/>
        </div>
      </el-form-item>
      <el-form-item style="width:100%;">
        <el-button
          :loading="loading"
          size="medium"
          type="primary"
          style="width:100%;"
          @click.native.prevent="handleQuery"
        >
          <span v-if="!loading">查 询</span>
          <span v-else>查询中...</span>
        </el-button>
      </el-form-item>
    </el-form>
    <!-- 添加或修改通知书信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="student" label-width="100px">
        <el-form-item label="考生号" prop="examNum">
          <el-input readonly v-model="student.examNum" placeholder="请输入考生号"/>
        </el-form-item>
        <el-form-item label="学生姓名" prop="stuName">
          <el-input readonly v-model="student.stuName" placeholder="请输入学生姓名"/>
        </el-form-item>
        <el-form-item label="录取院校" prop="stuDeptName">
          <el-input readonly v-model="student.stuDeptName" placeholder="请输入录取院校"/>
        </el-form-item>
        <el-form-item label="录取专业" prop="stuMajor">
          <el-input readonly v-model="student.stuMajor" placeholder="请输入录取专业"/>
        </el-form-item>
        <el-form-item label="是否录取" prop="isEnroll">
          {{ student.isEnroll === '1' ? '已录取' : '未录取' }}
        </el-form-item>
        <el-form-item label="通知书快递号" prop="noteExpCode">
          <el-input readonly v-model="student.noteExpCode" placeholder="请输入通知书快递号"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submit">确 定</el-button>
      </div>
    </el-dialog>
    <!--  底部  -->
    <div class="el-login-footer">
      <span>Copyright © 2018-2025 ruoyi.vip All Rights Reserved.</span>
    </div>
  </div>
</template>

<script>
import { getCodeImg } from '@/api/login'
import { queryEnrollNote } from '@/api/manage/enrollNote'

export default {
  name: 'Query',
  data() {
    return {
      student: {},
      open: false,
      title: '查询结果',
      codeUrl: '',
      form: {},
      rules: {
        stuName: [
          { required: true, trigger: 'blur', message: '请输入您的姓名' }
        ],
        examNum: [
          { required: true, trigger: 'blur', message: '请输入您的考生号' }
        ],
        idCard: [
          { required: true, trigger: 'blur', message: '请输入您的身份证号' }
        ],
        code: [{ required: true, trigger: 'change', message: '请输入验证码' }]
      },
      loading: false,
      // 验证码开关
      captchaEnabled: true,
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  created() {
    this.getCode()
  },
  methods: {
    getCode() {
      getCodeImg().then(res => {
        this.captchaEnabled = res.captchaEnabled === undefined ? true : res.captchaEnabled
        if (this.captchaEnabled) {
          this.codeUrl = 'data:image/gif;base64,' + res.img
          this.form.uuid = res.uuid
        }
      })
    },
    handleQuery() {
      console.log(this.form)
      if (this.captchaEnabled) {
        if (this.form.code === '') {
          this.$message({
            message: '请输入验证码',
            type: 'warning'
          })
          return
        }
      }
      this.student = {}
      queryEnrollNote(this.form).then(res => {
        console.log(res.data)
        this.student = res.data
        this.student.examNum = this.form.examNum
        this.open = true
      }).catch(err => {
        console.log(err)
        this.getCode()
      })
    },
    submit() {
      this.open = false
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url("../assets/images/login-background.jpg");
  background-size: cover;
}

.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;

  .el-input {
    height: 38px;

    input {
      height: 38px;
    }
  }

  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 2px;
  }
}

.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}

.login-code {
  width: 33%;
  height: 38px;
  float: right;

  img {
    cursor: pointer;
    vertical-align: middle;
  }
}

.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}

.login-code-img {
  height: 38px;
}
</style>
