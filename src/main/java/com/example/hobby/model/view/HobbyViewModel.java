package com.example.hobby.model.view;

import com.example.hobby.model.entity.PictureEntity;
import com.example.hobby.model.entity.enums.HobbyCategoryEnum;
import org.springframework.web.multipart.MultipartFile;

public class HobbyViewModel {
    private Long id;
    private String title;
    private PictureEntity pictureEntity;
    private HobbyCategoryEnum category;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PictureEntity getPictureEntity() {
        return pictureEntity;
    }

    public void setPictureEntity(PictureEntity pictureEntity) {
        this.pictureEntity = pictureEntity;
    }

    public HobbyCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(HobbyCategoryEnum category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
