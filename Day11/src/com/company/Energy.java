package com.company;

public class Energy {
    private Integer level;
    private Boolean isFlashing;

    public Energy(Integer level) {
        this.level = level;
        this.isFlashing = false;
    }

    public Integer getLevel() {
        return level;
    }

    private void flash() {
        isFlashing = true;
    }

    public void stopFlashing() {
        isFlashing = false;
    }

    public Boolean isFlashing() {
        return isFlashing;
    }

    public void incrementLevel() {
        level++;
    }

    public void resetLevelAndStartFlashing() {
        level = 0;
        isFlashing = true;
    }
}
