package se.yrgo.libraryapp.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsernameTest {

    @ParameterizedTest
    @ValueSource(strings = {"bosse", "Anna@holmen", "Cla_ra", "P4trick", "M2DD3.", "bnaskgalkrhgauerighlaehgeruigharighaergaleguilaeurguiaerghaerghaeruighairughaeruighaiurghairughareuighairughairughaeruighaerui"})
    void validUsernames(String validUsername) {
        assertTrue(Username.validate(validUsername));
    }

    @ParameterizedTest
    @ValueSource(strings = {"bo", "MånsÖhman", "M2d-3", "H#nr7", "?mat?s", "23", "Anna Bok"})
    @EmptySource
    void invalidUsername(String invalidUsername) {
        assertFalse(Username.validate(invalidUsername));
    }
}
