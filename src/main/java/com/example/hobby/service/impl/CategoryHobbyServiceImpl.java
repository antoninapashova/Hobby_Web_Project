package com.example.hobby.service.impl;

import com.example.hobby.model.entity.CategoryHobby;
import com.example.hobby.model.entity.enums.HobbyCategoryEnum;
import com.example.hobby.repository.HobbyCategoryRepository;
import com.example.hobby.service.CategoryHobbyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryHobbyServiceImpl implements CategoryHobbyService {
    private final HobbyCategoryRepository hobbyCategoryRepository;
    private final ModelMapper modelMapper;

    public CategoryHobbyServiceImpl(HobbyCategoryRepository hobbyCategoryRepository, ModelMapper modelMapper) {
        this.hobbyCategoryRepository = hobbyCategoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initCategories() {
        if (hobbyCategoryRepository.count() != 0) {
            return;
        }
        Arrays.stream(HobbyCategoryEnum.values())
                .forEach(hobbyCategoryEnum -> {
                    CategoryHobby categoryHobby = new CategoryHobby();
                    categoryHobby.setCategory(hobbyCategoryEnum);
                    hobbyCategoryRepository.save(categoryHobby);
                });

    }

    @Override
    public CategoryHobby getCategory(HobbyCategoryEnum hobbyCategoryEnum) {
       return hobbyCategoryRepository.getByCategory(hobbyCategoryEnum);
    }

    @Override
    public CategoryHobby findByCategory(HobbyCategoryEnum category) {
        return hobbyCategoryRepository.findByCategory(category);
    }

}
