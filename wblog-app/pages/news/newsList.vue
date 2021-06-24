<template>
	<view>
		<u-search placeholder="搜索资讯..." v-model="keyword" @search="toSearch" @clear="searchClear()" height="120"></u-search>
		<view style="padding-top: 20upx;">
			<u-card></u-card>
			<u-card class="item u-border-bottom" v-for="(news, index) in newsList" :key="index">
				<view class="" slot="body" @click="toNewsInfo(news.id)">
					<view class="top">
						<view class="left">
							<u-icon name="list" :size="30" color="rgb(94,94,94)"></u-icon>
							<view class="store">{{ news.title }}</view>
						</view>
					</view>

					<view class="u-body-item u-flex u-border-bottom u-col-between u-p-t-0">
						<view class="u-body-item-title u-line-2" v-text="news.desp">瓶身描绘的牡丹一如你初妆，冉冉檀香透过窗心事我了然，宣纸上走笔至此搁一半</view>
						<!-- <image :src="article.image"></image> -->
					</view>
				</view>

				<view class="" slot="foot">
					<u-icon name="calendar" size="34" color="" :label="`发布时间   :` + news.publishTime"></u-icon>

					<u-icon name="info-circle" size="34" color="" :label="`来源:` + news.sourceStr" style="margin-left: 20upx;"></u-icon>
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

			status: 'loadmore',
			list: 5,
			page: 1,

			newsList: [],
			keyword: ''
		};
	},
	onLoad() {
		this.getNewsDataList();
	},

	computed: {},
	onReachBottom() {
		// if (this.page >= 3) return;
		this.status = 'loading';
		this.page = ++this.page;
		this.getNewsDataList();
		// setTimeout(() => {
		// 	// this.list += 10;
		// 	this.articles.push(this.aa);
		// 	if (this.page >= 3) this.status = 'nomore';
		// 	else this.status = 'loading';
		// }, 2000);
	},
	methods: {
		toSearch(value) {
			// 跳转搜索结果页面
			this.keyword = value;
			this.newsList = [];
			this.page = 1;
			this.getNewsDataList();
		},
		searchClear() {
			this.newsList = [];
			this.page = 1;
			this.keyword = '';
			this.getNewsDataList();
		},
		toNewsInfo(id) {
			let pa = {
				id: id
			};
			this.$u.route({
				type: 'to',
				params: pa,
				url: '/pages/news/newsInfo',
				animationType: this.animate
			});
		},
		// 获取文章数据
		getNewsDataList() {
			let param = {
				pageSize: this.list,
				pageIndex: this.page,
				params: {
					title: this.keyword
				}
			};

			this.$u.post('business/api/v1/news/find/page', param).then(res => {
				// this.result = res;

				if (res.content.length == 0) {
					this.status = 'nomore';
				} else {
					this.newsList = [...this.newsList, ...res.content];
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
