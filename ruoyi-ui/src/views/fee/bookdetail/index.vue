<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="支出类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择支出类型" clearable size="small">
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="支出时间" prop="date">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.date"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择支出时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="支出人姓名" prop="name">
        <el-select v-model="queryParams.name" placeholder="请选择支出人姓名" clearable size="small">
          <el-option
            v-for="dict in nameOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
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
          v-hasPermi="['fee:bookdetail:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['fee:bookdetail:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['fee:bookdetail:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['fee:bookdetail:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bookdetailList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" type="index" />
      <el-table-column label="序号" align="center" prop="id" v-if="false" />
      <el-table-column label="支出类型" align="center" prop="type" />
      <el-table-column label="支出金额" align="center" prop="fee" />
      <el-table-column label="支出时间" align="center" prop="date" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.date, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="支出人姓名" align="center" prop="name" />
      <el-table-column label="物品描述" align="center" prop="description" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['fee:bookdetail:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['fee:bookdetail:remove']"
          >删除</el-button>
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

    <!-- 添加或修改支出统计对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="支出类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择支出类型">
            <el-option
              v-for="dict in typeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="支出金额" prop="fee">
          <el-input v-model="form.fee" placeholder="请输入支出金额" />
        </el-form-item>
        <el-form-item label="支出时间" prop="date">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.date"
            type="date"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择支出时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="支出人姓名" prop="name">
          <el-select v-model="form.name" placeholder="请选择支出人姓名">
            <el-option
              v-for="dict in nameOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="物品描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入物品描述" />
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
import { listBookdetail, getBookdetail, delBookdetail, addBookdetail, updateBookdetail, exportBookdetail } from "@/api/fee/bookdetail";

export default {
  name: "Bookdetail",
  components: {
  },
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
      // 支出统计表格数据
      bookdetailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 支出类型字典
      typeOptions: [],
      // 支出人姓名字典
      nameOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: null,
        date: null,
        name: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        type: [
          { required: true, message: "支出类型不能为空", trigger: "change" }
        ],
        fee: [
          { required: true, message: "支出金额不能为空", trigger: "blur" }
        ],
        date: [
          { required: true, message: "支出时间不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "支出人姓名不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("consume_type").then(response => {
      this.typeOptions = response.data;
    });
    this.getDicts("consume_name").then(response => {
      this.nameOptions = response.data;
    });
  },
  methods: {
    /** 查询支出统计列表 */
    getList() {
      this.loading = true;
      listBookdetail(this.queryParams).then(response => {
        this.bookdetailList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 支出类型字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    // 支出人姓名字典翻译
    nameFormat(row, column) {
      return this.selectDictLabel(this.nameOptions, row.name);
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
        deptId: null,
        type: null,
        fee: null,
        date: null,
        name: null,
        description: null
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加支出统计";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getBookdetail(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改支出统计";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateBookdetail(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBookdetail(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除支出统计编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delBookdetail(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有支出统计数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportBookdetail(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
