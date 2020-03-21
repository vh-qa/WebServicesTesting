package in.co.gorest.users;

import in.co.gorest.base.BaseTest;
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

public class UsersTests {
    private BaseTest baseTest;
    private String basePath;

    @Test(priority = 1, testName = "addUser", description = "POST")
    public void addUser() {
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

    @Test(priority = 2, testName = "getAllUsers", description = "GET all users")
    public void getAllUsers() {
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

    @Test(priority = 3, testName = "getUserById", description = "GET by userId")
    public void getUserById() {
        String userId = Helper.getProperty("/properties/common.properties", "user.id");
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

    @Test(priority = 4, testName = "updateUser")
    public void updateUser() {
        baseTest.initSpec(HttpRequestMethods.PUT, basePath, null);

        String userId = Helper.getProperty("/properties/common.properties", "user.id");
        String email = Helper.getProperty("/properties/common.properties", "user.update.email");

        UserModel userModel = new UserModel();
        userModel.setEmail(email);

        given()
                .spec(baseTest.getRequestSpecification())
                .when()
                .body(userModel, ObjectMapperType.GSON)
                .put("/" + userId)
                .prettyPeek()
                .then()
                .spec(baseTest.getResponseSpecification())
                .and()
                .body("result.email", equalTo(email));
    }

    @Test(priority = 5, testName = "deleteUser")
    public void deleteUser(){
        baseTest.initSpec(HttpRequestMethods.DELETE, basePath, null);
        String userId = Helper.getProperty("/properties/common.properties", "user.id");

        given()
                .spec(baseTest.getRequestSpecification())
                .when()
                .delete("/" + userId)
                .prettyPeek()
                .then()
                .spec(baseTest.getResponseSpecification());
    }

    private List<UserModel> getUsersFromExcel() {
        List<Object> objectList;
        List<UserModel> users;

        objectList = GetDataFromExcel.getDataToList("/properties/common.properties",
                "users.data.file", "user", "in.co.gorest.models.UserModel");

        users = ClassConverter.cast(UserModel.class, objectList);
        return users;
    }

    private Map<String, String> createQueryParams(){
        Map<String, String> queryParams = new HashMap<>();

        String formatKey = Helper.getProperty("/properties/common.properties", "get.param.format.key");
        String formatValue = Helper.getProperty("/properties/common.properties", "get.param.format.value");
        String accessKey = Helper.getProperty("/properties/common.properties", "get.param.access.key");
        String accessValue = Helper.getProperty("/properties/common.properties", "get.param.access.value");

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