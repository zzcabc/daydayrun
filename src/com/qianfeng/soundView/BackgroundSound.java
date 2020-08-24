package com.qianfeng.soundView;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

/**
 * @author zzc
 */
public class BackgroundSound {
    public static AudioClip audioBackgroundSound;

    public BackgroundSound(URL url) {
        try {
            // 设置背景BGM
            audioBackgroundSound = Applet.newAudioClip(url);
            audioBackgroundSound.play();
            audioBackgroundSound.loop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
