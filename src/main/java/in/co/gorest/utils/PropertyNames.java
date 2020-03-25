package in.co.gorest.utils;

public enum PropertyNames {
    BASE_URI("base.uri"),
    CHARSET_NAME_UTF8("charset.name.UTF8"),
    HTTP_HEADER_KEY("http.header.key"),
    HTTP_HEADER_VALUE("http.header.value"),
    STATUS_CODE_200("status.code.200"),
    STATUS_CODE_302("status.code.302"),

    BASE_PATH_USERS("base.path.users"),
    BASE_PATH_POSTS("base.path.posts"),
    BASE_PATH_COMMENTS("base.path.comments"),
    BASE_PATH_ALBUMS("base.path.albums"),
    BASE_PATH_PHOTOS("base.path.photos"),

    GET_PARAM_FORMAT_KEY("get.param.format.key"),
    GET_PARAM_FORMAT_VALUE("get.param.format.value"),
    GET_PARAM_ACCESS_KEY("get.param.access.key"),
    GET_PARAM_ACCESS_VALUE("get.param.access.value"),

    USERS_DATA_FILE("users.data.file"),
    USER_ID("user.id"),
    USER_UPDATE_EMAIL("user.update.email"),
    JSON_SCHEMA_RESPONSE_USERS("json.schema.response.users"),
    USERS_EXCEL_SHEET_NAME ("users.excel.sheet.name"),
    USER_MODEL_CLASS_NAME("user.model.class.name");

    private String propertyName;

    PropertyNames(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}