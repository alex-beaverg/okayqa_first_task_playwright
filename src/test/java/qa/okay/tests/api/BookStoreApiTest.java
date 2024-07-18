package qa.okay.tests.api;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import qa.okay.domain.api.account.UserCredentials;
import qa.okay.domain.api.account.UserDetails;
import qa.okay.domain.api.account.UserLoginDetails;
import qa.okay.domain.api.account.UserToken;
import qa.okay.domain.api.book.*;
import qa.okay.utils.ApiActions;
import qa.okay.utils.exceptions.JsonValidateException;

import java.io.File;
import java.util.ArrayList;

import static qa.okay.utils.JsonReader.readFile;
import static qa.okay.utils.PropertyGetter.getProperty;

@Epic(value = "Demo QA dot com")
@Feature(value = "API")
@Story(value = "Book Store API")
public class BookStoreApiTest {
    private final static String PATH = getProperty("json_path");

    @Test(description = "Verify getting Book by ISBN Test")
    @Description(value = "Verify getting Book by ISBN")
    @Severity(SeverityLevel.NORMAL)
    public void verifyGetBookByISBNTest() throws JsonValidateException {
        Book actualBook = ApiActions.getBookByISBN("9781449325862");
        Book expectedBook = readFile(new File(PATH + "book_get.json"), Book.class);
        Assert.assertEquals(actualBook, expectedBook, "Books aren't equal!");
    }

    @Test(description = "Verify registering User Test")
    @Description(value = "Verify registering User")
    @Severity(SeverityLevel.NORMAL)
    public void verifyRegisterUserTest() throws JsonValidateException, IllegalAccessException {
        UserCredentials userCredentials = readFile(new File(PATH + "user_credentials.json"), UserCredentials.class);
        UserDetails registeredUser = ApiActions.registerUser(userCredentials);
        Assert.assertEquals(registeredUser.getUsername(), userCredentials.getUserName(), "Users aren't equal!");
        Assert.assertFalse(registeredUser.getUserId().isEmpty(), "User ID is empty!");
        Assert.assertTrue(registeredUser.getBooks().isEmpty(), "Book list isn't empty!");

        UserToken tokenOfRegisteredUser = ApiActions.getToken(userCredentials);
        ApiActions.deleteUser(registeredUser, tokenOfRegisteredUser);
    }

    @Test(description = "Verify logining User Test")
    @Description(value = "Verify logining User")
    @Severity(SeverityLevel.NORMAL)
    public void verifyLoginUserTest() throws JsonValidateException, IllegalAccessException {
        UserCredentials userCredentials = readFile(new File(PATH + "user_credentials.json"), UserCredentials.class);
        UserDetails registeredUser = ApiActions.registerUser(userCredentials);
        UserLoginDetails registeredUserDetails = ApiActions.loginUser(userCredentials);
        Assert.assertEquals(registeredUserDetails.getUsername(), userCredentials.getUserName(), "Names aren't equal!");
        Assert.assertEquals(registeredUserDetails.getPassword(), userCredentials.getPassword(), "Passwords aren't equal!");

        UserToken tokenOfRegisteredUser = ApiActions.getToken(userCredentials);
        ApiActions.deleteUser(registeredUser, tokenOfRegisteredUser);
    }

    @Test(description = "Verify getting User Test")
    @Description(value = "Verify getting User")
    @Severity(SeverityLevel.NORMAL)
    public void verifyGetUserTest() throws JsonValidateException, IllegalAccessException {
        UserCredentials userCredentials = readFile(new File(PATH + "user_credentials.json"), UserCredentials.class);
        UserDetails registeredUser = ApiActions.registerUser(userCredentials);
        UserToken tokenOfRegisteredUser = ApiActions.getToken(userCredentials);
        UserDetails existingUser = ApiActions.getUser(registeredUser, tokenOfRegisteredUser);
        Assert.assertEquals(registeredUser, existingUser, "Users aren't equal!");

        ApiActions.deleteUser(registeredUser, tokenOfRegisteredUser);
    }

    @Test(description = "Verify deleting User Test")
    @Description(value = "Verify deleting User")
    @Severity(SeverityLevel.NORMAL)
    public void verifyDeleteUserTest() throws JsonValidateException, IllegalAccessException {
        UserCredentials userCredentials = readFile(new File(PATH + "user_credentials.json"), UserCredentials.class);
        UserDetails registeredUser = ApiActions.registerUser(userCredentials);
        UserToken tokenOfRegisteredUser = ApiActions.getToken(userCredentials);
        ApiActions.deleteUser(registeredUser, tokenOfRegisteredUser);
        String statusText = ApiActions.getAuthorizationInfo(userCredentials);

        Assert.assertTrue(statusText.contains("Not Found"), "Wrong message!");
    }

    @Test(description = "Verify updating User information Test")
    @Description(value = "Verify updating User information")
    @Severity(SeverityLevel.NORMAL)
    public void verifyUpdateUserTest() throws JsonValidateException, IllegalAccessException {
        UserCredentials userCredentials = readFile(new File(PATH + "user_credentials.json"), UserCredentials.class);
        UserDetails registeredUser = ApiActions.registerUser(userCredentials);
        UserToken tokenOfRegisteredUser = ApiActions.getToken(userCredentials);
        ArrayList<CollectionOfIsbn> isbnCollection = new ArrayList<>();
        isbnCollection.add(readFile(new File(PATH + "book_post.json"), CollectionOfIsbn.class));
        PostResponse userWithCollectionOfIsbn = ApiActions.addCollectionOfIsbnToUser(registeredUser, isbnCollection, tokenOfRegisteredUser);
        UserDetails updatedUser = ApiActions.getUser(registeredUser, tokenOfRegisteredUser);
        Assert.assertEquals(updatedUser.getBooks().get(0).getIsbn(), userWithCollectionOfIsbn.getBooks().get(0).getIsbn(), "ISBNs aren't equal!");

        ArrayList<CollectionOfIsbn> newIsbnCollection = new ArrayList<>();
        newIsbnCollection.add(readFile(new File(PATH + "book_put.json"), CollectionOfIsbn.class));
        UserDetails changedUser = ApiActions.putCollectionOfIsbn(updatedUser, newIsbnCollection.get(0).getIsbn(), tokenOfRegisteredUser);
        UserDetails newUpdatedUser = ApiActions.getUser(registeredUser, tokenOfRegisteredUser);
        Assert.assertEquals(newUpdatedUser.getBooks().get(0).getIsbn(), changedUser.getBooks().get(0).getIsbn(), "ISBNs aren't equal!");

        ApiActions.deleteUser(registeredUser, tokenOfRegisteredUser);
    }
}
