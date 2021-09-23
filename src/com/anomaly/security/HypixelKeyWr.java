package com.anomaly.security;

import java.util.ArrayList;

public interface HypixelKeyWr {
    public void addUUID(String uuid);
    public ArrayList<String> getUUIDs();

    public void setRequests(Integer value);
}
