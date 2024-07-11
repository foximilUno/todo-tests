package tests;

import java.util.List;

import entities.Todo;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import steps.GetTodosSteps;
import steps.PutTodoSteps;
import steps.TodoGenerator;
import utility.Utils;

public class PutTodoTest extends TestConfigurator {

    @Test
    public void successProcessingTextAndCompletedWithResponseStatusCheck() {
        Todo createdTodo = TodoGenerator.init().generateTodo();

        Todo editTodo = createdTodo.toBuilder()
            .text(createdTodo.getText() + " Edited")
            .completed(!createdTodo.getCompleted())
            .build();

        PutTodoSteps
            .editTodo(createdTodo.getId(), editTodo, HttpStatus.SC_OK);
    }

    //непонятно насколько такой кейс корректен
    @Test
    public void successProcessingIdWithResponseStatusCheck() {
        Todo createdTodo = TodoGenerator.init().generateTodo();

        Todo editTodo = createdTodo.toBuilder()
            .id(Utils.getRandomPositiveLong())
            .build();

        PutTodoSteps
            .editTodo(createdTodo.getId(), editTodo, HttpStatus.SC_OK);
    }

    @Test
    public void successEditTextAndCompletedWithEntityModifyCheck() {
        Todo createdTodo = TodoGenerator.init().generateTodo();

        Todo editTodo = createdTodo.toBuilder()
            .text(createdTodo.getText() + " Edited")
            .completed(!createdTodo.getCompleted())
            .build();

        PutTodoSteps
            .editTodo(createdTodo.getId(), editTodo, HttpStatus.SC_OK);

        List<Todo> actualTodos = GetTodosSteps.getAllTodos();

        Assertions.assertEquals(1,
            actualTodos
                .stream().filter(
                    todo -> todo.getId().equals(editTodo.getId())
                        && todo.getText().equals(editTodo.getText())
                        && todo.getCompleted().equals(editTodo.getCompleted())).toList()
                .size(), String.format("отсутствует todo %d с измененными text и completed", editTodo.getId()));
    }

    //непонятно насколько такой кейс корректен
    @Test
    public void successEditIdWithEntityModifyCheck() {
        Todo createdTodo = TodoGenerator.init().generateTodo();

        Todo editTodo = createdTodo.toBuilder()
            .id(Utils.getRandomPositiveLong())
            .build();

        PutTodoSteps
            .editTodo(createdTodo.getId(), editTodo, HttpStatus.SC_OK);

        List<Todo> currentList = GetTodosSteps.getAllTodos();

        Assertions.assertEquals(1,
            currentList
                .stream().filter(
                    todo -> todo.getId().equals(editTodo.getId())).toList()
                .size(), String.format("отсутствует todo %d с измененным id", editTodo.getId()));

        Assertions.assertEquals(0,
            currentList
                .stream().filter(
                    todo -> todo.getId().equals(createdTodo.getId())).toList()
                .size(), String.format("присутствует todo %d с изначальным id", createdTodo.getId()));
    }

    @Test
    public void withNoExistingId() {
        Todo todo = Todo.builder()
            .id(Utils.getRandomPositiveLong())
            .text(Utils.getRandomString())
            .completed(true)
            .build();
        PutTodoSteps
            .editTodo(Utils.getRandomPositiveLong(), todo, HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void withoutText() {
        Todo createdTodo = TodoGenerator.init().generateTodo();

        Todo editTodo = createdTodo.toBuilder()
            .text(null)
            .build();

        PutTodoSteps
            .editTodo(createdTodo.getId(), editTodo, HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void withoutCompleted() {
        Todo createdTodo = TodoGenerator.init().generateTodo();

        Todo editTodo = createdTodo.toBuilder()
            .completed(null)
            .build();

        PutTodoSteps
            .editTodo(createdTodo.getId(), editTodo, HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void withoutId() {
        Todo createdTodo = TodoGenerator.init().generateTodo();

        Todo editTodo = createdTodo.toBuilder()
            .id(null)
            .build();

        PutTodoSteps
            .editTodo(createdTodo.getId(), editTodo, HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void withEmptyBody() {
        Todo createdTodo = TodoGenerator.init().generateTodo();

        Todo editTodo = Todo.builder().build();

        PutTodoSteps
            .editTodo(createdTodo.getId(), editTodo, HttpStatus.SC_UNAUTHORIZED);
    }
}
