package se.yrgo.libraryapp.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;


public class UtilsTest {

    @ParameterizedTest
    @CsvSource({"Ada4m Brown, adam brown",
                "H432nna Blom, hnna blom",
                "45lyck!e, lycke",
                "Anna-Mariè smith, annamariè smith"})
    void validOnlyLettersAndWhitespace(String input, String actual) {
        assertEquals(Utils.onlyLettersAndWhitespace(input), actual);
    }





}
