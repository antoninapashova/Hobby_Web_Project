package com.example.hobby.repository;

import com.example.hobby.model.entity.CategoryHobby;
import com.example.hobby.model.entity.Hobby;
import com.example.hobby.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {


    @Query("SELECT h from Hobby h where h.user.username=:username")
    List<Hobby> getAllByUsername(String username);

   List<Hobby> getHobbiesByCategory(CategoryHobby categoryHobby);
   void deleteByUser(User user);

   List<Hobby> findAllByUser(User user);
   Optional<Hobby> findById(Long id);
}
