package tests;

import entities.Todo;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import steps.GetTodosSteps;
import steps.TodoGenerator;

import java.util.Arrays;
import java.util.Map;

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

    @Test
    public void checkCorrectPagination() {
        TodoGenerator generator = TodoGenerator.init();
        for (int i = 0; i < 4; i++) {
            generator.generateTodo();
        }

        Todo[] firstPageTodos = GetTodosSteps.getTodos(Map.of("limit", "2"));
        Todo[] secondPageTodos = GetTodosSteps.getTodos(Map.of("limit", "2", "offset", "2"));
        for (Todo currentFirstTodo : firstPageTodos) {
            Assertions.assertFalse(
                    Arrays.stream(secondPageTodos)
                            .anyMatch(todo -> todo.getId().equals(currentFirstTodo.getId())),
                    String.format("todo c id %s найдена на второй странице пагинации", currentFirstTodo.getId()));
        }
    }

    @Test
    public void checkLimitWorks() {
        TodoGenerator generator = TodoGenerator.init();
        for (int i = 0; i < 3; i++) {
            generator.generateTodo();
        }

        Todo[] todosPage = GetTodosSteps.getTodos(Map.of("limit", "2"));
        Assertions.assertEquals(2, todosPage.length);

    }
}
