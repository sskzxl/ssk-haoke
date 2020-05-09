<template>
    <!--整个登陆页-->
    <div class = "login_container">
        <!--登陆框-->
        <div class = "login_box">
            <!--头像区域-->
            <div class="avatar_box">
                <img src="../assets/logo.png" alt="">
            </div>
            <!--登陆表单-->
            <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules" label-width="0px" class="login_form">
                <!--用户名-->
                <el-form-item prop="username">
                    <el-input v-model="loginForm.username" prefix-icon="iconfont icon-user"></el-input>
                </el-form-item>
                <!--密码-->
                <el-form-item prop="password">
                    <el-input  type="password" v-model="loginForm.password" prefix-icon="iconfont icon-3702mima"></el-input>
                </el-form-item>
                <el-form-item class="btns">
                    <el-button type="primary" @click="login">登陆</el-button>
                    <el-button type="info" @click="resetLoginForm">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>


<script>
export default {
    data() {
        return {
            //表单的数据绑定对象
            loginForm:{
                username:'admin',
                password:'123456'
            },
            //表单验证规则对象
            loginFormRules:{
                //验证用户名
                username:[
                    {required: true, message:"请输入用户名",trigger:"blur"},
                    {min : 3, max : 10, message:"长度在3 到10 个字符串",trigger:"blur"}
                ],
                password:[
                    {required: true, message:"请输入用户名",trigger:"blur"},
                    {min : 6, max : 10, message:"长度在3 到10 个字符串",trigger:"blur"}
                ]
            }
        }
    },
    methods: {
        resetLoginForm(){
            //重置表单
            this.$refs.loginFormRef.resetFields();
        },
        login(){
            this.$refs.loginFormRef.validate(async valid =>{
                // console.log(valid);
                //登陆失败
                if(!valid) return;
                //post 左边是路径 右边是数据
                //data是取返回结果的一个属性,res为这个属性的别名
                const {data: res} = await this.$http.post("login",this.loginForm);
                if(res.meta.status !== 200) return this.$message.error('登陆失败');
                this.$message.success('登陆成功');
                 // 1、将登陆成功之后的token, 保存到客户端的sessionStorage中; localStorage中是持久化的保存
                    // 1.1 项目中出现了登录之外的其他API接口，必须在登陆之后才能访问
                    //1.2 token 只应在当前网站打开期间生效，所以将token保存在sessionStorage中
                window.sessionStorage.setItem("token",res.data.token);
                 // 2、通过编程式导航跳转到后台主页, 路由地址为：/home
                this.$router.push('/home')
            })
        }
    },
}
</script>

<style lang="less" scoped>
    .login_container{
        background-color: #2b4b6b;
        height:100%;
    }
    .login_box {
        width: 450px;
        height: 360px;
        background-color: #fff;
        border-radius: 3px;
        position: absolute;
        left: 50%;
        top: 50%;
        -webkit-transform: translate(-50%, -50%);
        background-color: #fff;
        .avatar_box {
            width: 130px;
            height: 130px;
            border: 1px solid #eee;
            border-radius: 50%;
            padding: 10px;
            box-shadow: 0 0 10px #ddd;
            position: absolute;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #fff;
            img {
                width: 100%;
                height: 100%;
                border-radius: 50%;
                background-color: #eee;
            }
        }
    }
   .login_form {
        position: absolute;
        bottom: 0px;
        width: 100%;
        padding: 0 20px;
        box-sizing: border-box;
    } 
    .btns {
        display: flex;
        justify-content: center;
    }    
    .info {
        font-size: 13px;
        margin: 10px 15px;
    }
</style>