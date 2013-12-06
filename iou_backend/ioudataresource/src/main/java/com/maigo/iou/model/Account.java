package com.maigo.iou.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account {

    private int accountId;
    private String authId;
    private String authPassword;
    private boolean enabled;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAuthPassword() {
        return authPassword;
    }

    public void setAuthPassword(String authPassword) {
        this.authPassword = authPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return String.format(
                "account:[account_id: %d, auth_id: %s, auth_password: %s, enabled: %b]",
                accountId, authId, authPassword, enabled);
    }

}
