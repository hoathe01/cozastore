package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.*;
import com.cybersoft.cozastore.entity.key.BlogTagKey;
import com.cybersoft.cozastore.payload.request.BlogRequest;
import com.cybersoft.cozastore.payload.request.TagRequest;
import com.cybersoft.cozastore.payload.response.*;
import com.cybersoft.cozastore.repository.BlogRepository;
import com.cybersoft.cozastore.repository.BlogTagRepository;
import com.cybersoft.cozastore.repository.CommentRepository;
import com.cybersoft.cozastore.repository.TagRepository;
import com.cybersoft.cozastore.service.imp.BlogServiceImp;
import io.jsonwebtoken.io.Decoders;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

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
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private TagRepository tagRepository;

    @CacheEvict(value = "BlogSearch",allEntries = true)
    @Override
    public void clearCacheBlog() {}

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
    public List<BlogResponse> getPagination(int index, int quantity, int category) {
        try {
            Page<BlogEntity> page = blogRepository.findAll(PageRequest.of(index, quantity));
            List<BlogResponse> blogResponses = page.stream()
                    .map(blogEntity -> BlogResponse.builder()
                            .id(blogEntity.getId())
                            .title(blogEntity.getTitle())
                            .image(blogEntity.getImage())
                            .content(blogEntity.getContent())
                            .createDate(new DateResponse(blogEntity.getCreateDate()))
                            .user(UserResponse.builder()
                                    .username(blogEntity.getUserEntity().getUsername())
                                    .build())
                            .listComment(blogEntity.getListComment().stream()
                                    .map(commentEntity -> CommentResponse.builder()
                                            .name(commentEntity.getName())
                                            .build())
                                    .toList())
                            .listTag(blogEntity.getListTag().stream().map(tag -> TagResponse.builder()
                                            .id(tag.getTag().getId())
                                            .name(tag.getTag().getName())
                                            .build())
                                    .toList())
                            .build()).toList();

            if (category > 0) {
                Optional<TagEntity> tagEntity = tagRepository.findById(category);
                log.error(tagEntity.get().getId() + " - " + tagEntity.get().getName());
                return blogResponses.stream().filter(
                        blog -> blog.getListTag().stream().anyMatch(
                                tag -> tag.getId() == category)).toList();

            }
            return blogResponses;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public List<BlogResponse> getBlogByYear(int begin, int end) {
        try {
            return blogRepository.findByYear(begin, end).stream()
                    .map(blogEntity -> BlogResponse.builder()
                            .id(blogEntity.getId())
                            .title(blogEntity.getTitle())
                            .image(blogEntity.getImage())
                            .content(blogEntity.getContent())
                            .createDate(new DateResponse(blogEntity.getCreateDate()))
                            .user(UserResponse.builder()
                                    .username(blogEntity.getUserEntity().getUsername())
                                    .build())
                            .listComment(blogEntity.getListComment().stream()
                                    .map(commentEntity -> CommentResponse.builder()
                                            .name(commentEntity.getName())
                                            .build())
                                    .toList())
                            .listTag(blogEntity.getListTag().stream().map(tag -> TagResponse.builder()
                                            .id(tag.getTag().getId())
                                            .name(tag.getTag().getName())
                                            .build())
                                    .toList())
                            .build()).toList();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }
    @Cacheable("BlogSearch")
    @Override
    public List<BlogResponse> getBlogByKeyWord(String key) {
        try {
            if (Objects.equals(key, "")) {
                return null;
            }
            List<BlogEntity> blogEntitiesList = blogRepository.findAll();
            return blogEntitiesList.stream().filter(blog -> blog.getTitle().toLowerCase().contains(key.toLowerCase()))
                    .map(blogEntity -> BlogResponse.builder()
                    .id(blogEntity.getId())
                    .title(blogEntity.getTitle())
                    .user(UserResponse.builder()
                            .username(blogEntity.getUserEntity().getUsername())
                            .build())
                    .build()).toList();

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }


    @Override
    public BlogResponse getBlog(int id) {
        try {
            Optional<BlogEntity> blogEntity = blogRepository.findById(id);
            if (blogEntity.isEmpty()) {
                return null;
            }
            BlogEntity blog = blogEntity.get();
            return BlogResponse.builder()
                    .title(blog.getTitle())
                    .image(blog.getImage())
                    .content(blog.getContent())
                    .createDate(new DateResponse(blog.getCreateDate()))
                    .user(UserResponse.builder()
                            .email(blog.getUserEntity().getEmail())
                            .username(blog.getUserEntity().getUsername())
                            .role(new RoleResponse(blog.getUserEntity().getRole().getName(), null))
                            .build())
                    .listComment(blog.getListComment().stream()
                            .map(commentEntity -> CommentResponse.builder()
                                    .name(commentEntity.getName())
                                    .email(commentEntity.getEmail())
                                    .content(commentEntity.getContent())
                                    .createDate(commentEntity.getCreateDate())
                                    .build())
                            .toList())
                    .listTag(blog.getListTag().stream().map(tag -> TagResponse.builder()
                                    .name(tag.getTag().getName())
                                    .build())
                            .toList())
                    .build();

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
                            .createDate(new Date())
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
            commentRepository.deleteAllByBlogEntity_Id(id);
            blogRepository.deleteById(id);
            log.warn("ID đã xóa: " + id);
            return true;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean updateBlog(BlogRequest blogRequest, MultipartFile file) {
        try {
            Optional<BlogEntity> blogEntity = blogRepository.findById(blogRequest.getId());
            if (blogEntity.isEmpty()) {
                return false;
            }
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
