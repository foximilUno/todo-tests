package utility;

import java.util.HashMap;
import java.util.Map;

public class Envs {
    private static Map<String, String> environments;

    public static void init (){
        environments = new HashMap<>(System.getenv());
    }

    public static String getEnv(String envName) {
        return environments.get(envName);
    }

    public static String setEnv(String envName, String envValue) {
        return environments.put(envName, envValue);
    }


}
