package utp.edu.pe.restaurant.util;

import java.util.ResourceBundle;

public class AppConfig {
    static ResourceBundle rb = ResourceBundle.getBundle("config");

    public static String separator(){
        return System.getProperty("file.separator");
    }

    public static String getSourceType(){
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("windows")) {
            return rb.getString("tc_sourceType");
        }else{
            return rb.getString("wf_sourceType");
        }
    }

    public static String getDatasource(){
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("windows")) {
            return rb.getString("tc_datasource");
        }else{
            return rb.getString("wf_datasource");
        }
    }
}
