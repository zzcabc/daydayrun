package com.qianfeng.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author zzc
 */
public class ContinueView extends JFrame implements Runnable {
    JLabel backImage;
    JProgressBar jProgressBar;
    String string;

    public ContinueView(String stateGame) {
        // 游戏加载背景图
        string = stateGame;
        backImage = new JLabel();
        try {
            backImage.setIcon(new ImageIcon(getClass().getResource("/image/hbg.jpg").toURI().toURL()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        backImage.setSize(568, 320);
        add(backImage, BorderLayout.NORTH);

        // 加载条
        jProgressBar = new JProgressBar();
        jProgressBar.setBackground(Color.orange);
        jProgressBar.setStringPainted(true);
        add(jProgressBar);


        try {
            setIconImage(new ImageIcon(getClass().getResource("/image/115.png").toURI().toURL()).getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 设置焦点
        setFocusable(false);
        setResizable(false);
        setSize(568, 420);
        setLocation(0, 0);
        setVisible(true);
    }


    @Override
    public void run() {
        int[] loadingNumber = new int[]{5, 10, 15, 20, 25, 40, 50, 65, 79, 86, 99, 100};
        for (int i : loadingNumber) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 进度条设置属性值
            jProgressBar.setValue(i);
            if (i == 100) {
                this.dispose();
                new GameView(string);
            }
        }


    }
}
