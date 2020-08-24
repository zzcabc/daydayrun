package com.qianfeng.view;

import com.qianfeng.imageView.BackgroundImage;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

/**
 * 选择窗体界面
 *
 * @author zzc
 */
public class ChooseView extends JFrame implements MouseListener {

    JLabel startGameJLabel;
    JLabel helpGameJLabel;
    JLabel exitJLabel;
    JLabel chooseJLabel;

    public ChooseView() {
        //开始游戏选项
        startGameJLabel = new JLabel();
        startGameJLabel.setLocation(360, 280);
        startGameJLabel.setSize(150, 40);
        try {
            startGameJLabel.setIcon(new ImageIcon(getClass().getResource("/image/hh1.png").toURI().toURL()));
            startGameJLabel.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        startGameJLabel.addMouseListener(this);
        add(startGameJLabel);
//
//        chooseJLabel = new JLabel();
//        chooseJLabel.setLocation(120, 120);
//        chooseJLabel.setSize(150, 40);
//        try {
//            chooseJLabel.setIcon(new ImageIcon(getClass().getResource("/image/h3.png").toURI().toURL()));
//            startGameJLabel.setEnabled(false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        chooseJLabel.setEnabled(false);
//        chooseJLabel.addMouseListener(this);
//        add(chooseJLabel);


        // 帮助选项
        helpGameJLabel = new JLabel();
        helpGameJLabel.setLocation(360, 350);
        helpGameJLabel.setSize(150, 40);
        try {
            helpGameJLabel.setIcon(new ImageIcon(getClass().getResource("/image/hh43.png").toURI().toURL()));
            startGameJLabel.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        helpGameJLabel.setEnabled(false);
        helpGameJLabel.addMouseListener(this);
        add(helpGameJLabel);

        //取消按钮
        exitJLabel = new JLabel();
        exitJLabel.setLocation(360, 420);
        exitJLabel.setSize(150, 40);
        try {
            exitJLabel.setIcon(new ImageIcon(getClass().getResource("/image/hh3.png").toURI().toURL()));
            startGameJLabel.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        exitJLabel.setEnabled(false);
        exitJLabel.addMouseListener(this);
        add(exitJLabel);


        BackgroundImage backgroundImage = new BackgroundImage(getClass().getResourceAsStream("/image/main.png"));
        add(backgroundImage);
        try {
            setIconImage(new ImageIcon(getClass().getResource("/image/115.png").toURI().toURL()).getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLocation(0, 0);
        setSize(1136, 640);
        setResizable(false);
        setVisible(true);
    }

    /**
     * 鼠标点击事件
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(startGameJLabel)) {
            // 停止当前窗口
            this.dispose();
            // 进入加载窗口
            Thread thread = new Thread(new LoadingView());
            thread.start();

        }
        if (e.getSource().equals(helpGameJLabel)) {

            File file = new File("savegame.bin");
            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "没有存档");
            } else if (file.exists()) {
                this.dispose();
                Thread thread = new Thread(new ContinueView("continue"));
                thread.start();
            }
        }
        if (e.getSource().equals(exitJLabel)) {
            JOptionPane.showMessageDialog(null, "exit");
            this.dispose();
            System.exit(0);
        }
        if (e.getSource().equals(chooseJLabel)) {

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * 鼠标移动
     * 如果鼠标移动至此，显示当前窗口
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(startGameJLabel)) {
            startGameJLabel.setEnabled(true);
        }
        if (e.getSource().equals(helpGameJLabel)) {
            helpGameJLabel.setEnabled(true);
        }
        if (e.getSource().equals(exitJLabel)) {
            exitJLabel.setEnabled(true);
        }

    }

    /**
     * 鼠标移动
     * 如果鼠标移动至此以外，不显示当前窗口
     *
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(startGameJLabel)) {
            startGameJLabel.setEnabled(false);
        }
        if (e.getSource().equals(helpGameJLabel)) {
            helpGameJLabel.setEnabled(false);
        }
        if (e.getSource().equals(exitJLabel)) {
            exitJLabel.setEnabled(false);
        }
    }
}
