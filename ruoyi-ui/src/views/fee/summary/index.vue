<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="人员" prop="consumer">
        <el-select v-model="queryParams.consumer" placeholder="请选择人员" clearable size="small">
          <el-option
            v-for="dict in consumerOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="支出时间" prop="consumeDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.consumeDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择支出时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['fee:summary:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="summaryList">
      <el-table-column label="序号" width="80" align="center" type="index" />
      <el-table-column label="人员" align="center" prop="consumer" />
      <el-table-column label="支出总金额" align="center" prop="sum" />
      <el-table-column label="平均支出金额" align="center" prop="average" />
      <el-table-column label="结算金额" align="center" prop="account" />
      <el-table-column label="支出时间" align="center" prop="consumeDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.consumeDate, '{y}-{m}') }}</span>
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
import { listSummary, exportSummary } from "@/api/fee/summary";

export default {
  name: "Summary",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 结算清单表格数据
      summaryList: [],
      // 人员字典
      consumerOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        consumer: null,
        consumeDate: null
      },
      // 表单参数
      form: {},
    };
  },
  created() {
    this.getList();
    this.getDicts("consume_name").then(response => {
      this.consumerOptions = response.data;
    });
  },
  methods: {
    /** 查询结算清单列表 */
    getList() {
      this.loading = true;
      listSummary(this.queryParams).then(response => {
        this.summaryList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有结算清单数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportSummary(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
