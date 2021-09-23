package com.anomaly.security;

import java.util.ArrayList;

public class HypixelKey implements HypixelKeyWr {
    private ArrayList<String> uuid = new ArrayList<String>();
    private Integer requests = 0; // Requests this minute.

    public HypixelKey(String uuid_) {
        uuid.add(uuid_);
    }

    @Override
    public void addUUID(String uuid_) {
        uuid.add(uuid_);
    }

    @Override
    public ArrayList<String> getUUIDs() {
        return uuid;
    }

    @Override
    public void setRequests(Integer value) {
        requests = value;
    }
}
