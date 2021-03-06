package cos.mos.toolkit.media.audio;

import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Description: 简易录音机
 * @Author: Kosmos
 * @Date: 2019.05.29 15:01
 * @Email: KosmoSakura@gmail.com
 * 1.录音-》保存为m4a
 * 2.丢子线程去
 * ---------------------------------------------
 * MediaRecorder(基于文件录音)
 * 已集成了录音，编码，压缩等，支持少量的音频格式文件。
 * 优点：封装度很高，操作简单
 * 缺点：无法实现实时处理音频，输出的音频格式少
 */
public class URecorderMedia extends Thread {
    private MediaRecorder recorder;
    private String dirs;//保存文件的路径+名字
    private long startTime = 0;

    /**
     * 保存根目录路径
     */
    private void checkDir() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "M4aRecorder" + File.separator);
        if (!file.exists()) {
            file.mkdir();
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        dirs = file.getAbsolutePath() + File.separator + dateFormat.format(new Date()) + ".m4a";
    }

    public String getDirs() {
        return dirs;
    }

    @Override
    public void run() {
        toStop();
        checkDir();
        toStart();
    }

    /**
     * 输出格式：
     * 1.MediaRecorder.OutputFormat.MPEG_4：
     * 输出的文件将是一个MPEG_4文件。可能同时包含音频和视频轨。
     * 2.MediaRecorder.OutputFormat.RAW_AMR：
     * 输出一个没有任何容器类型的原始文件。只支持音频且音频编码要求为AMR_NB
     * 3.MediaRecorder.OutputFormat.THREE_GPP：
     * 3gp格式、H263视频、ARM音频编码(扩展名.3gp)。可能包含音频和视频轨。
     */
    private void toStart() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);//从麦克风采集（必须在setOutputFormat之前调用）
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4); //输出格式：MP4
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);//编码格式：AAC
        recorder.setAudioChannels(1);//1(mono) , 2(stereo)
        recorder.setAudioSamplingRate(44100);//所有android系统都支持的适中采样的频率
        recorder.setAudioEncodingBitRate(96000);//设置音质频率(192000
        recorder.setOutputFile(dirs);//文件录音的位置
        startTime = System.currentTimeMillis();
        try {
            recorder.prepare();
            recorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long toStop() {
        if (recorder != null) {
            try {
                recorder.stop();
                recorder.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
            recorder = null;
            long diff = System.currentTimeMillis() - startTime;
            if (diff < 1000) {
                //录音时间小于1秒
            }
            return diff;
        } else {
            return 0;
        }
    }
}
