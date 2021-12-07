# Compose Learning Sample

本例子基于Compose Multiplatform运行在Android平台和Desktop平台.
学习一些跨平台组件和库,方便自己使用,不建议用于真实项目.

|module |description|
|---|---|
|android|android app|
|desktop|desktop app|
|:tool:libk|jvm平台工具库|
|:tool:libc|compose工具库|
|:tool:imageloader|图片加载库|
|:common:comm|通用类|
|:common:database|数据库|
|:common:network|网络请求库|
|:common:fun:*|子模块|
|:common:router|模块间路由|
|:common:main|compose ui|

**[引入库](https://github.com/qwwuyu/ComposeLearning/blob/main/buildSrc/src/main/config/Config.kt)**

|description|implementation|
|---|---|
|kotlin插件|org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31|
|Compose插件|org.jetbrains.compose:compose-gradle-plugin:1.0.0|
|android插件|com.android.tools.build:gradle:4.2.0|
|数据库插件|com.squareup.sqldelight:gradle-plugin:1.5.0|
|SQLDelight|com.squareup.sqldelight:*:1.5.0|
|MVIKotlin|com.arkivanov.mvikotlin:*:3.0.0-alpha01|
|Decompose|com.arkivanov.decompose:*:0.4.0|
|Reaktive|com.badoo.reaktive:*:1.1.22|
|OkHttp|com.squareup.okhttp3:*:3.14.9|
|Retrofit|com.squareup.retrofit2:*:2.9.0|
|coil|1.4.0|
|gson|2.8.6|

**学习于项目**
|url|功能|
|---|---|
|https://github.com/JetBrains/compose-jb|compose|
|https://github.com/JetBrains/compose-jb/tree/master/examples/todoapp|MVI+数据库|
|https://github.com/JetBrains/compose-jb/tree/master/examples/widgets-gallery|一些基础组件|
|https://github.com/JetBrains/compose-jb/tree/master/examples/notepad|desktop API|
|https://github.com/GerardPaligot/discovering-movies|imageloader|
|other|部分组件、Gradle配置|

**推荐相关学习**
|url|
|---|
|https://github.com/JetBrains/compose-jb/tree/master/examples|
|https://github.com/android/compose-samples|
|https://github.com/Gurupreet/ComposeCookBook|
|https://github.com/google/accompanist|
|https://juejin.cn/column/6960699198618468359|
|https://juejin.cn/post/7023667423618269191|
|https://github.com/leavesC/compose_chat|
|等等...|

