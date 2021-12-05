package com.example.hobby.model.entity;

import com.example.hobby.model.entity.enums.HobbyCategoryEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryHobby extends BaseEntity{

    private HobbyCategoryEnum category;

    public CategoryHobby() {
    }

    @Enumerated(EnumType.STRING)
    public HobbyCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(HobbyCategoryEnum category) {
        this.category = category;
    }


}
