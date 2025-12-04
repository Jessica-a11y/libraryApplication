package se.yrgo.libraryapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.yrgo.libraryapp.dao.UserDao;
import se.yrgo.libraryapp.entities.LoginInfo;
import se.yrgo.libraryapp.entities.UserId;
import se.yrgo.libraryapp.services.UserService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.*;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class UserServiceTest {
    @Mock
    private UserDao userDao;

    @Mock
    private final PasswordEncoder encoder = org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();


    @Test
    @SuppressWarnings("deprecation")
    void correctLogin() {
        final String userId = "1";
        final UserId id = UserId.of(userId);
        final String username = "testuser";
        final String password = "password";
        final String passwordHash = "password";
        final LoginInfo info = new LoginInfo(id, passwordHash);
        final PasswordEncoder encoder = org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
        when(userDao.getLoginInfo(username)).thenReturn(Optional.of(info));
        UserService userService = new UserService(userDao, encoder);
        assertThat(userService.validate(username,
                password)).isEqualTo(Optional.of(id));
    }

    @Test
    void registerTest() {
        UserService userService = new UserService(userDao, encoder);
        String name = "M4ttans";
        String realname = "Matts";
        String password = "newPassword";
        String hashedPassword = "newPassword";
        when(encoder.encode(password)).thenReturn(hashedPassword);
        when(userDao.register(name, realname, hashedPassword)).thenReturn(true);
        assertThat(userService.validateRegister(name, realname, password)).isTrue();
    }

    @Test
    void failRegisterTest() {
        UserService userService = new UserService(userDao, encoder);
        String name = "jane";
        String realname = "Jane Doe";
        String password = "1234";
        String hashedPassword = "1234";
        when(encoder.encode(password)).thenReturn(hashedPassword);
        when(userDao.register(name, "Jane Doe", hashedPassword)).thenReturn(false);
        boolean result = userService.validateRegister(name, realname, password);
        assertThat(result).isFalse();
    }

    @Test
    void nameAvailableTest() {
        UserService userService = new UserService(userDao, encoder);
        String name = "kikkan";
        when(userDao.isNameAvailable(name)).thenReturn(true);
        assertThat(userService.checkIfNameIsAvailable(name)).isTrue();
        verify(userDao).isNameAvailable(name);
    }

    @Test
    void nameIsNotAvailableTest() {
        UserService userService = new UserService(userDao, encoder);
        String name = "JanneForLife";
        when(userDao.isNameAvailable(name)).thenReturn(false);
        assertThat(userService.checkIfNameIsAvailable(name)).isFalse();
        verify(userDao).isNameAvailable(name);
    }

    @Test
    void nameIsEmptyTest() {
        UserService userService = new UserService(userDao, encoder);
        String name = "";
        assertThat(userService.checkIfNameIsAvailable(name)).isFalse();
        verify(userDao).isNameAvailable(name);
    }


}