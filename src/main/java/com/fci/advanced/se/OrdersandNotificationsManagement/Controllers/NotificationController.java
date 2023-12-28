package com.fci.advanced.se.OrdersandNotificationsManagement.Controllers;

import com.fci.advanced.se.OrdersandNotificationsManagement.Services.NotificationService;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.Channel;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.EmailChannel;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.Language;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.SMSChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notifications")
public class NotificationController
{
    @Autowired
    private NotificationService notificationService = new NotificationService();

    @GetMapping(value = "/displayNotifications")
    public String displayNotifications(@RequestParam Language language)
    {
        return notificationService.displayNotifications(language);
    }
}
