package steps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import entities.Todo;
import utility.Rest;

public class GetTodosSteps {

    private static final String GET_TODOS_PATH = "/todos";

    public static Todo[] getTodos(Map<String, String> queries) {
        return Rest
            .Get
            .sendGet(EndpointSteps.getServiceEndpoint(GET_TODOS_PATH), queries, Todo[].class, 200);
    }

    public static void getTodosWithError(Map<String, String> queries, int expectedHttpStatusCode) {
        Rest
            .Get
            .sendGetWithError(EndpointSteps.getServiceEndpoint(GET_TODOS_PATH), queries, expectedHttpStatusCode);
    }

    //may affect memory leak
    public static List<Todo> getAllTodos() {
        int limit = 1000;
        int startLimit = 0;
        int nextLimit = startLimit;
        HashMap<String, String> queries = new HashMap<>() {{
            put("limit", String.valueOf(limit));
            put("offset", String.valueOf(startLimit));
        }};
        Todo[] todos = getTodos(queries);
        List<Todo> accumulatedList = new LinkedList<>();
        while (todos.length > 0) {
            accumulatedList.addAll(Arrays.asList(todos));
            queries.replace("offset", String.valueOf(nextLimit += limit));
            todos = getTodos(queries);
        }

        return accumulatedList;
    }
}
