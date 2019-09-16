package com.cnitpm.z_common.Model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by zengwei on 2019/7/21.
 * 全部数据的状态
 */

public class AllDataState<T> implements MultiItemEntity {
    private int itemType;
    private T data;
    private int state;
    private String message;

    public AllDataState(T data,int itemType) {
        this.data=data;
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AllDataState{" +
                "itemType=" + itemType +
                ", data=" + data +
                ", state=" + state +
                ", message='" + message + '\'' +
                '}';
    }
}
