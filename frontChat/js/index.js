mui.init();
// 原生加载完成之后
mui.plusReady(function() {
	plus.navigator.setStatusBarStyle("light");
	plus.navigator.setStatusBarBackground("#F2F2F2");
});

var arr = [{
		pageId: "chatlist.html",
		pageUrl: "page/chatlist.html"
	},
	{
		pageId: "contact.html",
		pageUrl: "page/contact.html"
	},
	{
		pageId: "discover.html",
		pageUrl: "page/discover.html"
	},
	{
		pageId: "person.html",
		pageUrl: "page/person.html"
	}
];

// 设置上下边距
var t_b_style = {
	top: "44px",
	bottom: "50px"
};

mui.plusReady(function() {
	// 获取当前的webview
	var currentPage = plus.webview.currentWebview();
	// 创建4个webview对象
	for (var index = 0; index < arr.length; index++) {
		var page = plus.webview.create(arr[index].pageUrl, arr[index].pageId, t_b_style);
		page.hide();
		currentPage.append(page)
	};
	plus.webview.show(arr[0].pageId);

	// 批量绑定 tap 事件
	mui(".mui-bar-tab").on("tap", "a", function() {
		var thisPageIndex = this.getAttribute("tabindex");
		// 显示点击的tab选项对应的页面
		plus.webview.show(arr[thisPageIndex].pageId, "fade-in", 200);
		// 隐藏其他的页面
		for (var index = 0; index < arr.length; index++) {
			if (index != thisPageIndex) {
				plus.webview.hide(arr[index].pageId, "fade-out", 200);
			}
		}
	})
	// 获取当前点击页面
})
