package com.punia.notification.service;

import com.punia.notification.enums.ChannelType;

import java.util.Map;

public interface TemplateService {
    String render(String templateId, ChannelType channelType, Map<String, String> params);
}
