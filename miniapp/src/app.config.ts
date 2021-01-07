export default {
  pages: [
    'pages/index/index',
    'pages/product/index',
    'pages/mine/index'
  ],
  tabBar: {
    "color": "#e6e6e6",
    "selectedColor": "#368488",
    "list": [
      {
        "pagePath": "pages/index/index",
        "text": "首页",
        "iconPath": "./assets/images/index.png",
        "selectedIconPath": "./assets/images/index1.png"
      },
      {
        "pagePath": "pages/product/index",
        "text": "商品",
        "iconPath": "./assets/images/order.png",
        "selectedIconPath": "./assets/images/order1.png"
      },
      {
        "pagePath": "pages/mine/index",
        "text": "我的",
        "iconPath": "./assets/images/mine.png",
        "selectedIconPath": "./assets/images/mine1.png"
      }
    ]
  },
  window: {
    backgroundTextStyle: 'light',
    navigationBarBackgroundColor: '#fff',
    navigationBarTitleText: 'WeChat',
    navigationBarTextStyle: 'black'
  }
}
