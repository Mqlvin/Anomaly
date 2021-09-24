package com.anomaly.security;

import java.util.ArrayList;

public class HypixelKey implements HypixelKeyWr {
    private volatile ArrayList<String> uuid = new ArrayList<>();
    private volatile Integer requests = 0; // Requests this minute.
    private volatile String key = "";

    public HypixelKey(String uuid_, String key_) {
        uuid.add(uuid_);
        key = key_;
    }

    @Override
    public void addUUID(String uuid_) {
        uuid.add(uuid_);
    }

    @Override
    public Integer getRequests() {
        if(requests != null) {
            return requests;
        }
        return null;
    }

    @Override
    public ArrayList<String> getUUIDs() {
        return uuid;
    }

    @Override
    public String getKey() {
        if(key != null) {
            return key;
        }
        return null;
    }

    @Override
    public void setRequests(Integer value) {
        requests = value;
    }

    @Override
    public void addRequest() {
        requests += 1;
    }

    @Override
    public Boolean shouldRequest() {
        if(requests < 20) {
            return true;
        }
        return false;
    }
}
