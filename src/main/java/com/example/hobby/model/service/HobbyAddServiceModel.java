package com.example.hobby.model.service;

import com.example.hobby.model.entity.enums.HobbyCategoryEnum;
import org.springframework.web.multipart.MultipartFile;

public class HobbyAddServiceModel {
    private Long id;
    private MultipartFile multipartFile;
    private String title;
    private HobbyCategoryEnum hobbyCategoryEnum;
    private String description;

    public HobbyAddServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HobbyCategoryEnum getHobbyCategoryEnum() {
        return hobbyCategoryEnum;
    }

    public void setHobbyCategoryEnum(HobbyCategoryEnum hobbyCategoryEnum) {
        this.hobbyCategoryEnum = hobbyCategoryEnum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
