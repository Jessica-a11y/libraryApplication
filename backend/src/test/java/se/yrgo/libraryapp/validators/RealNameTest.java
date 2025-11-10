package se.yrgo.libraryapp.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RealNameTest {

    @ParameterizedTest
    @ValueSource(strings = {"L3nny 8o11", "damnman", "Arsh4d", "L0visa", "1337", "heck10r"})
    void validValidateTest(String userName) {
        assertTrue(RealName.validate(userName));
    }
}
