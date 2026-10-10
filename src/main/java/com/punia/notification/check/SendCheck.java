package com.punia.notification.check;

import com.punia.notification.entities.Notification;

public interface SendCheck {
    boolean allow(Notification notification);

    default String name() {
        return getClass().getSimpleName();
    }
}
