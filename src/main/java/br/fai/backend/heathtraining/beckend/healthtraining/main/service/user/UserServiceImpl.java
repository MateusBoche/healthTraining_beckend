package br.fai.backend.heathtraining.beckend.healthtraining.main.service.user;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.user.UserDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int create(UserModel entity) {
        if(entity == null){
            return 0;
        }
        if (entity.getFullName().isEmpty()
                || entity.getPassword().isEmpty()
                || entity.getEmail().isEmpty()){
            return 0;
        }
        int id = userDao.add(entity);
        return id;
    }

    @Override
    public void delete(int id) {
        if(id<0){
            return;
        }
        userDao.remove(id);

    }

    @Override
    public UserModel findById(int id) {
        if(id<0){
            return null;
        }
        UserModel user = userDao.readById(id);
        return user;
    }

    @Override
    public List<UserModel> findAll() {
        List<UserModel> users = userDao.readAll();
        return users;
    }

    @Override
    public void update(int id, UserModel entity) {
        UserModel user = findById(id);
        if (user == null) {
            return;
        }
        userDao.updateInformation(id, entity);
    }

    @Override
    public boolean updatePassword(int id, String oldPassword, String newPassword) {
        UserModel user = findById(id);
        if (user == null) {
            return false;
        }

        if (!user.getPassword().equals(oldPassword)) {
            return false;
        }

        boolean response = userDao.updatePassword(id, newPassword);
        return response;
    }




    @Override
    public UserModel findByEmail(String email) {
        if (email.isEmpty()) {
            return null;
        }
        return userDao.readByEmail(email);
    }

    @Override
    public UserModel authenticate(String email, String senha) {
        UserModel user = findByEmail(email);
        if(!user.getPassword().equals(senha)) {
            return null;
        }
        return user;
    }

    @Override
    public boolean recoveryPassword(int id, String newPassword) {
        UserModel user = findById(id);
        if (user == null) {
            return false; // Retorna falso se o usuário não existir
        }

        // Atualizar a senha (lembre-se de aplicar hash à senha antes de salvar)
        user.setPassword(newPassword);

        boolean response = userDao.updatePassword(id, user.getPassword());
        return response;
    }
}
