package in.co.gorest.utils;

public enum PropertyFiles {
    COMMON_PROPERTY("/properties/common.properties"),
    USERS_PROPERTY("/properties/users.properties");

    private String propertyFileName;

    PropertyFiles(String propertyFileName) {
        this.propertyFileName = propertyFileName;
    }

    public String getPropertyFileName() {
        return propertyFileName;
    }
}