<template>
	<view class="wrap">
		<view class="top">
			<view class="left">
				<view class="store">{{ article.title }}</view>
			</view>
		</view>
		<view class="item u-border-bottom">
			<view class="" style="margin-left: 20upx;">
				<u-icon name="eye" size="34" color="" :label="`发布时间   :` + article.createTime" style="margin-left: 20upx;"></u-icon>
				<u-icon name="eye" size="34" color="" :label="article.view + '人观看'" style="margin-left: 20upx;"></u-icon>
				<!-- <u-icon name="info-circle" size="34" color="" :label="article.userName + ' 发表'" style="margin-left: 20upx;"></u-icon> -->
			</view>
			<view style="margin-top: 30upx; margin-left: 30upx;">
				<!-- 		<u-collapse>
					<u-collapse-item title="更多标签" :item-style="itemStyle"> -->
				<u-tag v-for="label in article.labelVos" :text="label.name" mode="light" shape="circle" type="warning" :key="label.id" />
				<!-- 				</u-collapse-item>
				</u-collapse> -->
			</view>
		</view>
		<view class="u-content"><u-parse :html="article.content"></u-parse></view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			id: '',
			article: '',
			itemStyle: {
				marginTop: '211upx'
			}
		};
	},

	onLoad(option) {
		this.params = option;
		this.id = this.params.id;
		this.getArticleInfo();
	},
	computed: {},

	methods: {
		// 获取文章详情K
		getArticleInfo() {
			this.$u.get('business/api/v1/news/find/id/' + this.id).then(res => {
				// this.result = res;
				// console.log("get请求", res);
				this.article = res;
			});
		}
	}
};
</script>

<style lang="scss" scoped>
.wrap {
	margin-top: 100upx;
	margin-left: 10upx;
}

.u-content {
	margin-top: 100rpx;
	color: $u-content-color;
	font-size: 32rpx;
	line-height: 1.8;
	margin-left: 40upx;
	margin-right: 40upx;
}

// 标签形式无效
p {
	color: $u-tips-color;
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
			margin: 0 40upx 40upx 40upx;
			font-size: 52rpx;
			font-weight: bold;
		}
	}

	.right {
		color: $u-type-warning-dark;
	}
}
</style>
