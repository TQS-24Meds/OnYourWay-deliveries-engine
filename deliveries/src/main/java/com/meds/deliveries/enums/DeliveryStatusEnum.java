package com.meds.deliveries.enums;

import java.util.stream.IntStream;

public enum DeliveryStatusEnum {
    
    // PENDENT("pendend"), ACCEPTED("accepted"), PICKED_UP("picked_up"), DELIVERED("delivered");
    PENDENT, ACCEPTED, PICKED_UP, ON_DELIVERY, DELIVERED;

    static private final DeliveryStatusEnum[] values = values();

    public static int getNumber(DeliveryStatusEnum current) {
        return IntStream.range(0, values.length).filter(i -> current == values[i]).findFirst().orElse(-1);
    }

    public static DeliveryStatusEnum getNext(DeliveryStatusEnum current) {
        return values[(getNumber(current) +1) % values.length];
    }

    public DeliveryStatusEnum orElseThrow(Object object) {
        return null;
    }

    // private final String status;  

    // private DeliveryStatusEnum (String status) { this.status = status; }

    // public String toString() { return this.status; }    

}
