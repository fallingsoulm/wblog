<template>
	<view>
		<u-search placeholder="搜索..." v-model="keyword" @search="toSearch" @clear="searchClear()" height="120"></u-search>

		<u-tabs :list="tabs" :is-scroll="false" :current="current" @change="change"></u-tabs>
		<view style="padding-top: 20upx;">
			<u-card class="item u-border-bottom" v-for="article in articles" :key="article.id">
				<view class="" slot="body" @click="toArticleInfo(article.id)">
					<view class="top">
						<view class="left">
							<u-icon name="list" :size="30" color="rgb(94,94,94)"></u-icon>
							<view class="store">{{ article.title }}</view>
						</view>
					</view>

					<view class="u-body-item u-flex u-border-bottom u-col-between u-p-t-0">
						<view class="u-body-item-title u-line-2" v-text="article.introduction">瓶身描绘的牡丹一如你初妆，冉冉檀香透过窗心事我了然，宣纸上走笔至此搁一半</view>
						<image :src="article.image"></image>
					</view>
					<view>
						<!-- 标签;-->

						<u-tag
							v-for="(tag, index) in article.labelVos"
							v-if="index < 3"
							:text="tag.name"
							mode="light"
							shape="circle"
							:key="tag.id"
							type="warning"
							style="margin-left: 5px;"
						/>
						<u-tag text="......" mode="light" shape="circle" type="warning" style="margin-left: 5px;" />
					</view>
				</view>

				<view class="" slot="foot">
					<u-icon name="calendar" size="34" color="" :label="`发布时间   :` + article.createTime"></u-icon>

					<u-icon name="eye" size="34" color="" :label="article.view + '人观看'" style="margin-left: 20upx;"></u-icon>
					<u-icon name="info-circle" size="34" color="" :label="article.userName + ' 发表'" style="margin-left: 20upx;"></u-icon>
				</view>
			</u-card>
		</view>
		<u-loadmore :status="status" :load-text="loadText" bgColor="#f2f2f2"></u-loadmore>
	</view>
</template>

<script>
export default {
	data() {
		return {
			loadText: {
				loadmore: '轻轻上拉',
				loading: '努力加载中',
				nomore: '我也是有底线的'
			},
			tabs: [
				{
					name: '最新'
				},
				{
					name: '最热'
				}
			],
			status: 'loadmore',
			list: 5,
			page: 1,
			current: 0,

			articles: [],
			keyword: ''
		};
	},
	onLoad() {
		this.getArticleDataList();
	},

	computed: {},
	onReachBottom() {
		// if (this.page >= 3) return;
		this.status = 'loading';
		this.page = ++this.page;
		this.getArticleDataList();
		// setTimeout(() => {
		// 	// this.list += 10;
		// 	this.articles.push(this.aa);
		// 	if (this.page >= 3) this.status = 'nomore';
		// 	else this.status = 'loading';
		// }, 2000);
	},
	methods: {
		change(index) {
			this.current = index;
			this.page = 1;
			this.articles = [];
			this.keyword = '';
			this.getArticleDataList();
		},
		toSearch(value) {
			// 跳转搜索结果页面
			// alert('111:' + value);
			this.page = 1;
			this.articles = [];
			this.keyword = value;
			this.getArticleDataList();
		},
		searchClear() {
			this.keyword = '';
			this.getArticleDataList();
			this.articles = [];
			this.page = 1;
		},
		toArticleInfo(id) {
			let pa = {
				id: id
			};
			this.$u.route({
				type: 'to',
				params: pa,
				url: '/pages/article/articleInfo',
				animationType: this.animate
			});
		},
		// 获取文章数据
		getArticleDataList() {
			let param = {
				pageSize: this.list,
				pageIndex: this.page,
				params: {
					orderBy: this.current == 0 ? 100 : 101,
					introduction: this.keyword,
					"status":100
				}
			};

			this.$u.post('business/api/v1/info/article/find/page', param).then(res => {
				// this.result = res;
				// console.log("列表:",res);

				if (res.content.length == 0) {
					// alert('我没数据了');
					this.status = 'nomore';
				} else {
					// alert('我还有点');
					this.articles = [...this.articles, ...res.content];
					console.log('分页结果：', this.articles);
					this.status = 'loadmore';
				}
			});
			// this.$u
			// 	.get('/ebapi/store_api/hot_search', {
			// 		id: 2
			// 	})
			// 	.then(res => {
			// 		// this.result = res;
			// 		console.log("get请求",res);
			// 	});
			// this.$u
			// 	.post('/ebapi/public_api/index', {
			// 		id: 1
			// 	})
			// 	.then(res => {
			// 		console.log("posy请求",res);
			// 	});
		}
	}
};
</script>

<style>
/* #ifndef H5 */
page {
	height: 100%;
	background-color: #f2f2f2;
}

/* #endif */
</style>

<style lang="scss" scoped>
.u-card-wrap {
	background-color: $u-bg-color;
	padding: 1px;
}

.u-body-item {
	font-size: 32rpx;
	color: #333;
	padding: 20rpx 10rpx;
}

.u-body-item image {
	width: 120rpx;
	flex: 0 0 120rpx;
	height: 120rpx;
	border-radius: 8rpx;
	margin-left: 12rpx;
}

.top {
	display: flex;
	justify-content: space-between;

	.left {
		display: flex;
		align-items: center;

		.store {
			margin: 0 10rpx;
			font-size: 32rpx;
			font-weight: bold;
		}
	}

	.right {
		color: $u-type-warning-dark;
	}
}
</style>
