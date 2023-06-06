Integer

# 基础流程协议

## 获取验证码

用于用户注册和用户忘记密码密码找回功能

请求示例：

```json
{
  "Body": {
    "FindPasswordType": 2,
    "Language": "",
    "MobileCN": "",
    "UserInfo": "1111111@qq.com",
    "UserType": 27,
    "VerifyWay": 1
  },
  "MessageType": "GetVerifyCodeRequest"
}
```

响应示例：

```json
{
  "Body": {
    "CGSId": "cgsa-100003"
  },
  "MessageType": "GetVerifyCodeResponse",
  "ResultCode": 0
}
```

参数描述：

|     paramter     |  type  |      description       |
| :--------------: | :----: | :--------------------: |
| FindPasswordType |  int   | 2-邮箱地址,3-电话号码  |
|     Language     | String |         未使用         |
|     MobileCN     | String |         是否中国         |
|     UserInfo     | String |   用户邮箱/手机号码    |
|     UserType     |  int   |  客户类型-32-高斯康  |
|    VerifyWay     |  int   | 1-用户注册, 2-忘记密码 |
|      CGSId       | String |        cgs 标识        |

## 用户注册

请求示例：

```json
{
  "Body": {
    "AreaId": "",
    "EmailAddr": "",
    "MobileCN": "",
    "Password": "ync7NY6aTweB9syK8UvYwzelcvXKOsYdaWYPz7OrkYrJwRQeoiAbf0r39gD/86CH/9MCCnph/21MF7F5rc6G+A==",
    "PhoneNumber": "13714774825",
    "RegisterWay": 1,
    "UserName": "13714774825",
    "UserType": 32,
    "VerifyCode": "525525"
  },
  "MessageType": "UserRegisterRequest"
}
```

响应示例：

```json
{
  "Body": {
    "CGSId": "cgsa-100003"
  },
  "MessageType": "UserRegisterResponse",
  "ResultCode": -80009
}
```

参数描述：

|  paramter   |  type  |        description         |
| :---------: | :----: | :------------------------: |
|   AreaId    | String |           未使用           |
|  EmailAddr  | String |          邮箱地址          |
|  MobileCN   | String |            使用            |
|  Password   |  int   |     用户密码（已加密）     |
| PhoneNumber |  int   |          手机号码          |
| RegisterWay | String | 2 – 邮箱地址，3 – 电话号码 |
|  UserName   | String |           用户名           |
|  UserType   |  int   |          客户类型          |
| VerifyCode  |  int   |           验证码           |

## 用户登录

请求示例：

```json
{
  "Body": {
    "MobileCN": "1",
    "Password": "c1egiGvVlpAIcjR3EaEiHO1YuRqQcyPOWgpeuNg/Z/WJD2+8DhB8qpBCyRNqme4IOsjMeU97B91EGUfREzdI2w==",
    "ProtocolType": 1,
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "LoginCGSARequest"
}
```

响应示例：

```json
{
  "Body": {
    "AccessToken": "DB172A71D4ED4086BF360D0539A68EB6",
    "CGSId": "cgsa-000001",
    "EffectiveTime": 300,
    "KeepAliveTime": 30,
    "RefreshTokenTime": 30,
    "RefreshToken": "AC1CABECCFB84825AF426BCD608B2A44",
    "SessionId": "0",
    "SessionIdEx": "AC1CABECCFB84825AF426BCD608B2A44",
    "UserName": "542957111@qq.com",
    "UserToken": "b2a58f3c-65bb-11eb-930c-9944c03b973f"
  },
  "MessageType": "LoginCGSAResponse",
  "ResultCode": 0
}
```

参数描述：

|   paramter    |  type  |         description          |
| :-----------: | :----: | :--------------------------: |
|   MobileCN    | String |            未使用            |
|   Password    | String |         用户登录密码         |
|   UserName    | String |            用户名            |
| RefreshTokenTime  |  int   |          刷新TOKEN时间         |
|   UserType    |  int   |         APP定制类型          |
|  AccessToken  | String |        临时访问TOKEN         |
| EffectiveTime |  int   |    临时访问TOKEN有效时间     |
| RefreshToken  | String | 刷新TOKEN，用于临时TOKEN过期 |
| KeepAliveTime |  int   |            未使用            |
|   SessionId   |  int   |          session ID          |
|  SessionIdEx  | String |      用户登录SessionId       |
|   UserToken   | String |            token             |

## 重新获取临时访问TOKEN

请求示例：

```json
{
  "Body": {
    "RefreshToken": "AC1CABECCFB84825AF426BCD608B2A44",
    "AccessToken": "DB172A71D4ED4086BF360D0539A68EB6",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "GetAccessTokenRequest"
}
```

响应示例：

```json
{
  "Body": {
    "AccessToken": "EF80B1EC71B243EAB72D2ECAED78DF8A",
    "CGSId": "cgsa-000001",
    "EffectiveTime": 300,
    "RefreshToken": "AC1CABECCFB84825AF426BCD608B2A44",
    "SessionId": "0",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "GetAccessTokenResponse",
  "ResultCode": 0
}
```

参数描述：

|   paramter    |  type  |         description          |
| :-----------: | :----: | :--------------------------: |
|   UserName    |  int   |           用户名称           |
|   UserType    |  int   |         APP定制类型          |
|  AccessToken  | String |        临时访问TOKEN         |
| EffectiveTime |  int   |    临时访问TOKEN有效时间     |
| RefreshToken  | String | 刷新TOKEN，用于临时TOKEN过期 |

## 用户忘记密码

请求示例：

```json
{
  "Body": {
    "FindPasswordType": 2,
    "NewPassword": "yncc7NY6aTweB9syK8UvYwyAuF5OskO9av6MxW/Az1/AaDFvjhewx2pf79MS9z5BsrydlwUz7iCA+HHkgD8W17g==",
    "UserInfo": "542957111@qq.com",
    "VerifyCode": "318904"
  },
  "MessageType": "ModifyPasswordByVerifyRequest"
}
```

响应示例：

```json
{
  "Body": {
    "CGSId": "cgsa-100024",
    "UserInfo": "542957111@qq.com"
  },
  "MessageType": "ModifyPasswordByVerifyResponse",
  "ResultCode": 0
}
```

参数描述：

|     paramter     |  type  |     description      |
| :--------------: | :----: | :------------------: |
| FindPasswordType |  int   | 2--邮箱， 3-手机号码 |
|   NewPassword    | String |        新密码        |
|     UserInfo     | String |        用户名        |
|    VerifyCode    | String |        验证码        |

## 用户修改密码

请求示例：

```json
{
  "Body": {
    "NewPassword": "c1egiGvVlpAIcjR3EaEiHO1YuRqQcyPOWgpeuNg/Z/WJD2+8DhB8qpBCyRNqme4IOsjMeU97B91EGUfREzdI2w==",
    "OldPassword": "s8z+NUlviH9leO6jAfe8xmIYIQJLATZvak/j/3+1Q0K20uIuzGqUYsCu0VksHwASsuGOy1YRCArba4VVNLdcmw==",
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "ModifyUserPasswordRequest"
}
```

响应示例：

```json
{
  "MessageType": "ModifyUserPasswordResponse",
  "Body": {
    "SessionId": "184683677956"
  },
  "ResultCode": 0
}
```

参数描述：

|  paramter   |  type  | description |
| :---------: | :----: | :---------: |
| NewPassword | String |   新密码    |
| OldPassword | String |   旧密码    |
|  UserName   | String |   用户名    |

## 查询设备在线状态


响应示例：

```json
{
  "Body": {
    "DeviceId": "A99L42100000029",
    "Status": "1"
  },
  "MessageType": "NotifyDeviceStatus",
  "ResultCode": 0
}
```

参数描述：

| paramter |  type  |    description    |
| :------: | :----: | :---------------: |
| DeviceId | String |      设备ID       |
| Status | Integer | 0-不在线， 1-在线 |

## 查询设备联网状态

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99OA2100000073",
    "SessionId": "76675",
    "UserName": "542957111@qq.com",
    "UserType": 25,
    "AccessToken": "xxxxxxxxxxxxxxxxxxx"
  },
  "MessageType": "QueryDeviceNetOnlineRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99OA2100000073",
    "IsOnline": 1,
    "SessionId": "1937031987397",
    "UserName": "542957111@qq.com",
    "UserType": 25
  },
  "MessageType": "QueryDeviceNetOnlineResponse",
  "ResultCode": 0
}
```

参数描述：

| paramter |  type  |    description    |
| :------: | :----: | :---------------: |
| DeviceId | String |      设备ID       |
| IsOnline |  int   | 0-不在线， 1-在线 |

## 获取设备列表

请求示例：

```json
{
  "Body": {
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1,
    "SessionId": "xx"
  },
  "MessageType": "GetUserDeviceListRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceList": [
      {
        "AreaId": "",
        "DeviceCap": "2010101",
        "DeviceHdwVer": "M01C17S17W02",
        "DeviceId": "A99L42100000029",
        "DeviceName": "doorbell",
        "DeviceOwner": 1,
        "DeviceSfwVer": "SD202_V252-11-46",
        "DeviceType": 6,
        "MediaTransportType": 2,
        "Status": 0,
        "StreamPassword": "123",
        "StreamUser": "123"
      }
    ],
    "SessionId": "64424563064",
    "UserName": "542957111@qq.com"
  },
  "MessageType": "GetUserDeviceListResponse",
  "ResultCode": 0
}
```

参数描述：

|      paramter      |  type  |   description    |
| :----------------: | :----: | :--------------: |
|      UserName      | String |      用户名      |
|       AreaId       | String |      未使用      |
|     DeviceCap      | String |    设备能力集    |
|    DeviceHdwVer    |  int   |   设备硬件版本   |
|      DeviceId      |  int   |      设备ID      |
|     DeviceName     | String |     设备别名     |
|    DeviceOwner     | String |  0-分享、1-拥有  |
|    DeviceSfwVer    | String |   设备软件版本   |
|     DeviceType     |  int   |   1-IPC, 2-NVR   |
| MediaTransportType |  int   |   1-TUTK,2-P2P   |
|       Status       |  int   | 0-不在线，1-在线 |
|   StreamPassword   | String |      未使用      |
|     StreamUser     | String |      未使用      |

## 设备添加-获取绑定TOKEN

请求示例：

```json
{
  "Body": {
    "DeviceId": "xxx",
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "SessionId": "",
    "UserType": 1
  },
  "MessageType": "GetBindTokenRequest"
}
```

响应示例：

```json
{
  "MessageType": "GetBindTokenResponse",
  "Body": {
    "CGSId": "cgsa-100003",
    "SessionId": "68719531293",
    "BindToken": "4CDLTKT8AKM6"
  },
  "ResultCode": 0
}
```

参数描述：

| paramter  |  type  | description |
| :-------: | :----: | :---------: |
| UserName  | String |   用户名    |
| BindToken | String |  绑定TOKEN  |

## 设备添加-生产二维码规则

```
String qrText = appName + "\n" +
        ssid + "\n" +
        psw + "\n" +
        token + "\n" +
        server + "\n" +
        0;

配网
String qrText = appName + "\n" +
        ssid + "\n" +
        psw + "\n" +
        "\n" +
        server + "\n" +
        0; 
        
```

参数描述：

| paramter |  type  | description |
| :------: | :----: | :---------: |
| appName  | String |  应用名称   |
|   ssid   | String |  WIFI名称   |
|   psw    | String |  WIFI密码   |
|  token   | String |  绑定TOKEN  |
|  server  | String | 服务器类型  |

## 设备添加-查询设备绑定状态

请求示例：

```json
{
  "Body": {
    "BindToken": "4CDLTKT8AKM6",
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1,
    "SessionId": ""
  },
  "MessageType": "QueryUserBindResultRequest"
}
```

响应示例：

```json
{
  "MessageType": "QueryUserBindResultResponse",
  "Body": {
    "CGSId": "cgsa-100003",
    "SessionId": "60129596705",
    "DeviceId": "xxxx",
    "ResultDescribe": "new"
  },
  "ResultCode": -10000
}
```

参数描述：

|    paramter    |  type  |                         description                          |
| :------------: | :----: | :----------------------------------------------------------: |
|    UserName    | String |                            用户名                            |
|   BindToken    | String |                            未使用                            |
| ResultDescribe | String |                             描述                             |
|   ResultCode   |  int   | 0-绑定成功，-10000-未绑定，可继续查询，-10130-TOKEN失效，-10128-已被其他用户绑定， |

## 设备添加-APP分享设备

请求示例：

```json
{
  "Body": {
    "AreaId": "",
    "DeviceId": "A99L42100000029",
    "DeviceName": "",
    "DeviceOwner": 0,
    "DeviceType": 1,
    "StreamPassword": "",
    "StreamUser": "",
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1,
    "AppMatchType": 1
  },
  "MessageType": "BindSmartDeviceRequest"
}
```

响应示例：

```json
{
  "MessageType": "BindSmartDeviceResponse",
  "Body": {
    "SessionId": "60129596813",
    "DeviceId": "A99L42100000029"
  },
  "ResultCode": -10091
}
```

参数描述：

|    paramter    |  type  |  description   |
| :------------: | :----: | :------------: |
|     AreaId     | String |     未使用     |
|    DeviceId    | String |     设备ID     |
|   DeviceName   | String |    设备别名    |
|  DeviceOwner   |  int   | 0-分享、1-拥有 |
|   DeviceType   |  int   |  1-IPC, 2-NVR  |
| StreamPassword | String |     未使用     |
|   StreamUser   | String |     未使用     |
|    UserName    | String |     用户名     |
|   ResultCode   |  int   |     0-成功     |
|   AppMatchType   |  int   |     1     |

## 设备添加-APP解绑设备

请求示例：

```json
{
  "Body": {
    "DeviceId": "A99L42100000029",
    "DeviceOwner": 1,
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1,
    "SessionId": ""
  },
  "MessageType": "UnbindSmartDeviceRequest"
}
```

响应示例：

```json
{
  "MessageType": "UnbindSmartDeviceResponse",
  "Body": {
    "SessionId": "60129596880",
    "DeviceId": "A99L42100000029"
  },
  "ResultCode": 0
}
```

参数描述：

|  paramter   |  type  |  description   |
| :---------: | :----: | :------------: |
|  DeviceId   | String |     设备ID     |
| DeviceOwner |  int   | 0-分享、1-拥有 |
|  UserName   | String |     用户名     |
| ResultCode  |  int   |     0-成功     |

## 设备添加-修改设备别名

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99OA2100000073",
    "DeviceName": "SSCAMCS101_111",
    "StreamPassword": "",
    "StreamUser": "",
    "AccessToken": "9899D6B1ED8B4C6DB7E3B651E337047F",
    "SessionId": "0",
    "UserName": "542957111@qq.com",
    "UserType": 28
  },
  "MessageType": "ModifyDeviceAttrRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99OA2100000073",
    "SessionId": "2379412772696"
  },
  "MessageType": "ModifyDeviceAttrResponse",
  "ResultCode": 0
}
```

参数描述：

|    paramter    |  type  | description |
| :------------: | :----: | :---------: |
|    DeviceId    | String |   设备ID    |
|   DeviceName   | String |  设备别名   |
| StreamPassword | String |    无用     |
|   StreamUser   |  int   |    无用     |



# TF文件-获取文件日期列表

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99Z72100000015",
    "DeviceParam": {
      "CMDType": 971,
      "channel": 0
    },
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "BypassParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99Z72100000015",
    "DeviceParam": {
      "CMDType": 972,
      "page_data": "202008050003|202008070003|202008100003|202008110003"
    },
    "SessionId": "201863463107"
  },
  "MessageType": "BypassParamResponse",
  "ResultCode": 0
}
```

参数描述：

| paramter  |  type  |                      description                       |
| :-------: | :----: | :----------------------------------------------------: |
| page_data | String | 例如：202008050003，表示2020年08月05日这天含有录像文件 |

## TF文件-通过日期获取文件列表

通过P2P方式获取

请求示例：

```json
SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//20200806
String day = BackPlayDatesActivity.forMonth.monthTime;
Date parse = format.parse(day);
long startTime = parse.getTime()/1000;
//P2P获取文件列表
DevStreamActivity.devSession.NativeGetRecDayEventRefresh(0, (int) startTime,1,0);
```

响应示例：

```json
{
  "result": 0,
  "total_num": 1,
  "page_num": 1,
  "day_event_list": [
    {
      "start_time": 1597116179,
      "end_time": 1597116193,
      "type": 1,
      "reserved": [
        -1225915988,
        0
      ]
    }
  ],
  "reserved": [
    0,
    0
  ]
}
```

参数描述：

| paramter | type | description |
| :--------: | :--: | :--------------: | 
| total_num | int | 总页数 |
| page_num | int | 当前页数 | 
| start_time | int | 开始时间（秒值） | 
| end_time | int | 结束时间（秒值） |

# 设备OTA升级-流程

1.通过获取设备信息("CMDType":816)，获取设备类型（a_type值）和设备软件版本（a_software_version值）

2.通过设备类型（a_type值），在服务器中查询该设备类型("MessageType":"CheckNewerVerRequest")，最新的软件版本信息("Version":"
xxx.xxx.xxx.xxx")

3.对比设备软件版本（a_software_version值）和服务器中查询到的软件版本("Version":"xxx.xxx.xxx.xxx")，判断是否需要软件升级

4.发送升级命令("CMDType":4372)，将服务器中软件信息发送给设备端

5.轮训查询升级状态("CMDType":4374)，upgrade_progress值为100 或者upgrade_status值为2时，表示升级成功，然后等待设备重新连接

## 设备OTA升级-获取设备信息

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99G82100000049",
    "CMDTypeList": [
      ....
    ],
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "AppGetDeviceParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99G82100000049",
    "ParamArray": [
     
      {
        "CMDType": 817,
        "DeviceParam": {
          ....
        },
      {
        "CMDType": 818,
        "DeviceParam": {
          ....
        }
      }
    ]
    },
    "SessionId": "206158430435"
  },
  "MessageType": "AppGetDeviceParamResponse",
  "ResultCode": 0
}
```

