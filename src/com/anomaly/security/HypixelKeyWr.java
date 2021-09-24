package com.anomaly.security;

import java.util.ArrayList;

public interface HypixelKeyWr {
    public Integer getRequests();
    public ArrayList<String> getUUIDs();
    public String getKey();

    public void addUUID(String uuid);
    public void setRequests(Integer value);
    public void addRequest();

    public Boolean shouldRequest();
}
