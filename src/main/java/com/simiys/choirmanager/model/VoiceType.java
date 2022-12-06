package com.simiys.choirmanager.model;

public enum VoiceType {
    S("S"),A("A"),
    T("T"),B("B");

    private String type;

    VoiceType (String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
