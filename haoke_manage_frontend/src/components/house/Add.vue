<template>
<div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>房源管理</el-breadcrumb-item>
        <el-breadcrumb-item>房源添加</el-breadcrumb-item>
    </el-breadcrumb>
   <el-card>
       <el-alert show-icon center=""
                 title="添加房源信息"
                 type="info">
       </el-alert>
       <el-steps align-center :space="200" :active="activeIndex-0" finish-status="success">
           <el-step title="房源基本信息"></el-step>
           <el-step title="房东个人信息"></el-step>
           <el-step title="房源图片"></el-step>
           <el-step title="房源配套设施"></el-step>
       </el-steps>
       <!--表单-->
       <el-form :model="addHouse" :rules="addHoseRules" ref="addHouseRef" label-width="120px" >
            <!--左侧标签-->
           <el-tabs v-model="activeIndex" :tab-position="'left'" >
               <el-tab-pane label="基本信息" name="0">
                   <el-form-item label="房源标题" prop="title">
                       <el-input v-model="addHouse.title"></el-input>
                   </el-form-item>
                   <el-form-item label="租金" prop="rent" >
                       <el-input v-model="addHouse.rent" width="50px" type="number"></el-input>
                   </el-form-item>
                   <el-form-item label="使用面积" prop="useArea" >
                       <el-input v-model="addHouse.useArea" width="50px" type="number"></el-input>
                   </el-form-item>
                   <el-form-item label="建筑面积" prop="coveredArea" >
                       <el-input v-model="addHouse.coveredArea" width="50px" type="number"></el-input>
                   </el-form-item>
                   <el-form-item label="房屋朝向" prop="orientation" >
                       <el-input v-model="addHouse.orientation" width="50px"></el-input>
                   </el-form-item>
                   <el-form-item label="地址" prop="address" >
                       <el-input v-model="addHouse.address" width="50px"></el-input>
                   </el-form-item>
                   <el-form-item label="租房方式" prop="rentType" >
                           <el-select v-model="addHouse.rentType" placeholder="整租">
                               <el-option
                                       v-for="item in rentType"
                                       :key="item.value"
                                       :label="item.label"
                                       :value="item.value">
                               </el-option>
                           </el-select>
                   </el-form-item>
                   <el-form-item label="房屋描述" prop="houseDesc">
                       <el-input type="textarea" v-model="addHouse.houseDesc"></el-input>
                   </el-form-item>
               </el-tab-pane>
               <el-tab-pane label="房东个人信息" name="1">
                   <el-form-item label="姓名" prop="contact">
                       <el-input v-model="addHouse.contact"></el-input>
                   </el-form-item>
                   <el-form-item label="电话" prop="phone" >
                       <el-input v-model="addHouse.phone" width="50px" type="number"></el-input>
                   </el-form-item>
               </el-tab-pane>

               <el-tab-pane label="房源图片" name="2">
                   <div class="uploadText">请上传房源图片：</div>
                   <el-upload
                           action="http://127.0.0.1:18081/api/pic/upload"
                           list-type="picture-card"
                           :on-preview="handlePictureCardPreview"
                           :on-remove="handleRemove"
                           :on-success="handleSuccess" >
                       <i class="el-icon-plus"></i>
                   </el-upload>
                   <el-dialog :visible.sync="dialogVisible">
                       <img width="100%" :src="dialogImageUrl" alt="">
                   </el-dialog>
               </el-tab-pane>

               <el-tab-pane label="房源配套设施" name="3">
                   <el-checkbox-group v-model="addHouse.attachments">
                       <el-checkbox class="check_attachment" v-for="item in attachmentsEnum"
                                    :key="item.value"
                                    :label="item.value"
                                    :value="item.value">
                           {{item.label}}
                           <el-image  :src="`${imgUrl}/icon/${item.url}`" ></el-image>
                       </el-checkbox>
                   </el-checkbox-group>
                   <el-button type="primary" class="addBtn" @click="add">添加房源</el-button>
               </el-tab-pane>
           </el-tabs>
       </el-form>
   </el-card>
