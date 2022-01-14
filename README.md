# QrCodeLib

## 致谢
- ZXing 
- ahuyangdong  https://github.com/ahuyangdong/QrCodeLib

# zxing-lib 二维码使用

### 引入
#### 主gradle中添加
```gradle
buildscript {
    repositories {
        mavenCentral()
    }
}
```
#### 项目工程gradle中添加　　　　[当前最新版：————> 1.0.0　　　　![最新版](https://img.shields.io/badge/%E6%9C%80%E6%96%B0%E7%89%88-1.0.0-green.svg)](https://search.maven.org/artifact/com.kotlinx/zxing-lib)

```gradle
implementation 'com.kotlinx:zxing-lib:1.0.0'
```

### 扫描二维码
```kotlin
//开始扫码，代码调用这一行。
registerPermission.launch(Manifest.permission.CAMERA)


//请求权限结果 (这是成员变量，注册事件)
private val registerPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
    if (it) registerCapture.launch(Intent(this, CaptureActivity::class.java))
}

//扫码结果 (这是成员变量，注册事件)
private val registerCapture = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
    if (result.resultCode == Activity.RESULT_OK) {
        val scanResult = result.data?.extras?.getString(com.google.zxing.util.Constant.INTENT_EXTRA_KEY_QR_SCAN)
        //这儿是后续操作
        binding.tvResult.text = scanResult
    }
}
```

### 生成二维码
```java
Bitmap bitmap = QrCodeGenerator.getQrCodeImage("yujing", 512, 512);
```