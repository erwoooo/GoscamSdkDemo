# Android Sdk

## Tamper Alert

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "ParamArray": [
      {
        "CMDType": "cap47",
        "DeviceParam": {
          "a_doorbell_remove_alarm": 1
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

| Field name    | Type    | Describe                                     |
| -------- | ------- | -------------------------------------- |
| CMDType  | String  |cap47                  |
| a_doorbell_remove_alarm   | Integer | Enable device (doorbell device) anti disassembly alarm,value: 0-OFF 1-ON |

## Power saving Mode

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "ParamArray": [
      {
        "CMDType": "cap48",
        "DeviceParam": {
          "a_doorbell_lowpower": 1
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

| Field name    | Type    | Describe                                     |
| -------- | ------- | -------------------------------------- |
| CMDType  | String  |cap48                  |
| a_doorbell_lowpower   | Integer | Enable device (low-power device) power-saving mode,value: 0-OFF 1-ON |

## Intercom

Intercom requires calling the sdk function native int NativeStartTalk(int channel,String psw)
| Field name | Type | Describe | 
| -------- | ------- | -------------------------------------- |
| channel | Integer |0 | 
| psw | String | the password if stream |

## Volume setting （device）

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "ParamArray": [
      {
        "CMDType": "cap53",
        "DeviceParam": {
          "volume": 0
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

| Field name    | Type    | Describe                                     |
| -------- | ------- | -------------------------------------- |
| CMDType  | String  |cap53                  |
| volume   | Integer | device voice，value: 0-100 |

## Recording Duration

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "ParamArray": [
      {
        "CMDType": "cap17",
        "DeviceParam": {
          "un_duration": 12
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

| Field name    | Type    | Describe                                     |
| ----------- | ------- | -------------------------------------------- |
| CMDType     | String  | cap17                        |
| un_duration | Integer | The duration of a single file for event recording; Current value：6、9、12、15 |

## Take Picture

* nativeCapture 
| Field name | Type | Describe | 
| ----------- | ------- | -------------------------------------------- |
| port | long | GosMediaPlayer.getPort()  Create a
port for each decoder | | filePath | String | file save path |

## Record

* nativeStartRecord 
| Field name | Type | Describe | 
| ----------- | ------- | -------------------------------------------- | 
| port | long | GosMediaPlayer.getPort()  Create a port for each decoder |
| filePath | String | file save path |
| flag | Integer | just a tag |

## Video Quality

* nativeAVSwsScale 
  | Field name | Type | Describe |
  | ----------- | ------- | -------------------------------------------- | 
  | port | long | GosMediaPlayer.getPort()  Create a port for each decoder |
  | nEnable | Integer | 0-OFF,1-ON| 
  | nWidth | Integer | Modified width |
  | nHeight | Integer | Modified height |

## History

* TF file

### Get file for month

```json
{
  "Body": {
    "DeviceId": "Z99G82100000049",
    "DeviceParam": {
      "partion_index": 0,
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

| Field name    | Type    | Describe                                     |
| ----------- | ------- | -------------------------------------------- | 
| partion_index | Integer | 0|

### Rseponse

    * please refer to 'GetFileForMonthResult' 

```json
{
  "fileStatus": 0,
  "totalNum": 1,
  "currNo": 1,
  "mothFile": [
    {
      "monthTime": "",
      "fileNum": 1
    }
  ]
}
```   

| Field name    | Type    | Describe                                     |
| ----------- | ------- | -------------------------------------------- |
| fileStatus | Integer | no use |
| totalNum | Integer | no use  |
| currNo | Integer | no use |
| monthTime | Integer | used to sort  |
| fileNum | Integer |  0- no file|

### Get file for day

    * NativeGetRecDayEventRefresh

| Field name    | Type    | Describe                                     |
| ----------- | ------- | -------------------------------------------- | 
| channel | Integer | 0|
| time | Integer | current time|
| type | Integer | 0-normal record ,1-aram record|
| count | Integer | 0  |
| partionIndex | Integer | 0|

### Response

    * please refer to 'GetRecDayEventRefreshResult'

```json
{
  "result": 0,
  "total_num": 1,
  "page_num": 1,
  "day_event_list": [
    {
      "start_time": 1597116179,
      "end_time": 1597116193,
      "type": 1
    }
  ]
}
```    

| Field name    | Type    | Describe                                     |
| ----------- | ------- | -------------------------------------------- |
| total_num | Integer | Total pages |
| page_num | Integer | Current page |
| start_time | Integer |  |
| end_time | Integer |  |
| type | Integer | 0-normal record ,1-aram record |

## Album

* Photos and videos are stored in the phone's memory

## Battery Status

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "ParamArray": [
      {
        "CMDType": "cap29",
        "DeviceParam": {
          "battery_level": 12,
          "charging": 1
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

| Field name    | Type    | Describe                                     |
| ----------- | ------- | -------------------------------------------- |
| CMDType     | String  | cap29                        |
| battery_level | Integer | Current battery percentage; value：0-100 |
| charging | Integer | charge status  1-charging,0-idle |

## Speaker

    * microphone?

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

| Field name    | Type    | Describe                                     |
| ----------- | ------- | -------------------------------------------- |
| device_mic_switch | int | 0-OFF, 1-ON |

## Cellular Mobile Network (Automatic)

```json
{
  "MessageType": "AppGetDeviceParamRequest",
  "Body": {
    "DeviceId": "A99UB210PL4V57E",
    "ParamArray": [
      {
        "CMDType": "cap14"
      }
    ]
  }
}
```

```json
{
  "MessageType": "AppGetDeviceParamResponse",
  "Body": {
    "DeviceId": "A99UB210PL4V57E",
    "ParamArray": [
      {
        "CMDType": "cap14",
        "DeviceParam": {
          "a_SSID": "test",
          "a_number": "test",
          "a_imei": "test",
          "a_iccid": 0,
          "a_center": "test",
          "a_passwd": "test",
          "un_signal_level": 80
        }
      }
    ]
  }
}
```    

| Field name    | Type    | Describe                                     |
| ----------- | ------- | -------------------------------------------- |
| a_SSID    | String    | ssid |
| a_center    | String    | Operator Name |
| un_signal_level    | int    | 0-100 |

## Infrared Detection

### Set Pir

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
  "MessageType": "AppSetDeviceParamRequest"
}
```

### Get Pir

```json
{
  "Body": {
    "DeviceId": "Z99862100000011",
    "ParamArray": [
      {
        "CMDType": "cap6"
      }
    ],
    "AccessToken": "B07367BD4387464F956DF076C906C58C",
    "UserName": "542957111@qq.com",
    "UserType": 1
  },
  "MessageType": "AppGetDeviceParamRequest"
}
```

### Response

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
  "MessageType": "AppSetDeviceParamResponse"
}
```

| Field name    | Type    | Describe                                           |
| ------------ | ------- | ------------------------------------------------------ |
| CMDType      | String  | cap6 & Ulife_HWPir_en|
| un_switch    | Integer | pir switch 0-OFF, 1-ON                   |
| un_sensitivy | Integer | PIR level 20-40-60-80-100                          |
| un_stay      | Integer | Stay mode：level 0-3                                  |
| un_cdtime    | Integer | PIR detection cooling duration setting：second                            |

```C
typedef enum{
	ULIFE_HWPIR_NONE,				// PIR parameter setting not supported
	ULIFE_HWPIR_SWITCH 		= 1,	// PIR parameter setting  supported
	ULIFE_HWPIR_SENSITIVITY	= 2,	// Support for PIR sensitivity settings
	ULIFE_HWPIR_STAY_MODE	= 4,	// Supports PIR un_stay mode settings
    ULIFE_HWPIR_CDTIME		= 8,	// Support for PIR cooling time setting
}Ulife_HWPir_en;
```

# Device Capability Set

## Capability Set Request Structure

* ParamArray Multiple settings can be set at once
  <br> When we obtain the device list, we will obtain the device's capability set, `DeviceEntity`
  <br>get all device capability from `DeviceEntity.Cap` and we use `Device. parse (Cap cap)` to parse the device capability set,The parameters obtained are all of type int
  <br> set device capability by `com.gos.platform.api.devparam.DevParam.DevParamCmdType`
  <br> if you want to set the params to device,you can refer to `DevParamCmdType`,this  classes in  SDK display the parameters that the device can set
  <br> we get `cap20` is integer,and we set device params user `cap20` just a key 


```json
{
  "Body": {
    "ParamArray": [
      {
        "CMDType": "capxxx",
        "DeviceParam": {
        }
      },
      {
        "CMDType": "capxxx",
        "DeviceParam": {
        }
      },
      {
        "CMDType": "capxxx",
        "DeviceParam": {
        }
      }
    ]
  },
  "MessageType": "AppSetDeviceParamRequest"
}
```

## cap1

* Device Type

## cap2

* Main stream resolution size Width: high 16 bits Height: low 16 bits
* `com.gos.platform.api.devparam.VideoQualityParam`
```json
{
  "un_video_quality": 1,
  "video_resolution": [
    {
      "src_width": 1280,
      "src_height": 720,
      "dest_width": 1920,
      "dest_height": 1080
    }
  ]
}
```

| Field name    | Type    | Describe                                           |
| ------------ | ------- | ------------------------------------------------------ |
| CMDType      | String  | cap2 |
| un_video_quality    | Integer | Current live streaming resolution index value, corresponding to video_resolution subscript of resolution                |
| src_width | Integer | Video source width                          |
| src_height      | Integer | Video source height                                 |
| dest_width    | Integer | APP Display Video Width                           |
| dest_height    | Integer | APP Display Video Height                           |

## cap3

* Subcode stream (no used)

## cap4

* 3rd channel code stream (no used)

## cap5

* Is there an encryption IC 0: No 1: Yes
  ``
  cap.cap5 == 1
  ``

## cap6

* Is there a PIR sensor, 0: none, 1: yes
  * `com.gos.platform.api.devparam.PirSettingParam`  

  ``
  cap.cap6 == 1
  ``
  means support pir, see detail by `Infrared Detection`

## cap7

* Is there a pan tilt
  * `com.gos.platform.api.devparam.PtzPositionPresetSelectedParam`


<br> 0- None 1- Supports pan tilt control
<br> 2- Supports pan tilt control and preset positions
<br> 3- Supports pan tilt control/preset and cruise control
<br> 4- Support for pan tilt control/preset and privacy mode
<br> 5- Support pan tilt control/preset position and self check
<br> 32- Full function cloud console

```json
{
  "Privacy": 1,
  "Active": {
    "x": 123,
    "y": 123
  }
}

```

| Field name    | Type    | Describe                                           |
| ------------ | ------- | ------------------------------------------------------ |
| CMDType      | String  | cap7 |
| Privacy    | Integer | Whether to use privacy mode 0-OFF,1-ON |
| x | Integer | Coordinate values that can be preset for point x                         |
| y      | Integer | Coordinate values that can be preset for point y                    |

* `Note: When the APP enables preset points, privacy mode must be turned off first; The device has received a preset setting and is forced to turn off privacy mode`
  <br>  When setting privacy mode, the preset point Active coordinate value is forcibly set to
  -1;<br> When setting preset points, privacy mode needs to be cancelled first;

### cap7-1
* `com.gos.platform.api.devparam.PtzPositionPresetParam`

```json
{
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
    }
  ]
}
```

| Field name    | Type    | Describe                                           |
| ------------ | ------- | ------------------------------------------------------ |
| CMDType      | String  | cap7-1 |
| position | Object | Preset point coordinate values                         |
| x | Integer | Coordinate values that can be preset for point x                         |
| y      | Integer | Coordinate values that can be preset for point y                    |
| index      | Integer | unique identification                   |
| text      | String | Text description                  |
| picture      | String | preview url                   |

## cap8

* Is there a microphone
  ``cap.cap8 == 1``

## cap9

* Is there a horn
  ``cap.cap9 == 1``

## cap10

* Does it support SD card slots
  ``cap.cap10 == 1``

## cap11

* Is there a temperature sensing probe
  ``cap.cap11 == 1``

## cap12

* Does it support synchronous time zone 1. Only supports positive time zone 2. Supports half time
  zone
  ``cap.cap12 > 0`` support

* `com.gos.platform.api.devparam.TimeZoneInfoParam`

```json

{
  "AppTimeSec": 28800,
  "un_TimeZone": 8
}

```

| Field name    | Type    | Describe                                           |
| ------------ | ------- | ------------------------------------------------------ |
| CMDType      | String  | cap12 |
| AppTimeSec | Integer | Current timestamp                         |
| un_TimeZone      | Integer | timeZone(-12~11)                  |

## cap13

* Does it support night vision 0: No 1: Yes 2: Full color night vision
  `cap.cap13 > 0` support

* `com.gos.platform.api.devparam.NightSettingParam`

```json
{
  "un_auto": 0,
  "un_day_night": 0
}
```

| Field name    | Type    | Describe                                           |
| ------------ | ------- | ------------------------------------------------------ |
| CMDType      | String  | cap13 |
| un_auto | Integer | Whether to enable automatic mode/intelligent night vision, with values of 0- off 1- on                 |
| un_day_night      | Integer | Black and white mode, night vision mode is effective when automatic mode is turned off, with values of 0- day 1- night vision    |
| un_day_night      | Integer | Full color mode, night vision mode is effective when automatic mode is turned off, with values ranging from 0- black and white to 1- full color |

* Common Agreement for Ordinary Night Vision and Full Color Night Vision;

## cap14

* Is there a network card with 0: wifi 1 wired 2: wifi and wired
  *see detail by `Cellular Mobile Network (Automatic)`
  
<br> 0 supports WIFI
<br> 1 wired
<br> 2 2.4G WIFI+wired
<br> 3 5G WIFI
<br> 4 2.4G/5G dual mixing WIFI
<br> 5 4G traffic
<br> 6 2.4G/5G dual mixing WIFI+wired

* `com.gos.platform.api.devparam.CurrentWifiInfoParam`

## cap15

* Does it support smart scanning? 0 represents not supported, 1 represents 7601smart, and 2
  represents 8188mart
* no used

## cap16

* Does it support mobile detection? 0 does not support 1.support 2. supports self selected areas. 3.
  supports custom time
  `cap.cap16 > 0` support


* `com.gos.platform.api.devparam.MotionDetectionParam`

```json
{
  "c_sensitivity": 30,
  "c_switch": 1,
  "un_width": 14,
  "un_height": 14,
  "un_mode": 0,
  "un_submode": 0,
  "un_enable": 0,
  "s_threshold": 0,
  "rect_x": 0,
  "rect_y": 0,
  "custom_time_period": [],
  "un_enable_str": []
}

```

| Field name    | Type    | Describe                                           |
| ------------ | ------- | ------------------------------------------------------ |
| CMDType      | String  | cap16 |
| c_sensitivity | Integer | Value range (1~100) is the threshold value for motion detection level. The smaller the value, the higher the sensitivity. 1 (max)~100 (min): (Off: 100, Low: 90, Medium: 60, High: 30)                      |
| c_switch      | Integer | switch  0-Off 1-On               |
| un_width      | Integer |                  |
| un_height      | Integer |                 |
| s_threshold      | short |       no used          |
| un_mode      | Integer | Manual division of coordinates 0 or automatic multi screen division of coordinates 1*                  |
| un_submode      | Integer | 1x1=0, 2x2=1, 3x3=2, 4x4=3 in multi screen mode*                |
| un_enable      | Integer | Based on whether to enable up to 4x4=16 for the selected area in multi screen mode* |
| rect_x      | Integer | Number of regions in the horizontal axis direction (rect_x * rect_y<=200) by hand                 |
| rect_y      | Integer | Number of regions in the vertical coordinate direction  by hand                |
| custom_time_period      | String Array |                   |
| un_enable_str      |  Array | Is motion detection enabled for each region (JSON format is passed in an array with a size of rect_x * rect_y)           |

## cap17

* Set the duration of video recording
  `cap.cap17 == 1`

* `com.gos.platform.api.devparam.RecordDurationParam`
    * see detail by `Recording Duration`

## cap18

* Set the lighting switch
  `cap.cap18 == 1`

## cap19

* Sound detection alarm
  `cap.cap19 == 1`

* `com.gos.platform.api.devparam.SoundDetectionParam`
```json
{
  "un_switch": 0,
  "un_sensitivity": 0
}

```

| Field name    | Type    | Describe                                           |
| ------------ | ------- | ------------------------------------------------------ |
| CMDType      | String  | cap19 |
| un_switch | Integer |  Whether to enable motion tracking; Value: 0-Off 1-On                 |
| un_sensitivity      | Detection sensitivity, values: 1-low, 2-medium, 3-high            |

## cap20

* lullaby
  `cap.cap20 > 0`  no used

## cap21

* Is it equipped with a battery
  `cap.cap21 == 1`

## cap22

* Does it support WIFI remote wake-up
  `cap.cap22 == 1` support

## cap23

* Is there a status indicator light
  `cap.cap23 == 1`

* `com.gos.platform.api.devparam.LedSwitchParam`
```json
{
  "device_led_switch": 1
}
```

| Field name    | Type    | Describe                                           |
| ------------ | ------- | ------------------------------------------------------ |
| CMDType      | String  | cap23 |
| device_led_switch | Integer |   Value: 0-Off 1-On                 |

## cap24

* Is there a camera switch
  `cap.cap24 > 0`
  <br> 0 does not support switches,
  <br>1 supports switches,
  <br>2 supports switches and plans
  
  * `com.gos.platform.api.devparam.DeviceSwitchParam`
```json

{
  "device_switch": 0
}
```

| Field name    | Type    | Describe                                           |
| ------------ | ------- | ------------------------------------------------------ |
| CMDType      | String  | cap24 |
| device_switch | Integer |   Value: 0-Off 1-On                 |

## cap25

* Is there a microphone switch
  `cap.cap25 == 1`

* `com.gos.platform.api.devparam.MicSwitchParam`
* see detail by `Speaker`

## cap26

* Whether to support cloud storage 0: No 1: Alibaba Cloud 2: Amazon 3: China Unicom
  `cap.cap26 > 0`

## cap27

* Whether to support video stream encryption
  `cap.cap27 == 1`

## cap28

* Whether to play TF stream

## cap29

* Whether to support power acquisition
  `cap.cap29 == 1` support
  * `com.gos.platform.api.devparam.BatteryInfoParam`
<br> see detail by `Battery Status`

## cap30

* Get the strength of the gateway model
  `cap.cap30 == 1`

## cap31

* Is there an alexa function
  <br> `cap.cap31 == 1 || cap.cap31 == 3`  SupportAlexaSkills
  <br> `cap.cap31 == 2 || cap.cap31 == 3`  SupportGoogleHome

## cap32

* Rotate video recording 0. Not supported 1. Supported
  `cap32 == 1`
  
* `com.gos.platform.api.devparam.MirrorModeSettingParam`
```json
{
  "mirror_mode": 1
}
```

| Field name    | Type    | Describe                                           |
| ------------ | ------- | ------------------------------------------------------ |
| CMDType      | String  | cap32 |
| device_switch | Integer |  0- No flipping 1- Horizontal flipping 2- Vertical flipping 3- Horizontal vertical flipping  |

## cap33

* humidity  `no used`

## cap34

* wbgt `no used`

## cap35

* Whether to support the doorbell ringtone setting
  `cap.cap35 == 1`

* `com.gos.platform.api.devparam.DoorbellVolumeParam`
```json
{
  "doorbell_ring_switch": 0,
  "volume_level": 0
}
```

| Field name    | Type    | Describe                                           |
| ------------ | ------- | ------------------------------------------------------ |
| CMDType      | String  | cap35 |
| doorbell_ring_switch | Integer |  Ring switch  0-OFF 1-ON|
| volume_level | Integer |  Volume level and switch  0-100|

## cap36

* Doorbell LED Light Switch
  `cap.cap36 == 1`  no used

## cap37

* Whether to support the camera switch plan
  `cap.cap37 == 1`
  
* `com.gos.platform.api.devparam.CameraSwitchPlanSettingParam`
```json
{
  "enable": 0,
  "repeat": 1,
  "start_time": 0,
  "end_time": 0
}
```

| Field name    | Type    | Describe                                           |
| ------------ | ------- | ------------------------------------------------------ |
| CMDType      | String  | cap37 |
| enable | Integer | Is the plan activated  0-OFF 1-ON|
| repeat | Integer |  Plan period, in bits and values, if enabled, the position is 1, seven days of the week, days [7]={"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; 0 indicates a one-time effect|
| start_time | Integer |  Use second expression; For example, the 8:00 time zone is 28800, which means 8 * 60 * 60|
| end_time | Integer | |

## cap38

* Whether the sensor can be mounted
  `cap.cap38 > 0`  support

## cap39

* Whether to cry to the police
  `cap.cap39 == 1` support

## cap40

* Whether to support sound and light alarm
  `cap.cap40 > 0` support
  
* `com.gos.platform.api.devparam.WarnSettingParam`
```json
{
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
```

| Field name    | Type    | Describe                                           |
| ----------- | ------- | ------------------------------------------------------------ |
| CMDType     | String  | cap40                                        |
| un_switch   | Integer | Sound and light linkage main switch, value: 0- closed 1- open                        |
| schedule    | Object  | Linkage Plan                                                    |
| un_switch   | Integer | Linkage plan switch, values: 0- off 1- on                           |
| un_repeat   | Integer | Plan period, in bits and values, if enabled, the position is 1, seven days of the week, days [7]={"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; 0 indicates a one-time effect |
| start_time  | Integer | The start time of the linkage plan, expressed in seconds; For example, the 8:00 time zone is 28800, which means 8  * 60 * 60 |
| end_time    | Integer | End time of linkage plan                                           |
| audio       | Object  | Linkage sound                                                   |
| un_switch   | Integer | Linkage sound switch, value: 0-Off 1-On                           |
| un_times    | Integer | Number of linked sound broadcasts                                          |
| un_volume   | Integer | Linkage sound broadcasting volume                                          |
| un_type     | Integer | Serial number of audio files for linkage sound broadcasting                                  |
| url         | String  | Linkage sound broadcasting audio file download link; Audio requires single channel 8K sampling rate in g711A format, with G711A file header removed |
| light       | Object  | Linkage lighting                                                     |
| un_switch   | Integer | Linkage light switch, value: 0-Off 1-On                           |
| un_duration | Integer |Duration of linkage light on                                        |

## cap41

* Whether to support automatic restart plan
`cap.cap41 > 0` support
  
* `com.gos.platform.api.devparam.ResetSettingParam`
```json
{
  "enable": 0,
  "repeat": 1,
  "start_time": 0
}
```

| Field name    | Type    | Describe                                           |
| ----------- | ------- | ------------------------------------------------------------ |
| CMDType     | String  | cap41                                        |
| enable   | Integer |0-OFF 1-ON                       |
| repeat    | Integer  | Planning cycle set by bit                                                   |
| start_time   | Integer | Restart time point                         |

## cap42

* Wide dynamic settings   `no used`

## cap43

* HTTPS OTA Upgrade
  `cap.cap43 == 1` support
* `com.gos.platform.api.devparam.UpgradeStatusParam`
```json
{
  "upgrade_item_type": 1,
  "upgrade_status": 1,
  "upgrade_progress": 80
}
```

| Field name    | Type    | Describe                                           |
| ----------- | ------- | ------------------------------------------------------------ |
| CMDType     | String  | cap43                                        |
| upgrade_item_type   | Integer |Upgraded module, refer to enumeration UPGRADE_ ITEM_ TYPE, non mandatory                       |
| upgrade_status    | Integer  | Upgrade status, refer to enumerating UPRADE_ State_ E                                                   |
| upgrade_progress   | Integer | ota progress                         |

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
  E_UPGRADE_DOWNLOADING = 0,            //(0)Downloading
  E_UPGRADE_FAILURE,              		//(1)Upgrade failed
  E_UPGRADE_SUCCESS,                	//(2)Upgrade Successful
  E_UPGRADE_DOWNLOAD_START,				//(3)Start downloading
  E_UPGRADE_DOWNLOAD_FINISH,			//(4)Download completed
  E_UPGRADE_CHECK_ERROR,				//(5)Upgrade package validation error
  E_UPGRADE_INSTALL_START,				//(6)Start Installation
}UPRADE_STATE_E;
```

## cap44

* Does the base station have ringtone settings `no used`

## cap45

* Outdoor doorbell LED setting
  `cap.cap45 > 0` support

* `com.gos.platform.api.devparam.DoorbellLedSwitchParam`
```json
{
  "doorbell_led_switch": 1
}
```

| Field name    | Type    | Describe                                           |
| ----------- | ------- | ------------------------------------------------------------ |
| CMDType     | String  | cap45                                        |
| doorbell_led_switch   | Whether to turn on the device (doorbell device) status indicator light, with values of 0- off 1- on                      |

## cap46

* Outdoor doorbell ringtone settings
* no used

## cap47

* Outdoor doorbell forced demolition alarm
  `cap.cap47 == 1` support

* `com.gos.platform.api.devparam.RemoveAlarmParam`
  <br> see detail by `Tamper Alert`

## cap48

* Power saving mode

`cap.cap48 == 1` support
* `com.gos.platform.api.devparam.LowpowerModeSettingParam`
<br> see detail by `Power saving Mode`

## cap49

* PIR specific settings
* no used

## cap50

`cap.cap50 > 0` support

* Anthropomorphic detection alarm 0: No 1: Anthropomorphic detection alarm 2: Anthropomorphic
  detection can be selected by region

* `com.gos.platform.api.devparam.SmdAlarmSettingParam`

```json
{
  "permcnt": 1,
  "perms": [
    {
      "pcnt": 4,
      "rect": [
        {
          "x": 0,
          "y": 0
        }
      ]
    }
  ],
  "un_sensitivity": 4,
  "un_switch": 1
}

```

| Field name    | Type    | Describe                                           |
| ----------- | ------- | ------------------------------------------------------------ |
| CMDType     | String  | cap50                                        |
| un_switch      | Integer | Human shape detection switch, values: 0-Off 1-On |
| un_sensitivity | Integer | Sensitivity, from low to high 1, 2, 3, 4, 5 |
| permcnt        | Integer | Number of areas                          |
| perms          | Array   | Details of all regions                      |
| pcnt           | Integer | Number of coordinate points in a single area                |
| rect           | Array   | Details of individual area coordinate points                |
| x              | Integer | Coordinate point x coordinate value, ranging from 0 to 10000        |
| y              | Integer | Coordinate point y coordinate value, ranging from 0 to 10000        |

## cap51

* Object detection alarm
  `cap.cap51 > 0` support

* `com.gos.platform.api.devparam.ObjTrackSettingParam`
```json
{
  "un_switch": 0
}
```

| Field name    | Type    | Describe                                           |
| ----------- | ------- | ------------------------------------------------------------ |
| CMDType     | String  | cap51                                        |
| un_switch      | Integer | Whether to enable motion tracking; Value: 0-Off 1-On|

## cap52

* Humanoid tracking
  `cap.cap52 > 0` support

* `com.gos.platform.api.devparam.HumanTrackSettingParam`
```json
{
  "un_switch": 0
}
```

| Field name    | Type    | Describe                                           |
| ----------- | ------- | ------------------------------------------------------------ |
| CMDType     | String  | cap52                                        |
| un_switch      | Integer | Whether to enable humanoid tracking; Value: 0-Off 1-On|

## cap53

* Volume Setting
  `cap.cap53 > 0` support
  
* `com.gos.platform.api.devparam.VolumeSettingParam`
```json
{
  "volume": 0
}
```

| Field name    | Type    | Describe                                           |
| ----------- | ------- | ------------------------------------------------------------ |
| CMDType     | String  | cap53                                        |
| volume      | Integer |  Device (universal device) prompt volume, ranging from 0 to 100|

## cap54

* Push frequency and time interval settings
  `cap.cap54 > 0` support


* `com.gos.platform.api.devparam.PushIntervalSettingParam`
```json
{
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

```

| Field name    | Type    | Describe                                           |
| ------------------------ | ------- | ------------------------------------------------------------ |
| CMDType                  | String  | cap54                                        |
| interval                 | Integer | Push interval                                                     |
| motion_detection_switch  | Integer | Movement detection alarm push switch, values: 0-Off 1-On                    |
| sound_detection_switch   | Integer | Audible alarm push switch, value: 0-Off 1-On                       |
| person_detection_switch  | Integer | Humanoid detection alarm push switch, values: 0- off 1- on                    |
| cry_alarm_switch         | Integer | Cry detection alarm push switch, values: 0-Off 1-On                   |
| temperature_alarm_switch | Integer | Temperature alarm push switch, value: 0- off 1- on                        |
| pir_alarm_switch         | Integer | PIR alarm push switch, values: 0-Off 1-On                         |
| enable                   | Integer | Is the plan activated                                                |
| repeat                   | Integer | Plan period, in bits and values, if enabled, the position is 1, seven days of the week, days [7]={"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; |
| start_time               | Integer | Use second expression; For example, the 8:00 time zone is 28800, which means 8  * 60 * 60                  |
| end_time                 | Integer |                                              |

## cap55

* The Capability Set of P2P Encryption
  `cap.cap55`
    * no used

## cap56

* Device supports streaming type 1: TUTK 2: P2P 3: TCP 4: P2P_ TCP (all supported)
  `cap.cap56 = 2`

## cap57

* Intercom type 0: Half duplex 1: Full duplex 2: Both half and full duplex are supported
  `cap.cap57 = 1`

## cap58

* Is it more than one drag
  `cap.cap58 == 1`
    * the doorbell no used

## cap59

* Device audio type 0: AAC 1: G711A
  `cap.cap59 = 0`

## cap60

* AI access 0: No access 1: Access server version 2: Access device version
  `cap.cap60 > 0` support

* `com.gos.platform.api.devparam.AIDetectSettingParam`
```json
{
  "AiMode": 1,
  "Pet_detect": {
    "un_switch": 0,
    "un_sensitivity": 1
  },
  "Car_detect": {
    "un_switch": 0,
    "un_sensitivity": 1
  },
  "Face_detect": {
    "un_switch": 0,
    "un_sensitivity": 1
  },
  "Cross_detect": {
    "un_switch": 0,
    "un_sensitivity": 1
  }
}
```

| Field name    | Type    | Describe                                           |
| -------------- | ------- | ------------------------------------------------------------ |
| CMDType        | String  | cap60                                        |
| AiMode         | Integer | 0- Not supported or completely prohibited<br/>1- Server recognition mode<br/>2- Device recognition mode<br/>3- Server+Device dual mode |
| Pet_detect     | object  | Pet identification related attributes                                             |
| Car_detect     | object  | Vehicle identification related attributes                                             |
| Face_detect    | object  | Facial recognition related attributes                                             |
| Cross_detect   | object  | Cross line detection related attributes                                             |
| un_switch      | Integer | Switch 0- Off 1- On                                           |
| un_sensitivity | Integer | The sensitivity range is as follows:<br/>Pet recognition: 0-5<br/>Vehicle recognition: 0-4<br/>Face recognition: 0-3<br/>Cross line detection: default 1 |

## cap61

* Obtain TF file list method 0: Ulife Long connection 1: P2P method 2: P2P method pagination refresh
  <br>`(cap.cap61 == 1 || cap.cap61 == 2 || cap.cap61 == 3) ? 1 : 0`  -- 0-Ulife, 1-P2P
  <br>`(cap.cap61 == 2 || cap.cap61 == 3)` support P2pPaging

## cap62

* Device cloud storage resolution
    <br>0 -- Default
  <br> 1--1 million
  <br> 2--2 million (1920 * 1080)
  <br> 3--3 million (2304 * 1296)
  <br> 4--4 million

<br>`cap.cap62 = 0`

## cap63

* Does card recording support full day recording event recording switching? 0. Not supported. 1.
  Supported
  `cap.cap63 > 0` support
    * `no used `
  

# Channel 
* Channel is reserved and defaults to 0,all channel params set 0

# What is Cellular Mobile Network and what is its use
* As a mobile network card for devices

# Error Code
The error I currently have_ The code is not very comprehensive, I need to seek assistance from my colleagues
Here is a previous document that I hope will be helpful to you
`EN.7z`


# channel 
* channel represents the channel number of the stream,We have multi-stream products,so the channel is defined,the default value for unichannel streaming products is 0
# Start Talk
you just need to change the params for this function
need to add a stream password parameter
```java
    int talkType = STREAM_TYPE;

      public void startTalk(View view){
        if(mConnection.isConnected()){
          mConnection.startTalk(0,mDevice==null?"":mDevice.getStreamPsw());
          mBtnStopTalk.setEnabled(true);
          }
        }
```
* mute/unmute
```java

//unmute
mConnection.startAudio(0);
//mute
mConnection.stopAudio(0);
@Deprecated
mConnection.stopAudio(0,psw)
```
# TF playback
* you need to make a request MsgType refer to `com.gos.platform.api.request.Request.MsgType` , These MsgTypes are signaling agreed upon with the server

```json

{
  "Body": {
    "DeviceId": "A99UD210EL1ED5E",
    "DeviceParam": {
      "CMDType": 972,
      "page_data": "202305303|202305313|202306013|202306023|202306033"
    },
    "SessionId": "4574153488637"
  },
  "MessageType": "BypassParamResponse",
  "ResultCode": 0 
}
```

* page_data  `202305303--- 20230530 is monthday an 3 is the total file of the day ` the class `com.gos.platform.device.domain.FileForMonth.ForMonth`
<br>  `202305303` The 0th to 7th digits are dates ,the remaining quantity is the number of video files
<br> the number of total files used to Paginated loading
<br> `20230530` means the day has record data
  
```java
//20230530 day time
String dayTime = getIntent().getStringExtra("DAY_TIME");
// 0-All records 1-Alarm records
int type = getIntent().getIntExtra("TYPE", 0);
//get files by this time
long startTime = dateFormat.parse(dayTime).getTime() / 1000;
//getRecDayEventRefresh call native function NativeGetRecDayEventRefresh
mDevice.getConnection().getRecDayEventRefresh(0, (int) startTime, 0, 0);  
```
<br> * response `GetRecDayEventRefreshResult`

```json
{
  "result": 0,
  "total_num": 1,
  "day_event_list": [
    {
      "start_time": 1597116179,
      "end_time": 1597116193,
      "type": 1

    },
    {
      "start_time": 1597116179,
      "end_time": 1597116193,
      "type": 0

    }
    
  ]
}
```
| Field name    | Type    | Describe                                           |
| ------------------------ | ------- | ------------------------------------------------------------ |
| CMDType                  | String  | getRecDayEventRefresh                                        |
| result                 | Integer |                                                    |
| total_num  | Integer | total num                    |
| day_event_list   | Array |                     |
| start_time  | Integer | start time of one file                   |
| end_time         | Integer | end time                 |
| type | Integer | 0- normal 1- alarm                       |
  



 







