package com.example.hobby.repository;

import com.example.hobby.model.dto.HobbyDTO;
import com.example.hobby.model.entity.CategoryHobby;
import com.example.hobby.model.entity.Hobby;
import com.example.hobby.model.entity.enums.HobbyCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HobbyCategoryRepository extends JpaRepository<CategoryHobby, Long> {

    CategoryHobby getByCategory(HobbyCategoryEnum category);
    CategoryHobby findByCategory(HobbyCategoryEnum category);
    List<Hobby> getAllByCategory(HobbyCategoryEnum categoryEnum);
}
