<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <!-- 搜索 添加 -->
      <el-row :gutter="20">
        <el-col :span="6">
          <!--待优化模糊搜索-->
          <el-input placeholder="请输入内容" v-model="queryInfo.filter.username">
            <el-button slot="append" icon="el-icon-search" @click="this.getUserList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="dialogVisible = true">添加用户</el-button>
        </el-col>
      </el-row>
      <!--表单列表-->
      <el-table :data="userList" border stripe style="width: 100%">
        <el-table-column type="index"></el-table-column>
        <el-table-column prop="username" label="用户名" width="120"></el-table-column>
        <el-table-column prop="phone" label="手机号" width="150"></el-table-column>
        <el-table-column  label="是否房东" width="120">
          <template slot-scope="scope">
            <span v-if="scope.row.isHost == 1">是</span>
            <span v-if="scope.row.isHost == 0">否</span>
          </template>
        </el-table-column>
        <!--操作区域-->
        <el-table-column  label="操作" width="120">
          <template slot-scope="scope">
            <el-tooltip class="item" effect="dark" content="用户编辑" :enterable="false" placement="top">
              <el-button type="primary" size="mini" icon="el-icon-edit" @click="queryUserByid(scope.row.id) " ></el-button>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="用户删除" :enterable="false" placement="top">
              <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeUserBy(scope.row.id)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <!--分页-->
      <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :page-sizes="[5, 10, 15, 20]"
              :page-size=this.queryInfo.pageSize
              layout="sizes, prev, pager, next"
              :total=this.total>
      </el-pagination>
      <!--添加用户对话框-->
      <el-dialog
              title="添加用户"
              :visible.sync="dialogVisible"
              width="50%"
              @close="handleClose">
        <el-form
                :model="addUserForm"
                ref="addUserFormRef"
                :rules="addUserFormRules"
                label-width="100px"
        >
          <el-form-item label="用户名" prop="username">
            <el-input v-model="addUserForm.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="addUserForm.password"></el-input>
          </el-form-item>
          <el-form-item label="手机" prop="phone">
            <el-input v-model="addUserForm.phone"></el-input>
          </el-form-item>
          <el-form-item label="人员" prop="">
            <el-checkbox v-model="addUserForm.host">房东</el-checkbox>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="addUser">确认</el-button>
        </span>
      </el-dialog>
      <!--修改用户对话框-->
      <el-dialog
              title="编辑用户"
              :visible.sync="ModifDialogVisible"
              width="50%"
              @close="handleClose">
        <el-form
                :model="ModifyUserForm"
                ref="ModifyUserFormRef"
                :rules="addUserFormRules"
                label-width="100px"
        >
          <el-form-item label="用户名" prop="username">
            <el-input v-model="ModifyUserForm.username"></el-input>
          </el-form-item>
          <el-form-item label="手机" prop="phone">
            <el-input v-model="ModifyUserForm.phone"></el-input>
          </el-form-item>
          <el-form-item label="人员" prop="">
            <el-checkbox v-model="ModifyUserForm.host">房东</el-checkbox>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="ModifDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="modifyUser">确认</el-button>
        </span>
      </el-dialog>
    </el-card>
  </div>
</template>
<script>
  export default {
      data(){
          // 自定义手机号规则
          var checkMobile = (rule, value, callback) => {
              const regMobile = /^1[34578]\d{9}$/
              if (regMobile.test(value)) {
                  return callback()
              }
              // 返回一个错误提示
              callback(new Error('请输入合法的手机号码'))
          }
          return{
              //获取用户列表的参数对象
              queryInfo:{
                  filter:{
                      username:null
                  },
                  pageSize:10,
                  pageNum:1
              },
              userList:[],
              total:0,
              dialogVisible:false,
              ModifDialogVisible:false,
              isHost:false,
              // 用户添加
              addUserForm: {
                  username: '',
                  password: '',
                  phone: '',
                  host:true,
                  isHost:1
              },
              ModifyUserForm:{
                  username: '',
                  phone: '',
                  host:true,
                  isHost:1
              },
              // 用户添加表单验证规则
              addUserFormRules: {
                  username: [
                      { required: true, message: '请输入用户名', trigger: 'blur' },
                      {
                          min: 2,
                          max: 10,
                          message: '用户名的长度在2～10个字',
                          trigger: 'blur'
                      }
                  ],
                  password: [
                      { required: true, message: '请输入用户密码', trigger: 'blur' },
                      {
                          min: 6,
                          max: 18,
                          message: '用户密码的长度在6～18个字',
                          trigger: 'blur'
                      }
                  ],
                  phone: [
                      { required: true, message: '请输入手机号码', trigger: 'blur' },
                      { validator: checkMobile, trigger: 'blur' }
                  ]
              }

          }
      },
      created(){
        this.getUserList()
      },
      methods:{
        async getUserList(){
            const {data:res} = await this.$http.get('/house/user/page',{params: this.queryInfo})
            //数据数据失败
            if (res.resultCode !== 0) return this.$message.error("获取用户列表失败");
            this.userList = res.data.records;
            this.total = res.data.total;
        },
          handleSizeChange(newSize){
            this.queryInfo.pageSize = newSize;
            this.getUserList();
          },
          handleCurrentChange(newChange){
              this.queryInfo.pageNum = newChange;
              this.getUserList()
              console.log(newChange)
          },
          handleClose(){
              this.$refs.addUserFormRef.resetFields();
          },
          //点击按钮添加新用户
          addUser(){
              this.$refs.addUserFormRef.validate(async valid=>{
                  if (!valid)return;
                  //发起请求后端添加新用户
                  if (this.addUserForm.host){
                      this.addUserForm.isHost = 0;
                  }
                  const {data:res} = await this.$http.post("/house/user",this.addUserForm)
                  if (res.resultCode !== 0) return this.$message.error("添加用户失败");
                  this.dialogVisible = false;
                  this.getUserList();
                  return this.$message.success("添加用户成功")
              })
          },
          async removeUserBy(id){
              const {data:res} = await this.$http.delete("/house/user/"+id)
              if (res.resultCode !== 0)  return this.$message.error("删除好友失败");
              this.getUserList();

          },
          async queryUserByid(id){
              this.ModifDialogVisible = true;
              const {data:res} = await this.$http.get("/house/user/"+id)
              if (res.resultCode !== 0)  return this.$message.error("获取用户失败");
              this.ModifyUserForm = res.data;
          },
          async modifyUser(){
              const {data:res} = await this.$http.put("/house/user/",this.ModifyUserForm)
              if (res.resultCode !== 0)  return this.$message.error("修改用户失败");
              this.getUserList();
              this.ModifDialogVisible = false;
              return this.$message.success("修改用户成功");

          }
      }
  }

</script>

<style lang="less" scoped></style>
