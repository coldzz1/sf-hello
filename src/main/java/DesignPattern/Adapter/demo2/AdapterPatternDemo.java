package DesignPattern.Adapter.demo2;

/**
 *  使用 AudioPlayer 来播放不同类型的音频格式。
 */
public class AdapterPatternDemo {

    public static void main(String[] args) {
        String autioType="mp4";
        AudioPlayer audioPlayer= new AudioPlayer();
        audioPlayer.play(autioType,"ffff.com");
    }
}
