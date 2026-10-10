package com.punia.notification.channel.impl;

import com.punia.notification.channel.NotificationChannel;
import com.punia.notification.enums.ChannelType;

public class SmsChannel implements NotificationChannel {
    @Override
    public ChannelType type() {
        return ChannelType.SMS;
    }

    @Override
    public void send(com.punia.notification.entities.Notification notification) {
        System.out.println("SMS notification with id: " + notification.getId() + " with body: " + notification.getMessageBody() + " sent to: " + notification.getRecipient());
    }
}
