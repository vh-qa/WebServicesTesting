package in.co.gorest.utils;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecificationFactory {

    public ResponseSpecification createResponseSpecification(HttpRequestMethods httpRequestMethods) {
        ResponseSpecification responseSpecification = null;

        String statusCode200 = Helper
                .getProperty("/properties/common.properties", "status.code.200");

        String statusCode302 = Helper
                .getProperty("/properties/common.properties", "status.code.302");

        switch (httpRequestMethods) {
            case POST:
                responseSpecification = new ResponseSpecBuilder()
                        .expectContentType(ContentType.JSON)
                        .expectStatusCode(Helper.convertStringToInt(statusCode302))
                        .build();
                break;
            case GET:
            case PUT:
            case DELETE:
            default:
                responseSpecification = new ResponseSpecBuilder()
                        .expectContentType(ContentType.JSON)
                        .expectStatusCode(Helper.convertStringToInt(statusCode200))
//                      .expectResponseTime(lessThan(5000L))
                        .build();
                break;
        }

        return responseSpecification;
    }
}