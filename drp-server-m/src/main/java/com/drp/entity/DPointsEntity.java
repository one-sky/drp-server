package com.drp.entity;

public class DPointsEntity {
    private int id;
    private String pointsDesc;
    private int pointsAmount;
    private int actionAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPointsDesc() {
        return pointsDesc;
    }

    public void setPointsDesc(String pointsDesc) {
        this.pointsDesc = pointsDesc;
    }

    public int getPointsAmount() {
        return pointsAmount;
    }

    public void setPointsAmount(int pointsAmount) {
        this.pointsAmount = pointsAmount;
    }

    public int getActionAmount() {
        return actionAmount;
    }

    public void setActionAmount(int actionAmount) {
        this.actionAmount = actionAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DPointsEntity that = (DPointsEntity) o;

        if (id != that.id) return false;
        if (pointsAmount != that.pointsAmount) return false;
        if (actionAmount != that.actionAmount) return false;
        if (pointsDesc != null ? !pointsDesc.equals(that.pointsDesc) : that.pointsDesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pointsDesc != null ? pointsDesc.hashCode() : 0);
        result = 31 * result + pointsAmount;
        result = 31 * result + actionAmount;
        return result;
    }
}
