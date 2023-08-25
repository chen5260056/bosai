stalker api

实例公共返回参数：

| 字段                         | 类型                  | 说明                                          | 备注 |
| :--------------------------- | :-------------------- | :-------------------------------------------- | :--- |
| 字段                         | 类型                  | 说明                                          | 备注 |
| startAt                      | int                   | 起始页                                        |      |
| maxResults                   | int                   | 最大返回结果                                  |      |
| total                        | int                   | 总记录数                                      |      |
| errorMessage                 | String                | 错误信息                                      |      |
| resources                    | List                  | 资源集合                                      |      |
| resources.account            | String                | 账号                                          |      |
| resources.cloudType          | BaseCloudPlatformEnum | 云平台（aliyun、tencent）                     |      |
| resources.region             | String                | 区域                                          |      |
| resources.resourceType       | BaseResourceTypeEnum  | 资源类型（INSTANCE:实例）                     |      |
| resources.id                 | String                | id                                            |      |
| resources.hash               | String                | hash(排除 timestamp、version字段计算的hash值) |      |
| resources.timestamp          | long                  | 时间戳                                        |      |
| resources.instanceName       | String                | 实例名称                                      |      |
| resources.instanceType       | String                | 实例类型                                      |      |
| resources.instanceChargeType | String                | 实例计价类型                                  |      |
| resources.cpu                | int                   | cpu                                           |      |
| resources.memory             | int                   | 内存（单位:M）                                |      |
| resources.privateIP          | String                | 私网ip                                        |      |
| resources.publicIP           | String                | 公网ip                                        |      |
| resources.imageId            | String                | 镜像id                                        |      |
| resources.createTime         | String                | 创建时间                                      |      |
| resources.tags               | map                   | 标签                                          |      |
| resources.zone               | String                | 地区                                          |      |
| resources.vpc                | String                | vpc（多个用“,”拼接）                         |      |
| resources.securityGroup      | String                | 安全组                                        |      |
| resources.osName             | String                | 操作系统名称                                  |      |
| resources.internetChargeType | String                | 网络计费类型                                  |      |
| resources.status             | String                | 实例状态                                      |      |
| resources.description        | String                | 描叙                                          |      |
| resources.hostName           | String                |                                               |      |
| resources.key                | String                | 资源key                                       |      |
| resources.resourceKey        | String                | 资源类型key                                   |      |



## 获取实例详细信息

mothed：GET

url：http://${IP}:${port}/v1/${cloudType}/${account}/${region}/instance/${instanceId}

request:

| 字段       | 类型                  | 说明       | 备注 |
| :--------- | :-------------------- | :--------- | :--- |
| cloudType  | BaseCloudPlatformEnum | 云平台类型 | ``   |
| account    | String                | 云账号     |      |
| region     | String                | 区域       |      |
| instanceId | String                | 实例id     |      |

response：

```
{
    "startAt": null,
    "maxResults": null,
    "total": 1,
    "resources": [
        {
            "account": "mgtv_test",
            "cloudType": "ALIYUN",
            "region": "cn-beijing",
            "resourceType": "INSTANCE",
            "id": "i-2ze13w9axywmhxjc2mct",
            "hash": "d64f6360932420647feb6b788a5b1d3e",
            "timestamp": 1615165604726,
            "instanceName": "launch-advisor-20210302",
            "instanceType": "ecs.c6e.xlarge",
            "instanceChargeType": "PrePaid",
            "cpu": 4,
            "memory": 8192,
            "privateIP": "127.0.0.1",
            "publicIP": "127.0.0.1",
            "imageId": "centos_7_06_64_20G_alibase_20190711.vhd",
            "createTime": "2021-03-01T22:47:00.000+00:00",
            "tags": {
                "cpx": "ops"
            },
            "zone": "cn-beijing-h",
            "vpc": "127.0.0.1",
            "securityGroup": "sg-2zehxlt5pbzw08yu1ivb",
            "osName": "CentOS 7.6 64位",
            "internetChargeType": "PayByTraffic",
            "status": "Stopped",
            "description": "stalker测试停止3",
            "hostName": "iZ2ze13w9axywmhxjc2mctZ",
            "key": "aliyun-mgtv_test-cn-beijing-instance-i-2ze13w9axywmhxjc2mct",
            "resourceKey": "aliyun-mgtv_test-cn-beijing-instance"
        }
    ],
    "errorMessage": null
}
```

## 实例查询

mothed: POST

url:  http://${IP}:${port}/v1/${cloudType}/${account}/${region}/instances

