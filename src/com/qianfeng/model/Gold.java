package com.qianfeng.model;

import com.qianfeng.view.GameView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.Serializable;
import java.util.Random;

/**
 * @author zzc
 */
public class Gold implements Serializable {
    private int x;
    private int y;
    private int width = 40;
    private int height = 40;
    private transient Image goldImg = null;
    private int speed = 2;
    private int newGoldTime = 30;
    private int goldPrice = 10;
    private int imageIndex[] = new int[100];
    private int imageIndexLength;

    public Gold() {

    }

    public void initImage() {
        try {
            // 金币初始化图片
            goldImg = ImageIO.read(getClass().getResourceAsStream("/image/" + imageIndex[imageIndexLength] + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
        imageIndexLength++;
        imageIndex[imageIndexLength] = new Random().nextInt(6) + 21;

        x = 1000;
        y = new Random().nextInt(GameView.HEIGHT) - 180;

        try {
            // 金币初始化图片
            goldImg = ImageIO.read(getClass().getResourceAsStream("/image/" + imageIndex[imageIndexLength] + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 绘制金币图片
    public void goldPaint(Graphics graphics) {
        graphics.drawImage(goldImg, x, y, width, height, null);
    }

    // 金币移动
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

    public Image getGoldImg() {
        return goldImg;
    }

    public void setGoldImg(Image goldImg) {
        this.goldImg = goldImg;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getNewGoldTime() {
        return newGoldTime;
    }

    public void setNewGoldTime(int newGoldTime) {
        this.newGoldTime = newGoldTime;
    }

    public int getGoldPrice() {
        return goldPrice;
    }

    public void setGoldPrice(int goldPrice) {
        this.goldPrice = goldPrice;
    }

    public int[] getImageIndex() {
        return imageIndex;
    }

    public void setImageIndex(int[] imageIndex) {
        this.imageIndex = imageIndex;
    }

    public int getImageIndexLength() {
        return imageIndexLength;
    }

    public void setImageIndexLength(int imageIndexLength) {
        this.imageIndexLength = imageIndexLength;
    }


}