参数描述：

|      paramter      |  type  |             description              |
| :----------------: | :----: | :----------------------------------: |
|       CMDType       | String |          对应的参数，参考DevParamCmdType类        |
|       DeviceParam       | Object |          对应详细参数        |


## 设备OTA升级-查询设备最新软件版本

请求示例：

```json
{
  "Body": {
    "DeviceType": "SSCAMPT201",
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "CheckNewerVerRequest"
}
```

响应示例：

```json
{
  "Body": {
    "CGSId": "cgsa-100024",
    "Desc": "1.fixed some bugs ",
    "DeviceType": "SSCAMPT201",
    "FileSize": 2233183,
    "MD5": "ff790d5dde39f74d4b19378d7dd90263",
    "Url": "http://lbs.security.mysmitch.com/download/SSCAMPT201/SSCAMPT201.M01C18S17W05.370.app.E_800.tar.bz2",
    "Version": "E_800.U5810HCT.010.370"
  },
  "MessageType": "CheckNewerVerResponse",
  "ResultCode": 0
}
```

参数描述：

|  paramter  |  type  |                  description                   |
| :--------: | :----: | :--------------------------------------------: |
| DeviceType | String |                    设备类型                    |
|    Desc    | String |                  更新内容描述                  |
|  FileSize  |  int   |                 软件更新包大小                 |
|    MD5     | String |                软件更新包MD5值                 |
|    Url     | String |               软件更新包下载地址               |
|  Version   | String | 软件版本（可与设备软件比较，判断是否需要升级） |

## 设备OTA升级-发送升级命令

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99G82100000049",
    "DeviceParam": {
      "upgrade_channel": 0,
      "upgrade_len": 2233183,
      "upgrade_md5": "ff790d5dde39f74d4b19378d7dd90263",
      "upgrade_type": 1,
      "upgrade_url": "http://lbs.security.mysmitch.com/download/SSCAMPT201/SSCAMPT201.M01C18S17W05.370.app.E_800.tar.bz2",
      "upgrade_version": "E_800.U5810HCT.010.370",
      "CMDType": 4372,
      "channel": 0
    },
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "BypassParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99Z72100000015",
    "DeviceParam": {
      "CMDType": 4373
    },
    "SessionId": "201863463107"
  },
  "MessageType": "BypassParamResponse",
  "ResultCode": 0
}
```

参数描述：

|    paramter     |  type  |        description        |
| :-------------: | :----: | :-----------------------: |
| upgrade_channel |  int   |    通道号 （默认：0）     |
|   upgrade_len   |  int   |        软件包大小         |
|   upgrade_md5   | String |         软件包MD5         |
|  upgrade_type   |  int   | 0-主设备升级， 1-门铃升级 |
|   upgrade_url   | String |      软件包下载地址       |
| upgrade_version | String |        软件包版本         |

## 设备OTA升级-查询升级进度

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99G82100000049",
    "DeviceParam": {
      "CMDType": 4374,
      "channel": 0
    },
    "SessionId": "146"
  },
  "MessageType": "BypassParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99G82100000049",
    "DeviceParam": {
      "CMDType": 4375,
      "upgrade_item_type": 0,
      "upgrade_progress": 10,
      "upgrade_status": 0
    },
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "BypassParamResponse",
  "ResultCode": 0
}
```

参数描述：

| paramter | type | description | 
| :---------------: | :--: | :----------------------------------------------------------: | 
| upgrade_item_type | int | typedef enum<br/>{  <br/>  E_UPGRADE_ERROR = 0x72,<br/>  E_UPGRADE_STATION = 0x73,  <br/>
E_UPGRADE_CAMERA_DSP = 0x74,<br/>  E_UPGRADE_CAMERA_WIFI = 0x75,<br/>  E_UPGRADE_CAMERA_MCU =
0x76,  <br/>  E_UPGRADE_STATION_APP = 0x77,  <br/>} UPGRADE_ITEM_TYPE; |
| upgrade_progress | int | 更新进度（0-100） | 
| upgrade_status | int | typedef enum{<br/>  E_UPGRADE_ING = 0, //(0)正在升级<br/>
E_UPGRADE_FAILED, //(1)升级失败<br/>  E_UPGRADE_OK, //(2)升级成功<br/>  E_UPGRADE_SEND_COMPELETE, //(3)
发送文件完成<br/>  E_UPGRADE_NOEXIST, //(4)升级文件不存在或校验失败<br/>  E_UPGRADE_TIMEOUT = 780, //(780)
DSP升级超时  <br/>  E_UPGRADE_SEND_ERR, //(781)DSP升级发送失败  <br/>  E_UPGRADE_WRITE_FLASH_ERR, //(782)
DSP升刷flash<br/>  E_UPGRADE_NOT_READY_ERR, //(783)DSP设备没准备好  <br/>  E_UPGRADE_REPORT_ERR, //(784)
DSP出错<br/>  E_UPGRADE_E,              <br/>}UPRADE_STATE_E; |

## 设备OTA升级-发送升级命令

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99G82100000049",
    "DeviceParam": {
      "upgrade_channel": 0,
      "upgrade_len": 2233183,
      "upgrade_md5": "ff790d5dde39f74d4b19378d7dd90263",
      "upgrade_type": 1,
      "upgrade_url": "http://lbs.security.mysmitch.com/download/SSCAMPT201/SSCAMPT201.M01C18S17W05.370.app.E_800.tar.bz2",
      "upgrade_version": "E_800.U5810HCT.010.370",
      "CMDType": 4372,
      "channel": 0
    },
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "BypassParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99Z72100000015",
    "DeviceParam": {
      "CMDType": 4373
    },
    "SessionId": "201863463107"
  },
  "MessageType": "BypassParamResponse",
  "ResultCode": 0
}
```

参数描述：

|    paramter     |  type  |        description        |
| :-------------: | :----: | :-----------------------: |
| upgrade_channel |  int   |    通道号 （默认：0）     |
|   upgrade_len   |  int   |        软件包大小         |
|   upgrade_md5   | String |         软件包MD5         |
|  upgrade_type   |  int   | 0-主设备升级， 1-门铃升级 |
|   upgrade_url   | String |      软件包下载地址       |
| upgrade_version | String |        软件包版本         |



## 设备设置-设置TF卡手动录像参数

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "DeviceParam": {
      "manual_record_switch": 1,
      "CMDType": 983,
      "channel": 0
    },
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "BypassParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "DeviceParam": {
      "CMDType": 984
    },
    "SessionId": "22767624617217"
  },
  "MessageType": "BypassParamResponse",
  "ResultCode": 2
}
```

参数描述：

| paramter | type | description | 
| :------------------: | :--: | :----------------------------------------------------------: |
| manual_record_switch | int | 手动录像开关（ 0-关，1-开） |
| ResultCode | int | 0-设置成功， 2-无卡， 3-卡错误， 4-存储卡剩余容量不足 ，5-正在录像，6-持续记录 |

## 设备设置-获取移动侦测设置信息

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "SessionId": "XXX",
    "CMDTypeList": [
      "cap16"
    ],
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "AppGetDeviceParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "CMDType": "cap16",
    "DeviceParam": {
      "c_sensitivity": 60,
      "c_switch": 1,
      "e_algo_type": 0,
      "s_threshold": 0,
      "un_enable": 65535,
      "un_height": 0,
      "un_mode": 1,
      "un_start_x": 0,
      "un_start_y": 0,
      "un_submode": 3,
      "un_width": 0
    },
    "SessionId": "24975237622626"
  },
  "MessageType": "AppGetDeviceParamResponse",
  "ResultCode": 0
}
```

参数描述：

| paramter | type | description |
| :-----------: | :--: | :----------------------------------------------------------: | 
| c_sensitivity | int | 灵敏度，高（30）、中（60）、低（90） | 
| c_switch | int | 0-开启，1-关闭 | 
| e_algo_type | int | 0 未使用 | 
| s_threshold | int | 0 未使用 | 
| un_enable | int | 分屏模式选择区域，例如4*4模式，屏幕被分为16格，顺序从左到右，从上到下。区域全选二进制表示： 1111 1111 1111 1111；若左上第一块未选，则这样表示：1111 1111 1111 1110；若第一行，第四列，未选，则表示：1111 1111 1111 0111 |
| un_height | int | 未使用 | 
| un_mode | int | 0-手动划分坐标（暂不支持）、1- 自动多分屏坐标 | 
| un_start_x | int | 未使用 | 
| un_start_y | int | 未使用 |
| un_submode | int | 分屏模式，3（4*4分屏模式） | 
| un_width | int | 未使用 |

## 设备设置-修改移动侦测设置信息

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "ParamArray": [
      {
        "CMDType": "cap16",
        "DeviceParam": {
          "c_sensitivity": 60,
          "c_switch": 1,
          "e_algo_type": 0,
          "s_threshold": 0,
          "un_enable": 65503,
          "un_height": 0,
          "un_mode": 1,
          "un_start_x": 0,
          "un_start_y": 0,
          "un_submode": 3,
          "un_width": 0,
          "channel": 0
        }
      }
    ],
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "AppSetDeviceParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011"
  },
  "MessageType": "AppSetDeviceParamResponse",
  "ResultCode": 0
}
```

## 设备校时 - 通过拉流接口校时

示例：

```json
int timestamp = (int) (System.currentTimeMillis() / 1000L);
int timezone = (TimeZone.getDefault().getRawOffset() / 360000) + 24;//5:30->5.5时区(55)
if (TimeZone.getDefault().inDaylightTime(new Date())) {
timezone+=10;
}
devSession.NativeOpenStream(0,"user", 0, 2, timestamp, timezone);
```

参数说明：

| paramter | type | description | 
| :-------: | :--: | :--------------: | 
| timestamp | int | 手机时间（秒值） | | timezone | int | 时区 |

注意：

该方法在设备Reset后，第一次调用NativeOpenStream有效。

## 设备校时 - 流程

首先获取设备校时信息（设备校时 - 获取设备校时信息）--> 然后将修改时区后的信息设置给设备（设备校时 - 设置设备校时信息并校时）

## 设备校时 - 获取设备校时信息

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99Z72100000015",
    "DeviceParam": {
      "CMDType": 997,
      "channel": 0
    },
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "BypassParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99Z72100000015",
    "DeviceParam": {
      "CMDType": 998,
      "a_NtpServer": "1.cn.pool.ntp.org",
      "un_EuroTime": 0,
      "un_NtpOpen": 1,
      "un_NtpRefTime": 300,
      "un_TimeZone": 55,
      "un_ntp_port": 123
    },
    "SessionId": "210453403361"
  },
  "MessageType": "BypassParamResponse",
  "ResultCode": 0
}
```

参数描述：

|   paramter    |  type  |    description    |
| :-----------: | :----: | :---------------: |
|  a_NtpServer  | String | 1.cn.pool.ntp.org |
|  un_EuroTime  |  int   |         0         |
|  un_NtpOpen   |  int   |         1         |
| un_NtpRefTime |  int   |        300        |
|  un_TimeZone  |  int   |     设备时区      |
|  un_ntp_port  |  int   |        123        |

## 设备校时 - 设置设备校时信息并校时

请求示例：

```json
参数值计算方式
un_TimeZone：
int un_TimeZone = TimeZone.getDefault().getRawOffset() / 360000;//5:30->5.5时区(55)
boolean isLightTime = TimeZone.getDefault().inDaylightTime(new Date());
if (isLightTime) {
timeZone+=10;
}
AppTimeSec：
int AppTimeSec = System.currentTimeMillis() / 1000;


{"Body": {"DeviceId": "Z99Z72100000015", "DeviceParam":{"AppTimeSec": 1598262182, "a_NtpServer": "1.cn.pool.ntp.org", "un_EuroTime": 0, "un_NtpOpen": 1, "un_NtpRefTime": 300, "un_TimeZone": 55, "un_ntp_port": 123, "CMDType": 995, "channel": 0}, "AccessToken":"B07367BD4387464F956DF076C906C58C", "UserName": "542957111@qq.com", "UserType": 1}, "MessageType": "BypassParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99Z72100000015",
    "DeviceParam": {
      "CMDType": 996
    },
    "SessionId": "210453403361"
  },
  "MessageType": "BypassParamResponse",
  "ResultCode": 0
}
```

参数描述：

| paramter | type | description | 
| :--------: | :--: | :--------------: | 
| AppTimeSec | int | 手机时间（秒值） |

## 设备设置 - 获取物体跟踪开关

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "SessionId": "XXX",
    "CMDTypeList": [
      "cap51"
    ],
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "AppGetDeviceParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "CMDType": "cap51",
    "DeviceParam": {
      "un_switch": 0
    },
    "SessionId": "24975237622626"
  },
  "MessageType": "AppGetDeviceParamResponse",
  "ResultCode": 0
}
```

参数描述：

| paramter | type | description | 
| :-------: | :--: | :---------: | 
| un_switch | int | 0-OFF, 1-ON |

## 设备设置 - 设置物体跟踪开关

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "ParamArray": [
      {
        "CMDType": "cap51",
        "DeviceParam": {
          "un_switch": 1
        }
      }
    ],
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "AppSetDeviceParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99Z72100000015"
  },
  "MessageType": "AppSetDeviceParamResponse",
  "ResultCode": 0
}
```
参数描述：

| paramter | type | description |
| :-------: | :--: | :---------: |
| un_switch | int | 0-OFF, 1-ON |
## 设备设置 - 获取人形侦测报警开关

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "SessionId": "XXX",
    "CMDTypeList": [
      "cap52"
    ],
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "AppGetDeviceParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "CMDType": "cap52",
    "DeviceParam": {
      "un_switch": 0
    },
    "SessionId": "24975237622626"
  },
  "MessageType": "AppGetDeviceParamResponse",
  "ResultCode": 0
}
```

参数描述：

| paramter | type | description | 
| :-------: | :--: | :---------: | 
| un_switch | int | 0-OFF, 1-ON |

## 设备设置 - 设置人形侦测报警开关

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "ParamArray": [
      {
        "CMDType": "cap52",
        "DeviceParam": {
          "un_switch": 1
        }
      }
    ],
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "AppSetDeviceParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99Z72100000015"
  },
  "MessageType": "AppSetDeviceParamResponse",
  "ResultCode": 0
}
```

## 设备设置 - 视频图像旋转

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "ParamArray": [
      {
        "CMDType": "cap32",
        "DeviceParam": {
          "mirror_mode": 1
        }
      }
    ],
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "AppSetDeviceParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99N42100000022"
  },
  "MessageType": "AppSetDeviceParamResponse",
  "ResultCode": 0
}
```

参数描述：

| paramter | type | description |
| :------: | :--: | :----------------------------------------: | 
| mirror_mode | int | 视频镜像模式，0-无，1-水平，2-竖直，3-翻转 |



## 设备设置 - 获取夜视信息

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "SessionId": "XXX",
    "CMDTypeList": [
      "cap13"
    ],
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "AppGetDeviceParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "CMDType": "cap13",
    "DeviceParam": {
      "un_auto": 0,
      "un_day_night": 0
    },
    "SessionId": "24975237622626"
  },
  "MessageType": "AppGetDeviceParamResponse",
  "ResultCode": 0
}
```

参数描述：

| paramter | type | description |
| :----------: | :--: | :------------------: |
| un_auto | int | 自动(0-关闭，1-开启) |
| un_day_night | int | 0-关闭，1-开启 |

# 设备设置 - 设置夜视信息

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "ParamArray": [
      {
        "CMDType": "cap13",
        "DeviceParam": {
          "un_auto": 1,
          "un_day_night": 1
        }
      }
    ],
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "AppSetDeviceParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99N42100000022"
  },
  "MessageType": "AppSetDeviceParamResponse",
  "ResultCode": 0
}
```

