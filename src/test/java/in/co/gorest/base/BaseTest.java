package in.co.gorest.base;

import in.co.gorest.utils.*;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.util.Map;

public class BaseTest {
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public ResponseSpecification getResponseSpecification() {
        return responseSpecification;
    }

    public void initSpec(HttpRequestMethods httpRequestMethods, String basePath, Map<String, String> queryParams) {

        requestSpecification = new RequestSpecificationBuilder().createRequestSpecification(httpRequestMethods, basePath, queryParams);
        responseSpecification = new ResponseSpecificationBuilder().createResponseSpecification(httpRequestMethods);
    }
}