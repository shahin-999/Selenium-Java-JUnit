package manager;

import enums.DriverType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private final Properties properties;
    private static ConfigReader configReader;
    String env = EnvReader.getInstance().getEnvironment();

    private ConfigReader() {
        BufferedReader reader;
        String propertyFilePath = "configuration/"+ env +".properties";
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public static ConfigReader getInstance( ) {
        if(configReader == null) {
            configReader = new ConfigReader();
        }
        return configReader;
    }

    public String getAnyPropValue(String keyName) {
        String value = properties.getProperty(keyName);
        if(value != null) return value;
        else throw new RuntimeException(keyName + " not specified in the "+ env + ".properties file.");
    }

    public String getBaseUrl() {
        String baseUrl = properties.getProperty("baseURL");
        if(baseUrl != null) return baseUrl;
        else throw new RuntimeException("Base Url not specified in the "+ env + ".properties file.");
    }

    public DriverType getDriverType() {
        String driverType = properties.getProperty("browser").toLowerCase();
        switch (driverType){
            case "edge":
                return DriverType.EDGE;
            case "firefox":
                return DriverType.FIREFOX;
            case "safari":
                return DriverType.SAFARI;
            default:
                return DriverType.CHROME;
        }
    }

    public boolean isFullScreen(){
        String isFull = properties.getProperty("fullScreen").toLowerCase();
        if (isFull.equals("true") || isFull.equals("false")){
            return Boolean.parseBoolean(isFull);
        }
        return true;
    }

    public boolean isHeadless() {
        String isFull = properties.getProperty("headless").toLowerCase();
        if (isFull.equals("true") || isFull.equals("false")){
            return Boolean.parseBoolean(isFull);
        }
        return true;
    }

    public int implicitWait() {
        String implicitWait = properties.getProperty("implicitWait");
        if(implicitWait != null) return Integer.parseInt(implicitWait);
        else{
            return 30;
        }

    }
}


/*
config reader reference form:
<--https://toolsqa.com/rest-assured/configuration-reader/-->
*/