curl --request POST \
--url 'http://${IP}:${port}/v1/${cloudType}/${account}/${region}/instances' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data '{
"mql": "instanceType = C6.XLarge",
"maxResults": 100,
"fields": [
"instanceName",
"status",
"tags"
],
"startAt": 0
}'

request:

| 字段            | 类型                  | 说明         | 备注                     |
| :-------------- | :-------------------- | :----------- | :----------------------- |
| 字段            | 类型                  | 说明         | 备注                     |
| cloudType       | BaseCloudPlatformEnum | 云平台类型   | ``                       |
| account         | String                | 云账号       |                          |
| region          | String                | 区域         |                          |
| body            | json                  | post请求体   |                          |
| body.mql        | String                | 条件筛选     | 字段名=字段值，以"&"拼接 |
| body.fields     | List<String>          | 返回字段     | 以","拼接                |
| body.startAt    | int                   | 查询页码     |                          |
| body.maxResults | int                   | 每页查询数量 |                          |



response：

```
{
    "startAt": 1,
    "maxResults": 10,
    "total": 3,
    "resources": [
        {
            "account": "mgtv_test",
            "cloudType": "ALIYUN",
            "region": "cn-beijing",
            "resourceType": "INSTANCE",
            "id": "i-2ze1xi2no5rnvwx9a8yr",
            "hash": "2d28f88601b23b31541f8a6059b55ef5",
            "timestamp": 1615174559478,
            "instanceName": "ESS-asg-mock-web-test-v012",
            "instanceType": "ecs.c5.2xlarge",
            "instanceChargeType": "PostPaid",
            "cpu": 8,
            "memory": 16384,
            "privateIP": "127.0.0.1",
            "publicIP": null,
            "imageId": "m-2ze80dvecyty60id89mn",
            "createTime": "2021-03-07T17:32:00.000+00:00",
            "tags": {
                "acs:autoscaling:scalingGroupId": "asg-2ze5lyid3jhtymrf4gt4",
                "ESS": "ESS"
            },
            "zone": "cn-beijing-h",
            "vpc": "127.0.0.1",
            "securityGroup": "sg-2zehxlt5pbzw08yu1ivb",
            "osName": "CentOS  7.4 64 bit",
            "internetChargeType": "PayByTraffic",
            "status": "Running",
            "description": "ESS",
            "hostName": "iZ2ze1xi2no5rnvwx9a8yrZ",
            "key": "aliyun-mgtv_test-cn-beijing-instance-i-2ze1xi2no5rnvwx9a8yr",
            "resourceKey": "aliyun-mgtv_test-cn-beijing-instance"
        },
        {
            "account": "mgtv_test",
            "cloudType": "ALIYUN",
            "region": "cn-beijing",
            "resourceType": "INSTANCE",
            "id": "i-2ze1zeijykeg2vtza2c3",
            "hash": "d6b7b8ffce92a7f6ce8576b5629b46b0",
            "timestamp": 1615174559478,
            "instanceName": "ESS-asg-mock-web-test-v012",
            "instanceType": "ecs.c5.2xlarge",
            "instanceChargeType": "PostPaid",
            "cpu": 8,
            "memory": 16384,
            "privateIP": "127.0.0.1",
            "publicIP": null,
            "imageId": "m-2ze80dvecyty60id89mn",
            "createTime": "2021-03-07T19:30:00.000+00:00",
            "tags": {
                "acs:autoscaling:scalingGroupId": "asg-2ze5lyid3jhtymrf4gt4",
                "ESS": "ESS"
            },
            "zone": "cn-beijing-h",
            "vpc": "127.0.0.1",
            "securityGroup": "sg-2zehxlt5pbzw08yu1ivb",
            "osName": "CentOS  7.4 64 bit",
            "internetChargeType": "PayByTraffic",
            "status": "Running",
            "description": "ESS",
            "hostName": "iZ2ze1zeijykeg2vtza2c3Z",
            "key": "aliyun-mgtv_test-cn-beijing-instance-i-2ze1zeijykeg2vtza2c3",
            "resourceKey": "aliyun-mgtv_test-cn-beijing-instance"
        },
        {
            "account": "mgtv_test",
            "cloudType": "ALIYUN",
            "region": "cn-beijing",
            "resourceType": "INSTANCE",
            "id": "i-2ze13w9axywmhxjc2mct",
            "hash": "9df4bc65bacfc8d3bcb511536c16b0a1",
            "timestamp": 1615174559478,
            "instanceName": "launch-advisor-20210302",
            "instanceType": "ecs.c6e.xlarge",
            "instanceChargeType": "PrePaid",
            "cpu": 4,
            "memory": 8192,
            "privateIP": "127.0.0.1",
            "publicIP": "127.0.0.1",
            "imageId": "centos_7_06_64_20G_alibase_20190711.vhd",
            "createTime": "2021-03-01T22:47:00.000+00:00",
            "tags": {
                "cpx": "ops"
            },
            "zone": "cn-beijing-h",
            "vpc": "127.0.0.1",
            "securityGroup": "sg-2zehxlt5pbzw08yu1ivb",
            "osName": "CentOS  7.6 64 bit",
            "internetChargeType": "PayByTraffic",
            "status": "Stopped",
            "description": "stalker测试停止3",
            "hostName": "iZ2ze13w9axywmhxjc2mctZ",
            "key": "aliyun-mgtv_test-cn-beijing-instance-i-2ze13w9axywmhxjc2mct",
            "resourceKey": "aliyun-mgtv_test-cn-beijing-instance"
        }
    ],
    "errorMessage": null
}
```

