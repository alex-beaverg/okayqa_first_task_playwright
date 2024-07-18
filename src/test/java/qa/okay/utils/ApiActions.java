package qa.okay.utils;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import io.qameta.allure.Step;
import qa.okay.domain.api.account.UserCredentials;
import qa.okay.domain.api.account.UserDetails;
import qa.okay.domain.api.account.UserLoginDetails;
import qa.okay.domain.api.account.UserToken;
import qa.okay.domain.api.book.*;
import qa.okay.utils.exceptions.JsonValidateException;

import java.util.ArrayList;

import static qa.okay.utils.PropertyGetter.getProperty;

public class ApiActions {
    private final static String URL = getProperty("home_url");
    private final static APIRequestContext CONTEXT = Playwright.create().request().newContext();

    @Step(value = "Getting context step")
    public static APIRequestContext getContext() {
        return CONTEXT;
    }

    @Step(value = "Getting book by ISBN")
    public static Book getBookByISBN(String ISBN) throws JsonValidateException {
        String bookString = CONTEXT.get(URL + "BookStore/v1/Book", RequestOptions.create()
                .setQueryParam("ISBN", ISBN)).text();
        return JsonReader.readString(bookString, Book.class);
    }

    @Step(value = "Registering User step")
    public static UserDetails registerUser(UserCredentials userCredentials) throws IllegalAccessException, JsonValidateException {
        String userString = CONTEXT.post(URL + "Account/v1/User", RequestOptions.create()
                .setData(ObjectToMapConverter.convert(userCredentials))).text();
        return JsonReader.readString(userString, UserDetails.class);
    }

    @Step(value = "Getting Token step")
    public static UserToken getToken(UserCredentials userCredentials) throws IllegalAccessException, JsonValidateException {
        String tokenString = CONTEXT.post(URL + "Account/v1/GenerateToken", RequestOptions.create()
                .setData(ObjectToMapConverter.convert(userCredentials))).text();
        return JsonReader.readString(tokenString, UserToken.class);
    }

    @Step(value = "Deleting User step")
    public static void deleteUser(UserDetails user, UserToken token) {
        CONTEXT.delete(URL + "Account/v1/User/" + user.getUserId(), RequestOptions.create()
                .setHeader("Authorization", "Bearer " + token.getToken()));
    }

    @Step(value = "Logining User step")
    public static UserLoginDetails loginUser(UserCredentials userCredentials) throws IllegalAccessException, JsonValidateException {
        String userString = CONTEXT.post(URL + "Account/v1/Login", RequestOptions.create()
                .setData(ObjectToMapConverter.convert(userCredentials))).text();
        return JsonReader.readString(userString, UserLoginDetails.class);
    }

    @Step(value = "Getting User step")
    public static UserDetails getUser(UserDetails user, UserToken token) throws JsonValidateException {
        String existingUserString = CONTEXT.get(URL + "Account/v1/User/" + user.getUserId(), RequestOptions.create()
                .setHeader("Authorization", "Bearer " + token.getToken())).text();
        return JsonReader.readString(existingUserString, UserDetails.class);
    }

    @Step(value = "Getting Authorization info step")
    public static String getAuthorizationInfo(UserCredentials userCredentials) throws IllegalAccessException {
        return CONTEXT.post(URL + "/Account/v1/Authorized", RequestOptions.create()
                .setData(ObjectToMapConverter.convert(userCredentials))
                .setHeader("ContentType", "application/json")).statusText();
    }

    @Step(value = "Adding data to User step")
    public static PostResponse addCollectionOfIsbnToUser(UserDetails user, ArrayList<CollectionOfIsbn> isbns, UserToken token)
            throws IllegalAccessException, JsonValidateException {
        PostCollection postCollection = new PostCollection(user.getUserId(), isbns);
        String userString = CONTEXT.post(URL + "BookStore/v1/Books", RequestOptions.create()
                .setHeader("Authorization", "Bearer " + token.getToken())
                .setData(ObjectToMapConverter.convert(postCollection))).text();
        return JsonReader.readString(userString, PostResponse.class);
    }

    @Step(value = "Updating User step")
    public static UserDetails putCollectionOfIsbn(UserDetails user, String isbn, UserToken token)
            throws IllegalAccessException, JsonValidateException {
        PutCollection putCollection = new PutCollection(user.getUserId(), isbn);
        String userString = CONTEXT.put(URL + "BookStore/v1/Books/" + user.getBooks().get(0).getIsbn(), RequestOptions.create()
                .setHeader("Authorization", "Bearer " + token.getToken())
                .setData(ObjectToMapConverter.convert(putCollection))).text();
        return JsonReader.readString(userString, UserDetails.class);
    }
}
