package in.co.gorest.utils;

public enum PropertyNames {
    USERS_DATA_FILE("users.data.file"),
    USER_ID("user.id"),
    USER_UPDATE_EMAIL("user.update.email"),
    JSON_SCHEMA_RESPONSE_USERS("json.schema.response.users"),
    USERS_EXCEL_SHEET_NAME ("users.excel.sheet.name"),
    USER_MODEL_CLASS_NAME("user.model.class.name"),

    BASE_URI("base.uri"),
    HTTP_HEADER_KEY("http.header.key"),
    HTTP_HEADER_VALUE("http.header.value"),
    STATUS_CODE_200("status.code.200"),
    STATUS_CODE_302("status.code.302"),

    GET_PARAM_FORMAT_KEY("get.param.format.key"),
    GET_PARAM_FORMAT_VALUE("get.param.format.value"),
    GET_PARAM_ACCESS_KEY("get.param.access.key"),
    GET_PARAM_ACCESS_VALUE("get.param.access.value");

    private String propertyName;

    PropertyNames(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}