<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['m:notice:template:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['m:notice:template:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['m:notice:template:remove']"
        >删除
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="MNoticeTemplateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <!--      <el-table-column label="id" align="center" prop="id"/>-->
      <el-table-column label="token" align="center" prop="token"/>
      <el-table-column label="消息名称" align="center" prop="name"/>
      <el-table-column label="通知的消息的类型" align="center" prop="noticeType" :formatter="noticeTypeFormatter"/>

      <el-table-column label="创建时间" align="center" prop="createTime"/>

      <el-table-column label="发送类型" align="center" prop="sendType" :formatter="sendTypeFormatter"/>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.status"
            active-value="0"
            inactive-value="1"
            @change="handleStatusChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="发送时间" align="center" prop="sendTime"/>
      <el-table-column label="调用对象" align="center" prop="invokeTarget" :show-overflow-tooltip="true"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['m:notice:template:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['m:notice:template:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">


        <el-form-item label="消息名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入消息名称"/>
        </el-form-item>
        <el-form-item label="通知的消息的类型" prop="noticeType">
          <el-select v-model="form.noticeType" placeholder="请选择">
            <el-option
              v-for="dict in noticeTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
          <!--          <el-input v-model="form.noticeType" placeholder="请输入通知的消息的类型"/>-->
        </el-form-item>
        <el-form-item label="调用对象" prop="invokeTarget">
          <el-input v-model="form.invokeTarget" placeholder="请输入调用对象"/>
        </el-form-item>

        <el-form-item label="发送类型" prop="sendType">
          <!--          <el-input v-model="form.sendType" placeholder="请输入发送类型"/>-->
          <el-select v-model="form.sendType" placeholder="请选择">
            <el-option
              v-for="dict in sendTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="发送时间" prop="sendTime" v-if="form.sendType != 0">
          <!--          <el-input v-model="form.sendTime" placeholder="请输入发送时间"/>-->
          <el-time-picker v-model="form.sendTime" format="HH:mm" value-format="HH:mm"
                          :picker-options='{"selectableRange":"00:00:00-23:59:59"}' :style="{width: '100%'}"
                          placeholder="请选择时间选择" clearable
          ></el-time-picker>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { list, get, del, add, update, changeStatus } from '@/api/notice/MNoticeTemplate'
import { changeUserStatus } from '@/api/system/user'

export default {
  name: 'MNoticeTemplate',
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 参数表格数据
      MNoticeTemplateList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 类型数据字典
      typeOptions: [],
      // 日期范围
      dateRange: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {

        name: [
          { required: true, message: '消息名称不能为空', trigger: 'blur' }
        ],
        noticeType: [
          { required: true, message: '通知的消息的类型不能为空', trigger: 'blur' }
        ],
        invokeTarget: [
          { required: true, message: '调用对象不能为空', trigger: 'blur' }
        ],

        sendType: [
          { required: true, message: '发送类型', trigger: 'blur' }
        ],
        sendTime: [
          { required: true, message: '发送时间不能为空', trigger: 'blur' }
        ]

      },
      // 状态
      statusOptions: [],
      // 消息通知类型
      noticeTypeOptions: [],
      // 发送类型
      sendTypeOptions: []
    }
  },
  created() {
    this.getList()
    this.getDicts('NOTICE_STATUS').then(response => {
      this.statusOptions = response.data
    })
    this.getDicts('NOTICE_MESSAGE').then(response => {
      this.noticeTypeOptions = response.data
    })
    this.getDicts('NOTICE_SEND_TYPE').then(response => {
      this.sendTypeOptions = response.data
    })
  },
  methods: {
    /** 查询参数列表 */
    getList() {
      this.loading = true
      list(this.queryParams).then(response => {
          this.MNoticeTemplateList = response.data.content
          this.total = response.data.totalElements
          this.loading = false
        }
      )
    },
    // 系统内置字典翻译
    // typeFormat(row, column) {
    //     return this.selectDictLabel(this.typeOptions, row.configType);
    // },
    // 参数系统内置字典翻译
    noticeTypeFormatter(row, column) {
      return this.selectDictLabel(this.noticeTypeOptions, row.noticeType)
    },
    sendTypeFormatter(row, column) {
      return this.selectDictLabel(this.sendTypeOptions, row.sendType)
    },

    // 状态修改
    handleStatusChange(row) {

      let text = row.status === 0 ? '启用' : '停用'


      this.$confirm('确认要"' + text + '""' + row.id + '"吗?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return changeStatus(row.id,row.status)
      }).then(() => {
        this.msgSuccess(text + '成功')
      }).catch(function() {
        row.status = row.status === '100' ? '100' : '101'
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        token: undefined,
        name: undefined,
        noticeType: undefined,
        invokeTarget: undefined,
        createTime: undefined,
        createBy: undefined,
        updateTime: undefined,
        updateBy: undefined,
        sendType: undefined,
        sendTime: undefined

      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = []
      this.resetForm('queryForm')
      this.handleQuery()
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加参数'
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      get(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改参数'
      })
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            update(this.form).then(response => {
              this.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            add(this.form).then(response => {
              this.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$confirm('是否确认删除参数编号为"' + ids + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return del(ids)
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      })
    }
  }
}
</script>
