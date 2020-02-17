package com.whiteboardmaster.WhiteBoardMaster.Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationUserTest {

    @Test
    void setFirstName() {
        ApplicationUser actual =  new ApplicationUser("User", "password", "UserSample", "lastName");
        assertEquals("UserSample", actual.getFirstName());
    }

    @Test
    void setLastName() {
        ApplicationUser actual =  new ApplicationUser("User", "password", "UserSample", "Smith");
        assertEquals("Smith", actual.getLastName());
    }

    @Test
    void getUsername() {
        ApplicationUser actual =  new ApplicationUser("BoneCrusher", "password", "UserSample", "Smith");
        assertEquals("BoneCrusher", actual.getUserName());
    }

}