const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    '/custom',
    createProxyMiddleware({
      target: 'https://sjzq3u7j26.apigw.ntruss.com/',
      changeOrigin: true,
    }),
  );
};
