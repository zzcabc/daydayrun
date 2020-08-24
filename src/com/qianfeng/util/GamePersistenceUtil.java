package com.qianfeng.util;

import com.qianfeng.model.Bomb;
import com.qianfeng.model.GameData;
import com.qianfeng.model.Gold;
import com.qianfeng.model.Person;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author zzc
 */
public class GamePersistenceUtil {

    public static final String GAME_STATE_CONTINUE = "continue";

    public static void save(Person person, List<Gold> goldList, List<Bomb> bombList) {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream("savegame.bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            GameData gameData = new GameData();
            gameData.setPerson(person);
            gameData.setGoldList(goldList);
            gameData.setBombList(bombList);
            objectOutputStream.writeObject(gameData);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                objectOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static GameData getGameData() {
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream = null;
        GameData gameData = null;
        try {
            fileInputStream = new FileInputStream("savegame.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            gameData = (GameData) objectInputStream.readObject();
            gameData.getPerson().init();
            for (Gold gold : gameData.getGoldList()) {
                gold.initImage();
            }
            for (Bomb bomb : gameData.getBombList()) {
                bomb.initImage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                objectInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return gameData;
    }


}
