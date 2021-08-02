# QrCodeLib

![platform](https://img.shields.io/badge/platform-Android-lightgrey.svg)
![Gradle](https://img.shields.io/badge/Gradle-7.1-brightgreen.svg)
[![last commit](https://img.shields.io/github/last-commit/ahuyangdong/QrCodeLib.svg)](https://github.com/ahuyangdong/QrCodeLib/commits/master)
![repo size](https://img.shields.io/github/repo-size/ahuyangdong/QrCodeLib.svg)
[![Licence](https://img.shields.io/github/license/ahuyangdong/QrCodeLib.svg)](https://github.com/ahuyangdong/QrCodeLib/blob/master/LICENSE)

A Android library for qrcode scanning and generating, depends on zxing library


Android ZXing二维码扫描库，支持二维码扫描和生成，演示效果：

![image](https://github.com/ahuyangdong/QrCodeLib/blob/master/demo.gif)

特别提醒：本分支为androidx支持版本，如需非androidx版本库，请至[master分支](https://github.com/ahuyangdong/QrCodeLib/tree/master)

## 博客讲解
https://blog.csdn.net/ahuyangdong/article/details/89339970


## 致谢

- ZXing

[![Zxing](https://camo.githubusercontent.com/cd92fcc87ebc531c60edc667da4a77b90c004ff0/68747470733a2f2f7261772e6769746875622e636f6d2f77696b692f7a78696e672f7a78696e672f7a78696e672d6c6f676f2e706e67)](https://github.com/zxing/zxing)

- HappyMiao/QrCodeScan

https://www.jianshu.com/p/e80a85b17920

https://github.com/HappyMiao/QrCodeScan


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
#### 项目工程gradle中添加　　　　[当前最新版：————> 1.0.0　　　　![最新版](https://img.shields.io/badge/%E6%9C%80%E6%96%B0%E7%89%88-1.6.2-green.svg)](https://search.maven.org/artifact/com.kotlinx/zxing-lib)

```gradle
implementation 'com.kotlinx:zxing-lib:1.0.0'
```

### 扫描二维码
```java
//扫码结果
private val registerCapture = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
    if (result.resultCode == Activity.RESULT_OK) {
        val bundle = result.data?.extras
        var scanResult = bundle!!.getString(Constants.INTENT_EXTRA_KEY_QR_SCAN)
        //这儿是后续操作
    }
}

//请求权限结果
private val registerPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
    if (it) registerCapture.launch(Intent(this, CaptureActivity::class.java))
}

//开始扫码，代码调用这一行。
registerPermission.launch(Manifest.permission.CAMERA)

```


### 生成二维码
```java
Bitmap bitmap = QrCodeGenerator.getQrCodeImage("yujing", 512, 512);
```