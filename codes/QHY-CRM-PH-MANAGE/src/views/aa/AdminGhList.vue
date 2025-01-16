<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="企业名称">
              <a-input placeholder="请输入企业名称" v-model="queryParam.ssqyname"></a-input>
            </a-form-item>
          </a-col>
          <!--          <a-col :xl="6" :lg="7" :md="8" :sm="24">-->
          <!--            <a-form-item label="客户地址">-->
          <!--              <j-area-linkage type="cascader" v-model="queryParam.khAddress" placeholder="请选择省市区" />-->
          <!--            </a-form-item>-->
          <!--          </a-col>-->
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="客户地址">
              <a-input placeholder="请输入省市区" v-model="queryParam.sskhAddress"></a-input>
            </a-form-item>
          </a-col>
          <!--          <a-col :xl="6" :lg="7" :md="8" :sm="24">-->
          <!--            <a-form-item label="归属业务员">-->
          <!--              <j-dict-select-tag placeholder="请选择归属业务员" v-model="queryParam.khYwy" dictCode="sys_user,username,username"/>-->
          <!--            </a-form-item>-->
          <!--          </a-col>-->

          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>

            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" v-has="'admingh:add'" type="primary" icon="plus">新增</a-button>
      <!--      <a-button type="primary" v-has="'admingh:add'" icon="download" @click="handleExportXls('公海')">导出</a-button>-->
      <!--      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">-->
      <!--        <a-button type="primary" v-has="'admingh:add'" icon="import">导入</a-button>-->
      <!--      </a-upload>-->
      <!-- 高级查询区域 -->
      <j-super-query :fieldList="superFieldList" ref="superQueryModal"
                     @handleSuperQuery="handleSuperQuery"></j-super-query>

      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchPL">
            <a-icon type="setting" />
            批量领取客户
          </a-menu-item>

          <a-menu-item v-has="'admingh:add'" key="2" @click="batchPL2">
            <a-icon type="setting" />
            指派业务员
          </a-menu-item>

          <a-menu-item v-has="'admingh:add'" key="3" @click="batchDel">
            <a-icon type="delete" />
            删除
          </a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作
          <a-icon type="down" />
        </a-button>
      </a-dropdown>


      <!--      <a-button @click="handleAdd" type="primary" icon="plus">批量领取客户</a-button>-->
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a
        style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        :scroll="{x:true}"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt=""
               style="max-width:80px;font-size: 12px;font-style: italic;" />
        </template>
        <template slot="pcaSlot" slot-scope="text">
          <div>{{ getPcaText(text) }}</div>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <!--    指派业务员-->
    <j-modal
      title="指派业务员"
      :width="width"
      :visible="visiblezs"
      switchFullscreen
      @ok="handleOkzs"
      :okButtonProps="{ class:{'jee-hidden': disableSubmit} }"
      @cancel="handleCancelzs"
      cancelText="关闭">
      <a-row>
        <a-col :span="24">
          <a-form-model-item label="业务员" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <j-dict-select-tag type="list" v-model="ywy" dictCode="sys_user,username,username" placeholder="请选择业务员"
                               style="width: 100%" />
          </a-form-model-item>
        </a-col>
      </a-row>
    </j-modal>


    <admin-gh-modal ref="modalForm" @ok="modalFormOk"></admin-gh-modal>
  </a-card>
</template>

<script>

import '@/assets/less/TableExpand.less'
import { mixinDevice } from '@/utils/mixin'
import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import AdminGhModal from './modules/AdminGhModal'
import { filterMultiDictText } from '@/components/dict/JDictSelectUtil'
import Area from '@/components/_util/Area'
import { getAction, httpAction } from '@api/manage'
import store from '@/store'

