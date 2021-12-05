package com.example.hobby.service;

import com.example.hobby.model.dto.HobbyDTO;
import com.example.hobby.model.entity.CategoryHobby;
import com.example.hobby.model.entity.enums.HobbyCategoryEnum;

import java.util.List;

public interface CategoryHobbyService {
    void initCategories();


    CategoryHobby getCategory(HobbyCategoryEnum hobbyCategoryEnum);

    CategoryHobby findByCategory(HobbyCategoryEnum category);

}
