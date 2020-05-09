<template>
    <div>
        <!-- 面包屑导航区 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>房源管理</el-breadcrumb-item>
            <el-breadcrumb-item>房源列表</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card>
            <!-- 搜索 添加 -->
            <el-row :gutter="20">
                <el-col :span="6">
                    <!--待优化模糊搜索-->
                    <el-input placeholder="请输入内容" v-model="queryInfo.filter.title">
                        <el-button slot="append" icon="el-icon-search" @click="getHouseList()"></el-button>
                    </el-input>
                </el-col>
                <el-col :span="4">
                    <el-button type="primary" @click="addHousePath">添加房源</el-button>
                </el-col>
            </el-row>

            <!--表单列表-->
            <el-table :data="HouseList" border stripe style="width: 100%">
                <el-table-column type="index"></el-table-column>
                <el-table-column prop="title" label="房源标题" width="250"></el-table-column>
                <el-table-column prop="houseType" label="房屋类型" width="100"></el-table-column>
                <el-table-column prop="orientation" label="朝向" width="50"></el-table-column>
                <el-table-column prop="floor" label="楼层" width="90" ></el-table-column>
                <el-table-column prop="rent" label="租金" width="90"></el-table-column>
                <el-table-column prop="contact" label="房东" width="90"></el-table-column>
                <el-table-column prop="byReview" label="审核状态" width="95"
                                 :filters="[{ text: '已审核', value: 1 }, { text: '未审核', value: 0 }]"
                                 :filter-method="filterTag"
                                 filter-placement="bottom-end">
                >
                        <template slot-scope="scope">
                            <el-tag :type="scope.row.byReview=== 1 ? 'primary' : 'success'" disable-transitions size="medium" >
                                {{scope.row.byReview=== 1 ? '已审核' : '未审核'}}
                            </el-tag>
                        </template>

                </el-table-column>

                <!--操作区域-->
                <el-table-column  label="操作" width="120">
                    <template slot-scope="scope">
                        <el-tooltip class="item" effect="dark" content="房源审核" :enterable="false" placement="top">
                            <el-button type="primary" size="small" icon="el-icon-edit" @click="queryHouseByid(scope.row.id) " ></el-button>
                        </el-tooltip>
                        <el-tooltip class="item" effect="dark" content="房源删除" :enterable="false" placement="top">
                            <el-button type="danger" size="small" icon="el-icon-delete" @click="removeHouseBy(scope.row.id)"></el-button>
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
            <!--添加房源对话框-->
            <el-dialog
                    title="添加房源"
                    :visible.sync="dialogVisible"
                    width="50%"
                    @close="handleClose">
                <el-form
                        :model="addHouseForm"
                        ref="addHouseFormRef"
                        :rules="addHouseFormRules"
                        label-width="100px"
                >
                    <el-form-item label="房源名" prop="Housename">
                        <el-input v-model="addHouseForm.Housename"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input v-model="addHouseForm.password"></el-input>
                    </el-form-item>
                    <el-form-item label="手机" prop="phone">
                        <el-input v-model="addHouseForm.phone"></el-input>
                    </el-form-item>
                    <el-form-item label="人员" prop="">
                        <el-checkbox v-model="addHouseForm.host">房东</el-checkbox>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="addHouse">确认</el-button>
        </span>
            </el-dialog>
            <!--修改房源对话框-->
            <el-dialog
                    title="审核房源"
                    :visible.sync="ModifDialogVisible"
                    width="50%"
                    @close="handleClose">
                <el-form
                        :model="ModifyHouseForm"
                        ref="ModifyHouseFormRef"
                        :rules="addHouseFormRules"
                        label-width="100px"
                >
                    <el-form-item label="房源标题" prop="title">
                        <el-input :disabled="true" v-model="ModifyHouseForm.title"></el-input>
                    </el-form-item>
                    <el-form-item label="租金" prop="phone">
                        <el-input :disabled="true" v-model="ModifyHouseForm.rent"></el-input>
                    </el-form-item>
                    <el-form-item label="使用面积" prop="">
                        <el-input :disabled="true" v-model="ModifyHouseForm.useArea"></el-input>
                    </el-form-item>
                    <el-form-item label="建筑面积" prop="">
                        <el-input :disabled="true" v-model="ModifyHouseForm.coveredArea"></el-input>
                    </el-form-item>
                    <el-form-item label="地址" prop="">
                        <el-input :disabled="true" v-model="ModifyHouseForm.address"></el-input>
                    </el-form-item>
                    <el-form-item label="租房类型" prop="">
                        <el-input :disabled="true" v-model="ModifyHouseForm.rentType"></el-input>
                    </el-form-item>
                    <el-form-item label="房屋描述" prop="">
                        <el-input :disabled="true" type="textarea" v-model="ModifyHouseForm.houseDesc"></el-input>
                    </el-form-item>
                    <el-form-item label="联系人" prop="">
                        <el-input :disabled="true" v-model="ModifyHouseForm.contact"></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" prop="">
                        <el-input :disabled="true"  v-model="ModifyHouseForm.phone"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
          <el-button @click="ModifDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="modifyHouse" >
              通过审核
          </el-button>
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
                tableData: [{
                    date: '2016-05-03',
                    name: 'Tom',
                    address: 'No. 189, Grove St, Los Angeles',
                    tag: '已审核'
                }, {
                    date: '2016-05-02',
                    name: 'Tom',
                    address: 'No. 189, Grove St, Los Angeles',
                    tag: '未审核'
                }],
                //获取房源列表的参数对象
                queryInfo:{
                    filter:{

                    },
                    pageSize:10,
                    pageNum:1
                },
                HouseList:[],
                total:0,
                dialogVisible:false,
                ModifDialogVisible:false,
                isHost:false,
                // 房源添加
                addHouseForm: {
                    Housename: '',
                    password: '',
                    phone: '',
                    host:true,
                    isHost:1
                },
                ModifyHouseForm:{
                    title : '',
                    rent : 0,
                    useArea : 0,
                    orientation : '',
                    coveredArea:0,
                    address : '',
                    rentType : '整租',
                    houseDesc : '',
                    attachments : [],
                    pic :[],
                    contact : '',
                    phone : '',
                    byReview :0
                },
                // 房源添加表单验证规则
                addHouseFormRules: {
                    Housename: [
                        { required: true, message: '请输入房源名', trigger: 'blur' },
                        {
                            min: 2,
                            max: 10,
                            message: '房源名的长度在2～10个字',
                            trigger: 'blur'
                        }
                    ],
                    password: [
                        { required: true, message: '请输入房源密码', trigger: 'blur' },
                        {
                            min: 6,
                            max: 18,
                            message: '房源密码的长度在6～18个字',
                            trigger: 'blur'
                        }
                    ],
                    phone: [
                        { required: true, message: '请输入手机号码', trigger: 'blur' },
                        { validator: checkMobile, trigger: 'blur' }
                    ]
                },

            }
        },
        created(){
            this.getHouseList()
        },
        methods:{
            addHousePath(){
              this.$router.push("/add")
            },
            filterTag(value, row) {
                return row.byReview === value;
            },
            async getHouseList(){
                const {data:res} = await this.$http.get('/house/resources',{params: this.queryInfo})
                //数据数据失败
                if (res.resultCode !== 0) return this.$message.error("获取房源列表失败");
                this.HouseList = res.data.records;
                this.total = res.data.total;
            },
            handleSizeChange(newSize){
                this.queryInfo.pageSize = newSize;
                this.getHouseList();
            },
            handleCurrentChange(newChange){
                this.queryInfo.pageNum = newChange;
                this.getHouseList()
                console.log(newChange)
            },
            handleClose(){
                this.$refs.addHouseFormRef.resetFields();
            },
            //点击按钮添加新房源
            addHouse(){
                this.$refs.addHouseFormRef.validate(async valid=>{
                    if (!valid)return;
                    //发起请求后端添加新房源
                    if (this.addHouseForm.host){
                        this.addHouseForm.isHost = 0;
                    }
                    const {data:res} = await this.$http.post("/house/resources",this.addHouseForm)
                    if (res.resultCode !== 0) return this.$message.error("添加房源失败");
                    this.dialogVisible = false;
                    this.getHouseList();
                    return this.$message.success("添加房源成功")
                })
            },
            async removeHouseBy(id){
                const {data:res} = await this.$http.delete("/house/resources/"+id)
                if (res.resultCode !== 0)  return this.$message.error("删除房源失败");
                this.getHouseList();

            },
            async queryHouseByid(id){
                this.ModifDialogVisible = true;
                const {data:res} = await this.$http.get("/house/resources/"+id)
                if (res.resultCode !== 0)  return this.$message.error("获取房源失败");
                this.ModifyHouseForm = res.data;
            },
            async modifyHouse(){
                this.ModifyHouseForm.byReview = 1;
                const {data:res} = await this.$http.put("/house/resources",this.ModifyHouseForm)
                if (res.resultCode !== 0)  return this.$message.error("审核房源失败");
                this.getHouseList();
                this.ModifDialogVisible = false;
                return this.$message.success("审核房源成功");

            },
            // el-tag类型转换
            // 状态显示转换

        }
    }

</script>

<style lang="less" scoped>
    @import url("//unpkg.com/element-ui@2.13.1/lib/theme-chalk/index.css");
</style>
