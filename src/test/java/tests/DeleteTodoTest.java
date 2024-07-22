package tests;

import java.util.HashMap;
import java.util.Map;

import entities.Todo;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import steps.AuthRestSteps;
import steps.DeleteTodoSteps;
import steps.TodoGenerator;
import utility.Utils;

public class DeleteTodoTest extends TestConfigurator {

    @Test
    public void withCompletedTrue() {
        Todo createdTodo = TodoGenerator.init().generateTodoWithCompleted(true);

        DeleteTodoSteps.deleteTodo(createdTodo.getId(), HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void withCompletedFalse() {
        Todo createdTodo = TodoGenerator.init().generateTodoWithCompleted(false);

        DeleteTodoSteps.deleteTodo(createdTodo.getId(), HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void withNotExistingId() {
        DeleteTodoSteps.deleteTodo(Utils.getRandomPositiveLong(), HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void withWrongAuthorizationScheme() {
        Todo createdTodo = TodoGenerator.init().generateTodo();
        Map<String, String> wrongAuthHeaders = AuthRestSteps.getAuthorizationHeaderWithCustomScheme("Digest");

        DeleteTodoSteps.deleteTodo(createdTodo.getId(), wrongAuthHeaders, HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void withWrongAuthorizationLogin() {
        Todo createdTodo = TodoGenerator.init().generateTodo();
        Map<String, String> wrongAuthHeaders = AuthRestSteps.getAuthorizationHeaderWithCustomLogin("alfred");

        DeleteTodoSteps.deleteTodo(createdTodo.getId(), wrongAuthHeaders, HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void withWrongAuthorizationPass() {
        Todo createdTodo = TodoGenerator.init().generateTodo();
        Map<String, String> wrongAuthHeaders = AuthRestSteps.getAuthorizationHeaderWithCustomPass(Utils.getRandomString());

        DeleteTodoSteps.deleteTodo(createdTodo.getId(), wrongAuthHeaders, HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void withoutAuthHeaders() {
        Todo createdTodo = TodoGenerator.init().generateTodo();

        DeleteTodoSteps.deleteTodo(createdTodo.getId(), Map.of(), HttpStatus.SC_UNAUTHORIZED);
    }

}
