package com.punia.notification.channel.impl;

import com.punia.notification.channel.NotificationChannel;
import com.punia.notification.entities.Notification;
import com.punia.notification.enums.ChannelType;

public class EmailChannel implements NotificationChannel {
    @Override
    public ChannelType type() {
        return ChannelType.EMAIL;
    }

    @Override
    public void send(Notification notification) {
        System.out.println("Email notification with id: " + notification.getId() + " with body: " + notification.getMessageBody() + " sent to: " + notification.getRecipient());
    }
}
