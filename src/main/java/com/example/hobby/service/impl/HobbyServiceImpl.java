package com.example.hobby.service.impl;

import com.example.hobby.model.binding.HobbyAddBindingModel;
import com.example.hobby.model.dto.HobbyDTO;
import com.example.hobby.model.dto.UserDTO;
import com.example.hobby.model.entity.CategoryHobby;
import com.example.hobby.model.entity.Hobby;
import com.example.hobby.model.entity.PictureEntity;
import com.example.hobby.model.entity.User;
import com.example.hobby.model.entity.enums.HobbyCategoryEnum;
import com.example.hobby.model.service.HobbyAddServiceModel;
import com.example.hobby.model.view.HobbyViewModel;
import com.example.hobby.repository.HobbyRepository;
import com.example.hobby.repository.PictureRepository;
import com.example.hobby.service.CategoryHobbyService;
import com.example.hobby.service.HobbyService;
import com.example.hobby.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.modelmapper.Converters.Collection.map;

@Service
public class HobbyServiceImpl implements HobbyService {

    private final HobbyRepository hobbyRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryHobbyService categoryHobbyService;
    private final CloudinaryServiceImpl cloudinaryService;
    private final PictureRepository pictureRepository;

    public HobbyServiceImpl(HobbyRepository hobbyRepository, ModelMapper modelMapper, UserService userService,
                            CategoryHobbyService categoryHobbyService, CloudinaryServiceImpl cloudinaryService, PictureRepository pictureRepository) {
        this.hobbyRepository = hobbyRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryHobbyService = categoryHobbyService;
        this.cloudinaryService = cloudinaryService;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public HobbyAddServiceModel addHobby(HobbyAddBindingModel hobbyAddBindingModel, String userIdentifier) throws IOException {
        User user = userService.getByUsername(userIdentifier);
        HobbyAddServiceModel hobbyAddServiceModel = modelMapper.map(hobbyAddBindingModel, HobbyAddServiceModel.class);
        Hobby newHobby = modelMapper.map(hobbyAddServiceModel, Hobby.class);
        newHobby.setUser(user);
        CategoryHobby categoryHobby = categoryHobbyService.getCategory(hobbyAddBindingModel.getHobbyCategoryEnum());
        newHobby.setCategory(categoryHobby);
        PictureEntity pictureEntity = createPictureEntity(hobbyAddBindingModel.getMultipartFile());
        pictureRepository.save(pictureEntity);
        newHobby.setPhoto(pictureEntity);
        Hobby savedHobby = hobbyRepository.save(newHobby);
        return modelMapper.map(savedHobby, HobbyAddServiceModel.class);
    }

    @Override
    public List<HobbyDTO> getAllHobbies() {
        return hobbyRepository.findAll().stream().map(this::asHobby).collect(Collectors.toList());
    }

    @Override
    public List<HobbyDTO> getAllHobiesByUsername(String username) {
        return hobbyRepository.
                getAllByUsername(username).
                stream().map(this::asHobby).
                collect(Collectors.toList());
    }

    @Override
    public List<HobbyDTO> getAllHobbiesByMusicCategory() {
        CategoryHobby byCategory = categoryHobbyService.findByCategory(HobbyCategoryEnum.MUSIC);
        return hobbyRepository.getHobbiesByCategory(byCategory).stream().map(this::asHobby).collect(Collectors.toList());
    }

    @Override
    public List<HobbyDTO> getAllHobbiesByPhotographyCategory() {
        CategoryHobby byCategory = categoryHobbyService.findByCategory(HobbyCategoryEnum.PHOTOGRAPHY);
        return hobbyRepository.getHobbiesByCategory(byCategory).stream().map(this::asHobby).collect(Collectors.toList());
    }

    @Override
    public List<HobbyDTO> getAllHobbiesByOrigamiCategory() {
        CategoryHobby byCategory = categoryHobbyService.findByCategory(HobbyCategoryEnum.ORIGAMY);
        return hobbyRepository.getHobbiesByCategory(byCategory).stream().map(this::asHobby).collect(Collectors.toList());
    }

    @Override
    public List<HobbyDTO> getAllHobbiesByPetsCategory() {
        CategoryHobby byCategory = categoryHobbyService.findByCategory(HobbyCategoryEnum.PetTricks);
        return hobbyRepository.getHobbiesByCategory(byCategory).stream().map(this::asHobby).collect(Collectors.toList());
    }

    @Override
    public void deleteHobby(Long id) {
        hobbyRepository.deleteById(id);
    }

    @Override
    public List<HobbyViewModel> getAllHobbiesOfUser(String userIdentifier) {
        return hobbyRepository.getAllByUsername(userIdentifier)
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private PictureEntity createPictureEntity(MultipartFile file) throws IOException {
        final CloudinaryImage uploaded = this.cloudinaryService.upload(file);

        return new PictureEntity().
                setPublicId(uploaded.getPublicId()).
                setUrl(uploaded.getUrl());
    }

    private HobbyDTO asHobby(Hobby hobby) {
        HobbyDTO hobbyDTO = modelMapper.map(hobby, HobbyDTO.class);
        UserDTO userDTO = modelMapper.map(hobby.getUser(), UserDTO.class);

        hobbyDTO.setUser(userDTO);

        return hobbyDTO;
    }

    private HobbyViewModel map(Hobby hobby) {
        HobbyViewModel hobbyViewModel = this.modelMapper
                .map(hobby, HobbyViewModel.class);

        hobbyViewModel.setCategory(hobby.getCategory().getCategory());
        hobbyViewModel.setPictureEntity(hobby.getPhoto());
        return hobbyViewModel;
    }
}
