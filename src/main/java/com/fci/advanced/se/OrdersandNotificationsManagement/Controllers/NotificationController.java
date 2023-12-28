package com.fci.advanced.se.OrdersandNotificationsManagement.Controllers;

import com.fci.advanced.se.OrdersandNotificationsManagement.Services.NotificationService;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.Channel;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.EmailChannel;
import com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification.SMSChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping(value = "/send-notification")
    @ResponseBody
    public String sendNotification(){
        SMSChannel s = new SMSChannel();
        return this.notificationService.sendNotification(s,"01274520192");
    }
}
