package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.payload.response.SizeResponse;
import com.cybersoft.cozastore.repository.SizeRepository;
import com.cybersoft.cozastore.service.imp.SizeServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SizeService implements SizeServiceImp {
    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<SizeResponse> getListSize() {
        try {
            return sizeRepository.findAll().stream()
                    .map(
                            size -> SizeResponse.builder()
                                    .id(size.getId())
                                    .name(size.getName())
                                    .build()
                    ).toList();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }
}
