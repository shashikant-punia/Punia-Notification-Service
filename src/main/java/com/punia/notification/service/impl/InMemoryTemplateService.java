package com.punia.notification.service.impl;

import com.punia.notification.enums.ChannelType;
import com.punia.notification.service.TemplateService;

import java.util.Map;

public class InMemoryTemplateService implements TemplateService {
    private final Map<String, String> templates;

    public InMemoryTemplateService(Map<String, String> templates) {
        this.templates = templates;
    }

    @Override
    public String render(String templateId, ChannelType channelType, Map<String, String> params) {
        String template = templates.get(templateId + ":" + channelType.name());
        if (template == null) {
            throw new IllegalArgumentException("Template not found: " + templateId);
        }
        String messageBody = template;
        for (Map.Entry<String, String> entry: params.entrySet()) {
            messageBody = messageBody.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return messageBody;
    }
}
