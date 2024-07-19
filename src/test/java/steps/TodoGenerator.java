package steps;

import entities.Todo;
import org.apache.http.HttpStatus;
import utility.Utils;

public class TodoGenerator {

    public static TodoGenerator init() {
        return new TodoGenerator();
    }

    public Todo generateTodo() {
        Todo preparedTodo = Todo.builder()
                .id(Utils.getRandomPositiveLong())
                .text(Utils.getRandomString())
                .completed(false)
                .build();
        PostTodoSteps.createTodo(preparedTodo, HttpStatus.SC_CREATED);
        return preparedTodo;
    }

    public Todo generateTodoWithCompleted(Boolean completed) {
        Todo preparedTodo = Todo.builder()
                .id(Utils.getRandomPositiveLong())
                .text(Utils.getRandomString())
                .completed(completed)
                .build();
        PostTodoSteps.createTodo(preparedTodo, HttpStatus.SC_CREATED);
        return preparedTodo;
    }
}
