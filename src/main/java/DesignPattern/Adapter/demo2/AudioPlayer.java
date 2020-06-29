package DesignPattern.Adapter.demo2;

/**
 * Description: github
 * <p>
 * Created by ys on 2020/6/28 10:19
 */
public class AudioPlayer implements MediaPlayer{
    MediaAdapter mediaAdapter;



    @Override
    public void play(String audioType, String fileName) {

        //原本就支持的内置的mp3的播放器
        if("mp3".equalsIgnoreCase(audioType)){
            System.out.println("Playing mp3 file. Name"+ fileName);
        }
        //mediaAdapter 提供了播放其他文件格式的支持
        else if ("lvc".equalsIgnoreCase(audioType)||"mp4".equalsIgnoreCase(audioType)){
            mediaAdapter=new MediaAdapter(audioType);
            mediaAdapter.play(audioType,fileName);
        }
        else {
            System.out.println("Invalid media. "+
                    audioType + " format not supported");
        }
    }
}
