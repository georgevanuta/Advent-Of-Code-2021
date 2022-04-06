package com.company;

public class BoardNumber {
    private Integer number;
    private Boolean isBingo;

    public BoardNumber(Integer number) {
        this.number = number;
        isBingo = false;
    }

    public Integer Number() {
        return this.number;
    }

    public void getBingoed() {
        this.isBingo = true;
    }

    public Boolean isBingo() {
        return this.isBingo;
    }
}
