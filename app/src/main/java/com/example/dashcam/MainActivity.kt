package com.example.dashcam

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.pedro.common.ConnectChecker
import com.pedro.encoder.input.video.CameraHelper
import com.pedro.library.rtmp.RtmpCamera1
import com.pedro.library.view.OpenGlView

class MainActivity : AppCompatActivity(), ConnectChecker {
    private lateinit var rtmpCamera: RtmpCamera1
    private lateinit var openGlView: OpenGlView
    private val rollNo = "BTECH2520623"
    private val rtmpUrl = "rtmp://15.207.177.194:1936/hackathon/${rollNo}_front"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openGlView = findViewById(R.id.surfaceView)

        if (hasPermissions()) {
            initCamera()
        } else {
            requestPermissions()
        }

        findViewById<android.widget.Button>(R.id.btnStart).setOnClickListener {
            if (!::rtmpCamera.isInitialized) return@setOnClickListener
            if (!rtmpCamera.isStreaming) {
                startStreaming()
            } else {
                stopStreaming()
            }
        }
    }

    private fun initCamera() {
        rtmpCamera = RtmpCamera1(openGlView, this)
    }

    private fun hasPermissions(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO),
            101
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101 && hasPermissions()) {
            initCamera()
        }
    }

    private fun startStreaming() {
        if (rtmpCamera.prepareVideo(1280, 720, 25, 1500 * 1024, 0, CameraHelper.getCameraOrientation(this))
            && rtmpCamera.prepareAudio(128 * 1024, 32000, true)) {
            rtmpCamera.startStream(rtmpUrl)
        }
    }

    private fun stopStreaming() {
        rtmpCamera.stopStream()
    }

    override fun onConnectionStarted(url: String) {}
    override fun onConnectionSuccess() {}
    override fun onConnectionFailed(reason: String) { stopStreaming() }
    override fun onNewBitrate(bitrate: Long) {}
    override fun onDisconnect() {}
    override fun onAuthError() {}
    override fun onAuthSuccess() {}
}