package in.co.gorest.utils;

public class BasePathFactory {
    public static String buildBasePath(BasePathEndPointType basePathEndPointType) {
        String basePath = null;

        switch (basePathEndPointType) {
            case USERS:
                basePath = Helper
                        .getProperty(PropertyFiles.COMMON_PROPERTY.getPropertyFileName(),
                                     PropertyNames.BASE_PATH_USERS.getPropertyName());
                break;
            case POSTS:
                basePath = Helper
                        .getProperty(PropertyFiles.COMMON_PROPERTY.getPropertyFileName(),
                                     PropertyNames.BASE_PATH_POSTS.getPropertyName());
                break;
            case COMMENTS:
                basePath = Helper
                        .getProperty(PropertyFiles.COMMON_PROPERTY.getPropertyFileName(),
                                     PropertyNames.BASE_PATH_COMMENTS.getPropertyName());
                break;
            case ALBUMS:
                basePath = Helper
                        .getProperty(PropertyFiles.COMMON_PROPERTY.getPropertyFileName(),
                                     PropertyNames.BASE_PATH_ALBUMS.getPropertyName());
                break;
            case PHOTOS:
                basePath = Helper
                        .getProperty(PropertyFiles.COMMON_PROPERTY.getPropertyFileName(),
                                     PropertyNames.BASE_PATH_PHOTOS.getPropertyName());
                break;
            default:
                break;
        }
        return basePath;
    }
}