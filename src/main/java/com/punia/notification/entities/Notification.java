package com.punia.notification.entities;

import com.punia.notification.enums.ChannelType;
import com.punia.notification.enums.Priority;
import com.punia.notification.enums.Status;

import java.util.UUID;

public class Notification {
    private final String id;
    private final String requestId;
    private final String userId;
    private final ChannelType channelType;
    private final Priority priority;
    private Status status;
    private final String messageBody;
    private final String recipient;

    public Notification(String requestId, String userId, ChannelType channelType, Priority priority, String messageBody, String recipient) {
        this.messageBody = messageBody;
        this.recipient = recipient;
        this.id = UUID.randomUUID().toString();
        this.requestId = requestId;
        this.userId = userId;
        this.channelType = channelType;
        this.priority = priority;
        this.status = Status.PENDING;
    }

    public String getRecipient() {return recipient;}

    public void setStatus(Status status) {this.status = status;}

    public String getMessageBody() {return messageBody;}

    public String getId() {
        return id;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getUserId() {
        return userId;
    }

    public ChannelType getChannelType() {
        return channelType;
    }

    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }
}
