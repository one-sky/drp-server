package com.drp.entity;

public class DVipEntity {
    private int id;
    private String vipLevel;
    private int points;
    private int levelCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(int levelCode) {
        this.levelCode = levelCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DVipEntity that = (DVipEntity) o;

        if (id != that.id) return false;
        if (points != that.points) return false;
        if (levelCode != that.levelCode) return false;
        if (vipLevel != null ? !vipLevel.equals(that.vipLevel) : that.vipLevel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (vipLevel != null ? vipLevel.hashCode() : 0);
        result = 31 * result + points;
        result = 31 * result + levelCode;
        return result;
    }
}
