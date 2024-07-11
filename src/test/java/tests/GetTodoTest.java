package tests;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import entities.Todo;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import steps.GetTodosSteps;
import steps.TodoGenerator;

public class GetTodoTest extends TestConfigurator {

    @Test
    public void withoutQueries() {
        GetTodosSteps.getTodos(Map.of());
    }

    @Test
    public void withoutLimit() {
        GetTodosSteps.getTodos(Map.of("offset", "1"));
    }

    @Test
    public void withoutOffset() {
        GetTodosSteps.getTodos(Map.of("limit", "1"));
    }

    @Test
    public void withWrongLimit() {
        GetTodosSteps.getTodosWithError(
            Map.of("limit", "-11"), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void withWrongOffset() {
        GetTodosSteps.getTodosWithError(
            Map.of("offset", "-1"), HttpStatus.SC_BAD_REQUEST);
    }

    //если нет ошибки при добавлении в хэшмап то считаем, что все todos уникальны
    @Test
    public void checkCorrectPagination() {
        TodoGenerator generator = TodoGenerator.init();
        for (int i = 0; i < 4; i++) {
            generator.generateTodo();
        }
        HashMap<Long, Todo> result = new HashMap<>();
        Todo[] firstPageTodos = GetTodosSteps.getTodos(Map.of("limit", "2"));
        Todo[] secondPageTodos = GetTodosSteps.getTodos(Map.of("limit", "2", "offset", "2"));
        Arrays.stream(firstPageTodos).forEach(todo -> result.put(todo.getId(), todo));
        Arrays.stream(secondPageTodos).forEach(todo -> result.put(todo.getId(), todo));
    }
}
