import Vue from "vue";
import {Form,FormItem}from 'element-ui';
import {Input} from 'element-ui';
import {Button} from 'element-ui';
import {message} from 'element-ui'
//布局
import {Container,Aside,Header,Main,Menu,MenuItem,MenuItemGroup,Breadcrumb,BreadcrumbItem,Card,Select} from 'element-ui'
import {Image,Upload,Step,CheckboxGroup,TabPane,Table,TableColumn,Tooltip,Pagination,Dialog,Tag,Alert} from 'element-ui'
Vue.prototype.$message = message; 
Vue.use(Form);
Vue.use(FormItem);
Vue.use(Input);
Vue.use(Button);
Vue.use(Container);
Vue.use(Aside);
Vue.use(Header);
Vue.use(Main);
Vue.use(Menu);
Vue.use(MenuItem);
Vue.use(MenuItemGroup);
Vue.use(Breadcrumb);
Vue.use(BreadcrumbItem);
Vue.use(Card);
Vue.use(TableColumn);
Vue.use(Table);
Vue.use(Tooltip);
Vue.use(Pagination);
Vue.use(Dialog);
Vue.use(Select);
Vue.use(Tag);
Vue.use(Alert);
Vue.use(Step);
Vue.use(TabPane);
Vue.use(CheckboxGroup)
Vue.use(Upload)
Vue.use(Image)