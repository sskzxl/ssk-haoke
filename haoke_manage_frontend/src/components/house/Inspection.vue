<template>
    <div>
        <!-- 面包屑导航区 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>看房管理</el-breadcrumb-item>
            <el-breadcrumb-item>看房列表</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card>
            <!-- 搜索 添加 -->
            <el-row :gutter="20">
                <el-col :span="6">
                    <!--待优化模糊搜索-->
                    <el-input placeholder="请输入内容" v-model="queryInfo.filter.username">
                        <el-button slot="append" icon="el-icon-search" @click="this.getInspectionList"></el-button>
                    </el-input>
                </el-col>
            </el-row>
            <el-table :data="userList" border stripe style="width: 100%">
                <el-table-column type="index"></el-table-column>
                <el-table-column prop="tenantName" label="用户名" width="120"></el-table-column>
                <el-table-column prop="tenantPhone" label="手机号" width="150"></el-table-column>
                <el-table-column prop="address" label="地址" width="150"></el-table-column>
                <el-table-column prop="reqTime" label="请求时间" width="150"></el-table-column>
                <el-table-column prop="inspectionTime" label="看房时间" width="150"></el-table-column>

                <el-table-column prop="reqStatus" label="请求状态" width="150"></el-table-column>

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
        </el-card>

    </div>
</template>
<script>
    export default {
       data(){
           return {
               queryInfo :{
                   filter :{
                       tenantName : '',
                       tenantPhone : '',
                       address : '',
                       reqStatus : '',
                       reqTime : '',
                       inspectionTime : ''
                   }
               }
           }
       },
        methods:{
            getInspectionList(){

            }
        }
    }
</script>