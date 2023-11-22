package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.OdersRequest;
import com.cybersoft.cozastore.payload.response.OrderReponse;

import java.util.List;

public interface OrderServiceImp {
    boolean insertOrder(OdersRequest odersRequest);
    boolean updateOrder(OdersRequest odersRequest);
    boolean deleteOrder(int idUser);

    List<OrderReponse> getOrderRequestList(int idUser);

}
