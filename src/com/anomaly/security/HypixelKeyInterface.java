package com.anomaly.security;

import java.util.ArrayList;

public interface HypixelKeyInterface {
    public Integer getRequests();
    public ArrayList<String> getUUIDs();
    public String getKey();

    public void addUUID(String uuid);
    public void setRequests(Integer value);
    public void addRequest();

    public Boolean shouldRequest();
}