# 设备设置 - 获取声音侦测信息

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "SessionId": "XXX",
    "CMDTypeList": [
      "cap19"
    ],
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "AppGetDeviceParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "CMDType": "cap19",
    "DeviceParam": {
      "un_switch": 0,
      "un_sensitivity": 0
    },
    "SessionId": "24975237622626"
  },
  "MessageType": "AppGetDeviceParamResponse",
  "ResultCode": 0
}
```

参数描述：

| paramter | type | description |
| :------------: | :--: | :--------------------------------------: | 
| un_switch | int | 0:off 1:on |
| un_sensitivity | int | 灵敏度 LOW = 1; MIDDLE = 2; HEIGH = 3; |

# 设备设置 - 设置声音侦测信息

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "ParamArray": [
      {
        "CMDType": "cap19",
        "DeviceParam": {
          "un_switch": 1,
          "un_sensitivity": 1
        }
      }
    ],
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "AppSetDeviceParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99N42100000017"
  },
  "MessageType": "AppSetDeviceParamResponse",
  "ResultCode": 0
}
```


## 设备设置 - 码流设置

请求示例：

```json
int streamQuailty = 0;
session.NativeSetStreamQuailty(0, streamQuailty);
```

参数描述：

| paramter | type | description | 
| :-----------: | :--: | :-----------------------------: | 
| streamQuailty | int | 0-主码流（HD）， 1-次码流（SD） |

可以根据流解码之后的YUV的宽高来判断，当前码流的主次，如下解码回调中的 width 和 height 来判断

```java
@Override
public void decCallBack(DecType type,byte[]data,int dataSize,int width,int height,int rate,int ch,int flag){
        //解码回调
        if(DecType.YUV420==type){
        ByteBuffer buf=ByteBuffer.wrap(data);
        glRenderer.update(buf,width,height);
        }else if(DecType.AUDIO==type){
        audioTrack.write(data,data.length);
        }
        }
```

## 设备设置 - 设置摄像头开关

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "ParamArray": [
      {
        "CMDType": "cap24",
        "DeviceParam": {
          "device_switch": 1
        }
      }
    ],
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "AppSetDeviceParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99N42100000022",
  },
  "MessageType": "AppSetDeviceParamResponse",
  "ResultCode": 0
}
```

参数描述：

| paramter | type | description | 
| :-----------: | :--: | :---------: | 
| device_switch | int | 0-OFF, 1-ON |



## 设备设置 - 设置摄像头麦克风开关

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "ParamArray": [
      {
        "CMDType": "cap25",
        "DeviceParam": {
          "device_mic_switch": 1
        }
      }
    ],
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "AppSetDeviceParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99N42100000017"
  },
  "MessageType": "AppSetDeviceParamResponse",
  "ResultCode": 0
}
```

参数描述：

| paramter | type | description | 
| :---------------: | :--: | :---------: | 
| device_mic_switch | int | 0-OFF, 1-ON |



## 设备设置 - 格式化TF卡

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99N42100000017",
    "DeviceParam": {
      "format": 1,
      "CMDType": 987,
      "channel": 0
    },
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "BypassParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99N42100000017",
    "DeviceParam": {
      "CMDType": 988,
      "a_free_size": 7634,
      "a_total_size": 15173,
      "a_used_size": 7539
    },
    "SessionId": "223338307023"
  },
  "MessageType": "BypassParamResponse",
  "ResultCode": 0
}
```

参数描述：

| paramter | type | description | 
| :----------: | :--: | :---------------------------------------------: | 
| format | int | 0-查询格式化进度， 1-执行格式化TF卡 | 
| a_free_size | int | 剩余空间（M） | 
| a_total_size | int | 总空间（M） | 
| a_used_size | int | 已用空间（M） | 
| ResultCode | int | 0-格式化成功， 1-正在格式化， 2/其他-格式化失败 |

## 设备设置 - 状态指示灯设置

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "ParamArray": [
      {
        "CMDType": "cap23",
        "DeviceParam": {
          "device_led_switch": 1
        }
      }
    ],
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "AppSetDeviceParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99N42100000017"
  },
  "MessageType": "AppSetDeviceParamResponse",
  "ResultCode": 0
}
```

参数描述：

| paramter | type | description |
| :---------------: | :--: | :---------: |
| device_led_switch | int | 0-OFF, 1-ON |



## 设备设置 - 设置声报光警参数

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "ParamArray": [
      {
        "CMDType": "cap40",
        "un_switch": 0,
        "DeviceParam": {
          "Schedule": {
            "un_switch":0,
            "un_repeat":0,
            "start_time": 0,
            "end_time": 0
          },
          "Audio": {
            "un_switch": 0,
            "un_times": 0,
            "un_volume": 0,
            "un_type": 0,
            "url": ""
          },
          "Light": {
            "un_switch": 0,
            "un_duration": 0
          }
        }
      }
    ],
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "AppSetDeviceParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99G82100000065"
  },
  "MessageType": "AppSetDeviceParamResponse",
  "ResultCode": 0
}
```



## 设备设置 - 获取声光报警参数

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "SessionId": "XXX",
    "CMDTypeList": [
      "cap40"
    ],
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "AppGetDeviceParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "ParamArray": [
      {
        "CMDType": "cap40",
        "un_switch": 0,
        "DeviceParam": {
          "Schedule": {
            "un_switch":0,
            "un_repeat":0,
            "start_time": 0,
            "end_time": 0
          },
          "Audio": {
            "un_switch": 0,
            "un_times": 0,
            "un_volume": 0,
            "un_type": 0,
            "url": ""
          },
          "Light": {
            "un_switch": 0,
            "un_duration": 0
          }
        }
      }
    ],
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "AppGetDeviceParamResponse"
}
```

参数描述：

| paramter | type | description | 
| :-------------: | :--: | :---------: | 
| un_switch | int | 0-OFF, 1-ON  总开关| 
| Schedule.un_switch | int | 0-OFF, 1-ON 定时器开关| 
| Schedule.un_repeat | int | 定时器重复次数| 
| Schedule.start_time | long | 开始时间| 
| Schedule.end_time | long | 结束时间| 
| Audio.un_switch | int | 0-OFF , 1-ON 声音开关 |
| Audio.un_times | int | 次数 |
| Audio.un_volume | int | 音量 |
| Audio.un_type | int | 播放内容 |
| Audio.url | String | 文件地址 |
| Light.un_switch | int | 0-OFF , 1-ON 灯光警戒 |
| Light.un_duration | int | 闪烁时长 |



## 设备设置 - 播放报警声

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99G82100000065",
    "DeviceParam": {
      "CMDType": 957,
      "channel": 0
    },
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "BypassParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99G82100000065",
    "DeviceParam": {
      "CMDType": 958
    },
    "SessionId": "3156804155127"
  },
  "MessageType": "BypassParamResponse",
  "ResultCode": 0
}
```

## 设备设置 - 停止报警声

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99G82100000065",
    "DeviceParam": {
      "CMDType": 959,
      "channel": 0
    },
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "BypassParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99G82100000065",
    "DeviceParam": {
      "CMDType": 1558
    },
    "SessionId": "3156804155127"
  },
  "MessageType": "BypassParamResponse",
  "ResultCode": 0
}
```

## 设备设置 - 获取设备周边WIFI

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "DeviceParam": {
      "CMDType": 1554,
      "channel": 0
    },
    "AccessToken": "C0A9A05F41AB4E94BEBCC2CFE51164A6",
    "SessionId": "null",
    "UserName": "sample@test.com",
    "UserType": 1
  },
  "MessageType": "BypassParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "DeviceParam": {
      "CMDType": 1555,
      "ssid_info": [
        {
          "a_SSID": "abcd",
          "un_signal_level": 77
        },
        {
          "a_SSID": "Source",
          "un_signal_level": 68
        }
      ],
      "un_ssid_num": 2
    },
    "SessionId": "8379482658635"
  },
  "MessageType": "BypassParamResponse",
  "ResultCode": 0
}
```

参数描述：

|    paramter     |  type  |  description   |
| :-------------: | :----: | :------------: |
|     a_SSID      | String |    SSID名称    |
| un_signal_level |  int   | 信号强弱 0-100 |

## 设备设置 - 更改设备连接WIFI

请求示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "DeviceParam": {
      "a_SSID": "abcd",
      "a_passwd": "98765432xxx",
      "e_wifi_mode": 0,
      "un_encrypt": 0,
      "CMDType": 834,
      "channel": 0
    },
    "SessionId": "71175",
    "UserName": "sample@test.com",
    "UserType": 1
  },
  "MessageType": "BypassParamRequest"
}
```

响应示例：

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "DeviceParam": {
      "CMDType": 835
    },
    "SessionId": "8379482658635"
  },
  "MessageType": "BypassParamResponse",
  "ResultCode": 0
}
```

参数描述：

|  paramter   |  type  | description |
| :---------: | :----: | :---------: |
|   a_SSID    | String |  WIFI ssid  |
|  a_passwd   | String |  WIFI 密码  |
| e_wifi_mode |  int   |      0      |
| un_encrypt  |  int   |      0      |

## 云存储 - 查询用户设备云套餐信息

请求示例：

```json
https://css.security.mysmitch.com/api/cloudstore/cloudstore-service/service/data-valid?device_id=ZAAHA2100000003&token=3c25f4ec-d618-11ea-a45f-0a95118f680a&username=542957111@qq.com&version=1.0&check=novel
```

响应示例：

```json
{
  "code": "0",
  "data": [
    {
      "planName": "7天云存储",
      "dataLife": 7,
      "dateLife": 7,
      "serviceLife": 360,
      "orderCount": 1,
      "count": 0,
      "timeStamp": 0,
      "id": 938,
      "orderNo": "201803131813157524",
      "planId": 100,
      "deviceId": "ZAAHA2100000003",
      "status": "1",
      "startTime": "1599644555",
      "preinvalidTime": "1630748555",
      "dataExpiredTime": "1631353355",
      "switchEnable": "1",
      "createUser": "542957111@qq.com",
      "createTime": "1599644555"
    }
  ]
}
```

参数描述：

|    paramter     |  type  |              description               |
| :-------------: | :----: | :------------------------------------: |
|    device_id    | String |               被查询设备               |
|      token      | String |        用户登录返回值UserToken         |
|    username     | String |                用户名称                |
|     version     | String |                  1.0                   |
|    planName     | String |                套餐名称                |
|    dataLife     |  int   |              数据保留天数              |
|    dateLife     |  int   |        数据覆盖周期（单位：天）        |
|   serviceLife   |  int   |              套餐时长天数              |
|   orderCount    |  int   |                订单数量                |
|      count      |  int   |                 暂未用                 |
|    timeStamp    |  int   |                 暂未用                 |
|       id        |  int   |                 订单id                 |
|     orderNo     |  int   |                 订单号                 |
|     planId      |  int   |                 套餐ID                 |
|    deviceId     | String |              套餐所属设备              |
|     status      | String | 1:正在使用 0：过期 2：续费 7：移除 |
|    startTime    | String |              开始使用时间              |
| preinvalidTime  | String |              服务过期时间              |
| dataExpiredTime | String |              数据获取时间              |
|  switchEnable   | String |    是否允许使用 1-允许 ， 0-不允许     |
|   createUser    | String |              购买用户账号              |
|   createTime    | String |              服务购买时间              |

## 云存储 - 获取云存储信息

请求示例：POST

```json
https://css.security.mysmitch.com/api/cloudstore/cloudstore-service/aws/sts/check-token?device_id=ZAAHA2100000003&token=3c25f4ec-d618-11ea-a45f-0a95118f680a&username=542957111@qq.com&version=1.0&check=novel
```

响应示例：

```json
{
  "code": "0",
  "data": {
    "creater": "542957111@qq.com",
    "deviceid": "ZAAHA2100000003",
    "Expiration": "Thu Sep 17 10:38:35 UTC 2020",
    "key": "ASIA6A6CPGUIQRVWBBEM",
    "secret": "iJl7XPiXgOOazVM06TGRIyo6GgWPOwUEVnz0627j",
    "token": "FwoGZXIvYXdzEAsaDDcmd+8zR0c8C6V41SKBAR0lebIcjviisgIx6cqqrCINoFnS95ILRv6725GE6VwmMdNGEFWi2YwPvXfPEb3Tu5VzM6uSnCL2klaX56uHe/eB5WODzEJAgUtSWYlaOLrgS1ftrhOkgJmD1EjgS5foQuOdi+5bjkBjgY06H6lPZO+n2VJ6TEaizQjAyykaZ3t1Ziib3Yz7BTIoa2saAT+ZYeGIrYm6lokKg4T9FrnMg6tK/ghCXbaIp8Ma/oIiIiUKfA==",
    "endPoint": "ap-south-1",
    "bucket": "gs3-media-ap-south",
    "durationSeconds": 3600,
    "timeStamp": 1600335515
  }
}
```

参数描述：

|    paramter     |  type  |    description     |
| :-------------: | :----: | :----------------: |
|     creater     | String |      申请用户      |
|   Expiration    | String | 过期时间（0时区）  |
|       key       | String |  亚马逊云服务key   |
|     secret      | String | 亚马逊云服务secret |
|      token      | String | 亚马逊云服务token  |
|    endPoint     | String |      服务节点      |
|     bucket      | String | 亚马逊云服务bucket |
| durationSeconds |  int   |  有效时长（秒值）  |
|    timeStamp    |  int   |  请求时间（秒值）  |

## 云存储 - 获取录像文件列表

请求示例：

```json
https://css.security.mysmitch.com/api/cloudstore/cloudstore-service/move-video/time-line?device_id=ZAAHA2100000003&start_time=1599753600&end_time=1599840000&token=3c25f4ec-d618-11ea-a45f-0a95118f680a&username=542957111@qq.com&version=1.0&check=novel
```

响应示例：

```json
{
  "code": "0",
  "data": [
    {
      "startTime": 1599786254,
      "endTime": 1599786262,
      "alarmType": "1",
      "dateLife": 0,
      "cycle": 0
    },
    {
      "startTime": 1599786340,
      "endTime": 1599786348,
      "alarmType": "1",
      "dateLife": 0,
      "cycle": 0
    }
  ]
}
```

参数描述：

|  paramter  |  type  |       description       |
| :--------: | :----: | :---------------------: |
| start_time |  int   |   查询开始时间（秒）    |
|  end_time  |  int   |   查询结束时间（秒）    |
| device_id  | String |       被查询设备        |
|   token    | String | 用户登录返回值UserToken |
| startTime  |  int   |     TF录像开始时间      |
|  endTime   |  int   |     TF录像结束时间      |
| alarmType  | String |      录像告警类型       |
|  dateLife  |  int   |         暂未用          |
|   cycle    |  int   |         暂未用          |

## 云存储 - 获取录像文件云存储信息

请求示例：

```json
https://css.security.mysmitch.com/api/cloudstore/cloudstore-service/move-video/time-line/details?device_id=ZAAHA2100000003&start_time=1599792311&end_time=1599792431&token=3c25f4ec-d618-11ea-a45f-0a95118f680a&username=542957111@qq.com&version=1.0&check=novel
```

响应示例：

```json
{
  "code": "0",
  "data": [
    {
      "startTime": 1599792310,
      "endTime": 1599792317,
      "alarmType": "1",
      "bucket": "gs3-media-ap-south",
      "key": "7_ZAAHA2100000003/1599792310_001_0007.media",
      "dateLife": 0,
      "cycle": 0
    }
  ]
}
```

参数描述：

|  paramter  |  type  |       description       |
| :--------: | :----: | :---------------------: |
| start_time |  int   |   查询开始时间（秒）    |
|  end_time  |  int   |   查询结束时间（秒）    |
| device_id  | String |       被查询设备        |
|   token    | String | 用户登录返回值UserToken |
| startTime  |  int   |     TF录像开始时间      |
|  endTime   |  int   |     TF录像结束时间      |
| alarmType  | String |      录像告警类型       |
|  dateLife  |  int   |         暂未用          |
|   cycle    |  int   |         暂未用          |

## 云存储 - URL文件地址生成

```json
1.在APP gradle 中添加
implementation 'com.amazonaws:aws-android-sdk-s3:2.7.+'

