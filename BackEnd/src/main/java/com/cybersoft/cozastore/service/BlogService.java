package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.*;
import com.cybersoft.cozastore.entity.key.BlogTagKey;
import com.cybersoft.cozastore.payload.request.BlogRequest;
import com.cybersoft.cozastore.payload.request.TagRequest;
import com.cybersoft.cozastore.payload.response.*;
import com.cybersoft.cozastore.repository.BlogRepository;
import com.cybersoft.cozastore.repository.BlogTagRepository;
import com.cybersoft.cozastore.service.imp.BlogServiceImp;
import io.jsonwebtoken.io.Decoders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class BlogService implements BlogServiceImp {
    @Value("${path.upload.file}")
    private String FolderRoot;

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private BlogTagRepository blogTagRepository;

    @Override
    public List<BlogResponse> getListBlog() {
        try {
            //Lay het danh sach blog
            List<BlogEntity> blogEntitiesList = blogRepository.findAll();
            return blogEntitiesList.stream().map(blogEntity -> BlogResponse.builder()
                    .id(blogEntity.getId())
                    .title(blogEntity.getTitle())
                    .image(blogEntity.getImage())
                    .content(blogEntity.getContent())
                    .createDate(new DateResponse(blogEntity.getCreateDate()))
                    .user(UserResponse.builder()
                            .email(blogEntity.getUserEntity().getEmail())
                            .username(blogEntity.getUserEntity().getUsername())
                            .role(new RoleResponse(blogEntity.getUserEntity().getRole().getName(), null))
                            .build())
                    .listComment(blogEntity.getListComment().stream()
                            .map(commentEntity -> CommentResponse.builder()
                                    .name(commentEntity.getName())
                                    .email(commentEntity.getEmail())
                                    .content(commentEntity.getContent())
                                    .createDate(commentEntity.getCreateDate())
                                    .build())
                            .toList())
                    .listTag(blogEntity.getListTag().stream().map(tag -> TagResponse.builder()
                                    .name(tag.getTag().getName())
                                    .build())
                            .toList())
                    .build()).toList();

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public boolean addBlog(BlogRequest blogRequest, MultipartFile file) {
        try {
            Path root = Paths.get(FolderRoot);
            if (!Files.exists(root)) {
                Files.createDirectory(root);
            }
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            BlogEntity blog =
                    blogRepository.save(BlogEntity.builder()
                            .title(blogRequest.getTitle())
                            .image(file.getOriginalFilename())
                            .content(blogRequest.getContent())
                            .userEntity(UserEntity.builder()
                                    .id(blogRequest.getUser())
                                    .build())
                            .build());
            blogRequest.getListTag().forEach(
                    tagRequest -> blogTagRepository.save(BlogTagEntity.builder()
                            .blogTagKey(new BlogTagKey(blog.getId(), tagRequest.getId()))
                            .build()));
            return true;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean deleteBlog(int id) {
        try {
            Optional<BlogEntity> blogEntity = blogRepository.findById(id);
            String imgName = blogEntity.get().getImage();
            Path root = Paths.get(FolderRoot + imgName);
            log.info(root.toString());
            if (!Files.exists(root)) {
                Files.createDirectory(root);
            }
            Files.deleteIfExists(root);
            blogTagRepository.deleteAllByBlog_Id(id);
            log.warn("ID đã xóa: " + id);
            blogRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean updateBlog(BlogRequest blogRequest, MultipartFile file) {
        try {
            if (blogRequest.getId() < 1) {
                return false;
            }
            Optional<BlogEntity> blogEntity = blogRepository.findById(blogRequest.getId());
            String imgName = blogEntity.get().getImage();
            Path root = Paths.get(FolderRoot + imgName);
            log.info(root.toString());
            if (!Files.exists(root)) {
                Files.createDirectory(root);
            }
            Files.deleteIfExists(root);
            BlogEntity blog = blogRepository.save(BlogEntity.builder()
                    .id(blogRequest.getId())
                    .title(blogRequest.getTitle())
                    .image(file.getOriginalFilename())
                    .content(blogRequest.getContent())
                    .userEntity(UserEntity.builder()
                            .id(blogRequest.getUser())
                            .build())
                    .build());
            blogTagRepository.deleteAllByBlog_Id(blog.getId());
            blogRequest.getListTag().forEach(
                    tagRequest -> blogTagRepository.save(BlogTagEntity.builder()
                            .blog(BlogEntity.builder().id(blog.getId()).build())
                            .blogTagKey(new BlogTagKey(blog.getId(), tagRequest.getId()))
                            .build()));
            return true;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return false;

        }


    }


}
