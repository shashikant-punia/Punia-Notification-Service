package com.punia.notification;

import com.punia.notification.channel.impl.EmailChannel;
import com.punia.notification.channel.impl.SmsChannel;
import com.punia.notification.check.CheckChain;
import com.punia.notification.check.impl.DuplicateCheck;
import com.punia.notification.check.impl.OptOutCheck;
import com.punia.notification.check.impl.RateLimitCheck;
import com.punia.notification.entities.NotificationRequest;
import com.punia.notification.enums.ChannelType;
import com.punia.notification.enums.Priority;
import com.punia.notification.repository.impl.InMemoryNotificationRepository;
import com.punia.notification.service.NotificationService;
import com.punia.notification.service.TemplateService;
import com.punia.notification.service.impl.InMemoryTemplateService;
import com.punia.notification.service.impl.NotificationServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NotificationServiceMain {
    static void main(String[] args) {
        Map<String, String> templates = new HashMap<>() {{
            put("template:EMAIL", "Hello {name}, your EMAIL-{orderId} is shipped");
            put("template:SMS", "Hello {name}, your SMS-{orderId} is shipped");
        }};
        TemplateService templateService = new InMemoryTemplateService(templates);

        Map<String, String> params = new HashMap<>() {{
            put("name", "Shashikant Punia");
            put("orderId", "FMPP56779");
        }};

        NotificationRequest notificationRequest = new NotificationRequest(
                "REQ-XMP-FRAGILE-ID-5698",
                "X-FRAGILE_SHIPMENT_ORCHESTRATOR",
                "template",
                Priority.CRITICAL,
                new HashMap<>() {{
                    put(ChannelType.SMS, "+917082329497");
                    put(ChannelType.EMAIL, "shashikant.punia@gmail.com");
                }},
                params
        );
        NotificationService notificationService = new NotificationServiceImpl(
                templateService,
                new ArrayList<>() {{
                    add(new SmsChannel());
                    add(new EmailChannel());
                }},
                new CheckChain(new ArrayList<>() {{
                    add(new DuplicateCheck());
                    add(new OptOutCheck());
                    add(new RateLimitCheck());
                }}),
                new InMemoryNotificationRepository()
        );

        notificationService.send(notificationRequest);
    }
}
