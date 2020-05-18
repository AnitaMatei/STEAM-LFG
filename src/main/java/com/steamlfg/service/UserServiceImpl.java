package com.steamlfg.service;

import com.google.inject.internal.asm.$AnnotationVisitor;
import com.steamlfg.model.dto.UserDTO;
import com.steamlfg.model.entity.User;
import com.steamlfg.repository.UserRepository;
import com.steamlfg.utils.HttpClientGame;
import com.steamlfg.utils.ParseSteamUserData;
import org.modelmapper.ModelMapper;
import org.openid4java.server.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO findByUserId(int userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        UserDTO userDTO;
        if (user.isEmpty()) {
            return null;
        }
        userDTO = modelMapper.map(user.get(), UserDTO.class);

        return userDTO;
    }

    @Override
    public List<UserDTO> findAll() {
        Iterable<User> users = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : users) {
            userDTOList.add(modelMapper.map(user, UserDTO.class));
        }

        return userDTOList;
    }

    @Override
    public UserDTO findByOid(String oid) {
        Optional<User> user = userRepository.findByOid(oid);
        UserDTO userDTO;
        if (user.isEmpty()) {
            return null;
        }
        userDTO = modelMapper.map(user.get(), UserDTO.class);

        return userDTO;
    }

    @Override
    public UserDTO saveUser(String oid) {
        User user = new User();
        List<String> userInfo = getSteamUserInfo(oid);
        user.setOid(oid);
        user.setUsername(userInfo.get(0));
        user.setIconLink(userInfo.get(1));
        user.setSteamProfile(userInfo.get(2));

        userRepository.save(user);

        return modelMapper.map(user,UserDTO.class);
    }

    private List<String> getSteamUserInfo(String oid){
        List<String> userInfo = new ArrayList<>();

        try {
            String userURI = "https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=8AD909F0F1A22F8E2FF5F9AA23E16D63&steamids="+ oid;
            String gameString = new HttpClientGame(userURI).getAll();
            userInfo = ParseSteamUserData.parseUserInfoList(gameString);
        } catch (ServerException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

}
