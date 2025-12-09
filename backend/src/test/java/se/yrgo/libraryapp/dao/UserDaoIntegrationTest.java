package se.yrgo.libraryapp.dao;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.radcortez.flyway.test.annotation.H2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.yrgo.libraryapp.entities.LoginInfo;
import se.yrgo.libraryapp.entities.User;
import se.yrgo.libraryapp.entities.UserId;

@Tag("integration")
@H2
public class UserDaoIntegrationTest {
    private static DataSource ds;

    @BeforeAll
    static void initDataSource() {
// this way we do not need to create a new datasource every time
        final JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:test");
        UserDaoIntegrationTest.ds = ds;
    }


    @Test
    void getUserById() {
// this data comes from the test migration files
        final String username = "test";
        final UserId userId = UserId.of(1);
        UserDao userDao = new UserDao(ds);
        Optional<User> maybeUser = userDao.get(Integer.toString(userId.getId()));
        assertThat(maybeUser).isPresent();
        assertThat(maybeUser.get().getName()).isEqualTo(username);
        assertThat(maybeUser.get().getId()).isEqualTo(userId);
    }

    @Test
    void userIdNotFound() {
        final UserId userId = UserId.of(4);
        UserDao userDao = new UserDao(ds);
        Optional<User> userNotFound = userDao.get(Integer.toString(userId.getId()));
        assertThat(userNotFound).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"23'", "4", " "})
    void getBadIds(String id){
        UserDao userDao = new UserDao(ds);
        Optional<User> userNotFound = userDao.get(id);
        assertThat(userNotFound).isEmpty();
    }

    @Test
    void getLoginInfoTest() {
        String username = "test";
        UserId userId = UserId.of(1);
        String hashedPassword = "$argon2i$v=19$m=16,t=2,p=1$MTIzNDU2Nzg5MDEyMzQ1NjA$LmFqTZeUWwqsnbZCS2E8XQ";
        UserDao userDao = new UserDao(ds);
        Optional<LoginInfo> user  = userDao.getLoginInfo(username);
        assertThat(user).isPresent();
        assertThat(user.get().getUserId()).isEqualTo(userId);
        assertThat(user.get().getPasswordHash()).isEqualTo(hashedPassword);
    }

    @Test
    void noLoginInfoFound() {
        UserDao userDao = new UserDao(ds);
        Optional<LoginInfo> emptyUser = userDao.getLoginInfo("Amanda");
        assertThat(emptyUser).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "Anne;"})
    void badLoginInfo(String user) {
        UserDao userDao = new UserDao(ds);
        Optional<LoginInfo> badLogin = userDao.getLoginInfo(user);
        assertThat(badLogin).isEmpty();
    }



}