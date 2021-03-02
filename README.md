# binance-client
This is a spring boot application that gets the candlestick data from Binance API and shows the chart with CanvasJS

### setup
Get API Key and Secret Key binance fill as "api-key" and "secret-key" in src/main/resouces/application.properties.

[How to create API key and Secret Key](https://www.binance.com/en/support/faq/360002502072)

### How to use
After excuting, access to "localhost:8080".

Choose the interval and symbol pair and push the "show chart" button, then it shows the chart.


# binance-client
BinanceのCandlestick Data APIからデータを取得し、それをCanvasJSでチャートを表示するSpring Bootアプリケーションです。

### セットアップ
個人アカウントのAPI KeyとSecret Keyが必要になります。Binance画面で作成し、src/main/resouces/application.propertiesの"api-key", "secret-key"に、取得した値を入力してください。

[How to create API key and Secret Key](https://www.binance.com/en/support/faq/360002502072)

### 使い方
アプリケーション実行後、"localhost:8080"にアクセスします。

インターバルとシンボルペアを選択し、"show chart"ボタンを押下すると、チャートが表示されます。
