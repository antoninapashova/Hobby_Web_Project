package com.example.hobby.service;

import com.example.hobby.model.binding.HobbyAddBindingModel;
import com.example.hobby.model.dto.HobbyDTO;
import com.example.hobby.model.entity.CategoryHobby;
import com.example.hobby.model.entity.Hobby;
import com.example.hobby.model.entity.User;
import com.example.hobby.model.entity.enums.HobbyCategoryEnum;
import com.example.hobby.model.service.HobbyAddServiceModel;
import com.example.hobby.model.view.HobbyViewModel;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface HobbyService {

    HobbyAddServiceModel addHobby(HobbyAddBindingModel hobbyAddBindingModel, String userIdentifier) throws IOException;

    List<HobbyDTO> getAllHobbies();

    List<HobbyDTO> getAllHobiesByUsername(String username);

    List<HobbyDTO> getAllHobbiesByMusicCategory();

    List<HobbyDTO> getAllHobbiesByPhotographyCategory();

    List<HobbyDTO> getAllHobbiesByOrigamiCategory();

    List<HobbyDTO> getAllHobbiesByPetsCategory();

    void deleteHobby(Long id);

    List<HobbyViewModel> getAllHobbiesOfUser(String userIdentifier);
}
