package com.dommy.qrcodelib

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dommy.qrcodelib.databinding.ActivityMainBinding
import com.google.zxing.activity.CaptureActivity
import com.google.zxing.util.QrCodeGenerator

/**
 * 扫码或者生成二维码
 * @author yujing 2021年9月8日11:31:47
 */
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //开始扫码，先请求摄像头权限
        binding.btnQrCode.setOnClickListener {
            registerPermission.launch(Manifest.permission.CAMERA)
        }
        //生成二维码
        binding.btnGenerate.setOnClickListener {
            generateQrCode()
        }
    }

    //请求权限结果
    private val registerPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it) registerCapture.launch(Intent(this, CaptureActivity::class.java))
    }

    //扫码结果
    private val registerCapture = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val scanResult = result.data?.extras?.getString(com.google.zxing.util.Constant.INTENT_EXTRA_KEY_QR_SCAN)
            //这儿是后续操作
            binding.tvResult.text = scanResult
        }
    }

    /**
     * 生成二维码
     */
    private fun generateQrCode() {
        if (binding.etContent.text.toString() == "") {
            Toast.makeText(this, "请输入二维码内容", Toast.LENGTH_SHORT).show()
            return
        }
        val bitmap = QrCodeGenerator.getQrCodeImage(binding.etContent.text.toString(), binding.imgQrcode.width, binding.imgQrcode.height)
        if (bitmap == null) {
            Toast.makeText(this, "生成二维码出错", Toast.LENGTH_SHORT).show()
            binding.imgQrcode.setImageBitmap(null)
        } else {
            binding.imgQrcode.setImageBitmap(bitmap)
        }
    }
}