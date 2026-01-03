package se.yrgo.libraryapp.controller;

import org.mockito.Mock;
import se.yrgo.libraryapp.controllers.LoginController;
import se.yrgo.libraryapp.dao.RoleDao;
import se.yrgo.libraryapp.dao.SessionDao;
import se.yrgo.libraryapp.entities.LoginInfo;
import se.yrgo.libraryapp.entities.UserId;
import se.yrgo.libraryapp.entities.forms.LoginData;
import se.yrgo.libraryapp.services.UserService;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class LoginControllerTest {
    @Mock
    private UserService userService;
    @Mock
    private RoleDao roleDao;
    @Mock
    private SessionDao sessionDao;

    void canLoginTest() {
        LoginController controller = new LoginController(userService, roleDao, sessionDao);
        LoginData loginData = new LoginData("test", "123");
        Optional<LoginInfo> loginInfo = Optional.of(new LoginInfo(new UserId(1), "123"));

        when(userService.validate(loginData.getUsername(), loginData.getPassword())).thenReturn(Optional.ofNullable(loginInfo.get().getUserId()));
    }

}
