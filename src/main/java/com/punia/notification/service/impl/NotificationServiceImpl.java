package com.punia.notification.service.impl;

import com.punia.notification.channel.NotificationChannel;
import com.punia.notification.check.CheckChain;
import com.punia.notification.entities.Notification;
import com.punia.notification.entities.NotificationRequest;
import com.punia.notification.enums.ChannelType;
import com.punia.notification.enums.Status;
import com.punia.notification.repository.NotificationRepository;
import com.punia.notification.service.NotificationService;
import com.punia.notification.service.TemplateService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NotificationServiceImpl implements NotificationService {
    private final TemplateService templateService;
    private final Map<ChannelType, NotificationChannel> notificationChannelMap;
    private final CheckChain checkChain;
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(TemplateService templateService, List<NotificationChannel> notificationChannelList, CheckChain checkChain, NotificationRepository notificationRepository) {
        this.templateService = templateService;
        this.notificationChannelMap = notificationChannelList.stream().collect(Collectors.toMap(NotificationChannel::type, c -> c));
        this.checkChain = checkChain;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void send(NotificationRequest notificationRequest) {
        for (Map.Entry<ChannelType, String> recipientInfo: notificationRequest.getRecipientInfo().entrySet()) {
            ChannelType channelType = recipientInfo.getKey();
            String recipient = recipientInfo.getValue();

            NotificationChannel notificationChannel = notificationChannelMap.get(channelType);
            String messageBody = templateService.render(notificationRequest.getTemplateId(), channelType, notificationRequest.getParams());

            Notification notification = new Notification(notificationRequest.getRequestId(),
                    notificationRequest.getUserId(),
                    channelType,
                    notificationRequest.getPriority(),
                    messageBody,
                    recipient
            );

            deliver(notification);
        }
    }

    private void deliver(Notification notification) {
        if (!checkChain.allow(notification)) {
            return;
        }

        NotificationChannel notificationChannel = notificationChannelMap.get(notification.getChannelType());
        try {
            notificationChannel.send(notification);
            notification.setStatus(Status.SENT);
        } catch (Exception e) {
            notification.setStatus(Status.FAILED);
        }
        notificationRepository.save(notification);
    }
}
