package com.simiys.choirmanager.model;

public enum Permission {
    READ("read"),
    JOIN_CHOIRS("join_choirs");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }
    public String getPermission() {
        return permission;
    }
}
