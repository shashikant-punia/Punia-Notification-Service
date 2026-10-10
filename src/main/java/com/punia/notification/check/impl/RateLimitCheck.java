package com.punia.notification.check.impl;

import com.punia.notification.check.SendCheck;
import com.punia.notification.entities.Notification;

public class RateLimitCheck implements SendCheck {
    @Override
    public boolean allow(Notification notification) {
        // Implementation for rate limit check
        return true;
    }
}
