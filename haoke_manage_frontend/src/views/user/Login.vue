<template>
  <div class="hk-login">
    <a-form
      id="components-form-demo-normal-login"
      :form="form"
      class="hk-login-form"
      @submit="handleSubmit"
    >
      <a-tabs defaultActiveKey="1" @change="handleChange">
        <a-tab-pane tab="账号密码登录" key="1">
          <a-form-item>
            <a-input
              v-decorator="[
                'userName',
                {
                  rules: [
                    { required: true, message: 'Please input your username!' }
                  ]
                }
              ]"
              placeholder="admin/user"
            >
              <a-icon
                slot="prefix"
                type="user"
                style="color: rgba(0,0,0,.25)"
              />
            </a-input>
          </a-form-item>
          <a-form-item>
            <a-input
              v-decorator="[
                'password',
                {
                  rules: [
                    { required: true, message: 'Please input your Password!' }
                  ]
                }
              ]"
              type="password"
              placeholder="888888/123456"
            >
              <a-icon
                slot="prefix"
                type="lock"
                style="color: rgba(0,0,0,.25)"
              />
            </a-input>
          </a-form-item>
        </a-tab-pane>
        <a-tab-pane tab="手机号登录" key="2">
          <a-form-item>
            <a-input
              v-decorator="[
                'userName',
                {
                  rules: [
                    { required: true, message: 'Please input your username!' }
                  ]
                }
              ]"
              placeholder="mobile number"
            >
              <a-icon
                slot="prefix"
                type="user"
                style="color: rgba(0,0,0,.25)"
              />
            </a-input>
          </a-form-item>
          <a-form-item>
            <a-row :gutter="8">
              <a-col :span="17">
                <a-input
                  v-decorator="[
                    'captcha',
                    {
                      rules: [
                        {
                          required: true,
                          message: 'Please input the captcha you got!'
                        }
                      ]
                    }
                  ]"
                />
              </a-col>
              <a-col :span="7">
                <a-button>获取验证码</a-button>
              </a-col>
            </a-row>
          </a-form-item>
        </a-tab-pane>
      </a-tabs>
      <a-form-item>
        <a-checkbox
          v-decorator="[
            'remember',
            {
              valuePropName: 'checked',
              initialValue: true
            }
          ]"
          >自动登录</a-checkbox
        >
        <a class="hk-login-forgot" href>忘记密码</a>
        <a-button
          block
          type="primary"
          html-type="submit"
          class="login-form-button"
          >Log in</a-button
        >Or
        <a href>register now!</a>
      </a-form-item>
    </a-form>
  </div>
</template>

<script>
import { fakeAccountLogin } from "@/api/user";
export default {
  data() {
    return {
      account: [
        {
          rules: [
            {
              required: true,
              message: "Please enter username!"
            }
          ]
        }
      ]
    };
  },
  beforeCreate() {
    this.form = this.$form.createForm(this);
  },
  methods: {
    handleChange() {},
    handleSubmit(e) {
      e.preventDefault();
      this.form.validateFields((err, values) => {
        if (!err) {
          console.log("Received values of form: ", values);
        }
        fakeAccountLogin(values).then(res => {
          console.log(res);
        });
      });
    }
  }
};
</script>

<style lang="scss">
.hk-login {
  width: 368px;
  margin: 0 auto;

  .ant-tabs-bar {
    text-align: center;
  }
}
.hk-login-forgot {
  float: right;
}
</style>
