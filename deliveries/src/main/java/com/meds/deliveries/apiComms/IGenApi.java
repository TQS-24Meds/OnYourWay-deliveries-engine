
package com.meds.deliveries.apiComms;

import com.meds.deliveries.exception.UnreachableServiceException;

import com.meds.deliveries.enums.DeliveryStatusEnum;

public interface IGenApi {

    void updateOrderStatus(DeliveryStatusEnum orderStatus, String storeUrl) throws UnreachableServiceException;
    void setRiderName(String rider, String storeUrl) throws UnreachableServiceException;

}
