package com.simiys.choirmanager.model;

public enum VoiceType {
    S("S", 4),A("A", 3),
    T("T", 2),B("B",1);

    private String type;

    private int val;

    VoiceType (String type, int val) {
        this.type = type; this.val = val;
    }

    public String getType() {
        return type;
    }

    public int getVal() {
        return val;
    }
}
