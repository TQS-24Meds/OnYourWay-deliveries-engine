package com.meds.deliveries.enums;

import java.util.stream.IntStream;

public enum RiderStatusEnum {
    
        // AVAILABLE("Available"), UNAVAILABLE("Unavailable");
        AVAILABLE, UNAVAILABLE;

        static private final RiderStatusEnum[] values = values();
    
        public static int getNumber(RiderStatusEnum current) {
            return IntStream.range(0, values.length).filter(i -> current == values[i]).findFirst().orElse(-1);
        }
    
        public static RiderStatusEnum getNext(RiderStatusEnum current) {
            return values[(getNumber(current) +1) % values.length];
        }
    
        // private final String status;  
    
        // private RiderStatusEnum (String status) { this.status = status; }
    
        // public String toString() { return this.status; }  
}
