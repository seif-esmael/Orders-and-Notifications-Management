package com.fci.advanced.se.OrdersandNotificationsManagement.Controllers;

import com.fci.advanced.se.OrdersandNotificationsManagement.Services.NotificationService;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.Channel;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationController
{
    @Autowired
    private NotificationService notificationService = new NotificationService();
    @GetMapping(value = "/displayNotifications")
    public String displayNotifications(@RequestParam Language language, @RequestParam Channel channel)
    {
        return notificationService.displayNotifications(language, channel);
    }
    @GetMapping(value = "/mostNotifiedEmail")
    public String mostNotifiedEmail()
    {
        return notificationService.mostNotifiedEmail();
    }
    @GetMapping(value = "/mostNotifiedPhoneNumber")
    public String mostNotifiedPhoneNumber()
    {
        return notificationService.mostNotifiedPhoneNumber();
    }
    @GetMapping(value = "/mostUsedTemplate")
    public String mostUsedTemplate()
    {
        return notificationService.mostUsedTemplate();
    }
}
