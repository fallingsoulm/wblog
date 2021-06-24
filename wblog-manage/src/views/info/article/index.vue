<template>
  <div class="app-container">
    <el-form :model="queryParams.params" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户名称" prop="userName">
        <el-input
          v-model="queryParams.params.userName"
          placeholder="请输入用户名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.params.title"
          placeholder="请输入文章标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类" prop="classifyName">
        <!--        <el-input-->
        <!--          v-model="queryParams.params.classifyName"-->
        <!--          placeholder="请输入分类"-->
        <!--          clearable-->
        <!--          size="small"-->
        <!--          @keyup.enter.native="handleQuery"-->
        <!--        />-->
        <el-select
          v-model="queryParams.params.classifyId"
          filterable
          remote
          clearable
          reserve-keyword
          placeholder="请输入分类"
          :remote-method="remoteMethod"
          :loading="loading">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>

        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.params.status" placeholder="请选择状态" clearable size="small">
          <el-option v-for="dict in statusOptions" :key="dict.dictValue" :label="dict.dictLabel"
                     :value="dict.dictValue"/>
        </el-select>
      </el-form-item>
      <el-form-item label="来源" prop="source">
        <el-select v-model="queryParams.params.source" placeholder="请选择来源" clearable size="small">
          <el-option v-for="dict in sourceOptions" :key="dict.dictValue" :label="dict.dictLabel"
                     :value="dict.dictValue"/>
        </el-select>
      </el-form-item>
      <el-form-item label="gitId" prop="gitId">
        <el-input
          v-model="queryParams.params.gitId"
          placeholder="请输入gitId"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="primary"-->
      <!--          icon="el-icon-plus"-->
      <!--          size="mini"-->
      <!--          @click="handleAdd"-->
      <!--          v-hasPermi="['info:article:add']"-->
      <!--        >新增-->
      <!--        </el-button>-->
      <!--      </el-col>-->
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="success"-->
      <!--          icon="el-icon-edit"-->
      <!--          size="mini"-->
      <!--          :disabled="single"-->
      <!--          @click="handleUpdate"-->
      <!--          v-hasPermi="['info:article:edit']"-->
      <!--        >修改-->
      <!--        </el-button>-->
      <!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="multiple"
          @click="updateStatusBatch(100)"
          v-hasPermi="['info:article:edit']"
        >全部上架
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="multiple"
          @click="updateStatusBatch(101)"
          v-hasPermi="['info:article:edit']"
        >全部下架
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="multiple"
          @click="updateStatusBatch(103)"
          v-hasPermi="['info:article:edit']"
        >全部废弃
        </el-button>
        <el-col :span="1.5">
          <el-button
            type="success"
            icon="el-icon-edit"
            size="mini"
            :disabled="multiple"
            @click="rename()"
            v-hasPermi="['info:article:edit']"
          >全部重命名
          </el-button>
        </el-col>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['info:article:remove']"
        >删除
        </el-button>
      </el-col>
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="warning"-->
      <!--          icon="el-icon-download"-->
      <!--          size="mini"-->
      <!--          @click="handleExport"-->
      <!--          v-hasPermi="['info:article:export']"-->
      <!--        >导出-->
      <!--        </el-button>-->
      <!--      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="articleList" @selection-change="handleSelectionChange"
              :row-class-name="tableRowClassName">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="id" align="center" prop="id"/>
      <el-table-column label="标题" align="center" prop="title"/>
      <el-table-column label="介绍" align="center" prop="introduction" :show-overflow-tooltip="true"/>

      <el-table-column label="头图" align="center">
        <template slot-scope="scope">
          <el-image
            style="width: 50px; height: 50px"
            :src="scope.row.image"
            fit="scale-down"></el-image>
        </template>
      </el-table-column>

      <el-table-column label="用户" align="center" prop="userName"/>
      <el-table-column label="审核标签" align="center" prop="reviewLabel"/>
      <el-table-column label="内容" align="center" prop="content">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="watchContent(scope.row)">查看内容
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="访问量" align="center" prop="view"/>
      <el-table-column label="分类" align="center" prop="classifyName" :show-overflow-tooltip="true"/>
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat"/>
      <el-table-column label="来源" align="center" prop="source" :formatter="sourceFormat"/>
      <!--      <el-table-column label="gitId" align="center" prop="gitId"/>-->
      <el-table-column label="创建时间" align="center" prop="createTime"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="rename(scope.row)"
            v-hasPermi="['info:article:edit']"
          >重命名
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="updateStatusBatch(103,scope.row)"
            v-hasPermi="['info:article:edit']"
          >废弃
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-info"
            @click="info(scope.row)"
            v-hasPermi="['info:article:edit']"
          >查看
          </el-button>


          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['info:article:edit']"
          >修改
          </el-button>


          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['info:article:remove']"
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

    <!-- 添加或修改文章对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="介绍" prop="introduction">
          <el-input v-model="form.introduction" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="头图" prop="image">
          <el-input v-model="form.image" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id"/>
        </el-form-item>
        <el-form-item label="访问量" prop="view">
          <el-input v-model="form.view" placeholder="请输入访问量"/>
        </el-form-item>
        <el-form-item label="分类id" prop="classifyId">
          <el-input v-model="form.classifyId" placeholder="请输入分类id"/>
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="删除标识" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标识"/>
        </el-form-item>
        <el-form-item label="来源 原创/转载/翻译" prop="source">
          <el-input v-model="form.source" placeholder="请输入来源 原创/转载/翻译"/>
        </el-form-item>
        <el-form-item label="gitId" prop="gitId">
          <el-input v-model="form.gitId" placeholder="请输入gitId"/>
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
import {
  listArticle,
  getArticle,
  delArticle,
  addArticle,
  updateArticle,
  exportArticle,
  updateStatusArticle,
  rename
} from '@/api/info/article'

