package com.qianfeng.imageView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

/**
 * @author zzc
 */
public class BackgroundImage extends JPanel {
    Image backgroundImage = null;

    public BackgroundImage(InputStream inputStream) {
        try {
            // 设置背景图片
            backgroundImage = ImageIO.read(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        //绘画图片
        g.drawImage(backgroundImage, 0, 0, null);
    }
}
