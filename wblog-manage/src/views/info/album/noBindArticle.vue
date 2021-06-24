<template>
  <div class="app-container">
    <el-form :model="queryParams.params.articleVo" ref="queryForm" :inline="true" v-show="showSearch"
             label-width="68px">

      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.params.articleVo.title"
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
            v-for="item in classifys"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>

        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.params.articleVo.status" placeholder="请选择状态" clearable size="small">
          <el-option v-for="dict in statusOptions" :key="dict.dictValue" :label="dict.dictLabel"
                     :value="dict.dictValue"/>
        </el-select>
      </el-form-item>
      <el-form-item label="来源" prop="source">
        <el-select v-model="queryParams.params.articleVo.source" placeholder="请选择来源" clearable size="small">
          <el-option v-for="dict in sourceOptions" :key="dict.dictValue" :label="dict.dictLabel"
                     :value="dict.dictValue"/>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button
          type="primary"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="bindHandler"
          v-hasPermi="['info:album:remove']"
        >添加
        </el-button>
      </el-form-item>
    </el-form>


    <el-table v-loading="loading" :data="articleList" stripe @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="文章id" align="center" prop="id"/>
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
            icon="el-icon-plus"
            @click="bindHandler(scope.row)"
            v-hasPermi="['info:album:edit']"
          >添加
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


  </div>
</template>

<script>
import { addAlbum, doBind, noBindArticle, updateBindSort } from '@/api/info/album'
import { listAll } from '@/api/info/classify'

export default {
  name: 'noBindArticle',
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

      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      options: [],

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,

        params: {
          albumId: undefined,
          articleVo: {
            title: null,
            userName: null,
            classifyId: null,
            status: null,
            source: null
          }
        }
      },
      articleList: [],
      //分类
      classifys: [],
      // 执行状态字典
      statusOptions: [],
      //来源
      sourceOptions: []

    }
  }, created() {
    this.queryParams.params.albumId = this.$route.params && this.$route.params.id
    this.getList()
    this.getDicts('ARTICLE_STATUS').then(response => {
      this.statusOptions = response.data
    })
    this.getDicts('GIT_SYN_DATA_SOURCE').then(response => {
      this.sourceOptions = response.data
    })
    // 加载所有的分类
    this.classifyAll()
  }, methods: {

    /** 查询列表 */
    getList() {
      this.loading = true
      noBindArticle(this.queryParams).then(response => {
        this.articleList = response.data.content
        this.total = response.data.totalElements
        this.loading = false
      })
    }, remoteMethod(query) {
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
    }, classifyAll() {
      listAll().then(response => {
        this.classifys = response.data
      })
    },
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
    },// 执行状态字典翻译
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

        delFlag: null,
        createTime: null,
        createBy: null,
        source: null,
        gitId: null,
        classifyName: null
      }
      this.options = []
      this.resetForm('form')
    }, /** 绑定操作 */

    bindHandler(row) {
      const ids = row.id || this.ids
      doBind(this.queryParams.params.albumId,ids).then(response => {
        if (response.status === 200) {
          this.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        }
      });
    }

  }

}
</script>

<style scoped>

</style>
