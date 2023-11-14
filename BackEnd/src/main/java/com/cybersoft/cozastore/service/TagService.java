package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.payload.response.TagResponse;
import com.cybersoft.cozastore.repository.TagRepository;
import com.cybersoft.cozastore.service.imp.TagServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TagService implements TagServiceImp {
    @Autowired
    private TagRepository tagRepository;
    @Override
    public List<TagResponse> getListTag() {
        try {
            return  tagRepository.findAll().stream()
                    .map(tag -> TagResponse.builder()
                            .id(tag.getId())
                            .name(tag.getName())
                            .build())
                    .toList();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }
}