export default {
  name: 'AdminGhList',
  mixins: [JeecgListMixin, mixinDevice],
  components: {
    AdminGhModal
  },
  data() {
    return {
      visiblezs: false,
      disableSubmit: false,
      width: 800,
      ywy: '',
      labelCol: {
        xs: { span: 24 },
        sm: { span: 5 }
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 }
      },
      ids2: '',
      description: '公海客户管理页面',
      // 表头
      columns: [
        {
          title: '#',
          dataIndex: '',
          key: 'rowIndex',
          width: 60,
          align: 'center',
          customRender: function(t, r, index) {
            return parseInt(index) + 1
          }
        },
        {
          title: '企业名称',
          align: 'center',
          dataIndex: 'qyname'
        },
        {
          title: '客户来源',
          align: 'center',
          dataIndex: 'sourceEnterprise'
        },
        {
          title: '客户所做产品',
          align: 'center',
          dataIndex: 'khProduct'
        },
        {
          title: '客户联系人',
          align: 'center',
          dataIndex: 'khContacts'
        },
        {
          title: '客户地址',
          align: 'center',
          dataIndex: 'khAddress'
        },
        {
          title: '客户联系方式',
          align: 'center',
          dataIndex: 'khContactInformation'
        },
        {
          title: '客户跟进状态',
          align: 'center',
          dataIndex: 'khType'
        },
        {
          title: '预备业务员',
          align: 'center',
          dataIndex: 'reserveClerk'
        },
        {
          title: '创建时间',
          align: 'center',
          dataIndex: 'createTime'
        },
        // {
        //   title:'归属业务员',
        //   align:"center",
        //   dataIndex: 'khYwy_dictText'
        // },
        // {
        //   title:'业务员联系方式',
        //   align:"center",
        //   dataIndex: 'khYwyPhone'
        // },
        {
          title: '操作',
          dataIndex: 'action',
          align: 'center',
          fixed: 'right',
          width: 147,
          scopedSlots: { customRender: 'action' }
        }
      ],
      url: {
        list: '/aa/adminGh/list',
        delete: '/aa/adminGh/delete',
        deleteBatch: '/aa/adminGh/deleteBatch',
        exportXlsUrl: '/aa/adminGh/exportXls',
        importExcelUrl: 'aa/adminGh/importExcel',
        edit: 'aa/adminGh/edit',
        edit2: 'aa/adminGh/edit2',
        one: '/crm/sz/one',
//不是公海，是个人拥有数量
        listNum: 'aa/adminKhgl/listNum'

      },
      dictOptions: {},
      pcaData: '',
      superFieldList: []
    }
  },
  created() {
    // this.pcaData = new Area()
    this.getSuperFieldList()
  },
  computed: {

    importExcelUrl: function() {
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`
    }
  },
  methods: {
    //选择业务员
    handleCancelzs() {
      this.closezs()
    },
    closezs() {
      this.$emit('close')
      this.visiblezs = false
    },
    handleOkzs() {
      var that = this

      var ids = that.ids2

      // var idss = ids.split(',')
      var ywy = that.ywy

      httpAction(that.url.edit2, { ids: ids, khYwy: ywy, type: 2 }, 'post').then((res) => {
        if (res.success) {
          that.$message.success(res.message)
          that.$emit('ok')
          that.loadData()
          this.visiblezs = false
        } else {
          that.$message.warning(res.message)
          this.visiblezs = false
        }
      }).finally(() => {
      })

      //
      // for (const id of idss) {
      //   if (id != '') {
      //     httpAction(that.url.edit2, { id: id, khYwy: ywy }, 'post').then((res) => {
      //       if (res.success) {
      //         that.$message.success(res.message)
      //         // that.$emit('ok')
      //       } else {
      //         that.$message.warning(res.message)
      //       }
      //     }).finally(() => {
      //     })
      //   }
      // }

    },

    batchPL2() {
      if (this.selectedRowKeys.length <= 0) {
        this.$message.warning('请选择一条记录！')
        return
      } else {
        var ids = ''
        for (var a = 0; a < this.selectedRowKeys.length; a++) {
          ids += this.selectedRowKeys[a] + ','
        }
        this.visiblezs = true
        this.ids2 = ids
      }
    },

    batchPL() {
      if (this.selectedRowKeys.length <= 0) {
        this.$message.warning('请选择一条记录！')
        return
      } else {
        var ids = ''
        for (var a = 0; a < this.selectedRowKeys.length; a++) {
          ids += this.selectedRowKeys[a] + ','
        }
        var that = this
        var idss = ids.split(',')
        httpAction(that.url.one, {}, 'post').then((res) => {
          if (res.success) {
            if (idss.length <= res.result.num) {

              let usercode = store.getters.userInfo.username

              httpAction(that.url.listNum, { khYwy: usercode }, 'post').then((res2) => {

                // idss数组多一个空数组所以减一
                if (idss.length + res2 - 1 <= res.result.num) {

                  httpAction(that.url.edit2, { ids: ids, khYwy: usercode, type: 1 }, 'post').then((res) => {
                    if (res.success) {
                      that.$message.success(res.message)
                      that.$emit('ok')
                      that.loadData()
                    } else {
                      that.$message.warning(res.message)
                    }
                  }).finally(() => {
                  })
                  // for (const id of idss) {
                  //   if (id != '') {
                  //     httpAction(that.url.edit, { id: id, khYwy: usercode }, 'post').then((res) => {
                  //       if (res.success) {
                  //         that.$message.success(res.message)
                  //         // that.$emit('ok')
                  //       } else {
                  //         that.$message.warning(res.message)
                  //       }
                  //     }).finally(() => {
                  //     })
                  //   }
                  // }
                } else {
                  this.$message.warning('超出设定业务员的领取数量')
                }

              }).finally(() => {
              })

            } else {
              this.$message.warning('超出设定业务员的领取数量')
            }
          } else {
            this.$message.warning(res.message)
          }
        }).finally(() => {
        })

        // this.$confirm({
        //   title: "确认删除",
        //   content: "是否删除选中数据?",
        //   onOk: function () {
        //     deleteAction(that.url.deleteBatch, {ids: ids}).then((res) => {
        //       if (res.success) {
        //         that.$message.success(res.message);
        //         that.loadData();
        //         that.onClearSelected();
        //       } else {
        //         that.$message.warning(res.message);
        //       }
        //     });
        //   }
        // });
      }
    },
    getPcaText(code) {
      return this.pcaData.getText(code)
    },
    initDictConfig() {
    },
    getSuperFieldList() {
      let fieldList = []
      fieldList.push({ type: 'string', value: 'qyname', text: '企业名称', dictCode: '' })
      fieldList.push({ type: 'string', value: 'sourceEnterprise', text: '客户来源', dictCode: '' })
      fieldList.push({ type: 'string', value: 'khProduct', text: '客户所做产品', dictCode: '' })
      fieldList.push({ type: 'string', value: 'khContacts', text: '客户联系人', dictCode: '' })
      fieldList.push({ type: 'string', value: 'khContactInformation', text: '客户联系方式', dictCode: '' })
      fieldList.push({ type: 'pca', value: 'khAddress', text: '客户地址' })
      fieldList.push({ type: 'string', value: 'khType', text: '客户跟进状态', dictCode: '' })
      fieldList.push({ type: 'string', value: 'khYwy', text: '归属业务员', dictCode: 'sys_user,username,username' })
      fieldList.push({ type: 'string', value: 'khYwyPhone', text: '业务员联系方式', dictCode: '' })
      fieldList.push({ type: 'string', value: 'khDitc', text: '所属区域', dictCode: '' })
      // fieldList.push({type:'datetime',value:'khJsckTime',text:'距上次开单时间'})
      fieldList.push({ type: 'string', value: 'remark', text: '备注', dictCode: '' })
      this.superFieldList = fieldList
    }
  }
}
</script>
<style scoped>
@import '~@assets/less/common.less';
</style>