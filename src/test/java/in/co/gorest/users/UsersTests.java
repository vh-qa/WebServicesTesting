package in.co.gorest.users;

import in.co.gorest.base.BaseTest;
import in.co.gorest.contract.ITestApiContract;
import in.co.gorest.models.User;
import in.co.gorest.models.UserModel;
import in.co.gorest.models.Users;
import in.co.gorest.utils.*;
import io.restassured.mapper.ObjectMapperType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class UsersTests implements ITestApiContract {
    private BaseTest baseTest;
    private String basePath;

    String propertyFileName = PropertyFiles.USERS_PROPERTY.getPropertyFileName();
    String userIdPropertyName = PropertyNames.USER_ID.getPropertyName();
    String userUpdateEmailPropertyName = PropertyNames.USER_UPDATE_EMAIL.getPropertyName();
    String jsonSchemaResponseUsersPropertyName = PropertyNames.JSON_SCHEMA_RESPONSE_USERS.getPropertyName();

    String userId = Helper.getProperty(propertyFileName, userIdPropertyName);
    String userUpdateEmail = Helper.getProperty(propertyFileName, userUpdateEmailPropertyName);
    String jsonSchemaResponseUsers = Helper.getProperty(propertyFileName, jsonSchemaResponseUsersPropertyName);

    @Override
    @Test(priority = 1, testName = "addUser", description = "POST")
    public void add() {
        baseTest.initSpec(HttpRequestMethods.POST, basePath, null);
        List<UserModel> users = getUsersFromExcel();

        for (int i = 0; i < users.size(); i++) {
            UserModel userModel = users.get(i);

            User user = given()
                    .log().all()
                    .spec(baseTest.getRequestSpecification())
                    .when()
                    .body(userModel, ObjectMapperType.GSON)
                    .post()
                    .prettyPeek()
                    .then()
                    .spec(baseTest.getResponseSpecification())
                    .and()
                    .extract()
                    .as(User.class, ObjectMapperType.GSON);

            AssertUtils.makeAssert(user.getResult().getFirstName(), userModel.getFirstName());
            AssertUtils.makeAssert(user.getResult().getLastName(), userModel.getLastName());
            AssertUtils.makeAssert(user.getResult().getGender(), userModel.getGender());
            AssertUtils.makeAssert(user.getResult().getEmail(), userModel.getEmail());
            AssertUtils.makeAssert(user.getResult().getPhone(), userModel.getPhone());
            AssertUtils.makeAssert(user.getResult().getWebsite(), userModel.getWebsite());
            AssertUtils.makeAssert(user.getResult().getAddress(), userModel.getAddress());
            AssertUtils.makeAssert(user.getResult().getStatus(), userModel.getStatus());

//          assertThat(actualUserModel, samePropertyValuesAs(userModel));
        }
    }

    @Override
    @Test(priority = 2, testName = "getAllUsers", description = "GET all users")
    public void getAll() {
        baseTest.initSpec(HttpRequestMethods.GET, basePath, createQueryParams());

        Users users = given()
                .log().all()
                .spec(baseTest.getRequestSpecification())
                .when()
                .get()
                .prettyPeek()
                .then()
                .spec(baseTest.getResponseSpecification())
                .and()
                .extract()
                .body()
                .as(Users.class, ObjectMapperType.GSON);

        List<Users.Result> results = users.getResult();
    }

    @Override
    @Test(priority = 3, testName = "getUserById", description = "GET by userId")
    public void getById() {
        baseTest.initSpec(HttpRequestMethods.GET, basePath + "/" + userId, createQueryParams());

        User user = given()
                .log().all()
                .spec(baseTest.getRequestSpecification())
                .when()
                .get()
                .prettyPeek()
                .then()
                .spec(baseTest.getResponseSpecification())
                .and()
                .extract()
                .body()
                .as(User.class, ObjectMapperType.GSON);

        UserModel userModel = new UserMapper().map(user);
        AssertUtils
                .makeAssert
                        (Helper.convertStringToInt(userModel.getId()),
                                Helper.convertStringToInt(userId));
    }

    @Override
    @Test(priority = 4, testName = "updateUser", description = "Update user info")
    public void update() {
        baseTest.initSpec(HttpRequestMethods.PUT, basePath, null);

        UserModel userModel = new UserModel();
        userModel.setEmail(userUpdateEmail);

        given()
                .spec(baseTest.getRequestSpecification())
                .when()
                .body(userModel, ObjectMapperType.GSON)
                .put("/" + userId)
                .prettyPeek()
                .then()
                .spec(baseTest.getResponseSpecification())
                .and()
                .body("result.email", equalTo(userUpdateEmail));
    }

    @Override
    @Test(priority = 5, testName = "deleteUser", description = "delete user")
    public void delete(){
        baseTest.initSpec(HttpRequestMethods.DELETE, basePath, null);

        given()
                .spec(baseTest.getRequestSpecification())
                .when()
                .delete("/" + userId)
                .prettyPeek()
                .then()
                .spec(baseTest.getResponseSpecification());
    }

    @Override
    @Test(priority = 6, testName = "checkJsonSchema", description = "check Json Schema")
    public void checkJsonSchema(){
        baseTest.initSpec(HttpRequestMethods.GET, basePath + "/" + userId, createQueryParams());
        String jsonSchema = Helper.getStringFromFile(jsonSchemaResponseUsers);

       given()
                .log().all()
                .spec(baseTest.getRequestSpecification())
                .when()
                .get()
                .prettyPeek()
                .then()
                .spec(baseTest.getResponseSpecification())
                .and()
                .assertThat()
                .body(matchesJsonSchema(jsonSchema));
    }

    private List<UserModel> getUsersFromExcel() {
        List<Object> objectList;
        List<UserModel> users;

        objectList = GetDataFromExcel
                        .getDataToList
                            (PropertyFiles.USERS_PROPERTY.getPropertyFileName(),
                             PropertyNames.USERS_DATA_FILE.getPropertyName(),
                             PropertyNames.USERS_EXCEL_SHEET_NAME.getPropertyName(),
                             PropertyNames.USER_MODEL_CLASS_NAME.getPropertyName());

        users = ClassConverter.cast(UserModel.class, objectList);
        return users;
    }

    private Map<String, String> createQueryParams(){
        Map<String, String> queryParams = new HashMap<>();

        String formatKey = Helper
                .getProperty
                        (PropertyFiles.COMMON_PROPERTY.getPropertyFileName(),
                         PropertyNames.GET_PARAM_FORMAT_KEY.getPropertyName());

        String formatValue = Helper
                .getProperty
                        (PropertyFiles.COMMON_PROPERTY.getPropertyFileName(),
                                PropertyNames.GET_PARAM_FORMAT_VALUE.getPropertyName());

        String accessKey = Helper
                .getProperty
                        (PropertyFiles.COMMON_PROPERTY.getPropertyFileName(),
                                PropertyNames.GET_PARAM_ACCESS_KEY.getPropertyName());

        String accessValue = Helper
                .getProperty
                        (PropertyFiles.COMMON_PROPERTY.getPropertyFileName(),
                                PropertyNames.GET_PARAM_ACCESS_VALUE.getPropertyName());

        queryParams.put(formatKey, formatValue);
        queryParams.put(accessKey, accessValue);

        return queryParams;
    }

    @BeforeClass()
    public void beforeClass() {
        baseTest = new BaseTest();
        basePath = BasePathFactory.buildBasePath(BasePathEndPointType.USERS);
    }
}