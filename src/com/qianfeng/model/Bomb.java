package com.qianfeng.model;

import com.qianfeng.view.GameView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.Serializable;
import java.util.Random;

/**
 * @author zzc
 */
public class Bomb implements Serializable {
    private int x;
    private int y;
    private int width;
    private int height;
    private transient Image bombImg;
    private int speed;
    private int newBombTime;

    public Bomb() {
        // 金币生成位置
        x = 1000;
        y = new Random().nextInt(GameView.HEIGHT) - 180;
        try {
            // 金币初始化图片
            bombImg = ImageIO.read(getClass().getResourceAsStream("/image/daodan.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initImage() {
        try {
            bombImg = ImageIO.read(getClass().getResourceAsStream("/image/daodan.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(int level) {
        x = GameView.WIDTH;
        y = new Random().nextInt(GameView.HEIGHT) - 180;
        width = 25 * level;
        height = 27 * level;
        speed = 3 * level;
        newBombTime = 130;
        try {
            bombImg = ImageIO.read(getClass().getResourceAsStream("/image/daodan.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bombPaint(Graphics graphics) {
        graphics.drawImage(bombImg, x, y, width, height, null);
    }

    public void step() {
        setX(getX() - speed);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getBombImg() {
        return bombImg;
    }

    public void setBombImg(Image bombImg) {
        this.bombImg = bombImg;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getNewBombTime() {
        return newBombTime;
    }

    public void setNewBombTime(int newBombTime) {
        this.newBombTime = newBombTime;
    }
}
