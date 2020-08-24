package com.qianfeng.panel;

import com.qianfeng.model.Bomb;
import com.qianfeng.model.GameData;
import com.qianfeng.model.Gold;
import com.qianfeng.model.Person;
import com.qianfeng.soundView.BackgroundSound;
import com.qianfeng.util.GamePersistenceUtil;
import com.qianfeng.view.GameOverView;
import com.qianfeng.view.GameView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 游戏主逻辑代码
 *
 * @author zzc
 */
public class GameMainPanel extends JPanel implements KeyListener {
    //stop flase 不暂停 true暂停

    public static final int LEVEL_2_SCORE = 100;
    Image backGroundImage;
    Image backGroundNewImage;
    Image scoreImage;
    Image stopImage;
    Person person = new Person();
    boolean stop = false;
    // 金币加入列表，对其频繁增加删除
    List<Gold> goldList = new LinkedList<>();
    List<Bomb> bombList = new LinkedList<>();
    int level = 1;
    // 背景图片位移
    int backIndex_x = 0;
    // 金币位移
    int goldIndex = 0;
    // 导弹位移
    int bombIndex = 0;
    private GameData gameData;


    public GameMainPanel() {
        loadingBGMAndImage();
    }

    public GameMainPanel(String gameState) {
        if (gameState.equals(GamePersistenceUtil.GAME_STATE_CONTINUE)) {
            gameData = GamePersistenceUtil.getGameData();
            if (gameData != null) {
                person = gameData.getPerson();
                goldList = gameData.getGoldList();
                bombList = gameData.getBombList();
            }
        }
        loadingBGMAndImage();

    }

    public void loadingBGMAndImage() {
        // 将之前的BGM停止
        BackgroundSound.audioBackgroundSound.stop();
        // 加载游戏BGM
        try {
            new BackgroundSound(getClass().getResource("/sound/game.wav").toURI().toURL());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // 加载背景图片a9.png cc.png cc2.png a12.png
            stopImage = ImageIO.read(getClass().getResourceAsStream("/image/a9.png"));
            backGroundImage = ImageIO.read(getClass().getResourceAsStream("/image/cc.png"));
            backGroundNewImage = ImageIO.read(getClass().getResourceAsStream("/image/cc2.png"));
            scoreImage = ImageIO.read(getClass().getResourceAsStream("/image/a12.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawCoin(Graphics g) {
        // 绘制金币
        Gold gold = new Gold();
        gold.init();
        // 添加金币到列表中
        if (goldIndex++ % gold.getNewGoldTime() == 0) {
            goldList.add(gold);
        }
        // 在列表中对金币进行渲染
        for (Gold gold1 : goldList) {
            gold1.goldPaint(g);
            gold1.step();
        }

        Iterator<Gold> goldIterator = goldList.iterator();
        while (goldIterator.hasNext()) {
            Gold gold1 = goldIterator.next();
            // 吃金币, 金币碰撞
            if (person.getX() + person.getWidth() > gold1.getX()
                    && person.getX() < gold1.getX() + gold1.getWidth()
                    && person.getY() + person.getHeight() > gold1.getY()
                    && person.getY() < gold1.getY() + gold1.getHeight()) {
                goldIterator.remove();
                person.setScore(person.getScore() + gold1.getGoldPrice());
            }
            // 金币移动到边界消失
            if (gold1.getX() <= -gold1.getWidth()) {
                goldIterator.remove();
            }
        }
    }

    public void drawBomb(Graphics g) {
        Bomb bomb = new Bomb();
        bomb.init(level);
        if (bombIndex++ % bomb.getNewBombTime() == 0) {
            bombList.add(bomb);
        }

        for (Bomb bomb1 : bombList) {
            bomb1.bombPaint(g);
            bomb1.step();
        }

        Iterator<Bomb> bombIterator = bombList.iterator();
        while (bombIterator.hasNext()) {
            Bomb bomb1 = bombIterator.next();
            // 炸弹碰撞
            if (person.getX() + person.getWidth() > bomb1.getX()
                    && person.getX() < bomb1.getX() + bomb1.getWidth()
                    && person.getY() + person.getHeight() > bomb1.getY()
                    && person.getY() < bomb1.getY() + bomb1.getHeight()) {
                person.setDie(true);
            }

            // 导弹移动到边界消失
            if (bomb1.getX() <= -bomb1.getWidth()) {
                bombIterator.remove();
            }
        }
    }

    /**
     * 重写绘制图片
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (person.getScore() > LEVEL_2_SCORE) {
            level = 2;
            backGroundImage = backGroundNewImage;
        }
        // 图片文件, 起始x,y轴, 终止游戏页面高度, 宽度
        g.drawImage(backGroundImage, backIndex_x--, 0, GameView.WIDTH, GameView.HEIGHT, null);
        // 第二张图片,
        g.drawImage(backGroundImage, GameView.WIDTH + backIndex_x, 0, GameView.WIDTH, GameView.HEIGHT, null);
        if (backIndex_x == -GameView.WIDTH) {
            backIndex_x = 0;
        }


        // 计分板
        g.drawImage(scoreImage, 80, 40, 259, 64, null);
        // 设置字体颜色
        g.setColor(Color.orange);
        // 设置字体为 "微软雅黑" ，粗体，大小为15
        g.setFont(new Font("微软雅黑", Font.BOLD, 15));
        // 设置那啥位置
        g.drawString("您当前的分数为:" + person.getScore(), 140, 80);

        // 绘制游戏主角, 让 GameMainPanel 进行渲染
        person.step();
        person.personPaint(g);
        // 人物下降
        person.low();

        drawCoin(g);
        drawBomb(g);


        if (stop) {
            g.drawImage(stopImage, 0, 0, 76, 78, null);
        }

    }

    public void action() {
        Thread thread = new Thread() {

            @Override
            public void run() {
                while (!person.isDie()) {
                    try {
                        // 每隔 10ms 渲染一次图片
                        Thread.sleep(10);
                        if (!stop) {
                            repaint();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                GameView.destroy();
                new GameOverView(person.getScore());
            }
        };
        thread.start();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        // 向右移动，判断位置
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (person.getX() < 895) {
                person.setX(person.getX() + 20);
            }
        }
        // 向左移动判断位置
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (person.getX() > 0) {
                person.setX(person.getX() - 20);
            }
        }
        // 向上移动, 起始坐标(0,0)在窗体左上角, 对应位置减
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (person.getY() > 0) {
                person.setY(person.getY() - 20);
            }
        }
        // 向下移动, 起始坐标(0,0)在窗体左上角, 对应位置加
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (person.getY() < 320) {
                person.setY(person.getY() + 20);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!stop) {
                System.out.println("按下空格，暂停");
                GamePersistenceUtil.save(person, goldList, bombList);
                repaint();
                stop = !stop;
            } else if (stop) {
                System.out.println("游戏继续");
                repaint();
                stop = !stop;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
