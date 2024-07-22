package steps;

import entities.Todo;
import utility.Rest;

public class PostTodoSteps {
    private static final String CREATE_TODO_PATH = "/todos";

    public static void createTodo(Todo todo, int expectedHttpStatusCode){
        Rest
            .Post
            .sendPostWithEmptyResponse(EndpointSteps.getServiceEndpoint(CREATE_TODO_PATH), todo, expectedHttpStatusCode);
    }
}
