package in.co.gorest.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RequestSpecificationBuilder {
    private RequestSpecification requestSpecification;

    public RequestSpecification createRequestSpecification
            (HttpRequestMethods httpRequestMethods, String basePath, Map<String, String> queryParams) {

        String baseUri = Helper
                            .getProperty(PropertyFiles.COMMON_PROPERTY.getPropertyFileName(),
                                         PropertyNames.BASE_URI.getPropertyName());

        String httpHeaderKey = Helper
                                 .getProperty(PropertyFiles.COMMON_PROPERTY.getPropertyFileName(),
                                              PropertyNames.HTTP_HEADER_KEY.getPropertyName());

        String httpHeaderValue = Helper
                                    .getProperty(PropertyFiles.COMMON_PROPERTY.getPropertyFileName(),
                                                PropertyNames.HTTP_HEADER_VALUE.getPropertyName());

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