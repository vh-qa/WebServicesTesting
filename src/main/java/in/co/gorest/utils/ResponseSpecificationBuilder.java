package in.co.gorest.utils;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecificationBuilder {

    public ResponseSpecification createResponseSpecification(HttpRequestMethods httpRequestMethods) {
        ResponseSpecification responseSpecification = null;

        String statusCode200 = Helper
                .getProperty(PropertyFiles.COMMON_PROPERTY.getPropertyFileName(),
                             PropertyNames.STATUS_CODE_200.getPropertyName());

        String statusCode302 = Helper
                .getProperty(PropertyFiles.COMMON_PROPERTY.getPropertyFileName(),
                             PropertyNames.STATUS_CODE_302.getPropertyName());

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