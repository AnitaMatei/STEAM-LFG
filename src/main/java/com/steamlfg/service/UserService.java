package com.steamlfg.service;

import com.steamlfg.model.dto.AnnouncementDTO;
import com.steamlfg.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public UserDTO findByUserId(int userId);
    public UserDTO findByOid(String oid);
    public List<UserDTO> findAll();
    public UserDTO saveUser(String oid);
}
