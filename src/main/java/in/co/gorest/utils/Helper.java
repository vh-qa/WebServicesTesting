package in.co.gorest.utils;

public class Helper {
    public static int convertDoubleToInt(double value) {
        return (int) value;
    }

    public static String convertDoubleToString(double value) {
        return String.valueOf(convertDoubleToInt(value));
    }

    public static int convertStringToInt(String value) {
        return Integer.parseInt(value);
    }

    public static String convertStringFromUpperCaseToLowercase(String upperCaseString) {
        return upperCaseString.trim().toLowerCase();
    }

    public static String getProperty(String propertyFileName, String propertyName) {
        return PropertyReader
                .from(propertyFileName, propertyName)
                .getProperty(propertyName);
    }
}