</div>
</template>
<script>

    import config from "./../../../app.config"
    import ElButton from "../../../node_modules/element-ui/packages/button/src/button.vue";
    export default {
        components: {ElButton},
        data(){
//            var checkRent = (rule, value, callback) => {
//                    const regRent = /^[1-9]\d*$/
//                    if (regRent.test(value)) {
//                        return callback()
//                    }
//                    // 返回一个错误提示
//                    callback(new Error('请输入合法的租金'))
//                }
            return {
                dialogImageUrl: '',
                dialogVisible: false,
                disabled: false,
                imgUrl: config.sourceUrl.img,
                activeIndex : "0",
                addHouse : {
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
                    phone : ''
                },
                attachmentsEnum : [
                    { value  : 1	, label: '床', url :	'L-bed.jpg'	}
                    ,{ value  : 2	, label: '洗衣机', url :	'L-washer.jpg'	}
                    ,{ value : 3	, label: '空调', url :	'L-airCondition.jpg'	}
                    ,{ value : 4	, label: '衣柜', url :	'L-wardrobe.jpg'	}
                    ,{ value : 5	, label: '电视', url :	'L-TV.jpg'	}
                    ,{ value : 6	, label: '冰箱', url :	'L-fridge.jpg'	}
                    ,{ value : 7	, label: '热水器', url :	'L-waterHeater.jpg'	}
                    ,{ value : 8	, label: '暖气', url :	'L-warm.jpg'	}
                    ,{ value : 9	, label: '宽带', url :	'L-broadband.jpg'	}
                    ,{ value : 10	, label: '天然气', url :	'L-fire.jpg'	}

                ],
                rentType :[
                    {
                        value: 1,
                        label: '整租'
                    }, {
                        value: 0,
                        label: '合租'
                    }
                ],

                addHoseRules : {
                    title:[
                        {required :true , message:'请输入房源标题', trigger:'blur'},
                    ],
                    rent:[
                        {required :true , message:'请输入租金', trigger:'blur'},
                    ],
                    useArea:[
                        {required :true , message:'请输入使用面积', trigger:'blur'},
                    ],
                    coveredArea:[
                        {required :true , message:'请输入使用面积', trigger:'blur'},
                    ],
                    orientation:[
                        {required :true , message:'请输入朝向', trigger:'blur'},
                    ],
                    houseDesc : [
                        {required :true , message:'请输入房屋描述', trigger:'blur'},
                    ],
                    address:[
                        {required :true , message:'请输入地址', trigger:'blur'},
                    ],
                    phone: [
                        { required: true, message: '请输入手机号码', trigger: 'blur' },
                    ],
                    contact: [
                        { required: true, message: '请输入姓名', trigger: 'blur' },
                    ]
                },


            }
        },
        methods:{
            handleRemove(file) {
                //获取当前图片对象
                const RmPicfile = file.response.response;
                //在房源添加对象中找到该图片索引
                const index  = this.addHouse.pics.findIndex(x=> x == RmPicfile)
                //删除该图片
                this.addHouse.pics.splice(index, 1)
                this.dialogVisible = false;
                console.log(this.addHouse)
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },
            handleDownload(file) {
                console.log(file);
            },
            handleSuccess(data){
                const pics = data.response;
                this.addHouse.pic.push(pics)
                console.log(this.addHouse)
            },
            async add(){
                this.$refs.addHouseRef.validate(async valid=>{
                    if (!valid) return;
                    const {data:res} = await this.$http.post("/house/resources",this.addHouse)
                    if (res.resultCode !== 0) return this.$message.error("添加房源失败");
                    this.$router.push("/house")
                    return this.$message.success("添加房源成功");
                })

            }
        }
    }


</script>
<style lang="less" scoped>

    .check_attachment{
        margin: 5px  23px 0 0 !important;
    }
    .uploadText{
        margin: 15px;
    }
    .addBtn{
        margin: 25px;
    }
</style>