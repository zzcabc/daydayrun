package com.qianfeng.view;

import com.qianfeng.imageView.BackgroundImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class GameOverView extends JFrame implements ActionListener {
    JLabel scoreJLabel;
    JButton againJButton;
    JButton exitJButton;
    FileOutputStream fos;
    FileInputStream fis;
    File file;
    int data;
    int scoreNew;
    boolean read = false;

    public GameOverView(int score) {

        scoreJLabel = new JLabel();
        scoreJLabel.setText("您本次游戏分数:" + score);
        scoreJLabel.setForeground(Color.orange);
        scoreJLabel.setLocation(120, 20);
        scoreJLabel.setSize(200, 20);
        add(scoreJLabel);


        againJButton = new JButton();
        againJButton.setText("再次游戏");
        againJButton.setLocation(80, 150);
        againJButton.setSize(100, 20);
        againJButton.addActionListener(this);
        add(againJButton);

        exitJButton = new JButton();
        exitJButton.setText("退出游戏");
        exitJButton.setSize(100, 20);
        exitJButton.setLocation(200, 150);
        exitJButton.addActionListener(this);
        add(exitJButton);

        BackgroundImage backgroundImage = new BackgroundImage(getClass().getResourceAsStream("/image/pp.png"));
        backgroundImage.setSize(393, 208);
        add(backgroundImage);


        setSize(393, 208);
        setLocation(750, 300);
        setUndecorated(true);
        setVisible(true);

//        if (Person.scoreMax < score) {
//            Person.scoreMax = score;
//            JOptionPane.showMessageDialog(null, "新纪录");
//        }
        file = new File("saveScore.bin");
        if (file.exists()) {
            System.out.println("文件存在，读取记录");
            try {
                fis = new FileInputStream("saveScore.bin");
                while ((data = fis.read()) != -1) {
                    System.out.println(data);
                    scoreNew = data;
                    read = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    System.out.println(scoreNew);
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("read" + read);
            if (read) {
                System.out.println("历史最高记录" + scoreNew);
                if (scoreNew < score) {
                    try {
                        fos = new FileOutputStream("saveScore.bin");
                        JOptionPane.showMessageDialog(null, "新纪录");
                        System.out.println("新纪录保存中...");
                        fos.write(score);
                        System.out.println("新纪录分数" + score);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            fos.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else if (!file.exists()) {
            try {
                fos = new FileOutputStream("saveScore.bin");
                System.out.println("没有文件，新建saveScore.bin文件");
                System.out.println("写入分数完成" + score);
                fos.write(score);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(againJButton)) {
            this.setVisible(false);
            this.dispose();
            new GameView();
        } else if (e.getSource().equals(exitJButton)) {
            this.setVisible(false);
            this.dispose();
            System.out.println("exit");
            System.exit(0);
        }
    }
}
