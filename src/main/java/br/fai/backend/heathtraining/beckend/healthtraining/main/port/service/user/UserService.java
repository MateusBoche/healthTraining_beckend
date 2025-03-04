package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.user;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.dto.RecoveryPasswordDto;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.authentication.AuthenticationService;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.crud.CrudService;

public interface UserService extends CrudService<UserModel>, UpdatePasswordService, ReadByEmailService
, AuthenticationService, RecoveryPasswordService {
}
