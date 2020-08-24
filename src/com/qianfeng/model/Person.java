package com.qianfeng.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * 游戏角色
 *
 * @author zzc
 */
public class Person implements Serializable {
    int personIndex = 0;
    private int speed = 5;
    private int x = 20;
    private int y = 320;
    private int width = 110;
    private int height = 110;
    private transient Image personImage;
    private transient BufferedImage[] personBufferedImage = {};
    private int score;
    private boolean die;
    private int maxScore;

    public Person() {
        try {
            // 角色的运动状态图片显示
            personBufferedImage = new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/image/1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/image/2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/image/3.png")),
                    ImageIO.read(getClass().getResourceAsStream("/image/4.png")),
                    ImageIO.read(getClass().getResourceAsStream("/image/5.png")),
                    ImageIO.read(getClass().getResourceAsStream("/image/6.png")),
                    ImageIO.read(getClass().getResourceAsStream("/image/7.png")),
                    ImageIO.read(getClass().getResourceAsStream("/image/8.png")),
                    ImageIO.read(getClass().getResourceAsStream("/image/9.png"))
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
        try {
            personBufferedImage = new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/image/1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/image/2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/image/3.png")),
                    ImageIO.read(getClass().getResourceAsStream("/image/4.png")),
                    ImageIO.read(getClass().getResourceAsStream("/image/5.png")),
                    ImageIO.read(getClass().getResourceAsStream("/image/6.png")),
                    ImageIO.read(getClass().getResourceAsStream("/image/7.png")),
                    ImageIO.read(getClass().getResourceAsStream("/image/8.png")),
                    ImageIO.read(getClass().getResourceAsStream("/image/9.png"))
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 绘制游戏角色的函数,一定要保证是 游戏主场景 绘制文件,服务于游戏主场景
    public void personPaint(Graphics graphics) {
        graphics.drawImage(personImage, x, y, width, height, null);
    }

    // 移动时更换角色动作
    public void step() {
        if (personIndex == 1000) {
            personIndex = 0;
        }
        personImage = personBufferedImage[personIndex++ / speed % personBufferedImage.length];
    }

    public void low() {
        if (getY() < 320) {
            setY(getY() + 2);
        }
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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

    public Image getPersonImage() {
        return personImage;
    }

    public void setPersonImage(Image personImage) {
        this.personImage = personImage;
    }

    public BufferedImage[] getPersonBufferedImage() {
        return personBufferedImage;
    }

    public void setPersonBufferedImage(BufferedImage[] personBufferedImage) {
        this.personBufferedImage = personBufferedImage;
    }

    public boolean isDie() {
        return die;
    }

    public void setDie(boolean die) {
        this.die = die;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }
}
