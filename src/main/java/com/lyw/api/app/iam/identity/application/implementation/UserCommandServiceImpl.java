package com.lyw.api.app.iam.identity.application.implementation;

import org.springframework.stereotype.Service;

import com.lyw.api.app.iam.identity.application.services.UserCommandService;
import com.lyw.api.app.iam.identity.domain.commands.PatchUserCommand;
import com.lyw.api.app.iam.identity.domain.commands.RegisterUserBikerCommand;
import com.lyw.api.app.iam.identity.domain.commands.RegisterUserRenterCommand;
import com.lyw.api.app.iam.identity.domain.model.Role;
import com.lyw.api.app.iam.identity.domain.model.User;
import com.lyw.api.app.iam.identity.domain.repositories.UserRepository;
import com.lyw.api.app.iam.identity.infrastructure.dto.UserRequestDto;
import com.lyw.api.app.shared.utils.ValidationUtil;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;

    public UserCommandServiceImpl(UserRepository userRepository,
            ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public User handle(RegisterUserBikerCommand registerUserCommand) {
        User user = new User();
        Role role = validationUtil.getBikerRole();
        return getUser(user, role, registerUserCommand.userRequestDto());
    }

    @Override
    public User handle(RegisterUserRenterCommand registerUserCommand) {
        User user = new User();
        Role role = validationUtil.getRenterRole();
        return getUser(user, role, registerUserCommand.userRequestDto());
    }

    @Override
    public User handle(PatchUserCommand patchUserCommand) {
        User user = validationUtil.findUserById(patchUserCommand.userRequestDto().getId());
        Role role = user.getRole();
        return getUser(user, role, patchUserCommand.userRequestDto());
    }

    private User getUser(User user, Role role, UserRequestDto userRequestDto) {
        user.setId(userRequestDto.getId());
        user.setEmail(userRequestDto.getEmail());
        if (userRequestDto.getName() != null) {
            user.setGoogleName(userRequestDto.getName());
        }
        if (userRequestDto.getPhotoUrl() != null) {
            user.setGooglePhotoUrl(userRequestDto.getPhotoUrl());
        }
        user.setRole(role);
        userRepository.save(user);
        return user;
    }
}
