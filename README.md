# QrCodeLib

![platform](https://img.shields.io/badge/platform-Android-lightgrey.svg)
![Gradle](https://img.shields.io/badge/Gradle-4.10.1-brightgreen.svg)
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
        maven { url 'http://kotlinx.com/repository' }
    }
}
```
#### 项目工程gradle中添加
```gradle
implementation 'com.kotlinx.zxing-lib:zxing-lib:1.0.0'
```



### 扫描二维码
```java
// 开始扫码
private void startQrCode() {
    // 申请相机权限
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
        // 申请权限
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                .CAMERA)) {
            Toast.makeText(this, "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_SHORT).show();
        }
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, Constant.REQ_PERM_CAMERA);
        return;
    }
    // 申请文件读写权限（部分朋友遇到相册选图需要读写权限的情况，这里一并写一下）
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        // 申请权限
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                .WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "请至权限中心打开本应用的文件读写权限", Toast.LENGTH_SHORT).show();
        }
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constant.REQ_PERM_EXTERNAL_STORAGE);
        return;
    }
    // 二维码扫码
    Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
    startActivityForResult(intent, Constant.REQ_QR_CODE);
}


    @Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode) {
        case Constant.REQ_PERM_CAMERA:
            // 摄像头权限申请
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 获得授权
                startQrCode();
            } else {
                // 被禁止授权
                Toast.makeText(MainActivity.this, "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_LONG).show();
            }
            break;
        case Constant.REQ_PERM_EXTERNAL_STORAGE:
            // 文件读写权限申请
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 获得授权
                startQrCode();
            } else {
                // 被禁止授权
                Toast.makeText(MainActivity.this, "请至权限中心打开本应用的文件读写权限", Toast.LENGTH_LONG).show();
            }
            break;
    }
}


@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    //扫描结果回调
    if (requestCode == Constant.REQ_QR_CODE && resultCode == RESULT_OK) {
        Bundle bundle = data.getExtras();
        String scanResult = bundle.getString(Constant.INTENT_EXTRA_KEY_QR_SCAN);
        //将扫描出的信息显示出来
        tvResult.setText(scanResult);
    }
}
```


### 生成二维码
```java
Bitmap bitmap = QrCodeGenerator.getQrCodeImage("yujing", 512, 512);
```