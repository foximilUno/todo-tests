package tests;

import entities.Todo;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import steps.PostTodoSteps;
import utility.Utils;

public class PostTodoTest extends TestConfigurator {

    @Test
    public void correctRequestWithCompletedFalse() {
        Todo todoEntity = Todo.builder()
            .id(Utils.getRandomPositiveLong())
            .text(Utils.getRandomString())
            .completed(false)
            .build();
        PostTodoSteps.createTodo(todoEntity, HttpStatus.SC_CREATED);
    }

    @Test
    public void correctRequestWithCompletedTrue() {
        Todo todoEntity = Todo.builder()
            .id(Utils.getRandomPositiveLong())
            .text(Utils.getRandomString())
            .completed(true)
            .build();
        PostTodoSteps.createTodo(todoEntity, HttpStatus.SC_CREATED);
    }

    @Test
    public void withoutId() {
        Todo todoEntity = Todo.builder()
            .text(Utils.getRandomString())
            .completed(false)
            .build();
        PostTodoSteps.createTodo(todoEntity, HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void withoutText() {
        Todo todoEntity = Todo.builder()
            .id(Utils.getRandomPositiveLong())
            .completed(false)
            .build();
        PostTodoSteps.createTodo(todoEntity, HttpStatus.SC_BAD_REQUEST);
    }


    @Test
    public void withoutCompleted() {
        Todo todoEntity = Todo.builder()
            .id(Utils.getRandomPositiveLong())
            .text(Utils.getRandomString())
            .build();
        PostTodoSteps.createTodo(todoEntity, HttpStatus.SC_BAD_REQUEST);
    }


    @Test
    public void withAlreadyExistedId() {
        Todo todoEntity = Todo.builder()
            .id(Utils.getRandomPositiveLong())
            .text(Utils.getRandomString())
            .completed(false)
            .build();
        PostTodoSteps.createTodo(todoEntity, HttpStatus.SC_CREATED);

        PostTodoSteps.createTodo(todoEntity, HttpStatus.SC_BAD_REQUEST);
    }


    @Test
    public void withEmptyString() {
        Todo todoEntity = Todo.builder()
            .id(Utils.getRandomPositiveLong())
            .text("")
            .completed(false)
            .build();
        PostTodoSteps.createTodo(todoEntity, HttpStatus.SC_CREATED);
    }

    @Test
    public void withNegativeId() {
        Todo todoEntity = Todo.builder()
            .id(Utils.getRandomPositiveLong()*-1)
            .text(Utils.getRandomString())
            .completed(false)
            .build();
        PostTodoSteps.createTodo(todoEntity, HttpStatus.SC_BAD_REQUEST);
    }
}
