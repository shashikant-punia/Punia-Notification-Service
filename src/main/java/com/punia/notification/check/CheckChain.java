package com.punia.notification.check;

import com.punia.notification.entities.Notification;

import java.util.List;

public class CheckChain {
    private final List<SendCheck> checks;

    public CheckChain(List<SendCheck> checks) {
        this.checks = checks;
    }

    public boolean allow(Notification notification) {
        for (SendCheck sendCheck: checks) {
            if (!sendCheck.allow(notification)) {
                System.out.println("Notification: " + notification.getId() + " by check: " + sendCheck.name());
                return false;
            }
        }
        return true;
    }
}