import {
  listAll
} from '@/api/info/classify'

export default {
  name: 'Article',
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
      // 文章表格数据
      articleList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        params: {
          title: null,
          userName: null,
          classifyId: null,
          status: null
        }

      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      // 执行状态字典
      statusOptions: [],
      //来源
      sourceOptions: [],
      options: [],
      //分类
      classifys: []
    }
  },

  created() {
    this.getList()
    this.getDicts('ARTICLE_STATUS').then(response => {
      this.statusOptions = response.data
    })
    this.getDicts('GIT_SYN_DATA_SOURCE').then(response => {
      this.sourceOptions = response.data
    })
    // 加载所有的分类
    this.classifyAll()

  },
  methods: {

    classifyAll() {
      listAll().then(response => {
        this.classifys = response.data
      })
    },
    remoteMethod(query) {
      if (query !== '') {
        this.loading = true
        setTimeout(() => {
          this.loading = false
          this.options = this.classifys.filter(item => {
            return item.label.toLowerCase()
              .indexOf(query.toLowerCase()) > -1
          })
        }, 200)
      } else {
        this.options = []
      }
    }
    ,
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex % 2 == 1) {
        return 'warning-row'
      } else {
        return 'success-row'
      }
      return ''
    },
    watchContent(row) {
      const content = row.content
      this.$alert(content, 'HTML 片段', {
        dangerouslyUseHTMLString: true
      })
    },
    /** 查询文章列表 */
    getList() {
      this.loading = true

      listArticle(this.queryParams).then(response => {
        this.articleList = response.data.content
        this.total = response.data.totalElements
        this.loading = false
      })
    }
    ,
// 内容的展示
    contentShow(row, column) {

      return '点击查看内容'

    }
    ,
// 执行状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status)
    }
    ,
// 来源
    sourceFormat(row, column) {
      return this.selectDictLabel(this.sourceOptions, row.source)
    }
    ,
// 取消按钮
    cancel() {
      this.open = false
      this.reset()
    }
    ,
// 表单重置
    reset() {

      this.form = {
        id: null,
        title: null,
        introduction: null,
        image: null,
        userId: null,
        view: null,
        classifyId: null,
        status: 0,
        delFlag: null,
        createTime: null,
        createBy: null,
        source: null,
        gitId: null,
        classifyName: null
      }
      this.options = []
      this.resetForm('form')
    }
    ,
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1

      this.getList()
    }
    ,
    /** 重置按钮操作 */
    resetQuery() {
      this.queryParams.params.classifyId = null

      this.resetForm('queryForm')
      this.handleQuery()
    }
    ,
// 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    }
    ,
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加文章'
    }
    ,
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getArticle(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改文章'
      })
    }
    ,
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateArticle(this.form).then(response => {
              if (response.status === 200) {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              }
            })
          } else {
            addArticle(this.form).then(response => {
              if (response.status === 200) {
                this.msgSuccess('新增成功')
                this.open = false
                this.getList()
              }
            })
          }
        }
      })
    }
    ,
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$confirm('是否确认删除文章编号为"' + ids + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return delArticle(ids)
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      }).catch(function() {
      })
    }
    ,
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams
      this.$confirm('是否确认导出所有文章数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return exportArticle(queryParams)
      }).then(response => {
        this.download(response.msg)
      }).catch(function() {
      })
    },

    // 全部上下架
    updateStatusBatch(status, row) {
      console.log(status)
      const ids = row == null ? this.ids : row.id
      let msg = ''
      if (status == 100) {
        msg = '上架'
      } else if (status == 101) {
        msg = '下架'
      } else if (status == 103) {
        msg = '废弃'
      } else {
        msg = '未知'
      }

      this.$confirm('是否确认' + msg + '文章编号为"' + ids + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'success'
      }).then(function() {
        return updateStatusArticle(ids, status)
      }).then(() => {
        this.getList()
        this.msgSuccess('修改成功')
      }).catch(function() {
      })
    },
    // 查看详情
    info(row) {

      const url = row.url
      window.open(url)

    },
    // 重命名
    rename(row) {
      console.log(row)
      const ids = row == null ? this.ids : row.id
      this.$confirm('是否确认重命名文章编号为"' + ids + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'success'
      }).then(function() {
        return rename(ids)
      }).then(() => {
        this.getList()
        this.msgSuccess('重命名成功')
      }).catch(function() {
      })
    },
    //废弃
    discard() {

    }
  }
}

</script>
<style>
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}

</style>
