<template>
  <div class="app-container">
    <el-form :model="queryParams.params" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户id" prop="userId">
        <el-input
          v-model="queryParams.params.userId"
          placeholder="请输入用户id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.params.projectName"
          placeholder="请输入项目名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
      <el-form-item label="作者" prop="owner">
        <el-input
          v-model="queryParams.params.owner"
          placeholder="请输入作者"
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['info:gitSynData:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['info:gitSynData:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['info:gitSynData:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['info:gitSynData:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="gitSynDataList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="gitId" align="center" prop="id"/>
      <el-table-column label="用户" align="center" prop="userName" :formatter="userNameFormat"/>
      <el-table-column label="项目名称" align="center" prop="projectName"/>
      <el-table-column label="git地址" align="center" prop="gitUrl">
        <template slot-scope="scope">
          <el-link :href="scope.row.gitUrl" type="primary" target="_blank">{{ scope.row.gitUrl }}</el-link>
        </template>
      </el-table-column>
      <el-table-column label="分支名" align="center" prop="branch"/>
      <el-table-column label="基础路径" align="center" prop="basePath"/>
      <el-table-column label="忽略文件" align="center" prop="ignoreFile"/>
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat"/>
      <el-table-column label="来源" align="center" prop="source" :formatter="sourceFormat"/>
      <el-table-column label="介绍" align="center" prop="description" :show-overflow-tooltip="true"/>
      <el-table-column label="作者" align="center" prop="owner"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-if="scope.row.status != 100"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="updateStatus(scope.row,100)"
            v-hasPermi="['info:gitSynData:edit']"
          >上架
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="updateStatus(scope.row,103)"
            v-hasPermi="['info:gitSynData:edit']"
          >废弃
          </el-button>
          <el-button v-if="scope.row.status==100"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="syn(scope.row)"
            v-hasPermi="['info:gitSynData:edit']"
          >同步
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['info:gitSynData:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['info:gitSynData:remove']"
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

    <!-- 添加或修改git同步信息配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称"/>
        </el-form-item>
        <el-form-item label="git地址" prop="gitUrl">
          <el-input v-model="form.gitUrl" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="分支名" prop="branch">
          <el-input v-model="form.branch" placeholder="请输入分支名" />
        </el-form-item>
        <el-form-item label="基础路径" prop="basePath">
          <el-input v-model="form.basePath" placeholder="请输入基础路径"/>
        </el-form-item>
        <el-form-item label="忽略文件" prop="ignoreFile">
          <el-input v-model="form.ignoreFile" type="textarea" placeholder="请输入内容"/>
        </el-form-item>



        <el-form-item label="介绍" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容"/>
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
  listGitSynData,
  getGitSynData,
  delGitSynData,
  addGitSynData,
  updateGitSynData,
  exportGitSynData,
  updateStatus,
  syn
} from "@/api/info/gitSynData";
import {updateStatusArticle} from "@/api/info/article";

export default {
  name: "GitSynData",
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
      // git同步信息配置表格数据
      gitSynDataList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,

        params: {
          userId: null,
          projectName: null,
          gitUrl: null,
          branch: null,
          basePath: null,
          ignoreFile: null,
          status: null,
          source: null,
          description: null,
          owner: null,
          gitId: null
        }
      },
      // 表单参数
      form: {
        branch:"master"
      },
      // 表单校验
      rules: {},
      // 执行状态字典
      statusOptions: [],
      //来源
      sourceOptions: []
    };
  },
  created() {
    this.getList();
    this.getDicts('GIT_SYN_DATA_STATUS').then(response => {
      this.statusOptions = response.data
    })
    this.getDicts('GIT_SYN_DATA_SOURCE').then(response => {
      this.sourceOptions = response.data
    })
  },
  methods: {
    /** 查询git同步信息配置列表 */
    getList() {
      this.loading = true;
      listGitSynData(this.queryParams).then(response => {
        this.gitSynDataList = response.data.content;
        this.total = response.data.totalElements;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        userId: null,
        projectName: null,
        gitUrl: null,
        branch: null,
        basePath: null,
        ignoreFile: null,
        status: null,
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        source: null,
        description: null,
        owner: null,
        gitId: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加git同步信息配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getGitSynData(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改git同步信息配置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateGitSynData(this.form).then(response => {
              if (response.status === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addGitSynData(this.form).then(response => {
              if (response.status === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除git同步信息配置编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delGitSynData(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有git同步信息配置数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportGitSynData(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () {
      });
    },// 执行状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status)
    }
    ,
// 来源
    sourceFormat(row, column) {
      return this.selectDictLabel(this.sourceOptions, row.source)
    },
    // 修改状态
    updateStatus(row,status) {
      const ids = row.id;
      let msg = "";
      if (status == 100) {
        msg = "上架";
      } else if (status == 101) {
        msg = "下架";
      }else if (status == 103){
        msg = "废弃"
      }else {
        msg  = "未知";
      }

      this.$confirm('是否确认'+msg+'编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "success"
      }).then(function () {
        return updateStatus(ids, status);
      }).then(() => {
        this.getList();
        this.msgSuccess("修改成功");
      }).catch(function () {
      });
    },
    //同步
    syn(row) {
      const ids =row.id;

      this.$confirm('是否确认同步编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "success"
      }).then(function () {

        return syn(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("修改成功");
      }).catch(function (err) {

        alert(err)
      });
    },
    userNameFormat(row, column){
      let  userName= row.userName;
      if (userName != null && userName.length > 0 ){
        return  userName;
      }else {
        return  '未设置用户';
      }
    }

  }
};
</script>
