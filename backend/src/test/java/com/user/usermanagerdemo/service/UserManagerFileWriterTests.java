package com.user.usermanagerdemo.service;

import com.user.usermanagerdemo.exception.UserManagerException;
import com.user.usermanagerdemo.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserManagerFileWriterTests {

    @Autowired
    private UserManagerFileWriter userManagerFileWriter;

    @Test
    @Order(1)
    @DisplayName("Testing userManagerFileWriter: to not be null")
    void test_User_Manager_File_Writer_To_Not_Be_Null() {
        assertThat(userManagerFileWriter).isNotNull();
    }

    @Test
    @Order(2)
    @DisplayName("Testing getFormatedData: when parameters are valid for POST request")
    void test_Get_Formated_Data_When_Parameters_Are_Valid_For_POST_Request() {
        User user = new User(0L, "Test User", "test.user@gmail.com", "Test St, 123", "(11)98765-4321");
        String formatedData = userManagerFileWriter.getFormatedData(UserManagerFileWriter.POST_FILE, user);
        String expectedFormatedDatabaseLine = "CREATED 1 User IN THE 'users' TABLE at";
        String expectedFormatedUserData = "User id: 0\n" +
                                          "User name: Test User\n" +
                                          "User email: test.user@gmail.com\n" +
                                          "User address: Test St, 123\n" +
                                          "User phone_number: (11)98765-4321\n";

        assertTrue(formatedData.contains(expectedFormatedDatabaseLine));
        assertTrue(formatedData.contains(expectedFormatedUserData));
    }

    @Test
    @Order(3)
    @DisplayName("Testing getFormatedData: when parameters are valid for PUT request")
    void test_Get_Formated_Data_When_Parameters_Are_Valid_For_PUT_Request() {
        User user = new User(0L, "Test User", "test.user@gmail.com", "Test St, 123", "(11)98765-4321");
        String formatedData = userManagerFileWriter.getFormatedData(UserManagerFileWriter.PUT_FILE, user);
        String expectedFormatedDatabaseLine = "UPDATED 1 User IN THE 'users' TABLE at";
        String expectedFormatedUserData = "User id: 0\n" +
                "User name: Test User\n" +
                "User email: test.user@gmail.com\n" +
                "User address: Test St, 123\n" +
                "User phone_number: (11)98765-4321\n";

        assertTrue(formatedData.contains(expectedFormatedDatabaseLine));
        assertTrue(formatedData.contains(expectedFormatedUserData));
    }

    @Test
    @Order(4)
    @DisplayName("Testing getFormatedData: when parameters are valid for DELETE request")
    void test_Get_Formated_Data_When_Parameters_Are_Valid_For_DELETE_Request() {
        User user = new User(0L, "Test User", "test.user@gmail.com", "Test St, 123", "(11)98765-4321");
        String formatedData = userManagerFileWriter.getFormatedData(UserManagerFileWriter.DELETE_FILE, user);
        String expectedFormatedDatabaseLine = "DELETED 1 User IN THE 'users' TABLE at";
        String expectedFormatedUserData = "User id: 0\n" +
                "User name: Test User\n" +
                "User email: test.user@gmail.com\n" +
                "User address: Test St, 123\n" +
                "User phone_number: (11)98765-4321\n";

        assertTrue(formatedData.contains(expectedFormatedDatabaseLine));
        assertTrue(formatedData.contains(expectedFormatedUserData));
    }

    @Test
    @Order(5)
    @DisplayName("Testing getFormatedData: when file name/type is invalid") // file name/type is not mapped like POST, PUT and DELETE
    void test_Get_Formated_Data_When_File_Is_Invalid() {
        User user = new User(0L, "Test User", "test.user@gmail.com", "Test St, 123", "(11)98765-4321");
        UserManagerException exception = assertThrows(UserManagerException.class, () -> userManagerFileWriter.getFormatedData("Invalid file name", user));
        assertEquals(exception.getMessage(), "Database changelogs file name isn't mapped.");
    }

    @Test
    @Order(6)
    @DisplayName("Testing getFormatedData: when user is null")
    void test_Get_Formated_Data_When_User_Is_Null() {
        User user = null;
        UserManagerException exception = assertThrows(UserManagerException.class, () -> userManagerFileWriter.getFormatedData(UserManagerFileWriter.POST_FILE, user));
        assertEquals(exception.getMessage(), "User is null.");
    }
}
