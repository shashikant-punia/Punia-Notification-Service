package com.punia.notification.entities;

import com.punia.notification.enums.ChannelType;
import com.punia.notification.enums.Priority;

import java.util.Map;

public class NotificationRequest {
    private final String requestId;
    private final String userId;
    private final String templateId;
    private final Priority priority;
    private final Map<ChannelType, String> recipientInfo;
    private final Map<String, String> params;

    public NotificationRequest(String requestId, String userId, String templateId, Priority priority, Map<ChannelType, String> recipientInfo, Map<String, String> params) {
        this.requestId = requestId;
        this.userId = userId;
        this.templateId = templateId;
        this.priority = priority;
        this.recipientInfo = recipientInfo;
        this.params = params;
    }


    public Map<ChannelType, String> getRecipientInfo() {
        return recipientInfo;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getUserId() {
        return userId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public Priority getPriority() {
        return priority;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
