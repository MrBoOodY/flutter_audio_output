package com.example.flutter_audio_output

import android.content.Context
import android.media.AudioManager
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

class FlutterAudioOutputPlugin: FlutterPlugin, MethodCallHandler {
  private lateinit var channel : MethodChannel
  private lateinit var audioManager: AudioManager

  override fun onAttachedToEngine(binding: FlutterPluginBinding) {
    channel = MethodChannel(binding.binaryMessenger, "flutter_audio_output")
    channel.setMethodCallHandler(this)
    audioManager = binding.applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
  }

  override fun onMethodCall(call: MethodCall, result: Result) {

    when (call.method) {
      "setSpeaker" -> {
        val useSpeaker = call.argument<Boolean>("useSpeaker") ?: true

        audioManager.isSpeakerphoneOn = useSpeaker
        audioManager.mode = if (useSpeaker) AudioManager.MODE_NORMAL else AudioManager.MODE_IN_COMMUNICATION
        result.success(null)
      }
      else -> result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(binding: FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
