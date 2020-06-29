package DesignPattern.Adapter.demo2;

/**
 * Description: github
 * <p>
 * Created by ys on 2020/6/28 10:10
 */
public class Mp4Player implements AdvancedMediaPlayer{
    @Override
    public void playLvc(String fileName) {
        //什么都不做
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name "+fileName);
    }
}
