package DesignPattern.Adapter.demo2;

/**
 * Description: github
 * <p>
 * Created by ys on 2020/6/28 10:09
 */
public class VlcPlayer implements AdvancedMediaPlayer{
    @Override
    public void playLvc(String fileName) {
        System.out.println("Playing vlc file. Name: "+ fileName);
    }

    @Override
    public void playMp4(String fileName) {
        //什么都不做
    }
}
