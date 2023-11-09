package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.CategoryEntity;
import com.cybersoft.cozastore.entity.ColorEntity;
import com.cybersoft.cozastore.entity.ProductEntity;
import com.cybersoft.cozastore.entity.SizeEntity;
import com.cybersoft.cozastore.payload.request.ProductRequest;
import com.cybersoft.cozastore.payload.response.*;
import com.cybersoft.cozastore.repository.ProductRepository;
import com.cybersoft.cozastore.service.imp.ProductServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ProductService implements ProductServiceImp {

    @Value("${path.upload.file}")
    private String FolderRoot;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductResponse> getListProduct() {
        try {
            List<ProductEntity> getListProduct = productRepository.findAll();
            return getListProduct.stream().map(productEntity -> ProductResponse.builder()
                    .id(productEntity.getId())
                    .name(productEntity.getName())
                    .image(Arrays.stream(productEntity.getImage().split(" ")).toList())
                    .price(productEntity.getPrice())
                    .description(productEntity.getDescription())
                    .quanity(productEntity.getQuanity())
                    .categoryEntity(CategoryResponse.builder()
                            .name(productEntity.getCategoryEntity().getName())
                            .build())
                    .colorEntity(ColorResponse.builder()
                            .name(productEntity.getColorEntity().getName())
                            .build())
                    .sizeEntity(SizeResponse.builder()
                            .name(productEntity.getSizeEntity().getName())
                            .build())
                    .createDate(productEntity.getCreateDate())

                    .listCart(productEntity.getListCart().stream()
                            .map(cartEntity -> CartResponse.builder()
                                    .id(cartEntity.getId())
                                    .quanity(cartEntity.getQuanity())
                                    .createDate(cartEntity.getCreateDate())
                                    .build())
                            .toList())

//                    .listOrder(null)

                    .build()).toList();

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public boolean addProduct(ProductRequest productRequest, List<MultipartFile> files) {
        try {
            Path root = Paths.get(FolderRoot);
            if (!Files.exists(root)) {
                Files.createDirectory(root);
            }
            String imgNames = "";
            for (MultipartFile file : files
            ) {
                Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                imgNames += file.getOriginalFilename() + " ";
            }
            Date now = new Date();
            ProductEntity productEntity = productRepository.save(ProductEntity.builder()
                    .name(productRequest.getName())
                    .image(imgNames.trim())
                    .price(productRequest.getPrice())
                    .description(productRequest.getDescription())
                    .quanity(productRequest.getQuanity())
                    .categoryEntity(CategoryEntity.builder()
                            .id(productRequest.getCategory())
                            .build())
                    .colorEntity(ColorEntity.builder()
                            .id(productRequest.getColor())
                            .build())
                    .sizeEntity(SizeEntity.builder()
                            .id(productRequest.getSize())
                            .build())
                    .createDate(now)
                    .build());
            return true;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean deleteProduct(int id) {
        try {
            Optional<ProductEntity> productEntity = productRepository.findById(id);
            String imgName = productEntity.get().getImage();
            Path root = Paths.get(FolderRoot + imgName);
            log.info(root.toString());
            if (!Files.exists(root)) {
                Files.createDirectory(root);
            }
            Files.deleteIfExists(root);
            productRepository.deleteById(id);
            log.warn("ID đã xóa: " + id);
            return true;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return false;
        }


    }
}
