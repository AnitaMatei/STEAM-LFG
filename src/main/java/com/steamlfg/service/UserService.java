package com.steamlfg.service;

import com.steamlfg.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDTO findByUserId(int userId);

    UserDTO findByOid(String oid);

    List<UserDTO> findAll();

    UserDTO saveUser(String oid);
}
