package com.example.hobby.model.binding;

import com.example.hobby.model.entity.PictureEntity;
import com.example.hobby.model.entity.enums.HobbyCategoryEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

public class HobbyAddBindingModel {

    private Long id;
    private MultipartFile multipartFile;
    private String title;
    private HobbyCategoryEnum hobbyCategoryEnum;
    private String description;

    public HobbyAddBindingModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    @NotEmpty
    @Size(min=3)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
    public HobbyCategoryEnum getHobbyCategoryEnum() {
        return hobbyCategoryEnum;
    }

    public void setHobbyCategoryEnum(HobbyCategoryEnum hobbyCategoryEnum) {
        this.hobbyCategoryEnum = hobbyCategoryEnum;
    }

    @NotEmpty
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
