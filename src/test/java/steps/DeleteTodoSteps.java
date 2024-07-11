package steps;

import java.util.Map;

import utility.Rest;

public class DeleteTodoSteps {
    public static final String DELETE_TODO_PATH = "/todos/%d";

    public static void deleteTodo(Long id, Map<String, String> headers, int expectedHttpStatusCode){
        Rest.Delete
            .sendDelete(
                EndpointSteps.getServiceEndpoint(DELETE_TODO_PATH, id),
                headers,
                expectedHttpStatusCode);
    }

    public static void deleteTodo(Long id, int expectedHttpStatusCode){
        deleteTodo(id, AuthRestSteps.getAuthorizationHeader(), expectedHttpStatusCode);
    }
}
