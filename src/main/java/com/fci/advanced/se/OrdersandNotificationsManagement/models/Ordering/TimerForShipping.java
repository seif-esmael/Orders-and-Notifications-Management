package com.fci.advanced.se.OrdersandNotificationsManagement.models.Ordering;

import java.util.TimerTask;

class TimerForShipping extends TimerTask
{
    Shipping shipping;

    TimerForShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    @Override
    public void run() {
        shipping.setCancelPlacementDuration(0);
    }
}
