package com.punia.notification.check.impl;

import com.punia.notification.check.SendCheck;
import com.punia.notification.entities.Notification;

public class DuplicateCheck implements SendCheck {
    @Override
    public boolean allow(Notification notification) {
        // Implementation for duplicate check
        return true;
    }
}
