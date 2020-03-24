package in.co.gorest.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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

    public static String getStringFromFile(String filePath){
        StringBuilder textFromFile = new StringBuilder();
        Scanner scanner = null;

        try {
            FileReader fileReader = new FileReader(filePath);
            scanner = new Scanner(fileReader);

            while(scanner.hasNextLine()){
                textFromFile.append(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
        return textFromFile.toString();
    }

    public static String getProperty(String propertyFileName, String propertyName) {
        return PropertyReader
                .from(propertyFileName, propertyName)
                .getProperty(propertyName);
    }
}