package com.lyw.api.app.iam.identity.application.services;

import com.lyw.api.app.iam.identity.domain.commands.PatchUserCommand;
import com.lyw.api.app.iam.identity.domain.commands.RegisterUserBikerCommand;
import com.lyw.api.app.iam.identity.domain.commands.RegisterUserRenterCommand;
import com.lyw.api.app.iam.identity.domain.model.User;

public interface UserCommandService {
    User handle(RegisterUserBikerCommand registerUserCommand);

    User handle(RegisterUserRenterCommand registerUserCommand);

    User handle(PatchUserCommand patchUserCommand);
}