# 通用查询

POST

http://${IP}:${port}/v1/search

curl --request POST \
--url 'http://${IP}:${port}/v1/search' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data '{
"cloudType": "aliyun",
"resourceType": "instance",
"region": "cn-beijing",
"account": "mgtvcloud3",
"mql": "instanceType = C6.XLarge",
"maxResults": 100,
"fields": [
"instanceName",
"status",
"tags"
],
"startAt": 0
}'



response

```
{
    "startAt": 1,
    "maxResults": 2,
    "total": 2,
    "resources": [
        {
            "account": "mgtv_test",
            "cloudType": "ALIYUN",
            "region": "cn-beijing",
            "resourceType": "INSTANCE",
            "id": "i-2ze1xi2no5rnvwx9a8yr",
            "hash": "2d28f88601b23b31541f8a6059b55ef5",
            "timestamp": 1615175041323,
            "instanceName": "ESS-asg-mock-web-test-v012",
            "instanceType": "ecs.c5.2xlarge",
            "instanceChargeType": "PostPaid",
            "cpu": 8,
            "memory": 16384,
            "privateIP": "127.0.0.1",
            "publicIP": null,
            "imageId": "m-2ze80dvecyty60id89mn",
            "createTime": "2021-03-07T17:32:00.000+00:00",
            "tags": {
                "acs:autoscaling:scalingGroupId": "asg-2ze5lyid3jhtymrf4gt4",
                "ESS": "ESS"
            },
            "zone": "cn-beijing-h",
            "vpc": "127.0.0.1",
            "securityGroup": "sg-2zehxlt5pbzw08yu1ivb",
            "osName": "CentOS  7.4 64 bit",
            "internetChargeType": "PayByTraffic",
            "status": "Running",
            "description": "ESS",
            "hostName": "iZ2ze1xi2no5rnvwx9a8yrZ",
            "key": "aliyun-mgtv_test-cn-beijing-instance-i-2ze1xi2no5rnvwx9a8yr",
            "resourceKey": "aliyun-mgtv_test-cn-beijing-instance"
        },
        {
            "account": "mgtv_test",
            "cloudType": "ALIYUN",
            "region": "cn-beijing",
            "resourceType": "INSTANCE",
            "id": "i-2ze1zeijykeg2vtza2c3",
            "hash": "d6b7b8ffce92a7f6ce8576b5629b46b0",
            "timestamp": 1615175041323,
            "instanceName": "ESS-asg-mock-web-test-v012",
            "instanceType": "ecs.c5.2xlarge",
            "instanceChargeType": "PostPaid",
            "cpu": 8,
            "memory": 16384,
            "privateIP": "127.0.0.1",
            "publicIP": null,
            "imageId": "m-2ze80dvecyty60id89mn",
            "createTime": "2021-03-07T19:30:00.000+00:00",
            "tags": {
                "acs:autoscaling:scalingGroupId": "asg-2ze5lyid3jhtymrf4gt4",
                "ESS": "ESS"
            },
            "zone": "cn-beijing-h",
            "vpc": "127.0.0.1",
            "securityGroup": "sg-2zehxlt5pbzw08yu1ivb",
            "osName": "CentOS  7.4 64 bit",
            "internetChargeType": "PayByTraffic",
            "status": "Running",
            "description": "ESS",
            "hostName": "iZ2ze1zeijykeg2vtza2c3Z",
            "key": "aliyun-mgtv_test-cn-beijing-instance-i-2ze1zeijykeg2vtza2c3",
            "resourceKey": "aliyun-mgtv_test-cn-beijing-instance"
        }
    ],
    "errorMessage": null
}
```

  