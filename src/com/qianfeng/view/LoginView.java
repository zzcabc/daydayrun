package com.qianfeng.view;

import com.qianfeng.imageView.BackgroundImage;
import com.qianfeng.soundView.BackgroundSound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 游戏登录界面展示
 *
 * @author zzc
 */
public class LoginView extends JFrame implements ActionListener {
    JLabel usernameJlabel = null;
    JLabel passwordJlabel = null;
    JTextField usernameJTextField = null;
    JPasswordField passwordJTextField = null;
    JButton loginJButton = null;
    JButton escJButton = null;

    public void loginShow() {
        // 用户名显示
        usernameJlabel = new JLabel();
        // 设置文本名称
        usernameJlabel.setText("用户名:");
        // 设置窗体位置
        usernameJlabel.setLocation(40, 140);
        // 设置窗体大小
        usernameJlabel.setSize(50, 20);
        // 设置字体颜色
        usernameJlabel.setForeground(Color.blue);
        // 添加到主窗体
        add(usernameJlabel);

        // 密码显示
        passwordJlabel = new JLabel();
        passwordJlabel.setText("密 码:");
        passwordJlabel.setLocation(40, 180);
        passwordJlabel.setSize(50, 20);
        passwordJlabel.setForeground(Color.blue);
        add(passwordJlabel);

        // 用户名填写框
        usernameJTextField = new JTextField();
        usernameJTextField.setLocation(90, 143);
        usernameJTextField.setSize(100, 20);
        usernameJTextField.setText("admin");
        add(usernameJTextField);

        // 密码填写框
        passwordJTextField = new JPasswordField();
        passwordJTextField.setLocation(90, 182);
        passwordJTextField.setSize(100, 20);
        passwordJTextField.setText("password");
        add(passwordJTextField);

        // 登录按钮
        loginJButton = new JButton();
        try {
            loginJButton.setIcon(new ImageIcon(getClass().getResource("/image/denglu.gif").toURI().toURL()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        loginJButton.setLocation(40, 250);
        loginJButton.setSize(68, 20);
        // 将窗体加入事件监听
        loginJButton.addActionListener(this);
        add(loginJButton);

        // 取消按钮
        escJButton = new JButton();
        try {
            escJButton.setIcon(new ImageIcon(getClass().getResource("/image/quxiao.gif").toURI().toURL()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        escJButton.setLocation(130, 250);
        escJButton.setSize(68, 20);
        escJButton.addActionListener(this);
        add(escJButton);
        // 背景音乐
        try {
            new BackgroundSound(getClass().getResource("/sound/main.wav").toURI().toURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 背景图片
        // 图层问题，背景图放置最后
        BackgroundImage backgroundImage = new BackgroundImage(getClass().getResourceAsStream("/image/login.jpg"));
        backgroundImage.setSize(599, 370);
        add(backgroundImage);

        // 设置主窗体图标
        try {
            setIconImage(new ImageIcon(getClass().getResource("/image/115.png").toURI().toURL()).getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 设置主窗体位置
        setLocation(0, 0);
        // 设置主窗体大小
        setSize(599, 370);
        // 展示窗口
        setVisible(true);
    }

    /**
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = "admin";
        String password = "password";
        if (e.getSource().equals(loginJButton)) {
            if (username.equals(usernameJTextField.getText()) && password.equals(String.valueOf(passwordJTextField.getPassword()))) {
                JOptionPane.showMessageDialog(null, "登录成功");
                //关闭当前窗口
                this.dispose();
                // 进入选择窗口
                new ChooseView();
            } else {
                JOptionPane.showMessageDialog(null, "登录失败");
            }
        } else if (e.getSource().equals(escJButton)) {
            System.exit(0);
        }
    }
}
