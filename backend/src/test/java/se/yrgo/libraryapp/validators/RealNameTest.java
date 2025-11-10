package se.yrgo.libraryapp.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RealNameTest {

    @ParameterizedTest
    @ValueSource(strings = {"L3nny 8o11", "damnman", "Arsh4d", "L0visa", "1337", "heck10r"})
    void validValidateTest(String name) {
        assertTrue(RealName.validate(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {"frack Henry", "Karl d4mn", "Hanna b4d4ss", "B1im3y D@rek"})
    void invalidValidateTest(String name) {
        assertFalse(RealName.validate(name));
    }
}
