package com.qianfeng.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author zzc
 */
public class GameData implements Serializable {
    private Person person;
    private List<Gold> goldList;
    private List<Bomb> bombList;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Gold> getGoldList() {
        return goldList;
    }

    public void setGoldList(List<Gold> goldList) {
        this.goldList = goldList;
    }

    public List<Bomb> getBombList() {
        return bombList;
    }

    public void setBombList(List<Bomb> bombList) {
        this.bombList = bombList;
    }

}
