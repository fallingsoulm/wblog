<template>
  <div class="app-container">
    <el-form :model="queryParams.params" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="专辑名称" prop="title">
        <el-input
          v-model="queryParams.params.title"
          placeholder="请输入专辑标题"
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
          v-hasPermi="['info:album:add']"
        >新增
        </el-button>
      </el-col>

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

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="albumList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="id" align="center" prop="id"/>
      <el-table-column label="用户" align="center" prop="userName"/>
      <el-table-column label="专辑标题" align="center" :show-overflow-tooltip="true" prop="title"/>
      <el-table-column label="专辑介绍" align="center" :show-overflow-tooltip="true" prop="desp"/>
      <el-table-column label="文章" align="center" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <router-link :to="'/album/bind/article/' + scope.row.id" class="link-type">
            <span>{{ '文章[' + scope.row.articleNum + ']' }}</span>
          </router-link>
        </template>

      </el-table-column>
      <!--      <el-table-column label="专辑封面图片" align="center" prop="image"/>-->
      <el-table-column label="专辑封面图片" align="center">
        <template slot-scope="scope">
          <el-image
            style="width: 50px; height: 50px"
            :src="scope.row.image"
            fit="scale-down"></el-image>
        </template>
      </el-table-column>

      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-if="scope.row.status != 100"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['info:album:edit']"
          >修改
          </el-button>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdateStatus(scope.row)"
            v-hasPermi="['info:album:edit']"
          >
            <span v-text="scope.row.status == 100 ? '下架': '上架'"></span>
          </el-button>
          <el-button v-if="scope.row.status != 100"
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

    <!-- 添加或修改文章专辑对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="专辑标题" prop="title">
          <el-input v-model="form.title" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="专辑介绍" prop="desp">
          <el-input v-model="form.desp" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="专辑封面" prop="image">
          <!--          <el-input v-model="form.image" placeholder="请输入专辑封面图片" />-->
          <!--          <el-form-item label="图标地址" prop="icon">-->
          <el-upload
            ref="rebateUpload"
            :multiple="false"
            class="upload-demo"
            :action="uploadAction"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :on-success="handleSuccess"
            :on-change="handleChange"
            :file-list="fileList"
            :auto-upload="true"
            list-type="picture">
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
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
import {listAlbum, getAlbum, delAlbum, addAlbum, updateAlbum, exportAlbum} from "@/api/info/album";

export default {
  name: "Album",
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
      // 文章专辑表格数据
      albumList: [],
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
          title: null,
          desp: null,
          image: null,
        }

      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      statusOptions: [],
      //文件上传相关
      uploadAction: process.env.VUE_APP_BASE_API + '/uaa/api/v1/file/upload/102',
      fileList: []
    };
  },
  created() {
    this.getList();
    this.getDicts('ALBUM_STATUS').then(response => {
      this.statusOptions = response.data
    })
  },
  methods: {
    /** 查询文章专辑列表 */
    getList() {
      this.loading = true;
      listAlbum(this.queryParams).then(response => {
        this.albumList = response.data.content
        this.total = response.data.totalElements
        this.loading = false
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
        title: null,
        desp: null,
        image: null,
        createTime: null,
        updateTime: null,


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
      this.title = "添加文章专辑";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAlbum(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改文章专辑";
        if (null != response.data.image) {
          this.fileList = [
            {
              name: 'icon.jpg',
              url: response.data.image
            }
          ]
        }
      });
    },
    /** 提交按钮 */
    submitForm() {
      // 手动上传
      // this.$refs.rebateUpload.submit();

      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAlbum(this.form).then(response => {
              if (response.status === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addAlbum(this.form).then(response => {
              if (response.status === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
      this.fileList = []
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除文章专辑编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delAlbum(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有文章专辑数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportAlbum(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () {
      });
    },// 执行状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status)
    },
    // 文件上传后的钩子函数
    handleSuccess(res, file, fileList) {
      alert("22")
      this.form.image = res.data
      console.log(res.data)


    },
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePreview(file) {
      console.log(file)
    },
    handleChange(file, fileList) {
      if (fileList.length > 0) {
        this.fileList = [fileList[fileList.length - 1]]  // 这一步，是 展示最后一次选择的csv文件
      }
    },
    /** 修改按钮操作 */
    handleUpdateStatus(row) {
      const id = row.id
      let status = row.status;
      if (status == 100) {
        status = 101;
      } else if (status == 101) {
        status = 100;
      }
      let params = {
        id: id,
        status: status

      }
      updateAlbum(params).then(response => {
        if (response.status === 200) {
          this.msgSuccess("修改成功");
          this.open = false;
          this.getList();
        }
      });
    },
  }
};
</script>
