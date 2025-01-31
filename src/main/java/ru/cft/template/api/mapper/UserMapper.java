package ru.cft.template.api.mapper;

import org.springframework.stereotype.Component;
import ru.cft.template.api.dto.user.CreateUserDto;
import ru.cft.template.api.dto.user.GetUserByIdDto;
import ru.cft.template.entity.User;

@Component
public class UserMapper {

    public static User createUserMapper(CreateUserDto createUserDto)
    {
        User user = new User();
        user.setLastName(createUserDto.lastName());
        user.setFirstName(createUserDto.firstName());
        user.setMiddleName(createUserDto.middleName());
        user.setPhone(createUserDto.phone());
        user.setEmail(createUserDto.email());
        user.setBirthday(createUserDto.birthday());
        return user;
    }

    public static GetUserByIdDto getUserMapper(User user)
    {
        return new GetUserByIdDto(
                user.getLastName(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getPhone(),
                user.getEmail(),
                user.getBirthday()
        );

    }
}
