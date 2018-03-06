package config;

import java.util.Arrays;

public class TestManagerConfiguration {
    private static TestManagerConfiguration instance;
    private TestManagerConfiguration(){

    }

    public static TestManagerConfiguration getInstance (){
        if(instance == null){
            instance = new TestManagerConfiguration();
        }
        return instance;
    }

    public void destroyInstance(){
        instance = null;
    }

    private String valueOrDefault(String envName, String[] possibleValues, String defaultValue){
        if(System.getenv(envName) == null || System.getenv(envName).isEmpty()
                || !Arrays.asList(possibleValues).contains(System.getenv(envName))){
            return defaultValue;
        }
        return System.getenv(envName);
    }

    public String getTestBrowser(){
        return (valueOrDefault("testBrowser",new String[]{"Chrome","Firefox","PhantomJS"},"Chrome"));
    }

    public String getTestEnv() {
        return (valueOrDefault("testEnv",new String[]{"Production", "Alfa", "Beta"},"Production"));
    }
}

