package com.drp.entity;

public class RChannelResourceEntity {
    private int id;
    private String channelName;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RChannelResourceEntity that = (RChannelResourceEntity) o;

        if (id != that.id) return false;
        if (status != that.status) return false;
        if (channelName != null ? !channelName.equals(that.channelName) : that.channelName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (channelName != null ? channelName.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }
}
