<template>
  <div class="app-container">


    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['info:album:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="toNoBindArticle"
          v-hasPermi="['info:album:add']"
        >添加
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="articleList" stripe @selection-change="handleSelectionChange"
              highlight-current-row @row-click="handleCurrentChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="id" align="center" prop="id"/>
      <el-table-column label="文章id" align="center" prop="articleVo.id"/>
      <el-table-column label="标题" align="center" prop="articleVo.title"/>
      <el-table-column label="介绍" align="center" prop="articleVo.introduction" :show-overflow-tooltip="true"/>

      <el-table-column label="头图" align="center">
        <template slot-scope="scope">
          <el-image
            style="width: 50px; height: 50px"
            :src="scope.row.articleVo.image"
            fit="scale-down"></el-image>
        </template>
      </el-table-column>

      <el-table-column label="分类" align="center" prop="articleVo.classifyName" :show-overflow-tooltip="true"/>
      <el-table-column label="状态" align="center" prop="articleVo.status" :formatter="statusFormat"/>
      <el-table-column label="来源" align="center" prop="articleVo.source" :formatter="sourceFormat"/>
      <!--      <el-table-column label="gitId" align="center" prop="gitId"/>-->
      <el-table-column label="创建时间" align="center" prop="articleVo.createTime"/>
      <el-table-column label="顺序" align="center" prop="orderNum">
        <template scope="scope">

          <el-input size="small" onkeyup="value=value.replace(/[^\d]/g,'')" v-model="scope.row.orderNum"
                    placeholder="请输入排序"
                    @change="handleEdit(scope.$index, scope.row)"></el-input>
          <span>{{ scope.row.address }}</span>

        </template>

      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['info:album:remove']"
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


  </div>
</template>

<script>
import {bindArticle, delBindArticle, updateBindSort} from "@/api/info/album";
import {listAll} from "@/api/info/classify";
import {delArticle} from "@/api/info/article";

export default {
  name: "bindArticle",
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


      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,

        params: {
          albumId: undefined,
          dictType: undefined,
          status: undefined
        }
      },
      articleList: [],
      //分类
      classifys: [],
      // 执行状态字典
      statusOptions: [],
      //来源
      sourceOptions: [],


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
      bindArticle(this.queryParams).then(response => {
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
      return this.selectDictLabel(this.statusOptions, row.articleVo.status)
    }
    ,
// 来源
    sourceFormat(row, column) {
      return this.selectDictLabel(this.sourceOptions, row.articleVo.source)
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
    }, /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$confirm('是否确认删除文章编号为"' + ids + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function () {
        return delBindArticle(ids)
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      }).catch(function () {
      })
    },
    handleCurrentChange(row, event, column) {
      // console.log(row, event, column, event.currentTarget)
    },
    handleEdit(index, row) {
      updateBindSort(row.id, row.orderNum).then(response => {
        // this.articleList = response.data.content
        // this.total = response.data.totalElements
        // this.loading = false
        this.msgSuccess('顺序修改成功')
        this.getList();
      })

    },
    // 跳转到未绑定的页面
    toNoBindArticle() {
      this.$router.push('/album/no/bind/article/' + this.queryParams.params.albumId); //跳转到指定页面
    }
  }

}
</script>

