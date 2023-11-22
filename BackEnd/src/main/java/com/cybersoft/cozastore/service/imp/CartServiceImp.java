package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.CartRequest;
import com.cybersoft.cozastore.payload.response.CartResponse;

import java.util.List;

public interface CartServiceImp {
    boolean insertCart(CartRequest cartRequest);
    boolean updateCart(CartRequest cartRequest);

    boolean deleteCart(int id);


    List<CartResponse> getCartResponse(int idUser);
}
