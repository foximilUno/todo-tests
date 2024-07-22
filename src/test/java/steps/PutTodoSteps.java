package steps;

import entities.Todo;
import utility.Rest;

public class PutTodoSteps {
    public static final String PUT_TODO_PATH = "/todos/%d";

    public static void editTodo(Long id, Todo todo, int expectedHttpStatusCode){
        Rest.Put
            .sendPut(
                EndpointSteps.getServiceEndpoint(PUT_TODO_PATH, id),
                todo,
                expectedHttpStatusCode);
    }
}
