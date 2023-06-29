package com.backend.beatflow.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.backend.beatflow.model.UserModel;
import com.backend.beatflow.model.DTO.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMaper {
    
    List<UserDTO> toUserDtoList(List<UserModel> source);
    UserDTO toDto(UserModel source);
    
}
