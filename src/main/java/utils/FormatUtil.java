package utils;

public class FormatUtil {

    public String formatStringToFirstUpperRestLowercase(String string){
        return string.substring(0,1).toUpperCase() + string.substring(1).toLowerCase();
    }
}
