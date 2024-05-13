package manager;

import enums.Environments;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EnvReader {

    private final Properties properties;
    private static EnvReader envReader;

    private EnvReader() {
        BufferedReader reader;
        String propertyFilePath = "configuration/environment/env.properties";
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

    public static EnvReader getInstance( ) {
        if(envReader == null) {
            envReader = new EnvReader();
        }
        return envReader;
    }

    public String getEnvironment(){
        String env = properties.getProperty("env").toLowerCase();
        switch (env) {
            case "local":
                return Environments.LOCAL.toString().toLowerCase();
            case "uat":
                return Environments.UAT.toString().toLowerCase();
            case "prod":
                return Environments.PROD.toString().toLowerCase();
            case "stage":
                return Environments.STAGE.toString().toLowerCase();
            default:
                return Environments.DEV.toString().toLowerCase();
        }
    }

    public String getAnyPropValue(String keyName) {
        String value = properties.getProperty(keyName);
        if(value != null) return value;
        else throw new RuntimeException(keyName + " Not specified in the env.properties file.");
    }
}


/*
config reader reference form:
<--https://toolsqa.com/rest-assured/configuration-reader/-->
*/