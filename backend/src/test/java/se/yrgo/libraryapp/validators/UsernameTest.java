package se.yrgo.libraryapp.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsernameTest {
    @Test
    void correctUsername() {
        assertTrue(Username.validate("bosse"));
    }

    @Test
    void incorrectUsername() {
        assertFalse(Username.validate("name with space"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"bosse", "Anna@holmen", "Cla_ra", "P4trick", "M2DD3.", "bnaskgalkrhgauerighlaehgeruigharighaergaleguilaeurguiaerghaerghaeruighairughaeruighaiurghairughareuighairughairughaeruighaerui"})
    void validUsernames(String validUsername) {
        assertTrue(Username.validate(validUsername));
    }
}
