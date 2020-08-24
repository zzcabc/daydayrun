package com.qianfeng.view;

import com.qianfeng.panel.GameMainPanel;

import javax.swing.*;

/**
 * 游戏主视图页
 *
 * @author zzc
 */
public class GameView extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 550;
    public static GameView gameView;

    public GameView() {
        gameView = this;
        GameMainPanel gameMainPanel = new GameMainPanel();
        // 添加游戏主逻辑代码
        addKeyListener(gameMainPanel);
        add(gameMainPanel);
        gameMainPanel.action();

        // 设置窗体宽度，高度
        setSize(WIDTH, HEIGHT);
        // 设置初始化位置
        setLocation(0, 0);
        // 设置窗口可调整大小为不能调整
        setResizable(false);
        // 禁用或启用此框架的装饰。去掉窗体边框（最大最小化）
        setUndecorated(false);
        // 设置焦点
        setFocusable(true);
        // 显示窗口
        setVisible(true);
        // 设置图标
        try {
            setIconImage(new ImageIcon(getClass().getResource("/image/115.png").toURI().toURL()).getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public GameView(String gameState) {
        gameView = this;
        GameMainPanel gameMainPanel = new GameMainPanel(gameState);
        // 添加游戏主逻辑代码
        addKeyListener(gameMainPanel);
        add(gameMainPanel);
        gameMainPanel.action();

        // 设置窗体宽度，高度
        setSize(WIDTH, HEIGHT);
        // 设置初始化位置
        setLocation(0, 0);
        // 设置窗口可调整大小为不能调整
        setResizable(false);
        // 禁用或启用此框架的装饰。去掉窗体边框（最大最小化）
        setUndecorated(false);
        // 设置焦点
        setFocusable(true);
        // 显示窗口
        setVisible(true);
        // 设置图标
        try {
            setIconImage(new ImageIcon(getClass().getResource("/image/115.png").toURI().toURL()).getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void destroy() {
        gameView.dispose();
    }


}
