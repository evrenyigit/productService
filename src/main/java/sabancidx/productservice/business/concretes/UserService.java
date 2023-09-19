package sabancidx.productservice.business.concretes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sabancidx.productservice.dataAccess.UserRepository;
import sabancidx.productservice.entities.concretes.User;

import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User userSave(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception ex) {
            logger.error("Kullanıcı kayıt islemi sırasında hata :", ex);
            return null;
        }
    }

    public User getUser(String username) {
        try {
            User user = userRepository
                    .findByUsername(username).get();
            return user;
        } catch (Exception ex) {
            logger.error("User bilgilerini almada hata yaşandı.", ex);
            return null;
        }
    }

    public Boolean existsUser(String key, String type) {
        try {
            if (type.equals("userName")) {
                return userRepository.existsByUsername(key);
            } else if (type.equals("email")) {
                return userRepository.existsByEmail(key);
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("existsUser hata:", ex);
            return null;
        }
    }


    public User getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }



    public List<User> fetchUsers() {
        return userRepository.findAll();
    }

    public User update(User user) {
        if (userRepository.existsById(user.getRecordId())) {
            return userRepository.save(user);
        } else {
            return null;
        }
    }

}
