<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
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
          v-hasPermi="['s:social:add']"
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
          v-hasPermi="['s:social:edit']"
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
          v-hasPermi="['s:social:remove']"
        >删除
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="SSocialList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="平台" align="center" prop="platform"/>
      <el-table-column label="客户端id" align="center" prop="clientId"/>
      <el-table-column label="客户端密钥" align="center" prop="clientSecret"/>
      <el-table-column label="应用id" align="center" prop="agentId"/>
      <el-table-column label="回调地址" align="center" prop="redirectUri"/>
      <el-table-column label="accessToken" align="center" prop="accessToken"/>
      <el-table-column label="失效时间" align="center" prop="expire"/>
      <el-table-column label="状态" align="center" prop="status"/>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="创建时间" align="center" prop="createTime"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['s:social:edit']"
          >登录
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['s:social:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['s:social:remove']"
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

        <el-form-item label="平台" prop="platform">
          <el-input v-model="form.platform" placeholder="请输入平台"/>
        </el-form-item>
        <el-form-item label="客户端id" prop="clientId">
          <el-input v-model="form.clientId" placeholder="请输入客户端id"/>
        </el-form-item>
        <el-form-item label="客户端密钥" prop="clientSecret">
          <el-input v-model="form.clientSecret" placeholder="请输入客户端密钥"/>
        </el-form-item>
        <el-form-item label="应用id(企业微信使用)" prop="agentId">
          <el-input v-model="form.agentId" placeholder="请输入应用id(企业微信使用)"/>
        </el-form-item>
        <el-form-item label="回调地址" prop="redirectUri">
          <el-input v-model="form.redirectUri" placeholder="请输入回调地址"/>
        </el-form-item>

        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注"/>
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
  import { list, get, del, add, update } from '@/api/social/SSocial'

  export default {
    name: 'SSocial',
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
        SSocialList: [],
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

          platform: [
            { required: true, message: '平台不能为空', trigger: 'blur' }
          ],
          clientId: [
            { required: true, message: '客户端id不能为空', trigger: 'blur' }
          ],
          clientSecret: [
            { required: true, message: '客户端密钥不能为空', trigger: 'blur' }
          ],
          agentId: [
            { required: true, message: '应用id(企业微信使用)不能为空', trigger: 'blur' }
          ],
          redirectUri: [
            { required: true, message: '回调地址不能为空', trigger: 'blur' }
          ],

          remark: [
            { required: true, message: '备注不能为空', trigger: 'blur' }
          ]

        }
      }
    },
    created() {
      this.getList()
      // this.getDicts("sys_yes_no").then(response => {
      //     this.typeOptions = response.data;
      // });
    },
    methods: {
      /** 查询参数列表 */
      getList() {
        this.loading = true
        list(this.queryParams, this.dateRange).then(response => {
            this.SSocialList = response.data.content
            this.total = response.data.totalElements
            this.loading = false
          }
        )
      },
      // 系统内置字典翻译
      // typeFormat(row, column) {
      //     return this.selectDictLabel(this.typeOptions, row.configType);
      // },
      // 取消按钮
      cancel() {
        this.open = false
        this.reset()
      },
      // 表单重置
      reset() {
        this.form = {
          id: undefined,
          platform: undefined,
          clientId: undefined,
          clientSecret: undefined,
          agentId: undefined,
          redirectUri: undefined,
          createTime: undefined,
          createBy: undefined,
          updateBy: undefined,
          updateTime: undefined,
          userId: undefined,
          status: undefined,
          remark: undefined

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
        this.$confirm('是否确认删除参数编号为"' + configIds + '"的数据项?', '警告', {
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
