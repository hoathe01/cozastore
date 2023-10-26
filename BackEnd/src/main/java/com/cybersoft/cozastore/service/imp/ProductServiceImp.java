package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.BlogRequest;
import com.cybersoft.cozastore.payload.request.ProductRequest;
import com.cybersoft.cozastore.payload.response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductServiceImp {
    List<ProductResponse> getListProduct();

    boolean addProduct(ProductRequest productRequest, MultipartFile image);
}
