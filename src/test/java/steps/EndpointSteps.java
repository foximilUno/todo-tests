package steps;

import utility.Envs;

public class EndpointSteps {
    private static final String SERVICE_ENDPOINT_ENV = "SERVICE_ENDPOINT";
    private static final String DEFAULT_ENDPOINT_FOR_LOCAL = "http://localhost:8080";

    public static String getServiceEndpoint(){
        if(Envs.getEnv(SERVICE_ENDPOINT_ENV) == null) {
            Envs.setEnv(SERVICE_ENDPOINT_ENV, DEFAULT_ENDPOINT_FOR_LOCAL);
        }
        return Envs.getEnv(SERVICE_ENDPOINT_ENV);
    }

    public static String getServiceEndpoint(String path){
        return String.format("%s%s", getServiceEndpoint(), path);
    }

    public static String getServiceEndpoint(String path, Long id){
        return getServiceEndpoint(String.format(path, id));
    }
}
