package com.fci.advanced.se.OrdersandNotificationsManagement.models.Notification;

import java.util.TimerTask;

class TimerForNotification extends TimerTask
{
    @Override
    public void run()
    {
        NotificationManager.pop();
    }
}
