package in.co.gorest.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public class RequestSpecificationFactory {
    private RequestSpecification requestSpecification;

    public RequestSpecification createRequestSpecification
            (HttpRequestMethods httpRequestMethods, String basePath, Map<String, String> queryParams) {

        String baseUri = Helper
                .getProperty("/properties/common.properties", "base.uri");

        String httpHeaderKey = Helper.getProperty("/properties/common.properties", "http.header.key");
        String httpHeaderValue = Helper.getProperty("/properties/common.properties", "http.header.value");

        switch (httpRequestMethods) {
            case GET:
                requestSpecification = new RequestSpecBuilder()
                        .setBaseUri(baseUri)
                        .setContentType(ContentType.JSON)
                        .setAccept(ContentType.JSON)
                        .setBasePath(basePath)
                        .addQueryParams(queryParams)
                        .build();
                break;
            case POST:
            case DELETE:
            case PUT:
                requestSpecification = new RequestSpecBuilder()
                        .setBaseUri(baseUri)
                        .setContentType(ContentType.JSON)
                        .setAccept(ContentType.JSON)
                        .setBasePath(basePath)
                        .addHeader(httpHeaderKey, httpHeaderValue)
                        .build();
                break;
            default:
                break;
        }
        return requestSpecification;
    }
}