2.通过云存储信息、录像文件云存储信息，来生成url地址
String accessKeyID = "";
String accessKeySecret = "";
String token = "";
String endPoint = "";
BasicSessionCredentials awsCredentials = new BasicSessionCredentials(
accessKeyID,
accessKeySecret,
token);
AmazonS3Client mS3Client = new AmazonS3Client(awsCredentials);
mS3Client.setRegion(Region.getRegion(endPoint);

Date expiration = new Date();
long expTimeMillis = expiration.getTime();
expTimeMillis += 1000 * 60 * 60;
expiration.setTime(expTimeMillis);

String buket = "";
String keyUrl = "";
GeneratePresignedUrlRequest generatePresignedUrlRequest =
new GeneratePresignedUrlRequest(mBucket, keyUrl)
.withMethod(HttpMethod.GET)
.withExpiration(expiration);
URL URL = mS3Client.generatePresignedUrl(generatePresignedUrlRequest);
//可下载的url地址
String url = URL.toString();            
```

## 云存储 - 获取图片URL下载地址

请求示例：

```json
https://css.security.mysmitch.com/api/cloudstore/cloudstore-service/aws/sts/presignedurl?device_id=ZAAHA2100000003&bucket_name=gs3-pic-ap-south&object_key=7_ZAAHA2100000003/202009171609520bb0000.jpg&token=3c25f4ec-d618-11ea-a45f-0a95118f680a&username=542957111@qq.com&version=1.0&check=novel
```

响应示例：

```json
{
  "code": "0",
  "data": {
    "url": "https://gs3-pic-ap-south.s3.ap-south-1.amazonaws.com/7_ZAAHA2100000003/202009171609520bb0000.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20200917T112305Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=AKIA6A6CPGUIWVANV2EW/20200917/ap-south-1/s3/aws4_request&X-Amz-Signature=f96d012685723fb067fa75ce196f8a6a75b19d6ff8c916b54d37e1c4f2e1c781",
    "bucketName": "gs3-pic-ap-south",
    "deviceId": "ZAAHA2100000003",
    "objectKey": "7_ZAAHA2100000003/202009171609520bb0000.jpg"
  }
}
```

参数描述：

|  paramter   |  type  |       description       |
| :---------: | :----: | :---------------------: |
|  device_id  | String |         设备ID          |
| bucket_name | String |    云存储bucket name    |
| object_key  | String |    云存储object key     |
|    token    | String | 用户登录返回值UserToken |
|  username   | String |         用户名          |
|     url     | String |   可下载的图片url地址   |

## 云存储 - 获取当前已开通套餐的列表

请求示例：

```json
https://css.security.mysmitch.com/api/cloudstore/cloudstore-service/service/list?device_id=ZAAHA2100000003&token=3c25f4ec-d618-11ea-a45f-0a95118f680a&username=542957111@qq.com&version=1.0&check=novel
```

响应示例：

```json
{
  "code": "0",
  "data": [
    {
      "planName": "7天云存储",
      "dataLife": 7,
      "dateLife": 7,
      "serviceLife": 360,
      "orderCount": 1,
      "count": 0,
      "timeStamp": 0,
      "id": 938,
      "orderNo": "201803131813157524",
      "planId": 100,
      "deviceId": "ZAAHA2100000003",
      "status": "1",
      "startTime": "1599644555",
      "preinvalidTime": "1630748555",
      "dataExpiredTime": "1631353355",
      "switchEnable": "1",
      "createUser": "542957111@qq.com",
      "createTime": "1599644555"
    }
  ]
}
```

## 云存储 - 查询云存储套餐列表

请求示例：

```json
https://css.security.mysmitch.com/api/cloudstore/cloudstore-service/plan?token=3c25f4ec-d618-11ea-a45f-0a95118f680a&username=542957111@qq.com&version=1.0&product_code=000002&check=novel
```

响应示例：

```json
{
  "code": "0",
  "data": [
    {
      "planId": 100,
      "planName": "7天云存储",
      "alwaysWriteEnable": "1",
      "planDesc": "一年",
      "enable": "1",
      "renewEnable": "1",
      "freeFlag": "0",
      "dataLife": 7,
      "serviceLife": 360,
      "price": "193",
      "originalPrice": "218",
      "createUser": "admin",
      "createTime": "1578450855",
      "deleteEnable": "0",
      "productCode": "000002",
      "appleId": 90
    },
    {
      "planId": 101,
      "planName": "7天云存储",
      "alwaysWriteEnable": "1",
      "planDesc": "三个月",
      "enable": "1",
      "renewEnable": "1",
      "freeFlag": "0",
      "dataLife": 30,
      "serviceLife": 90,
      "price": "60",
      "originalPrice": "73",
      "createUser": "admin",
      "createTime": "1578450943",
      "deleteEnable": "0",
      "productCode": "000002",
      "appleId": 91
    }
  ]
}
```

参数描述：

|     paramter      |  type  |             description             |
| :---------------: | :----: | :---------------------------------: |
|      planId       |  int   |               套餐ID                |
|     planName      | String |              套餐名称               |
| alwaysWriteEnable | String | 是否支持24小时录制 0=不支持，1=支持 |
|     planDesc      | String |              套餐描述               |
|      enable       | String |    是否可用 0 = 禁用，1=可用    |
|    renewEnable    | String |  是否可延续 0=不可延续，1=可延续  |
|     freeFlag      | String | freeflag免费状态 0=不免费 ，1=免费  |
|     dataLife      |  int   |          数据保存生命周期           |
|    serviceLife    |  int   |              服务时长               |
|       price       | String |             套餐现价格              |
|   originalPrice   | String |             套餐原价格              |
|    createUser     | String |             套餐创建者              |
|    createTime     | String |            套餐创建时间             |
|   deleteEnable    | String |     删除状态 0-未删除，1-已删除     |
|    productCode    | String |              产品类型               |
|      appleId      |  int   |           苹果手机内购ID            |

## 云存储 - 创建订单

请求示例：POST

```json
https://css.security.mysmitch.com/api/pay/pay-service/inland/cloudstore/order/create?device_id=ZAAHA2100000003&plan_id=100&count=1&total_price=193.0&token=3c25f4ec-d618-11ea-a45f-0a95118f680a&username=542957111@qq.com&version=1.0&check=novel
```

响应示例：

```json
{
  "code": "0",
  "data": {
    "orderNo": "202009150752327034",
    "userId": "542957111@qq.com",
    "deviceId": "ZAAHA2100000003",
    "planId": 100,
    "orderCount": 1,
    "totalPrice": "193.0",
    "status": 0,
    "createTime": "20200915075232"
  }
}
```

参数描述：

|  paramter   |  type  |       description       |
| :---------: | :----: | :---------------------: |
|  device_id  | String |         设备ID          |
|   plan_id   |  int   |         套餐ID          |
|    count    |  int   |        套餐份数         |
| total_price | String |          价格           |
|    token    | String | 用户登录返回值UserToken |
|   orderNo   | String |         订单号          |
|   userId    | String |        用户账号         |
|  deviceId   | String |         设备ID          |
|   planId    |  int   |         套餐ID          |
| orderCount  |  int   |        套餐份数         |
| totalPrice  | String |          价格           |
|   status    |  int   |         暂未用          |
| createTime  | String |      订单创建时间       |

## 云存储 - 获取Paypal TOKEN

请求示例：POST

```json
https://css.security.mysmitch.com/api/pay/pay-service/paypal/check/client_token?order_no=202009150836445832&token=3c25f4ec-d618-11ea-a45f-0a95118f680a&username=542957111@qq.com&version=1.0&check=novel
```

响应示例：

```json
{
  "code": "0",
  "data": "eyJ2ZXJzaW9uIjoyLCJlbnZpcm9ubWVudCI6InByb2R1Y3Rpb24iLCJhdXRob3JpemF0aW9uRmluZ2VycHJpbnQiOiJleUowZVhBaU9pSktWMVFpTENKaGJHY2lPaUpGVXpJMU5pSXNJbXRwWkNJNklqSXdNVGd3TkRJMk1UWXRjSEp2WkhWamRHbHZiaUlzSW1semN5STZJbWgwZEhCek9pOHZZWEJwTG1KeVlXbHVkSEpsWldkaGRHVjNZWGt1WTI5dEluMC5leUpsZUhBaU9qRTJNREF5TkRVME9UTXNJbXAwYVNJNklqSXlOVGszWVdJNExUQmpORGd0TkRaa1lTMWhNMkppTFdVeVlUZGxNbUpoWXpabVpTSXNJbk4xWWlJNkltZzNZako0WW5kcmNHUjVjekpvZWpRaUxDSnBjM01pT2lKb2RIUndjem92TDJGd2FTNWljbUZwYm5SeVpXVm5ZWFJsZDJGNUxtTnZiU0lzSW0xbGNtTm9ZVzUwSWpwN0luQjFZbXhwWTE5cFpDSTZJbWczWWpKNFluZHJjR1I1Y3pKb2VqUWlMQ0oyWlhKcFpubGZZMkZ5WkY5aWVWOWtaV1poZFd4MElqcG1ZV3h6Wlgwc0luSnBaMmgwY3lJNld5SnRZVzVoWjJWZmRtRjFiSFFpWFN3aWMyTnZjR1VpT2xzaVFuSmhhVzUwY21WbE9sWmhkV3gwSWwwc0ltOXdkR2x2Ym5NaU9udDlmUS5HYzBVLTFhS3hEd0NzMnVMWUVlUlZNUEJjRWRYTmN4WC02SmRSeWRuOENVRFd4VFo0cHRUb2hVamthZFhCamFCM3VNN2EzLWlxaFN5aDBOOG9wY3IwQSIsImNvbmZpZ1VybCI6Imh0dHBzOi8vYXBpLmJyYWludHJlZWdhdGV3YXkuY29tOjQ0My9tZXJjaGFudHMvaDdiMnhid2twZHlzMmh6NC9jbGllbnRfYXBpL3YxL2NvbmZpZ3VyYXRpb24iLCJncmFwaFFMIjp7InVybCI6Imh0dHBzOi8vcGF5bWVudHMuYnJhaW50cmVlLWFwaS5jb20vZ3JhcGhxbCIsImRhdGUiOiIyMDE4LTA1LTA4In0sImNoYWxsZW5nZXMiOltdLCJjbGllbnRBcGlVcmwiOiJodHRwczovL2FwaS5icmFpbnRyZWVnYXRld2F5LmNvbTo0NDMvbWVyY2hhbnRzL2g3YjJ4YndrcGR5czJoejQvY2xpZW50X2FwaSIsImFzc2V0c1VybCI6Imh0dHBzOi8vYXNzZXRzLmJyYWludHJlZWdhdGV3YXkuY29tIiwiYXV0aFVybCI6Imh0dHBzOi8vYXV0aC52ZW5tby5jb20iLCJhbmFseXRpY3MiOnsidXJsIjoiaHR0cHM6Ly9jbGllbnQtYW5hbHl0aWNzLmJyYWludHJlZWdhdGV3YXkuY29tL2g3YjJ4YndrcGR5czJoejQifSwidGhyZWVEU2VjdXJlRW5hYmxlZCI6ZmFsc2UsInBheXBhbEVuYWJsZWQiOnRydWUsInBheXBhbCI6eyJkaXNwbGF5TmFtZSI6IumrmOaWr+i0neWwlOaZuuiDveWutuWxhea3seWcs+W4gumrmOaWr+i0neWwlOWutuWxheaZuuiDveeUteWtkOaciemZkOWFrOWPuCIsImNsaWVudElkIjoiQWZWYjg5NmRHeVQxMWt6Z1B4UzcwWWRkSUJzeGlVYzktVkxVZzRkSWpEaFBWdi05aEpXVzBlRGRrcmY4dDN3R3dHR3RiU0gwa2Q5anFGYkEiLCJwcml2YWN5VXJsIjoiaHR0cHM6Ly9leGFtcGxlLmNvbSIsInVzZXJBZ3JlZW1lbnRVcmwiOiJodHRwczovL2V4YW1wbGUuY29tIiwiYmFzZVVybCI6Imh0dHBzOi8vYXNzZXRzLmJyYWludHJlZWdhdGV3YXkuY29tIiwiYXNzZXRzVXJsIjoiaHR0cHM6Ly9jaGVja291dC5wYXlwYWwuY29tIiwiZGlyZWN0QmFzZVVybCI6bnVsbCwiYWxsb3dIdHRwIjpmYWxzZSwiZW52aXJvbm1lbnROb05ldHdvcmsiOmZhbHNlLCJlbnZpcm9ubWVudCI6ImxpdmUiLCJ1bnZldHRlZE1lcmNoYW50IjpmYWxzZSwiYnJhaW50cmVlQ2xpZW50SWQiOiJBUktyWVJEaDNBR1hEelc3c09fM2JTa3EtVTFDN0hHX3VXTkMtejU3TGpZU0ROVU9TYU90SWE5cTZWcFciLCJiaWxsaW5nQWdyZWVtZW50c0VuYWJsZWQiOnRydWUsIm1lcmNoYW50QWNjb3VudElkIjoiVVNEIiwiY3VycmVuY3lJc29Db2RlIjoiVENEIn0sIm1lcmNoYW50SWQiOiJoN2IyeGJ3a3BkeXMyaHo0IiwidmVubW8iOiJvZmYifQ=="
}
```

参数描述：

| paramter |  type  |       description       |
| :------: | :----: | :---------------------: |
| order_no | String |         订单号          |
|  token   | String | 用户登录返回值UserToken |
|   data   | String |      Paypal TOKEN       |

## 请求分派服务器

```json
{
  "MessageType": "AppGetBSAddressRequest",
  "Body": {
    "UserName": "FF24C000652989155169FFEF28394DA2",
    "Password": null,
    "ServerType": [
      3,
      4
    ]
  }
}
```

| 字段名称   | 类型   | 描述                                                         |
| ---------- | ------ | ------------------------------------------------------------ |
| UserName   | String | 设备唯一标识(Id)                                             |
| ServerType | Array  | 支持的服务器地址及端口号传数组格式[3，4] 为了构造方法同步接收response |
| Password   | String | 密码可传null                                                    |

```json
{
  "MessageType": "AppGetBSAddressResponse",
  "Body": {
    "UserName": "FF24C000652989155169FFEF28394DA2",
    "CryptKey": "PujvjKu/7Nzl1ojM+3ARg6ZgdDFiDKIapOYX1KEXE7R8ESB1Bc/uHCQfwYqbNasFv6rgQ02PtSKJCGJeeoi4PanQTtnKA+FG/FVbhb3EddUwtyu/R/5cuELorv23jtjS",
    "ServerType": [
      {
        "Type": 3,
        "Address": "119.23.130.8",
        "Port": 8000
      },
      {
        "Type": 3,
        "Address": "119.23.128.98",
        "Port": 8000
      },
      {
        "Type": 4,
        "Address": "119.23.128.98",
        "Port": 5301
      }
    ]
  },
  "ResultCode": 0
}
```

| 字段名称   | 类型        | 描述                                                |
| ---------- | ----------- | --------------------------------------------------- |
| UserName   | String      | 设备唯一标识(uuid)                                    |
| ServerList | ArrayObject | 服务器列表信息                                      |
| Type       | Integer     | 服务器类型                                          |
| Address    | String      | 服务器地址                                          |
| Port       | Integer     | 服务器端口                                          |
| CryptKey   | String      | 通讯协议加密密匙, 注： 需要平台提供解码算法方可使用 |
| ResultCode | Integer     | 响应返回值, 0 - 成功, 其它值 - 失败                 |



## 上报能力集
能力集在设备列表获取时会返回设备支持的能力集

```json
参考GetDeviceListResponse类
```

| 字段名称  | 类型   | 描述                                                         |
| --------- | ------ | ------------------------------------------------------------ |
| ModleType | String | 设备机型                                                     |
| DeviceId  | String | 设备唯一标识(Id)                                             |
| Version   | String | 软件版本                                                     |
| DeviceCap | Array  | 设备能力集，请参考文档【设备&平台参数统一文档V1.xlsx】的设备能力集 |

## 设备登录

```json
{
  "MessageType": "RegisterCGSDRequest",
  "Body": {
    "DeviceIP": "127.0.0.1",
    "DeviceId": "A99UB210PL4V57E",
    "SimIccid": "xxxxxx",
    "Software": "C_801.UP5833JDB.013.037036",
    "Hardware": "M01C17S17W02",
    "RegType": 1,
    "DeviceResetFlag": 0,
    "CGSDAddress": "goslbs-cn.linkintec.cn:6002;8.142.112.152:6002",
    "KeepAliveFlag": 1,
    "OfflineDelayNotic": 0
  }
}
```

| 字段名称          | 类型    | 描述                                                       |
| ----------------- | ------- | ---------------------------------------------------------- |
| DeviceIP          | String  | 设备局域网地址                                             |
| DeviceId          | String  | 设备唯一标识(Id)                                           |
| SimIccid          | String  | 运营商IC码，4G项目使用                                     |
| Software          | String  | 设备软件版本                                               |
| Hardware          | String  | 设备硬件版本                                               |
| RegType           | Integer | ulifepro已舍弃，默认填值1                                  |
| DeviceResetFlag   | Integer | ulifepro已舍弃，默认填值0                                  |
| CGSDAddress       | String  | 接入网关地址(CGSD), 该地址从LBS服务器获取                  |
| KeepAliveFlag     | Integer | ulifepro已舍弃，默认填值1                                  |
| OfflineDelayNotic | Integer | 低功耗使用，进入保活是否通知APP离线 取值：0-通知 1-不通知 |

```json
{
  "Body": {
    "DeviceId": "A99UB210PL4V57E",
    "DeviceResetFlag": 0,
    "KeepAliveHeartTime": 38,
    "KeepAliveTime": 30,
    "MediaTransportType": 2,
    "SessionId": "107374295771",
    "StreamPassword": "4RLYRDBY",
    "StreamUser": "ulife",
    "ServerUTCTime": 1661914257
  },
  "MessageType": "RegisterCGSDResponse",
  "ResultCode": 0
}
```

| 字段名称           | 类型    | 描述                                             |
| ------------------ | ------- | ------------------------------------------------ |
| DeviceId           | String  | 设备唯一标识(Id)                                 |
| DeviceResetFlag    | Integer | ulifepro已舍弃                                   |
| KeepAliveHeartTime | String  | 设备软件版本                                     |
| KeepAliveTime      | String  | 设备硬件版本                                     |
| MediaTransportType | Integer | ulifepro已舍弃，默认填值1                        |
| StreamUser         | Integer | ulifepro已舍弃，默认填值0                        |
| StreamPassword     | String  | 接入网关地址(CGSD), 该地址从LBS服务器获取        |
| ServerUTCTime      | Integer | 用于设备同步平台时间，unix时间戳信息             |
| ResultCode         | Integer | 返回值，0表示成功，非0失败，-10095表示设备未绑定 |

## 同步平台参数

```json
{
  "MessageType": "STDGetDeviceParamRequest",
  "Body": {
    "DeviceId": "A99UB210PL4V57E"
  }
}
```

| 字段名称 | 类型   | 描述             |
| -------- | ------ | ---------------- |
| DeviceId | String | 设备唯一标识(Id) |

将通过STDGetDeviceParamResponse返回所有参数；

## 查询是否绑定

```JSON
{
  "MessageType": "STDQueryDeviceBindRequest",
  "Body": {
    "DeviceId": "A99EB210ZY6IURM"
  }
}
```

| 字段名称 | 类型   | 描述             |
| -------- | ------ | ---------------- |
| DeviceId | String | 设备唯一标识(Id) |

```json
{
  "Body": {
    "BindStatus": 0,
    "CGSId": "cgsd-100003",
    "DeviceId": "A99EB210ZY6IURM",
    "SessionId": "1069447715535"
  },
  "MessageType": "STDQueryDeviceBindResponse",
  "ResultCode": 0
}
```

| 字段名称   | 类型   | 描述                      |
| ---------- | ------ | ------------------------- |
| DeviceId   | String | 设备唯一标识(Id)          |
| BindStatus | String | 是否绑定；0-未绑定 1-绑定 |

## 获取绑定Token(有线)

```JSON
{
  "MessageType": "STDGetBindTokenRequest",
  "Body": {
    "DeviceId": "A99EB210ZY6IURM"
  }
}
```

| 字段名称 | 类型   | 描述             |
| -------- | ------ | ---------------- |
| DeviceId | String | 设备唯一标识(Id) |

```JSON
{
  "Body": {
    "BindToken": "",
    "DeviceId": "A99EB210ZY6IURM"
  },
  "MessageType": "STDGetBindTokenResponse",
  "ResultCode": 0
}
```

| 字段名称  | 类型   | 描述             |
| --------- | ------ | ---------------- |
| DeviceId  | String | 设备唯一标识(Id) |
| BindToken | String | 有效的绑定token  |

## AP模式绑定

```JSON
{
  "MessageType": "ApConfigRequest",
  "Body": {
    "device_id": "A9911111121212",
    "app_name": "goscam pro",
    "ssid": "factory_2.4G1",
    "passwd": "123456789",
    "token": "XXXXXXXX",
    "server": ""
  }
}
```

```JSON
{
  "MessageType": "ApConfigResponse",
  "Body": {
    "device_id": "A9911111121212"
  }
}
```

广播通讯，约定广播端口8629

## 心跳

```json
{
  "MessageType": "STDHeartRequest",
  "Body": {
    "DeviceId": "A99UB210PL4V57E",
    "DeviceIP": "192.168.1.101"
  }
}
```

```JSON
{
  "MessageType": "STDHeartResponse",
  "Body": {
    "DeviceId": "A99UB210PL4V57E",
    "ServerUTCTime": 1661914257
  }
}
```

| 字段名称      | 类型    | 描述                                 |
| ------------- | ------- | ------------------------------------ |
| DeviceId      | String  | 设备唯一标识(Id)                     |
| DeviceIP      | String  | 设备IP，非必要                       |
| ServerUTCTime | Integer | 用于设备同步平台时间，unix时间戳信息 |

心跳交互用于判断跟服务器链接是否出现异常，如果异常需要重新建立新的链接；

## 云存信息

设备上线后，平台主动下发云存储状态给设备，设备立即请求token，因为token有时效，设备必须检测token是否快要超时，在token失效前重新请求token以保证持续有效。

### 服务器下发云存储开通状态

```json
{
  "Body": {
    "Content": {
      "DeviceId": "A99UB210PL4V57E",
      "StartTime": 1656411617,
      "EndTime": 1656411617,
      "Status": "off",
      "ComboType": 1
    },
    "NameSpace": "service:status",
    "Type": "set"
  },
  "MessageType": "CloudStore"
}
```

| 字段名称  | 类型    | 描述                                       |
| --------- | ------- | ------------------------------------------ |
| DeviceId  | String  | 设备唯一标识(Id)                           |
| StartTime | Integer | 云存储有效期开始时间                       |
| EndTime   | Integer | 云存储有效期结束时间                       |
| Status    | String  | 取值："off"-未开通云存储 “on”-已开通云存储 |
| Type      | String  | 固定"set"                                  |
| NameSpace | String  | 固定"service:status"                       |
| ComboType | Integer | 套餐类型：1-事件套餐 2-全天套餐            |

### 设备请求云存储token

```json
{
  "Body": {
    "Content": {
      "DeviceId": "A99UB210PL4V57E"
    },
    "From": "",
    "To": "",
    "Type": "set",
    "NameSpace": "osstoken:write"
  },
  "MessageType": "CloudStore"
}
```

| 字段名称  | 类型   | 描述                                                         |
| --------- | ------ | ------------------------------------------------------------ |
| DeviceId  | String | 设备唯一标识(Id)                                             |
| Type      | String | 固定"set"                                                    |
| NameSpace | String | 阿里云-"osstoken:write";亚马逊云-"awstoken:write";联通云-"lttoken:write" |

### 服务器返回Token和Bucket信息

```json
{
  "Body": {
    "Content": {
      "Bucket": "gos-media-c,gos-pic-c,gos-device-log-cn,gos-device-timy-cn",
      "TimyBucket": "gos-device-timy-cn",
      "CallbackUrl": "http://8.142.107.52:9998/api/callback/callback-service/call/cloudstoreage/file",
      "DeviceId": "A99UB210PL4V57E",
      "DirPrefix": "7_A99UB210PL4V57E",
      "DurationSeconds": "2999",
      "EndPoint": "http://oss-cn-zhangjiakou.aliyuncs.com",
      "KeyId": "STS.NV3RWQXhNdYrGqtHZasZnqD8M",
      "KeySecret": "C7SGHiKvo5ory5MEe5RD2Ss5mHPqUueZs6CaR2Hr3KDp",
      "Token": "CAIS9wF1q6Ft5B2yfSjIr5WGGe3ltbdv05uZRVfFrFo0f9VCnoGTrzz2IHhPdHVoAuscsf43mGxX5voclqZaGs54bxadNfNUsqoKri75wFIKDiXtv9I+k5SANTW5KXyShb3/AYjQSNfaZY3eCTTtnTNyxr3XbCirW0ffX7SClZ9gaKZ8PGD6F00kYu1bPQx/ssQXGGLMPPK2SH7Qj3HXEVBjt3gX6wo9y9zmnJTHtkGA0QermrRP+dyvGPX+MZkwZqUYesyuwel7epDG1CNt8BVQ/M909vccomeb5orGWQYLu0nabLqIrMcOKB5hI7Q9GKNCvA5xFCyHP2q5GoABYT8eyOagZxPn0p5bDufKDjwpZ4+4IFrBeMQIhlOqSQ0YAI7VPkfCVVKlFCyetQaRAECwQDD4toRKzH6CZoHjgywcio0P0wOMcsHuATnU2eFscvC17yQlws96JQPTOyhvSNFI2DFVWtiSfNwJbJFcR1BnWvtqbPdc+eWbeimI/2U="
    },
    "From": "",
    "NameSpace": "osstoken:write",
    "PrivData": "1649728492355",
    "To": "",
    "Type": "result"
  },
  "MessageType": "CloudStore"
}
```

| 字段名称        | 类型   | 描述                                                         |
| --------------- | ------ | ------------------------------------------------------------ |
| DeviceId        | String | 设备唯一标识(Id)                                             |
| DirPrefix       | String | 云端存储目录                                                 |
| DurationSeconds | String | token有效时长                                                |
| EndPoint        | String | 云端OSS服务地址                                              |
| KeyId           | String | 访问云端OSS服务key                                           |
| KeySecret       | String | 访问云端OSS服务secret                                        |
| CallbackUrl     | String | 云端OSS服务回调地址，用于同步文件数据库信息                  |
| Bucket          | String | OSS存储空间Bucket；三段分别是云存储媒体数据、事件抓拍图、设备日志 |
| TimyBucket      | String | OSS存储空间Bucket；时光留影桶存在则表示套餐开通，不存在表示未开通 |
| Token           | String | 有效的token信息                                              |
| Type            | String | 固定"result"                                                 |
| NameSpace       | String | 阿里云-"osstoken:write";亚马逊云-"awstoken:write";联通云-"lttoken:write" |

## 服务器AI套餐状态

```JSON
{
  "Body": {
    "DeviceId": "A99EB210FCKRDYM"
  },
  "MessageType": "AiTokenRequest"
}
```

```json
{
  "Body": {
    "DeviceId": "A99EB210FCKRDYM",
    "Status": "1",
    "EndTime": 1689576472,
    "StartTime": 1658472472,
    "tokenExpire": 3600,
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NTk1MDE2NDksImRldmljZUlkIjoiQTk5VEIyMDBONEFMU0xIIn0.3G18aL5hXg4qZh8T6REB0o2olgnclzQQ1xe8o7vY--o"
  },
  "MessageType": "AiTokenResponse",
  "ResultCode": 0
}
```

| 字段名称    | 类型    | 描述                             |
| ----------- | ------- | -------------------------------- |
| DeviceId    | String  | 设备唯一标识(Id)                 |
| Status      | String  | AI套餐状态 “0”-未开通 “1”-已开通 |
| StartTime   | Integer | AI套餐有效期开始时间             |
| EndTime     | Integer | AI套餐有效期结束时间             |
| tokenExpire | Integer | token有效时长                    |
| token       | String  | token字串                        |

# 报警推送

```json
{
  "MessageType": "PubMsgRequest",
  "Body": {
    "Stamp": 0,
    "DeviceId": "A99UB210PL4V57E",
    "Type": 1,
    "Time": "1649729526",
    "Msg": "",
    "Version": "C_811.UP5833JDB.013.037036"
  }
}
```

| 字段名称 | 类型    | 描述                                                         |
| -------- | ------- | ------------------------------------------------------------ |
| DeviceId | String  | 设备唯一标识(Id)                                             |
| Stamp    | Integer | 随机数                                                       |
| Type     | Integer | 告警类型，请参考文档【设备&平台参数统一文档V1.xlsx】的告警类型 |
| Time     | String  | 告警触发时间, 单位(秒), 时间格式取值定义：Linux/Unix系统时间，从1970年1月1号0时0分0秒 至 当前时间的总秒数 |
| Msg      | String  | 联动抓拍图片上传地址，非必要                                 |
| Version  | String  | 新版本推送方式，用来区分很老之前的推送方式，有字段就行，值可随便填 |

# 低功耗产品休眠

## 获取休眠必要参数

暂未定义，目前采用写死参数形式

## 通知进入休眠状态

```json
{
  "MessageType": "NotifyLinkStatusChange",
  "Body": {
    "DeviceId": "A99UB210PL4V57E"
  }
}
```

| 字段名称 | 类型   | 描述             |
| -------- | ------ | ---------------- |
| DeviceId | String | 设备唯一标识(Id) |

低功耗产品进入休眠前主动发送消息给平台，避免低功耗切换socket进入保活时平台通知APP设备下线；

## 保活心跳报文

```
{
    "M":"1",
    "D":"A99UB210PL4V57E",
    "E":10
}
```

```
typedef enum
{
	UMS_ALIVE_MODE_SOCKET_REUSE = 1,		// socket复用，socket不会断开
	UMS_ALIVE_MODE_SOCKET_CHANGE = 2,		// socket会断开，重新建立socket进行保活
}Ums_Alive_Mode_e;
```

| 字段名称 | 类型    | 描述                                                         |
| -------- | ------- | ------------------------------------------------------------ |
| M        | Integer | 设备socket复用机制，参考Ums_Alive_Mode_e                     |
| D        | String  | 设备唯一标识(Id)                                             |
| E        | Integer | 拓展位，保证心跳包偶数位，372的保活报文会被底层修改成偶数位对齐造成平台认为异常，奇数时json强制添加"E":10 |

## 远程唤醒报文

注：目前设备写死唤醒报文为字符串123456

# 上报设备参数

## 上报摄像头开关状态

```JSON
{
  "MessageType": "STDSetDeviceParamRequest",
  "Body": {
    "DeviceId": "A99UB210PL4V57E",
    "ParamArray": [
      {
        "CMDType": "cap24",
        "DeviceParam": {
          "device_switch": 1
        }
      }
    ]
  }
}
```

| 字段名称      | 类型    | 描述                  |
| ------------- | ------- | --------------------- |
| DeviceId      | String  | 设备唯一标识(Id)      |
| CMDType       | String  | 对应能力集，固定cap24 |
| device_switch | Integer | 取值，0-关闭 1-开启   |

## 上报时区

```json
{
  "MessageType": "STDSetDeviceParamRequest",
  "Body": {
    "DeviceId": "A99UB210PL4V57E",
    "ParamArray": [
      {
        "CMDType": "cap12",
        "DeviceParam": {
          "un_TimeZone": 28800
        }
      }
    ]
  }
}
```

| 字段名称    | 类型    | 描述                                              |
| ----------- | ------- | ------------------------------------------------- |
| DeviceId    | String  | 设备唯一标识(Id)                                  |
| CMDType     | String  | 对应能力集，固定cap12                             |
| un_TimeZone | Integer | 时区使用秒表达形式；如+8时区表示为28800即8\*60*60 |

## 上报当前温度

```json
{
  "MessageType": "STDSetDeviceParamRequest",
  "Body": {
    "DeviceId": "A99UB210PL4V57E",
    "ParamArray": [
      {
        "CMDType": "cap11-1",
        "DeviceParam": {
          "temperature": 45
        }
      }
    ]
  }
}
```

| 字段名称    | 类型    | 描述                    |
| ----------- | ------- | ----------------------- |
| DeviceId    | String  | 设备唯一标识(Id)        |
| CMDType     | String  | 对应能力集，固定cap11-1 |
| temperature | Integer | 当前温度                |

## 上报当前湿度

```JSON
{
  "MessageType": "STDSetDeviceParamRequest",
  "Body": {
    "DeviceId": "A99UB210PL4V57E",
    "ParamArray": [
      {
        "CMDType": "cap33-1",
        "DeviceParam": {
          "humidity": 45
        }
      }
    ]
  }
}
```

| 字段名称 | 类型    | 描述                    |
| -------- | ------- | ----------------------- |
| DeviceId | String  | 设备唯一标识(Id)        |
| CMDType  | String  | 对应能力集，固定cap33-1 |
| humidity | Integer | 当前湿度                |

## 上报电池状态

```json
{
  "MessageType": "STDSetDeviceParamRequest",
  "Body": {
    "DeviceId": "A99UB210PL4V57E",
    "ParamArray": [
      {
        "CMDType": "cap29",
        "DeviceParam": {
          "battery_level": 40,
          "charging": 1
        }
      }
    ]
  }
}
```

| 字段名称      | 类型    | 描述                              |
| ------------- | ------- | --------------------------------- |
| DeviceId      | String  | 设备唯一标识(Id)                  |
| CMDType       | String  | 对应能力集，固定cap29             |
| battery_level | Integer | 当前电量百分比，取值0-100         |
| charging      | Integer | 是否充电状态，0-未在充电 1-充电中 |

## 上报网络信息

```json
{
  "MessageType": "STDSetDeviceParamRequest",
  "Body": {
    "DeviceId": "A99UB210PL4V57E",
    "ParamArray": [
      {
        "CMDType": "cap14",
        "DeviceParam": {
          "a_SSID": "test",
          "un_signal_level": 80
        }
      }
    ]
  }
}
```

```json
{
  "MessageType": "STDSetDeviceParamRequest",
  "Body": {
    "DeviceId": "A99UB210PL4V57E",
    "ParamArray": [
      {
        "CMDType": "cap14",
        "DeviceParam": {
          "a_number": "test",
          "a_iccid": "test",
          "a_imei": "test",
          "a_center": 0,
          "a_desc": "运营商信息",
          "un_signal_level": 80
        }
      }
    ]
  }
}
```

| 字段名称        | 类型    | 描述                                              |
| --------------- | ------- | ------------------------------------------------- |
| DeviceId        | String  | 设备唯一标识(Id)                                  |
| CMDType         | String  | 对应能力集，固定cap14                             |
| a_SSID          | String  | 当前所链接wifi的ssid                              |
| un_signal_level | Integer | 当前所链接wifi的信号量0-100， 0-APP不显示         |
| a_number        | String  | SIM卡设备本机号码                                 |
| a_iccid         | String  | SIM卡设备本机ICCID                                |
| a_imei          | String  | 4G模块唯一标识                                    |
| a_center        | Integer | 运营商名称：0-中国移动 1-中国联通 2-中国电信      |
| a_desc          | String  | 运营商名称，a_center为SERVICE_CENTER_EXTERN是使用 |

```c
typedef enum
{
	SERVICE_CENTER_CMCC=0,	//中国移动
	SERVICE_CENTER_CUCC, 	//中国联通
	SERVICE_CENTER_CTCC,	//中国电信
    SERVICE_CENTER_EXTERN,	//非国内三大运营商
}SERVICE_CENTER_E;
```

常规WIFI设备上报ssid代码段，SIM卡设备上报iccid代码段

## 上报OTA进度

```json
{
  "MessageType": "STDSetDeviceParamRequest",
  "Body": {
    "DeviceId": "A99UB210PL4V57E",
    "ParamArray": [
      {
        "CMDType": "cap43",
        "DeviceParam": {
          "upgrade_item_type": 1,
          "upgrade_status": 1,
          "upgrade_progress": 80
        }
      }
    ]
  }
}
```

```C
typedef enum
{  
  E_UPGRADE_ERROR     	= 0x72,
  E_UPGRADE_STATION     = 0x73,  
  E_UPGRADE_CAMERA_DSP  = 0x74,
  E_UPGRADE_CAMERA_WIFI = 0x75,
  E_UPGRADE_CAMERA_MCU  = 0x76,  
  E_UPGRADE_STATION_APP = 0x77,  
} UPGRADE_ITEM_TYPE;

typedef enum{
  E_UPGRADE_DOWNLOADING = 0,            //(0)正在下载
  E_UPGRADE_FAILURE,              		//(1)升级失败
  E_UPGRADE_SUCCESS,                	//(2)升级成功
  E_UPGRADE_DOWNLOAD_START,				//(3)开始下载
  E_UPGRADE_DOWNLOAD_FINISH,			//(4)下载完成
  E_UPGRADE_CHECK_ERROR,				//(5)升级包校验出错
  E_UPGRADE_INSTALL_START,				//(6)开始安装
}UPRADE_STATE_E;
```

| 字段名称          | 类型    | 描述                                          |
| ----------------- | ------- | --------------------------------------------- |
| DeviceId          | String  | 设备唯一标识(Id)                              |
| CMDType           | String  | 对应能力集，固定cap43                         |
| upgrade_item_type | Integer | 升级的模块，参考枚举UPGRADE_ITEM_TYPE，非必须 |
| upgrade_status    | Integer | 升级状态，参考枚举UPRADE_STATE_E              |
| upgrade_progress  | Integer | 升级进度                                      |

## 上报TF卡信息

```JSON
{
  "MessageType": "STDSetDeviceParamRequest",
  "Body": {
    "DeviceId": "A99UB210PL4V57E",
    "ParamArray": [
      {
        "CMDType": "cap10",
        "DeviceParam": {
          "a_sd_status": -2,
          "a_total_size": 0,
          "a_used_size": 0,
          "a_free_size": 0
        }
      }
    ]
  }
}
```

| 字段名称     | 类型    | 描述                                       |
| ------------ | ------- | ------------------------------------------ |
| DeviceId     | String  | 设备唯一标识(Id)                           |
| CMDType      | String  | 对应能力集，固定cap10                      |
| a_sd_status  | Integer | tf卡状态，0-正常 -1-未插卡 -2-已插入但异常 |
| a_total_size | Integer | 总容量，单位MB                             |
| a_used_size  | Integer | 升级进度                                   |
| a_free_size  | Integer | 剩余容量，单位MB                           |

# APP设置参数通知

```json
{
  "Body": {
    "AccessToken": "7B5D963A1CAC4429B980A23FFD6EE9CF",
    "CGSId": "cgsa-8001",
    "DestSession": "133144099926",
    "DeviceId": "A99UB210PL4V57E",
    "ParamArray": [
      ...
    ],
    "SessionId": "64424542669",
    "UserName": "18664983039",
    "UserType": 25
  },
  "MessageType": "DeviceParamReportNotify"
}
```

APP设置命令格式如上，不同设置只是ParamArray字段携带不一样，后面介绍只描述ParamArray字段部分。

## 设置视频流清晰度切换

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap2",
        "DeviceParam": {
          "un_video_quality": 1,
          "video_resolution": [
            {
              "src_width": 1280,
              "src_height": 720,
              "dest_width": 1920,
              "dest_height": 1080
            },
            {
              "src_width": 640,
              "src_height": 360
            }
          ]
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称         | 类型    | 描述                                                  |
| ---------------- | ------- | ----------------------------------------------------- |
| CMDType          | String  | 对应能力集，固定cap2                                  |
| un_video_quality | Integer | 当前直播分辨率下标值，对应video_resolution的array下标 |
| src_width        | Integer | 视频源宽                                              |
| src_height       | Integer | 视频源高                                              |
| dest_width       | Integer | APP显示视频宽                                         |
| dest_height      | Integer | APP显示视频高                                         |

本协议un_video_quality暂未使用，当不支持此能力集时，采有原有流媒体协议切换分辨率逻辑；当支持此能力集时，可以根据video_resolution元素解析出机型支持切换的分辨率选项，当src宽高和dest宽高不一致时，需要APP做插值显示；dest宽高不存在或者与src宽高一致时正常显示。

## 设置温度报警参数

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap11",
        "DeviceParam": {
          "alarm_enable": 1,
          "temperature_type": 0,
          "max_alarm_value": 0,
          "min_alarm_value": 0
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称         | 类型    | 描述                                     |
| ---------------- | ------- | ---------------------------------------- |
| CMDType          | String  | 对应能力集，固定cap11                    |
| alarm_enable     | Integer | 是否开启温度报警，取值0-关闭 1-开启      |
| temperature_type | Integer | 温度表示模式，取值0 - 摄氏度 1 - 华氏度 |
| max_alarm_value  | Integer | 高温报警临界值                           |
| min_alarm_value  | Integer | 低温报警临界值                           |

## 设置湿度报警参数

```JSON
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap33",
        "DeviceParam": {
          "alarm_enable": 1,
          "max_alarm_value": 0,
          "min_alarm_value": 0
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称        | 类型    | 描述                              |
| --------------- | ------- | --------------------------------- |
| CMDType         | String  | 对应能力集，固定cap33             |
| alarm_enable    | Integer | 是否开湿度报警，取值0-关闭 1-开启 |
| max_alarm_value | Integer | 高湿度报警临界值                  |
| min_alarm_value | Integer | 低湿度报警临界值                  |

## 设置设备时区

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap12",
        "DeviceParam": {
          "AppTimeSec": 1649303057,
          "un_TimeZone": 28800
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称    | 类型    | 描述                                                         |
| ----------- | ------- | ------------------------------------------------------------ |
| CMDType     | String  | 对应能力集，固定cap12                                        |
| AppTimeSec  | Integer | 当前时间, 单位(秒), 时间格式取值定义：Linux/Unix时间戳，从1970年1月1号0时0分0秒 至 当前时间的总秒数 |
| un_TimeZone | Integer | 时区使用秒表达形式；如+8时区表示为28800即8\*60*60            |

AppTimeSec因为影响卡存储，暂时未使用；

## 设置指示灯状态

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap23",
        "DeviceParam": {
          "device_led_switch": 1,
          "color_ctrl": 0XFFEEDD
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

```json
typedef enum{
  E_SDK_LED_MODE_OFF,
  /*全灭*/
  E_SDK_LED_MODE_ON,
  /*常亮*/
  E_SDK_LED_MODE_FLICKER,
  /*闪烁*/
  E_SDK_LED_MODE_BREATH,
  /*呼吸*/
  E_SDK_LED_MODE_RHYTHM
  /*律动*/
}E_SDK_LED_MODE;
```

| 字段名称          | 类型    | 描述                                                       |
| ----------------- | ------- | ---------------------------------------------------------- |
| CMDType           | String  | 对应能力集，固定cap23                                      |
| device_led_switch | Integer | 是否开启设备（常规设备）状态指示灯，取值参考E_SDK_LED_MODE |
| color_ctrl        | Integer | RGB三基色取值，高8位R、中8位G、低8位B                      |

## 设置门铃状态指示灯开关

```JSON
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap45",
        "DeviceParam": {
          "doorbell_led_switch": 1
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称            | 类型    | 描述                                                   |
| ------------------- | ------- | ------------------------------------------------------ |
| CMDType             | String  | 对应能力集，固定cap45                                  |
| doorbell_led_switch | Integer | 是否开启设备（门铃设备）状态指示灯，取值 0-关闭 1-开启 |

## 设置防拆警报开关

```JSON
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap47",
        "DeviceParam": {
          "a_doorbell_remove_alarm": 1
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称                | 类型    | 描述                                                 |
| ----------------------- | ------- | ---------------------------------------------------- |
| CMDType                 | String  | 对应能力集，固定cap47                                |
| a_doorbell_remove_alarm | Integer | 是否开启设备（门铃设备）防拆报警，取值 0-关闭 1-开启 |

## 设置铃声设置

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap35",
        "DeviceParam": {
          "volume_level": 90,
          "ring_langue": 0
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称     | 类型    | 描述                                                       |
| ------------ | ------- | ---------------------------------------------------------- |
| CMDType      | String  | 对应能力集，固定cap35，能力集参考Ulife_DoorRing_en，可与或 |
| volume_level | Integer | 设备（门铃设备）提示音音量，取值 0-100                     |
| ring_langue  | Integer | 门铃提示音语言类型，取值：0-中文 1-英文                    |

```C
typedef enum{
	ULIFE_DOORRING_NONE,				// 不支持门铃声音设置
	ULIFE_DOORRING_RING_VOLUME 	= 1,	// 支持门铃按铃提示音音量设置
	ULIFE_DOORRING_RING_LANG	= 2,	// 支持门铃按铃提示音语言设置
}Ulife_DoorRing_en;
```

## 设置省电模式开关

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap48",
        "DeviceParam": {
          "a_doorbell_lowpower": 1
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称            | 类型    | 描述                                                   |
| ------------------- | ------- | ------------------------------------------------------ |
| CMDType             | String  | 对应能力集，固定cap48                                  |
| a_doorbell_lowpower | Integer | 是否开启设备（低功耗设备）省电模式，取值 0-关闭 1-开启 |

## 设置夜视模式

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap13",
        "DeviceParam": {
          "un_auto": 0,
          "un_day_night": 0
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称     | 类型    | 描述                                                      |
| ------------ | ------- | --------------------------------------------------------- |
| CMDType      | String  | 对应能力集，固定cap13                                     |
| un_auto      | Integer | 是否开启自动模式/智能夜视，取值 0-关闭 1-开启             |
| un_day_night | Integer | 黑白模式，当自动模式关闭时夜视模式有效，取值0-白天 1-夜视 |
| un_day_night | Integer | 全彩模式，当自动模式关闭时夜视模式有效，取值0-黑白 1-全彩 |

普通夜视和全彩夜视共用协议；

## 设置PIR侦测参数

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap6",
        "DeviceParam": {
          "un_switch": 0,
          "un_sensitivy": 100,
          "un_stay": 0,
          "un_cdtime": 10
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称     | 类型    | 描述                                                   |
| ------------ | ------- | ------------------------------------------------------ |
| CMDType      | String  | 对应能力集，固定cap6，能力集参考Ulife_HWPir_en，可与或 |
| un_switch    | Integer | 是否开启PIR侦测，取值 0-关闭 1-开启                    |
| un_sensitivy | Integer | PIR灵敏度五档 20-40-60-80-100                          |
| un_stay      | Integer | 逗留模式：取值四档0-3                                  |
| un_cdtime    | Integer | PIR检测冷却时长设置：单位秒                            |

```C
typedef enum{
	ULIFE_HWPIR_NONE,				// 不支持PIR参数设置
	ULIFE_HWPIR_SWITCH 		= 1,	// 支持PIR开关设置
	ULIFE_HWPIR_SENSITIVITY	= 2,	// 支持PIR灵敏度设置
	ULIFE_HWPIR_STAY_MODE	= 4,	// 支持PIR逗留模式设置
    ULIFE_HWPIR_CDTIME		= 8,	// 支持PIR冷却时间设置
}Ulife_HWPir_en;
```

## 设置事件告警录像时长

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap17",
        "DeviceParam": {
          "un_duration": 12
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称    | 类型    | 描述                                         |
| ----------- | ------- | -------------------------------------------- |
| CMDType     | String  | 对应能力集，固定cap17                        |
| un_duration | Integer | 事件录像单个文件时长；目前取值：6、9、12、15 |

## 设置摄像头开关

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap24",
        "DeviceParam": {
          "device_switch": 0
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称      | 类型    | 描述                                |
| ------------- | ------- | ----------------------------------- |
| CMDType       | String  | 对应能力集，固定cap24               |
| device_switch | Integer | 是否开启摄像头；取值：0-关闭 1-开启 |

## 设置麦克风采集开关

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap25",
        "DeviceParam": {
          "device_mic_switch": 0
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称          | 类型    | 描述                                    |
| ----------------- | ------- | --------------------------------------- |
| CMDType           | String  | 对应能力集，固定cap25                   |
| device_mic_switch | Integer | 是否开启麦克风采集；取值：0-关闭 1-开启 |

## 设置设备播音音量

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap53",
        "DeviceParam": {
          "volume": 0
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称 | 类型    | 描述                                   |
| -------- | ------- | -------------------------------------- |
| CMDType  | String  | 对应能力集，固定cap53                  |
| volume   | Integer | 设备（通用设备）提示音音量，取值 0-100 |

## 设置运动追踪参数

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap51",
        "DeviceParam": {
          "un_switch": 0
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称  | 类型    | 描述                                  |
| --------- | ------- | ------------------------------------- |
| CMDType   | String  | 对应能力集，固定cap51                 |
| un_switch | Integer | 是否开启运动追踪；取值：0-关闭 1-开启 |

## 设置声音侦测参数

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap19",
        "DeviceParam": {
          "un_switch": 0,
          "un_sensitivity": 0
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称       | 类型    | 描述                                  |
| -------------- | ------- | ------------------------------------- |
| CMDType        | String  | 对应能力集，固定cap19                 |
| un_switch      | Integer | 是否开启运动追踪；取值：0-关闭 1-开启 |
| un_sensitivity | Integer | 侦测灵敏度，取值：1-低 2-中 3-高   |

## 设置视频翻转

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap32",
        "DeviceParam": {
          "mirror_mode": 0
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称    | 类型    | 描述                                                         |
| ----------- | ------- | ------------------------------------------------------------ |
| CMDType     | String  | 对应能力集，固定cap32                                        |
| mirror_mode | Integer | 翻转模式，取值：0-不翻转 1-水平翻转 2-垂直翻转 3-水平垂直翻转 |

## 设置人形侦测参数

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap50",
        "DeviceParam": {
          "permcnt": 1,
          "perms": [
            {
              "pcnt": 4,
              "rect": [
                {
                  "x": 0,
                  "y": 0
                }
                ...
              ]
            }
          ],
          "un_sensitivity": 4,
          "un_switch": 1
        }
        ...
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称       | 类型    | 描述                              |
| -------------- | ------- | --------------------------------- |
| CMDType        | String  | 对应能力集，固定cap50             |
| un_switch      | Integer | 人形侦测开关，取值：0-关闭 1-开启 |
| un_sensitivity | Integer | 灵敏度，灵敏度低到高1，2，3，4，5 |
| permcnt        | Integer | 区域数量                          |
| perms          | Array   | 所有区域详情                      |
| pcnt           | Integer | 单个区域坐标点数量                |
| rect           | Array   | 单个区域坐标点详情                |
| x              | Integer | 坐标点x坐标值，取值0-10000        |
| y              | Integer | 坐标点y坐标值，取值0-10000        |

## 设置移动侦测参数

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap16",
        "DeviceParam": {
          "c_sensitivity": 30,
          "c_switch": 1,
          "rect_x": 14,
          "rect_y": 14,
          "un_enable_str": [
            1
            ...
          ]
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称      | 类型    | 描述                                        |
| ------------- | ------- | ------------------------------------------- |
| CMDType       | String  | 对应能力集，固定cap16                       |
| c_switch      | Integer | 移动侦测开关，取值：0-关闭 1-开启           |
| c_sensitivity | Integer | 灵敏度，取值：90-低 60-中 30-高          |
| rect_x        | Integer | 网格长度                                    |
| rect_y        | Integer | 网格高度                                    |
| un_enable_str | Array   | 网格中矩阵区域是否使能，取值：0-关闭 1-开启 |

## 设置摇篮曲类目

```JSON
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap20",
        "DeviceParam": {
          "list_type": 0,
          "local_list": [
            "一闪一闪亮晶晶",
            "拔萝卜",
            "捉泥鳅"
          ],
          "remote_list": {
            "basic_url": "http://xxxx/lullaby/kb11/",
            "list": [
              {
                "file": "ring_star.g711a",
                "name": "一闪一闪亮晶晶"
              },
              {
                "file": "baluobo.g711a",
                "name": "拔萝卜"
              },
              {
                "file": "zhuoniqiu.g711a",
                "name": "捉泥鳅"
              }
            ]
          }
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称    | 类型    | 描述                                                |
| ----------- | ------- | --------------------------------------------------- |
| CMDType     | String  | 对应能力集，固定cap20                               |
| list_type   | Integer | 设备音乐列表类型，取值：0-本地列表 1-服务器远端列表 |
| local_list  | Array   | 本地列表音乐名称，需与设备端保持一致                |
| remote_list | Integer | 服务器远端列表                                      |
| basic_url   | Integer | 服务器远端列表基础http url地址                      |
| list        | Array   | 音乐列表                                            |
| file        | String  | 远端列表基础地址下的文件名                          |
| name        | String  | url文件对应的名称                                   |

## 设置声光联动参数

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap40",
        "DeviceParam": {
          "un_switch": 0,
          "schedule": {
            "un_switch": 0,
            "un_repeat": 127,
            "start_time": 0,
            "end_time": 86399
          },
          "audio": {
            "un_switch": 0,
            "un_times": 1,
            "un_volume": 80,
            "un_type": 0,
            "url": "https://xxxx"
          },
          "light": {
            "un_switch": 0,
            "un_duration": 3
          }
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称    | 类型    | 描述                                                         |
| ----------- | ------- | ------------------------------------------------------------ |
| CMDType     | String  | 对应能力集，固定cap40                                        |
| un_switch   | Integer | 声光联动总开关，取值：0-关闭 1-开启                          |
| schedule    | Object  | 联动计划                                                     |
| un_switch   | Integer | 联动计划开关，取值：0-关闭 1-开启                            |
| un_repeat   | Integer | 计划周期，按位与数值，开启则该位置为1，星期的七天，days[7]={"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; 0表示一次生效 |
| start_time  | Integer | 联动计划开始时间，使用秒表达形式；如8:00时区为28800即8\*60*60 |
| end_time    | Integer | 联动计划结束时间                                             |
| audio       | Object  | 联动声音                                                     |
| un_switch   | Integer | 联动声音开关，取值：0-关闭 1-开启                            |
| un_times    | Integer | 联动声音播音次数                                             |
| un_volume   | Integer | 联动声音播音音量                                             |
| un_type     | Integer | 联动声音播音音频文件序号                                     |
| url         | String  | 联动声音播音音频文件下载链接；音频要求单通道8K采样率g711A格式，去除G711A文件头 |
| light       | Object  | 联动灯光                                                     |
| un_switch   | Integer | 联动灯光开关，取值：0-关闭 1-开启                            |
| un_duration | Integer | 联动灯光开启持续时长                                         |

## 设置推送参数

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap54",
        "DeviceParam": {
          "interval": 0,
          "motion_detection_switch": 0,
          "sound_detection_switch": 0,
          "person_detection_switch": 1,
          "cry_alarm_switch": 1,
          "temperature_alarm_switch": 1,
          "pir_alarm_switch": 1,
          "schedule": {
            "enable": 0,
            "repeat": 1,
            "start_time": 0,
            "end_time": 28800
          }
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称                 | 类型    | 描述                                                         |
| ------------------------ | ------- | ------------------------------------------------------------ |
| CMDType                  | String  | 对应能力集，固定cap54                                        |
| interval                 | Integer | 推送间隔                                                     |
| motion_detection_switch  | Integer | 移动侦测报警推送开关，取值：0-关闭 1-开启                    |
| sound_detection_switch   | Integer | 声音报警推送开关，取值：0-关闭 1-开启                        |
| person_detection_switch  | Integer | 人形侦测报警推送开关，取值：0-关闭 1-开启                    |
| cry_alarm_switch         | Integer | 哭声检测报警推送开关，取值：0-关闭 1-开启                    |
| temperature_alarm_switch | Integer | 温度报警推送开关，取值：0-关闭 1-开启                        |
| pir_alarm_switch         | Integer | PIR报警推送开关，取值：0-关闭 1-开启                         |
| enable                   | Integer | 计划是否开启                                                 |
| repeat                   | Integer | 计划周期，按位与数值，开启则该位置为1，星期的七天，days[7]={"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; |
| start_time               | Integer | 使用秒表达形式；如8:00时区为28800即8\*60*60                  |
| end_time                 | Integer | 使用秒表达形式                                               |

## 设置自动维护计划

```
{
    "Body":{
        "ParamArray":[
            {
                "CMDType":"cap41",
                "DeviceParam":{
                    "enable":0,
                    "repeat":1,
                    "start_time":0,
                    "end_time":28800
                }
            }
        ]
    },
    "MessageType":"DeviceParamReportNotify"
}
```

| 字段名称   | 类型    | 描述                                                         |
| ---------- | ------- | ------------------------------------------------------------ |
| CMDType    | String  | 对应能力集，固定cap41                                        |
| enable     | Integer | 计划是否开启                                                 |
| repeat     | Integer | 计划周期，按位与数值，开启则该位置为1，星期的七天，days[7]={"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; |
| start_time | Integer | 重启开始时间点，使用秒表达形式；如8:00时区为28800即8\*60*60  |
| end_time   | Integer | 重启结束时间点                                               |

注：开始时间到结束时间内重启即可

## 设置摄像头开关计划

```JSON
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap37",
        "DeviceParam": {
          "enable": 0,
          "repeat": 1,
          "start_time": 0,
          "end_time":
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称   | 类型    | 描述                                                         |
| ---------- | ------- | ------------------------------------------------------------ |
| CMDType    | String  | 对应能力集，固定cap37                                        |
| enable     | Integer | 计划是否开启                                                 |
| repeat     | Integer | 计划周期，按位与数值，开启则该位置为1，星期的七天，days[7]={"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; 0表示一次生效 |
| start_time | Integer | 使用秒表达形式；如8:00时区为28800即8\*60*60                  |
| end_time   | Integer | 使用秒表达形式                                               |

## 设置隐私模式和调用云台预置点

```JSON
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap7",
        "DeviceParam": {
          "Privacy": 1,
          "Active": {
            "x": 123,
            "y": 123
          }
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称 | 类型    | 描述                                             |
| -------- | ------- | ------------------------------------------------ |
| CMDType  | String  | 对应能力集，固定cap7                             |
| Privacy  | Integer | 是否使能隐私模式； 0-关闭隐私模式 1-开启隐私模式 |
| Active   | Integer | 当前使能预置点的坐标值                           |

注：APP使能预置点时，必须先关闭隐私模式；设备接收到设置预置位，强制关闭隐私模式

1. 设置隐私模式时，预置点Active坐标值强制设置为-1；
2. 设置预置点时，需先取消隐私模式；

```JSON
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap7-1",
        "DeviceParam": {
          "Preset": [
            {
              "index": 1,
              "position": {
                "x": 123,
                "y": 123
              },
              "text": "门口",
              "picture": "https://xxxx"
            },
            {
              "index": 2,
              "position": {
                "x": 123,
                "y": 123
              },
              "text": "门口",
              "picture": "https://xxxx"
            },
            {
              "index": 4,
              "position": {
                "x": 123,
                "y": 123
              },
              "text": "门口",
              "picture": "https://xxxx"
            }
          ]
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称 | 类型    | 描述                                  |
| -------- | ------- | ------------------------------------- |
| CMDType  | String  | 对应能力集，固定cap7-1，APP上传此数据 |
| Preset   | Array   | 预置点列表                            |
| index    | Integer | 唯一标识                              |
| position | object  | 预置点坐标值                          |
| text     | string  | 文字描述                              |
| picture  | string  | 预览图url                             |

## 设置AI功能参数

```
{
    "Body":{
        "ParamArray":[
            {
                "CMDType":"cap60",
                "DeviceParam":{
                	"AiMode":1,
                    "Pet_detect":{
                    	"un_switch":0,
                    	"un_sensitivity":1
                    },
                    "Car_detect":{
                    	"un_switch":0,
                    	"un_sensitivity":1
                    },
                    "Face_detect":{
                    	"un_switch":0,
                    	"un_sensitivity":1
                    },
                    "Cross_detect":{
                    	"un_switch":0,
                    	"un_sensitivity":1
                    }
                }
            }
        ]
    },
    "MessageType":"DeviceParamReportNotify"
}
```

| 字段名称       | 类型    | 描述                                                         |
| -------------- | ------- | ------------------------------------------------------------ |
| CMDType        | String  | 对应能力集，固定cap60                                        |
| AiMode         | Integer | 0-不支持或全部禁止 <br />1-服务端识别模式<br />2-设备端识别模式<br />3-服务端+设备端双重模式 |
| Pet_detect     | object  | 宠物识别相关属性                                             |
| Car_detect     | object  | 车辆识别相关属性                                             |
| Face_detect    | object  | 人脸识别相关属性                                             |
| Cross_detect   | object  | 越线检测相关属性                                             |
| un_switch      | Integer | 开关 0-关闭 1-开启                                           |
| un_sensitivity | Integer | 灵敏度 取值范围如下<br />宠物识别：0~5<br />车辆识别：0-4<br />人脸识别：0-3<br />越线检测：默认1 |

## 设置全天录像事件录像切换

```JSON
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap63",
        "DeviceParam": {
          "manual_record_switch": 0
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称  | 类型    | 描述                                   |
| --------- | ------- | -------------------------------------- |
| CMDType   | String  | 对应能力集，固定cap7                   |
| un_switch | Integer | 是否使能 0-带卡只录事件 1-带卡全天录像 |

## 设置时光留影参数

```JSON
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap65",
        "DeviceParam": {
          "enable": 0,
          "session": "xxxxxxxx",
          "start_time": 1666121233,
          "end_time": 166813123,
          "schedule_type": 0,
          "interval_point": {
            "start": 28800,
            "end": 72000,
            "interval": 60,
            "type": 0
          },
          "time_point": [
            {
              "time": 28800,
              "type": 0
            },
            {
              "time": 43200,
              "type": 0
            },
            {
              "time": 64800,
              "type": 0
            }
          ]
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称       | 类型    | 描述                                                         |
| -------------- | ------- | ------------------------------------------------------------ |
| CMDType        | String  | 对应能力集，固定cap65                                        |
| enable         | Integer | 是否使能时光留影计划                                         |
| session        | String  | 计划的唯一标识                                               |
| start_time     | Integer | 计划的开始时间，unix时间戳                                   |
| end_time       | Integer | 计划的结束时间，unix时间戳                                   |
| schedule_type  | Integer | 计划类型；0-按设定计划，使用interval_point 1-按间隔计划，使用time_point |
| interval_point | object  | 间隔设定                                                     |
| start          | Integer | 每天的开始时间；使用秒表达形式；如8:00时区为28800即8\*60*60  |
| end            | Integer | 每天的结束时间；使用秒表达形式；如8:00时区为28800即8\*60*60  |
| interval       | Integer | 时间间隔，单位分钟                                           |
| type           | Integer | 执行操作类型；0-抓图 1-录制视频                              |
| time_point     | object  | 操作时间点表，可拓展元素个数                                 |
| time           | Integer | 执行操作的时间点；使用秒表达形式；如8:00时区为28800即8\*60*60 |
| type           | Integer | 执行操作类型；0-抓图 1-录制视频                              |

## 全彩补光灯常亮计划

```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "cap36",
        "DeviceParam": {
          "enable": 0,
          "repeat": 1,
          "start_time": 0,
          "end_time": 28800
        }
      }
    ]
  },
  "MessageType": "DeviceParamReportNotify"
}
```

| 字段名称   | 类型    | 描述                                                         |
| ---------- | ------- | ------------------------------------------------------------ |
| CMDType    | String  | 对应能力集，固定cap66                                        |
| enable     | Integer | 计划是否开启                                                 |
| repeat     | Integer | 计划周期，按位与数值，开启则该位置为1，星期的七天，days[7]={"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; |
| start_time | Integer | 使用秒表达形式；如8:00时区为28800即8*60*60                   |
| end_time   | Integer | 使用秒表达形式                                               |

# bypass命令

bypass指令是要求设备在线收到指令处理完成后返回结果给APP的；

```json
{
  "MessageType": "BypassParamRequest",
  "Body": {
    "DeviceId": "A99xxxxx123",
    "SessionId": "26855",
    "DstInfo": {
      "Session": "2336462227194",
      "SvrId": "cgsd-xxxxxxxxx"
    },
    "SrcInfo": {
      "Session": "90195761776",
      "SvrId": "cgsa-xxxxxxxxx"
    },
    "DeviceParam": {
      "CMDType": 1104,
      "a_ipaddr": "192.168.0.10",
      "un_port": 10000
    }
  }
}
```

```json
{
  "MessageType": "BypassParamResponse",
  "Body": {
    "DeviceId": "A99xxxxx123",
    "SessionId": "26855",
    "DstInfo": {
      "Session": "90195761776",
      "SvrId": "cgsa-xxxxxxxxx"
    },
    "SrcInfo": {
      "Session": "2336462227194",
      "SvrId": "cgsd-xxxxxxxxx"
    },
    "DeviceParam": {
      "CMDType": 1104,
      "result": 0
    }
  },
  "ResultCode": 0
}
```

| 字段名称   | 类型    | 描述                                |
| ---------- | ------- | ----------------------------------- |
| CMDType    | Integer | 约定的信令编号                      |
| DeviceId   | Integer | 设备唯一标识(Id)                    |
| ResultCode | Integer | 响应返回值, 0 - 成功, 其它值 - 失败 |

以上是bypass指令格式，后续命令只描述DeviceParam字段；

## OTA升级

```json
{
  "MessageType": "BypassParamRequest",
  "Body": {
    "DeviceParam": {
      "CMDType": 4372,
      "upgrade_type": 0,
      "upgrade_channel": 0,
      "upgrade_url": "xxxx",
      "upgrade_md5": "xxxx",
      "upgrade_len": 12345,
      "upgrade_version": "xxxx"
    }
  }
}
```

| 字段名称        | 类型    | 描述                               |
| --------------- | ------- | ---------------------------------- |
| CMDType         | Integer | 约定的信令编号，固定0x1114，即4372 |
| upgrade_type    | Integer | 套装设备使用，0-基站；1-子设备     |
| upgrade_channel | Integer | 套装设备使用，子设备通道标识       |
| upgrade_url     | String  | 升级包下载地址url                  |
| upgrade_md5     | String  | 升级包md5值字符串                  |
| upgrade_len     | Integer | 升级包总大小，bytes                |
| upgrade_version | String  | 服务器上升级包版本号               |

```json
{
  "MessageType": "BypassParamResponse",
  "Body": {
    "DeviceParam": {
      "CMDType": 4373
    }
  },
  "ResultCode": 0
}
```

| 字段名称   | 类型    | 描述                                       |
| ---------- | ------- | ------------------------------------------ |
| CMDType    | Integer | 约定的信令编号，固定0x1115，即4373         |
| ResultCode | Integer | 返回值, 0- 成功 -1-失败 10002-已经在升级 |

## 通知保持唤醒

```JSON
{
  "MessageType": "BypassParamRequest",
  "Body": {
    "DeviceParam": {
      "CMDType": 4376
    }
  }
}
```

| 字段名称 | 类型    | 描述                               |
| -------- | ------- | ---------------------------------- |
| CMDType  | Integer | 约定的信令编号，固定0x1118，即4376 |

```JSON
{
  "MessageType": "BypassParamResponse",
  "Body": {
    "DeviceParam": {
      "CMDType": 4377
    }
  },
  "ResultCode": 0
}
```

| 字段名称   | 类型    | 描述                               |
| ---------- | ------- | ---------------------------------- |
| CMDType    | Integer | 约定的信令编号，固定0x1119，即4377 |
| ResultCode | Integer | 返回值, 0- 成功 -1-失败           |

## 获取卡信息

```JSON
{
  "MessageType": "BypassParamRequest",
  "Body": {
    "DeviceParam": {
      "CMDType": 985
    }
  }
}
```

| 字段名称 | 类型    | 描述                              |
| -------- | ------- | --------------------------------- |
| CMDType  | Integer | 约定的信令编号，固定0x03D9，即985 |

```JSON
{
  "MessageType": "BypassParamResponse",
  "Body": {
    "DeviceParam": {
      "CMDType": 986,
      "a_sd_status": 0,
      "a_total_size": 1024,
      "a_used_size": 1024,
      "a_free_size": 0
    }
  },
  "ResultCode": 0
}
```

| 字段名称     | 类型    | 描述                                     |
| ------------ | ------- | ---------------------------------------- |
| CMDType      | Integer | 约定的信令编号，固定0x03DA，即986        |
| a_sd_status  | Integer | 卡状态，0-正常 -1-未插卡 -2-已插入但异常 |
| a_total_size | Integer | 本地存储空间总大小，单位：MB             |
| a_used_size  | Integer | 本地存储空间已用大小，单位：MB           |
| a_free_size  | Integer | 本地存储空间剩余大小，单位：MB           |
| ResultCode   | Integer | 返回值, 0- 成功 -1-失败                 |

## 卡格式化

```json
{
  "MessageType": "BypassParamRequest",
  "Body": {
    "DeviceParam": {
      "CMDType": 987,
      "format": 0
    }
  }
}
```

| 字段名称 | 类型    | 描述                              |
| -------- | ------- | --------------------------------- |
| CMDType  | Integer | 约定的信令编号，固定0x03DB，即987 |
| format   | Integer | 0获取状态 1执行格式化            |

```json
{
  "MessageType": "BypassParamResponse",
  "Body": {
    "DeviceParam": {
      "CMDType": 988,
      "a_sd_status": 0,
      "a_total_size": 1024,
      "a_used_size": 1024,
      "a_free_size": 0
    }
  },
  "ResultCode": 0
}
```

| 字段名称     | 类型    | 描述                                     |
| ------------ | ------- | ---------------------------------------- |
| CMDType      | Integer | 约定的信令编号，固定0x03DC，即988        |
| a_sd_status  | Integer | 卡状态，0-正常 -1-已插入但异常 -2-未插卡 |
| a_total_size | Integer | 本地存储空间总大小，单位：MB             |
| a_used_size  | Integer | 本地存储空间已用大小，单位：MB           |
| a_free_size  | Integer | 本地存储空间剩余大小，单位：MB           |
| ResultCode   | Integer | 返回值, 0- 成功 -1-失败 1-正在格式化    |

## 获取卡录像天列表

```JSON
{
  "MessageType": "BypassParamRequest",
  "Body": {
    "DeviceParam": {
      "CMDType": 971
    }
  }
}
```

| 字段名称 | 类型    | 描述                              |
| -------- | ------- | --------------------------------- |
| CMDType  | Integer | 约定的信令编号，固定0x03CB，即971 |

```json
{
  "MessageType": "BypassParamResponse",
  "Body": {
    "DeviceParam": {
      "CMDType": 972,
      "page_data": "202204110003|202204130013"
    }
  },
  "ResultCode": 0
}
```

| 字段名称   | 类型    | 描述                              |
| ---------- | ------- | --------------------------------- |
| CMDType    | Integer | 约定的信令编号，固定0x03CC，即972 |
| page_data  | String  | 天信息组合字符串                  |
| ResultCode | Integer | 返回值, 0- 成功 -1-失败          |

202204110003|202204130013表示：20220411-yyyyMMdd表示日期，0003和0013表示当天总文件数（分页查找已经舍弃此后缀）

## 云台控制

```JSON
{
  "MessageType": "BypassParamRequest",
  "Body": {
    "DeviceParam": {
      "CMDType": 4097,
      "control": 0,
      "virtual_pos": {
        "x": 0,
        "y": 10000
      }
    }
  }
}
```

```C
typedef enum
{
	PTZCtrTurnType_stop                 = 0,        // 停止转动
	PTZCtrTurnType_up                   = 1,        // 向上转动
	PTZCtrTurnType_down                 = 2,        // 向下转动
	PTZCtrTurnType_left                 = 3,        // 向左转动
	PTZCtrTurnType_right                = 6,        // 向右转动
	PTZCtrTurnType_keepUp               = 38,       // 持续向上转动
	PTZCtrTurnType_keepDown             = 39,       // 持续向下转动
	PTZCtrTurnType_keepLeft             = 40,       // 持续向左转动
	PTZCtrTurnType_keepRight            = 41,       // 持续向右转动
    PTZCtrTurnType_selfcheck            = 252,      // 云台自检
    PTZCtrTurnType_virtualCheck			= 253,		// 虚拟转向-校准
    PTZCtrTurnType_virtualto			= 254,		// 虚拟转向-APP上直接点击屏蔽坐标
}ulife_ptz_cmd_bypass_e;
```

| 字段名称    | 类型    | 描述                                     |
| ----------- | ------- | ---------------------------------------- |
| CMDType     | Integer | 约定的信令编号，固定0x1001，即4097       |
| control     | Integer | 控制动作，参考枚举ulife_ptz_cmd_bypass_e |
| virtual_pos | Object  | 虚拟坐标位置                             |
| x           | Integer | 坐标点x坐标值，取值0-10000               |
| y           | Integer | 坐标点x坐标值，取值0-10000               |

## 新增云台预置位

```JSON
{
  "MessageType": "BypassParamRequest",
  "Body": {
    "DeviceParam": {
      "CMDType": 4422
    }
  }
}
```

| 字段名称 | 类型    | 描述                               |
| -------- | ------- | ---------------------------------- |
| CMDType  | Integer | 约定的信令编号，固定0x1146，即4422 |

```JSON
{
  "MessageType": "BypassParamResponse",
  "Body": {
    "DeviceParam": {
      "CMDType": 4423,
      "position": {
        "x": 123,
        "y": 123
      }
    }
  },
  "ResultCode": 0
}
```

| 字段名称 | 类型    | 描述                               |
| -------- | ------- | ---------------------------------- |
| CMDType  | Integer | 约定的信令编号，固定0x1147，即4423 |
| position | object  | 坐标位置                           |

                       |

## 开始流媒体直播推流

```json
{
  "MessageType": "BypassParamRequest",
  "Body": {
    "DeviceParam": {
      "CMDType": 959
    },
    "PrivData": {
      "url": "xxx"
    }
  }
}
```

| 字段名称 | 类型    | 描述                               |
| -------- | ------- | ---------------------------------- |
| CMDType  | Integer | 约定的信令编号，固定0x0918，即2328 |
| PrivData | Object  | 自定义信息                         |
| url      | String  | rtmp推流地址                       |

当没有PrivData或者url字段时，走常规TCP推流，当代url字段时，转rtmp推流

```JSON
{
  "MessageType": "BypassParamResponse",
  "Body": {
    "DeviceParam": {
      "CMDType": 2329
    }
  },
  "ResultCode": 0
}
```

| 字段名称   | 类型    | 描述                               |
| ---------- | ------- | ---------------------------------- |
| CMDType    | Integer | 约定的信令编号，固定0x0919，即2329 |
| ResultCode | Integer | 返回值, 0- 成功 -1-失败           |

## 停止流媒体直播推流

```JSON
{
  "MessageType": "BypassParamRequest",
  "Body": {
    "DeviceParam": {
      "CMDType": 2330
    }
  }
}
```

| 字段名称 | 类型    | 描述                               |
| -------- | ------- | ---------------------------------- |
| CMDType  | Integer | 约定的信令编号，固定0x091A，即2330 |

```JSON
{
  "MessageType": "BypassParamResponse",
  "Body": {
    "DeviceParam": {
      "CMDType": 2331
    }
  },
  "ResultCode": 0
}
```

| 字段名称   | 类型    | 描述                               |
| ---------- | ------- | ---------------------------------- |
| CMDType    | Integer | 约定的信令编号，固定0x091B，即2331 |
| ResultCode | Integer | 返回值, 0- 成功 -1-失败           |

## 设置日志等级参数

```json
{
  "MessageType": "BypassParamRequest",
  "Body": {
    "DeviceParam": {
      "CMDType": 4388,
      "Level": 1,
      "Immediately": 0
    }
  }
}
```

| 字段名称    | 类型    | 描述                                         |
| ----------- | ------- | -------------------------------------------- |
| CMDType     | Integer | 约定的信令编号，固定0x1124，即4388           |
| Level       | Integer | 日志等级, 取值：0- 5，0-信息最少，5-信息最多 |
| Immediately | Integer | 是否立即打捞一次日志，0-否 1-是              |

```
{
    "MessageType":"BypassParamResponse",
    "Body":{
        "DeviceParam":{
            "CMDType":4389
        }
    },
    "ResultCode":0
}
```

| 字段名称   | 类型    | 描述                               |
| ---------- | ------- | ---------------------------------- |
| CMDType    | Integer | 约定的信令编号，固定0x1125，即4389 |
| ResultCode | Integer | 返回值, 0- 成功 -1-失败           |

## 设置设备立即重启

```JSON
{
  "MessageType": "BypassParamRequest",
  "Body": {
    "DeviceParam": {
      "CMDType": 4400
    }
  }
}
```

| 字段名称 | 类型    | 描述                               |
| -------- | ------- | ---------------------------------- |
| CMDType  | Integer | 约定的信令编号，固定0x1130，即4400 |

注：不关注回复，存在自动维护能力集APP才显示UI按钮

## 获取变焦参数

```
{
    "MessageType":"BypassParamRequest",
    "Body":{
        "DeviceParam":{
            "CMDType":4424,
        }
    }
}
```

| 字段名称 | 类型    | 描述                               |
| -------- | ------- | ---------------------------------- |
| CMDType  | Integer | 约定的信令编号，固定0x1148，即4424 |

```
{
    "MessageType":"BypassParamResponse",
    "Body":{
        "DeviceParam":{
            "CMDType":4425,
            "un_value":20
        }
    },
    "ResultCode":0
}
```

## 设置变焦参数

```
{
    "MessageType":"BypassParamRequest",
    "Body":{
        "DeviceParam":{
            "CMDType":4426,
            "un_value":20
        }
    }
}
```

| 字段名称 | 类型    | 描述                               |
| -------- | ------- | ---------------------------------- |
| CMDType  | Integer | 约定的信令编号，固定0x114A，即4426 |
| un_value | Integer | 变焦系数，范围0-100                |

```
{
    "MessageType":"BypassParamResponse",
    "Body":{
        "DeviceParam":{
            "CMDType":4427,
        }
    },
    "ResultCode":0
}
```

## 获取云台自检结果

```
{
    "MessageType":"BypassParamRequest",
    "Body":{
        "DeviceParam":{
            "CMDType":4428
        }
    }
}
```

| 字段名称 | 类型    | 描述                               |
| -------- | ------- | ---------------------------------- |
| CMDType  | Integer | 约定的信令编号，固定0x114C，即4428 |

```
{
    "MessageType":"BypassParamResponse",
    "Body":{
        "DeviceParam":{
            "CMDType":4429,
            "un_status" 0
        }
    },
    "ResultCode":0
}
```

| 字段名称  | 类型    | 描述                                  |
| --------- | ------- | ------------------------------------- |
| CMDType   | Integer | 约定的信令编号，固定0x114C，即4429    |
| un_status | Integer | 取值；-1-发送错误 0-自检中 1-自检完成 |

## 云台控制

```json
{
  "MessageType": "BypassParamRequest",
  "Body": {
    "DeviceParam": {
      "CMDType": 4097,
      "control": 0,
      "virtual_pos": {
        "x": 0,
        "y": 10000
      }
    }
  }
}
```

```c
typedef enum
{
	PTZCtrTurnType_stop                 = 0,        // 停止转动
	PTZCtrTurnType_up                   = 1,        // 向上转动
	PTZCtrTurnType_down                 = 2,        // 向下转动
	PTZCtrTurnType_left                 = 3,        // 向左转动
	PTZCtrTurnType_right                = 6,        // 向右转动
	PTZCtrTurnType_keepUp               = 38,       // 持续向上转动
	PTZCtrTurnType_keepDown             = 39,       // 持续向下转动
	PTZCtrTurnType_keepLeft             = 40,       // 持续向左转动
	PTZCtrTurnType_keepRight            = 41,       // 持续向右转动
	PTzCtrTurnType_se1fcheck            = 252,       // 持续向右转动
	PTZctrTurnType_virtualcheck            = 253,       // 持续向右转动
	PTzCtrTurnType_virtualto            = 254,       // 持续向右转动
	
}ulife_ptz_cmd_bypass_e;
```

| 字段名称 | 类型    | 描述                                     |
| -------- | ------- | ---------------------------------------- |
| CMDType  | Integer | 约定的信令编号，固定0x1001，即4097       |
| control  | Integer | 控制动作，参考枚举ulife_ptz_cmd_bypass_e |
| virtual_pos  | Object | 虚拟坐标位置 |
|  x | Integer | 坐标点x坐标值 ，取值0-10000 |
|  y | Integer | 坐标点x坐标值 ，取值0-10000 |

## 设置声音侦测开关

```json
{
  "Body": {
    "DeviceId": "xxxxxx",
    "paramArray": [
      {
        "CMDType": "cap25",
        "DeviceParam": {
          "device_mic_switch": 0
        }
      }
    ]
  },
  "MessageType": "AppSetDeviceParamResponse"
}
```

| 字段名称  | 类型    | 描述                              |
| --------- | ------- | --------------------------------- |
| DeviceId   | String | 设备id |
| device_mic_switch | Integer | 声音侦测开关，0-关闭 1-开启       |

```json
{
  "Body": {
    "DeviceId": "xxxxxx",
    "paramArray": [
      {
        "CMDType": "cap25",
        "DeviceParam": {
          "device_mic_switch": 0
        }
      }
    ]
  },
  "MessageType": "AppGetDeviceParamRequest"
}
```

| 字段名称  | 类型    | 描述                              |
| --------- | ------- | --------------------------------- |
| DeviceId   | String | 设备id |
| device_mic_switch | Integer | 声音侦测开关，0-关闭 1-开启       |

## 设置运动侦测开关

```json
{
  "Body": {
    "DeviceId": "xxxxxx",
    "paramArray": [
      {
        "CMDType": "cap16",
        "DeviceParam": {
          "c_switch": 0,
          "c_sensitivity": 0,
          "un_enable": []
        }
      }
    ]
  },
  "MessageType": "AppSetDeviceParamResponse"
}
```

| 字段名称  | 类型    | 描述                              |
| --------- | ------- | --------------------------------- |
| DeviceId   | String | 设备id |
| c_sensitivity   | Integer | 灵敏度 |
| un_enable   | Array | 选中区域 |
| c_switch | Integer | 运动侦测开关，0-关闭 1-开启       |

```json
{
  "Body": {
    "DeviceId": "xxxxxx",
    "paramArray": [
      {
        "CMDType": "cap16",
        "DeviceParam": {
          "c_switch": 0,
          "c_sensitivity": 0,
          "un_enable": []
        }
      }
    ]
  },
  "MessageType": "AppGetDeviceParamRequest"
}
```

| 字段名称  | 类型    | 描述                              |
| --------- | ------- | --------------------------------- |
| DeviceId   | String | 设备id |
| c_sensitivity   | Integer | 灵敏度 |
| un_enable   | Array | 选中区域 |
| c_switch | Integer | 运动侦测开关，0-关闭 1-开启       |

## 设置设备指示灯开关

```json
{
  "Body": {
    "DeviceId": "xxxxxx",
    "paramArray": [
      {
        "CMDType": "cap23",
        "DeviceParam": {
          "device_led_switch": 0
        }
      }
    ]
  },
  "MessageType": "AppSetDeviceParamResponse"
}
```

| 字段名称  | 类型    | 描述                               |
| --------- | ------- | ---------------------------------- |
| DeviceId   | String | 设备id |
| CMDType   | String | cap23 |
| un_switch | Integer | 设备指示灯开关，0-关闭 1-开启      |

```json
{
  "MessageType": "AppGetDeviceParamRequest",
  "Body": {
    "DeviceId": "xxxxxx",
    "CMDTypeList": [
      {
        "CMDType": "cap23",
        "DeviceParam": {
          "device_led_switch": 0
        }
      }
    ]
  }
}
```

| 字段名称   | 类型    | 描述                               |
| ---------- | ------- | ---------------------------------- |
| DeviceId    | String | 设备id |
| CMDTypeList | Array | 设备能力集列表          |
| device_led_switch | Integer | 0-关 ，1-开          |

## 设置PIR侦测开关

```json
{
  "Body": {
    "DeviceId": "xxxxxx",
    "paramArray": [
      {
        "CMDType": "cap6",
        "DeviceParam": {
          "un_switch": 0
        }
      }
    ]
  },
  "MessageType": "AppSetDeviceParamResponse"
}
```

| 字段名称   | 类型    | 描述                               |
| ---------- | ------- | ---------------------------------- |
| paramArray | Array | 能力集参数数组         |
| CMDType | String | cap6         |
| un_switch | Integer | 0 - 关 ，1 - 开          |

## 设置录像开关

| 字段名称   | 类型    | 描述                               |
| ---------- | ------- | ---------------------------------- |
| filePath    | String | 录像文件存储地址 |
| flag | Integer | 0 对应开流的flag          |
