# 一、愿景
作为一款针对程序员和开发者的即时通讯工具（GeekIM），在产品功能和内容特色方面，以下是一些考虑的功能特色：

- 实时代码协作：允许用户共享代码片段或整个项目，并实现实时编辑、同步预览和版本控制。类似于VS Code Live Share 或 Google Docs 的协作模式。
- 集成开发环境(IDE)嵌入：将 GeekIM 直接整合到各种主流 IDE（如 IntelliJ IDEA, Eclipse, Visual Studio Code 等）中，提供内联聊天窗口和快速反馈机制。
- 代码片段分享与讨论：支持代码高亮和格式化，方便用户分享代码片段并进行讨论。还可以添加语法错误检查和一键运行结果展示等功能。
- 问题求助与解答：构建一个问答社区，用户可以在遇到编程难题时迅速发起提问，并通过标签系统精准匹配专家或团队成员进行解答。
- 消息模板与快捷回复：内置常见技术问题的回答模板以及程序员常用语录，提高沟通效率。
- AI辅助：结合AI技术，实现智能搜索历史对话中的解决方案，或者自动分析代码问题并推荐解决办法。
- 个性化设置：提供夜间模式、自定义主题色等个性化界面设置，以适应程序员长时间使用的需求。
- 群组管理与项目组创建：支持按项目或团队划分群组，方便成员之间围绕特定项目进行高效沟通。
- 知识库与文档共享：除了即时聊天，还应有知识库模块，用于团队内部共享技术文档、API手册、最佳实践等资料。
- 通知与提醒：集成CI/CD pipeline通知，当构建失败或部署成功时，能及时向相关开发者推送信息。

以上只是一部分可能的功能设想，具体的产品设计还需结合目标用户需求调研、竞品分析及实际技术实现可行性来综合考量。

