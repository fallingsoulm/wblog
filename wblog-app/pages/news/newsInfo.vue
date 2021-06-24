<template>
	<!-- <view> 收到的参数为：{{paramsStr.age}}</view> -->
	<web-view :webview-styles="webviewStyles" :src="newsVo.url"></web-view>
</template>


<script>
	export default {
		data() {
			return {
				id: "",
				params: {},
				newsVo: {}

			}
		},
		onLoad(option) {
			this.params = option;
			this.id = this.params.id;
			this.getNewsInfo();
		},
		computed: {
			paramsStr() {
				return this.params;
			}
		},

		methods: {

			getNewsInfo() {
				// 获取文章详情K

				this.$u.get('business/api/v1/news/find/id/' + this.id).then(res => {
					// this.result = res;
					// console.log("get请求", res);
					this.newsVo = res;
				});

			}

		}
	};
</script>



<style lang="scss" scoped>

</style>
