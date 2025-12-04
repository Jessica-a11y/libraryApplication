package se.yrgo.libraryapp.services;

import java.util.Optional;
import javax.inject.Inject;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.yrgo.libraryapp.dao.UserDao;
import se.yrgo.libraryapp.entities.*;

public class UserService {
    private UserDao userDao;
    private PasswordEncoder encoder;

    @Inject
    public UserService(UserDao userDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }

    public Optional<UserId> validate(String username, String password) {
        Optional<LoginInfo> maybeLoginInfo = userDao.getLoginInfo(username);
        if (maybeLoginInfo.isEmpty()) {
            return Optional.empty();
        }

        LoginInfo loginInfo = maybeLoginInfo.get();

        if (!encoder.matches(password, loginInfo.getPasswordHash())) {
            return Optional.empty();
        }

        return Optional.of(loginInfo.getUserId());
    }

    public boolean validateRegister(String name, String realname, String password) {
        String hashed = encoder.encode(password);
        String sanitizedRealname = realname.replace("'", "\\'");
        return userDao.register(name, sanitizedRealname, hashed);
    }

    public boolean checkIfNameIsAvailable(String name) {
        return userDao.isNameAvailable(name);
    }


}
