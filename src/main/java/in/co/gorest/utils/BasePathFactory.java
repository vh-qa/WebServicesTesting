package in.co.gorest.utils;

public class BasePathFactory {
    public static String buildBasePath(BasePathEndPointType basePathEndPointType) {
        String basePath = null;

        switch (basePathEndPointType) {
            case USERS:
                basePath = Helper
                        .getProperty("/properties/common.properties", "base.path.users");
                break;
            case POSTS:
                basePath = Helper
                        .getProperty("/properties/common.properties", "base.path.posts");
                break;
            case COMMENTS:
                basePath = Helper
                        .getProperty("/properties/common.properties", "base.path.comments");
                break;
            case ALBUMS:
                basePath = Helper
                        .getProperty("/properties/common.properties", "base.path.albums");
                break;
            case PHOTOS:
                basePath = Helper
                        .getProperty("/properties/common.properties", "base.path.photos");
                break;
            default:
                break;
        }
        return basePath;
    }
}