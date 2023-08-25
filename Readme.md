stalker
------------------------------------
 stalker是云资源发现框架,具备多云资源发现,云资源信息存储,云资源事件监控推送等功能。

#### 环境
jdk8、lombok、gradle-6.7.1

#### 使用

参考[使用说明]()

#### 扩展

1、扩展参数，参考配置文件[application.xml]()说明
2、扩展当前缓存：
	2.1、实现CacheProvider接口，实现其所有方法
	2.2、修改配置项 cache.dataSource 为对应的数据源
3、开启历史资源同步：修改配置项history.enabled=true
4、扩展历史信息存储数据源：
	4.1、实现HistoryProvider接口，并实现其所有方法，
	4.2、修改配置项 history.dataSource 对应数据源
5、开启webhook功能，配置需要推送的资源的推送地址，crawl.platform.region.resource.xxxResourcePushUrl;
6、扩展webhook功能，实现WebHookClientProvider接口及其方法
7、新增云平台：
	7.1、在BaseCloudPlatformEnum新增枚举
	7.2、继承AbstractCloudProvider类，实现所需要的资源方法
8、新增资源：
	8.1、在BaseResourceTypeEnum中新增资源类型
	8.2、在接口CloudProvider中新增资源方法，且该方法为默认空方法
	8.3、如现有平台需要实现该资源则在对应的cloudProvider重写该方法
	8.4、在AbstractCloudProvider类crawlOneTime方法中添加新case
9、修改限流：实现TokenBucketProvider接口及其方法