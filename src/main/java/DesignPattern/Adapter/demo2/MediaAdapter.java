package DesignPattern.Adapter.demo2;

/**
 *   原本MediaPlayer 只能播放Mp3,想让其也能播放Mp4 lvc
 */

//创建了MediaPlayed的适配器
public class MediaAdapter implements MediaPlayer{

    AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType){
        if("vlc".equalsIgnoreCase(audioType)){
            advancedMediaPlayer=new VlcPlayer();
        }else {
            advancedMediaPlayer=new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if("vlc".equalsIgnoreCase(audioType)){
            this.advancedMediaPlayer.playLvc(fileName);
        }else {
            this.advancedMediaPlayer.playMp4(fileName);
        }
    }
}
