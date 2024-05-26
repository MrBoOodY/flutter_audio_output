import 'flutter_audio_output_platform_interface.dart';

class FlutterAudioOutput {
  Future<void> setSpeaker(bool useSpeaker) {
    return FlutterAudioOutputPlatform.instance.setSpeaker(useSpeaker);
  }
}
