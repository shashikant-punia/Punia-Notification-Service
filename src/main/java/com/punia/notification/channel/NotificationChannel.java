package com.punia.notification.channel;

import com.punia.notification.entities.Notification;
import com.punia.notification.enums.ChannelType;

public interface NotificationChannel {
    ChannelType type();
    void send(Notification notification);
}
