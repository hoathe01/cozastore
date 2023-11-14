package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.payload.response.ColorResponse;
import com.cybersoft.cozastore.repository.ColorRepository;
import com.cybersoft.cozastore.service.imp.ColorServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ColorService implements ColorServiceImp {
    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<ColorResponse> getListColor() {
        try {
            return colorRepository.findAll()
                    .stream().map(
                            color -> ColorResponse.builder()
                                    .id(color.getId())
                                    .name(color.getName())
                                    .build()
                    ).toList();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }
}
