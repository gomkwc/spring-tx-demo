package com.neowiz.process.member.domain;

/**
 * User: David KIM
 * Date: 14. 9. 9.
 * Time: 오전 10:32
 * To change this template use File | Settings | File Templates.
 */
public class Member {

    private int seq;
    private String name;
    private int money;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}
