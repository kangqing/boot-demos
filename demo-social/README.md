### 使用OAuth2.0保护你的应用
1.要使用GitHub的OAuth 2.0身份验证系统进行登录，您必须首先添加一个新的GitHub应用，去这个地址 https://github.com/settings/developers
选择“新OAuth应用”，然后显示“注册新OAuth应用”页面。输入应用名称和描述。然后，在本例中，输入应用程序的主页，该主页应为http//localhost:8080。
最后，将授权回调URL指示为http://localhost:8080/login/oauth2/code/github 然后单击注册应用程序。

OAuth重定向URI是最终用户的用户代理在通过GitHub进行身份验证并在“授权应用程序”页面上授予对该应用程序的访问权之后，将重定向到该应用程序中的路径。

默认重定向URI模板为{baseUrl}/login/oauth2/code/{registrationId}。该registrationId是用于唯一标识符ClientRegistration。

2.配置application.yml
```yml
spring:
  security:
    oauth2:
      client:
        registration:
          github:
            clientId: github-client-id
            clientSecret: github-client-secret
```

3.启动应用程序
进行此更改后，您可以再次运行您的应用并访问位于http：//localhost:8080的主页。现在，您应该重定向到使用GitHub登录而不是主页。如果这样做，
并接受要求您进行的任何授权，您将被重定向回本地应用程序，并且主页将可见。

如果您保持登录GitHub，即使您在没有Cookie和缓存数据的全新浏览器中打开它，也不必使用此本地应用程序进行重新身份验证。（这就是“单点登录”的意思。）