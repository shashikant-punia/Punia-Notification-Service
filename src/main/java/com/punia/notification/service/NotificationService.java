package com.punia.notification.service;

import com.punia.notification.entities.NotificationRequest;

public interface NotificationService {
    void send(NotificationRequest notificationRequest);
}