# 二、项目简介
![](http://jackimgs.52im.net/images/mobileimsdk/preview/more_screenshots/others/github_header_logo_h.png)

<b>MobileIMSDK是一套专为移动端开发的原创IM通信层框架：</b>
* 历经10年、久经考验；
* 超轻量级、高度提炼，lib包50KB以内；
* 精心封装，一套API优雅支持<b>UDP</b> 、<b>TCP</b> 、<b>WebSocket</b>  三种协议（可能是全网唯一开源的）；
* 客户端支持iOS、Android、标准Java、H5([暂未开源](http://www.52im.net/thread-3682-1-1.html))、小程序([暂未开源](http://www.52im.net/thread-4169-1-1.html))、Uniapp([暂未开源](http://www.52im.net/thread-4225-1-1.html))；
* 服务端基于Netty，性能卓越、易于扩展；:point_left:
* 可与姊妹工程 [MobileIMSDK-Web](http://www.52im.net/thread-959-1-1.html) 无缝互通实现网页端聊天或推送等；:point_left:
* 可应用于跨设备、跨网络的聊天APP、企业OA、消息推送等各种场景。

> MobileIMSDK工程自2013年10月起持续升级至今（当前最新版是v6.4，[版本更新日志点此查看](http://www.52im.net/thread-1270-1-1.html) ），历经10年，起初用作某自用产品的即时通讯底层，完全从零开发。<br>
2023年5月10日，最新[Uniapp端](http://www.52im.net/thread-4225-1-1.html)已开发完成，希望对需要的人有所启发和帮助。

:point_right: 您可能需要：[查看更多关于MobileIMSDK的疑问及解答](http://www.52im.net/thread-60-1-1.html)。

:point_right: 另一姊妹工程：[轻量级Web端即时通讯框架：MobileIMSDK-Web](http://www.52im.net/thread-959-1-1.html) 也在持续更新中，专用于手机或PC端的网页聊天和消息推送等。

# 三、代码托管同步更新

**GitHub.com**

* 代码托管：  https://github.com/JackJiang2011/MobileIMSDK
* 技术社区：  [点击查看更多资料](http://www.52im.net/forum-89-1.html)

**码云gitee**

* 代码托管：  https://gitee.com/jackjiang/MobileIMSDK
* 技术社区：  [点击查看更多资料](http://www.52im.net/forum-89-1.html)

MobileIMSDK版本更新日志：[点此查看](http://www.52im.net/thread-1270-1-1.html) :point_left:

# 四、设计目标
让开发者专注于应用逻辑的开发，底层<code>复杂的即时通讯算法交由SDK开发人员</code>，从而<code>解偶即时通讯应用开发的复杂性</code>。

# 五、框架组成
<b>整套MobileIMSDK框架由以下6部分组成：</b>

1. <b>Android客户端SDK：</b>用于Android版即时通讯客户端，支持Android 4\.0及以上，[查看API文档](http://docs.52im.net/extend/docs/api/mobileimsdk/android_tcp/)；
2. <b>iOS客户端SDK：</b>用于开发iOS版即时通讯客户端，支持iOS 9\.0及以上，[查看API文档](http://docs.52im.net/extend/docs/api/mobileimsdk/ios_tcp/)；
3. <b>Java客户端SDK：</b>用于开发跨平台的PC端即时通讯客户端，支持Java 1\.6及以上，[查看API文档](http://docs.52im.net/extend/docs/api/mobileimsdk/java_tcp/)；
4. <b>H5客户端SDK：</b>暂无开源版，[查看精编注释版](http://www.52im.net/thread-3682-1-1.html)；
5. <b>微信小程序端SDK：</b>暂无开源版，[查看精编注释版](http://www.52im.net/thread-4169-1-1.html)；
6. <b>Uniapp端SDK：</b>暂无开源版，[查看精编注释版](http://www.52im.net/thread-4225-1-1.html)；
7. <b>服务端SDK：</b>用于开发即时通讯服务端，支持Java 1\.7及以上版本，[查看API文档](http://docs.52im.net/extend/docs/api/mobileimsdk/server/)。

<b>整套MobileIMSDK框架的架构组成：</b>

![](http://jackimgs.52im.net/images/mobileimsdk/preview/mibileimsdk_framework_2023_v2.png)

MobileIMSDK一直在持续开发和升级中，[Uniapp客户端](http://www.52im.net/thread-4225-1-1.html) 是MobileIMSDK工程的最新成果。

:point_right: <b>另外：</b>MobileIMSDK可与姊妹工程 [MobileIMSDK-Web](http://www.52im.net/thread-959-1-1.html) 无缝互通，从而实现Web网页端聊天或推送等。

# 六、技术特征
* <b>久经考验：</b>历经8年，从Andriod 2.3、iOS 5.0 时代持续升级至今（绝不烂尾）；
* <b>超轻量级：</b>高度提炼，lib包50KB以内；
* <b>多种协议：</b>可能是全网唯一开源可一套API同时支持UDP、TCP、WebSocket三种协议的同类框架  :new:；
* <b>多种网络：</b>精心优化的TCP、UDP、WebSocket协议实现，可应用于卫星网、移动网、嵌入式物联网等场景；
* <b>多端覆盖：</b>客户端支持iOS、Android、标准Java、[H5](http://www.52im.net/thread-3682-1-1.html)、[微信小程序](http://www.52im.net/thread-4169-1-1.html)、[Uniap](http://www.52im.net/thread-4225-1-1.html)；
* <b>高效费比：</b>独有的UDP协议实现，无连接特性，同等条件下可实现更高的网络负载和吞吐能力；
* <b>消息走向：</b>支持即时通讯技术中消息的所有可能走向，共3种（即C2C、C2S、S2C）；
* <b>粘包半包：</b>优雅解决各端的TCP经典粘包和半包问题，底层封装，应用层完全无感知；
* <b>QoS机制：</b>完善的消息送达保证机制（自动重传、消息去重、状态反馈等），不漏过每一条消息；
* <b>健壮可靠：</b>实践表明，非常适于在高延迟、跨洲际、不同网络制式环境中稳定、可靠地运行；
* <b>断网恢复：</b>拥有网络状况自动检测、断网自动治愈的能力；
* <b>原创算法：</b>核心算法和实现均为原创，保证了持续改进和提升的空间；
* <b>多种模式：</b>预设多种实时灵敏度模式，可根据不同场景控制即时性、流量和客户端电量消耗；
* <b>数据压缩：</b>自有协议实现，未来可自主定制数据压缩，灵活控制客户端的流量、服务端网络吞吐；
* <b>高度封装：</b>高度封装的API接口，保证了调用的简易性，也使得可应用于更多的应用场景；
* <b>Web支持：</b>可与姊妹工程 [MobileIMSDK-Web](http://www.52im.net/thread-959-1-1.html) 无缝互通实现网页端聊天或推送等；:point_left:
* <b>扩展性好：</b>服务端基于Netty，继承了Netty的优秀高可扩展性；
* <b>性能优异：</b>服务端继承了Netty高性能、高吞吐特性，适用于高性能服务端场景。

> <b>MobileIMSDK 所支持的全部3种即时通讯消息走向分别是：</b><br>
(1) Client to Client (C2C)：即由某客户端主动发起，接收者是另一客户端；<br>
(2) Client to Server (C2S)：即由某客户端主动发起，接收者是服务端；<br>
(3) Server to Client (S2C)：即由服务端主动发起，接收者是某客户端。

:point_right: 您可能需要：[查看更多关于MobileIMSDK的疑问及解答](http://www.52im.net/thread-60-1-1.html)。

# 七、性能测试
压力测试表明，MobileIMSDK用于推送场景时，理论单机负载可接近千万级。用于聊天应用时，单机负载也可达数十万。

> 当然，每款应用都有各自的特点和差异，请视具体场景具体评估之，测试数据仅供参考。

:point_right: 性能测试报告：[点此查看](http://www.52im.net/thread-57-1-1.html)。

# 八、演示程序
1. <b>Android客户端 Demo：</b>[点此安装和使用](http://www.52im.net/thread-55-1-1.html)；
2. <b>iOS客户端 Demo：</b>[点此安装和使用](http://www.52im.net/thread-54-1-1.html)；
3. <b>Java客户端 Demo：</b>[点此安装和使用](http://www.52im.net/thread-56-1-1.html)；
4. <b>H5客户端 Demo：</b>[点此查看介绍](http://www.52im.net/thread-3682-1-1.html)；
5. <b>微信小程序端 Demo：</b>[点此查看介绍](http://www.52im.net/thread-4169-1-1.html)  :new:；
6. <b>Uniapp端 Demo：</b>[点此查看介绍](http://www.52im.net/thread-4225-1-1.html)  :new:；
7. <b>服务端 Demo：</b>[点此安装和使用](http://www.52im.net/thread-1272-1-1.html)。

# 九、应用案例
#### ① 基于MobileIMSDK的产品级聊天APP：
> [详细介绍](http://www.52im.net/thread-19-1-1.html)、[下载体验](http://www.52im.net/thread-19-1-1.html) 或 [查看运行截图](http://www.52im.net/thread-20-1-1.html)。

#### ② MobileIMSDK在高网络延迟下的案例：
> 某款基于MobileIMSDK的商业商品，曾运营于跨洲际的复杂网络环境下，端到端通信延迟在洲际网络繁忙时可高达600ms以上（与服务端的单向延迟约为300ms左右，而通常大家访问国内主流门户的延迟约为20~50ms），某段时期的非敏感运营数据 [点此查看](http://www.52im.net/forum.php?mod=viewthread&tid=21&page=1&extra=#pid35)。

# 十、打包下载（all in one）
:paperclip: ① 最新发布版（国外地址）：[Github打包下载](https://github.com/JackJiang2011/MobileIMSDK/releases/latest)

:paperclip: ② 最新发布版（国内地址）：[码云gitee打包下载](https://gitee.com/jackjiang/MobileIMSDK/releases)（访问速度快！ :point_left:）
> 说明：最新发布版打包内容中，已包含完整的demo源码、sdk源码、api文档、编译后的分发包等。

# 十一、典型应用场景
### :triangular_flag_on_post: 场景1：聊天APP
* <b>应用说明：</b>可用于开发类似于微信、QQ等聊天工具。<br>
* <b>消息走向：</b>需使用C2C、C2S、S2C全部类型。<br>
* <b>特别说明：</b>MobileIMSDK并未定义聊天应用的应用层逻辑和协议，开发者可自行定义并实现之。

### :triangular_flag_on_post: 场景2：消息推送
* <b>应用说明：</b>可用于需要向客户端实时推送信息的各种类型APP。<br>
* <b>消息走向：</b>仅需使用S2C 1种消息走向，属MobileIMSDK的最简单应用场景。

### :triangular_flag_on_post: 场景3：企业OA
* <b>应用说明：</b>可用于实现企业OA的指令、公文、申请等各种消息实时推送，极大提升用户体验，并可延伸至移动设备。<br>
* <b>消息走向：</b>仅需使用S2C 1种消息走向，属MobileIMSDK的最简单应用场景。

### :triangular_flag_on_post: 场景4：企业OA的增强型
* <b>应用说明：</b>可用于实现企业OA中各种系统级、用户级消息的实时互动，充分利用即时通讯技术提升传统OA的价值。<br>
* <b>消息走向：</b>可使用C2C、C2S、S2C全部类型，这与聊天APP在很多方面已无差别，但企业OA有自已的用户关系管理模型和逻辑，较之全功能聊天APP要简单的多。

# 十二、开发指南
1. <b>Android客户端开发指南：</b>[点此查看](http://www.52im.net/thread-61-1-1.html)；
2. <b>iOS客户端开发指南：</b>[点此查看](http://www.52im.net/thread-62-1-1.html)；
3. <b>Java客户端开发指南：</b>[点此查看](http://www.52im.net/thread-59-1-1.html)；
4. <b>H5客户端开发指南：</b>[点此查看](http://www.52im.net/thread-4239-1-1.html)；
5. <b>微信小程序端开发指南：</b>[点此查看](http://www.52im.net/thread-4168-1-1.html)；
5. <b>Uniapp端开发指南：</b>[点此查看](http://www.52im.net/thread-4226-1-1.html)；
7. <b>Server端开发指南：</b>[点此查看](http://www.52im.net/thread-63-1-1.html)。

# 十三、授权方式
你可永久免费且自由地使用MobileIMSDK，如：用于研究、学习、甚至商业用途，
但禁止在超越License约束内容的情况下用于商业用途等，请尊重知识产权。
更详细的授权说明，请见[MobileIMSDK社区介绍贴](http://www.52im.net/thread-52-1-1.html)中的“十二、授权方式”一节。

> <b>补充说明：</b>如您还需获得更多技术支持或技术合作请联系作者，QQ：<code>413980957</code>（:point_right: [关于作者](http://www.52im.net/thread-2792-1-1.html)）。

# 十四、联系方式
* 讨论学习和资料区：:earth_americas: [点此进入](http://www.52im.net/forum-89-1.html)；
* 即时通讯技术交流群：`185926912` <a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=928f69a8f12edf8366a3b5a1a9dd51d97dd4bd1b8926952b0f49964ebb19a07a"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="即时通讯(IM/推送)开发2" title="即时通讯(IM/推送)开发2"></a> （[更多QQ群点此进入](http://www.52im.net/portal.php?mod=topic&topicid=2)）；
* bug和建议请发送至：:love_letter: `jb2011@163.com`；
* 技术培训/转让/合作/咨询等欢迎联系作者QQ：:penguin: `413980957`、微信：`hellojackjiang`  （:point_right: [关于作者](http://www.52im.net/thread-2792-1-1.html)）。

# 十五、捐助作者
优秀的开源需要您的支持才能走的更远，衷心感谢您的支持与理解，也希望您能从开源中收益。
* 捐助链接： [点此进入](http://www.52im.net/thread-411-1-1.html)。

# 十六、关注作者
* 关于作者：[点击进入](http://www.52im.net/thread-2792-1-1.html) :point_left:
* 推荐关注：[BeautyEye工程](https://github.com/JackJiang2011/beautyeye)
* 博客地址：[点击入进](http://www.52im.net/space-uid-1.html)
* Github主页：[点击进入](https://github.com/JackJiang2011)
* 我的公众号：:point_down: :point_down:

![](http://jackimgs.52im.net/images/mobileimsdk/preview/more_screenshots/others/my_mp_qr_400_b.png)
