import Flutter
import UIKit
import AVFoundation

public class SwiftFlutterAudioOutputPlugin: NSObject, FlutterPlugin {
    public static func register(with registrar: FlutterPluginRegistrar) {
        let channel = FlutterMethodChannel(name: "flutter_audio_output", binaryMessenger: registrar.messenger())
        let instance = SwiftFlutterAudioOutputPlugin()
        registrar.addMethodCallDelegate(instance, channel: channel)
    }

    public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
        if call.method == "setSpeaker" {
            if let args = call.arguments as? [String: Any],
               let useSpeaker = args["useSpeaker"] as? Bool {
                let session = AVAudioSession.sharedInstance()
                try? session.setCategory(useSpeaker ? .playback : .playAndRecord)
                try? session.overrideOutputAudioPort(useSpeaker ? .speaker : .none)
                try? session.setActive(true)
                result(nil)
            } else {
                result(FlutterError(code: "BAD_ARGS", message: "Wrong arguments", details: nil))
            }
        } else {
            result(FlutterMethodNotImplemented)
        }
    }
}
