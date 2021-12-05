package com.example.hobby.model.dto;

import com.example.hobby.model.entity.CategoryHobby;
import com.example.hobby.model.entity.PictureEntity;

public class HobbyDTO {

    private UserDTO user;
    private Long id;
    private PictureEntity photo;
    private String title;
    private CategoryHobby category;
    private String description;


    public HobbyDTO() {
    }

    public PictureEntity getPhoto() {
        return photo;
    }

    public void setPhoto(PictureEntity photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CategoryHobby getCategory() {
        return category;
    }

    public void setCategory(CategoryHobby category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
