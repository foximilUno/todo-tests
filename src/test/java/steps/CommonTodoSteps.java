package steps;

import entities.Todo;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommonTodoSteps {

    public static void checkEquals(Todo expected, Todo actual) {
        Assertions.assertNotNull(actual, String.format("не удалось найти Todo с id: %s", expected.getId()));

        //возможно это избыточно
        Assertions.assertEquals(expected.getId(), actual.getId(), "не соответствует поле Id");

        Assertions.assertEquals(expected.getText(), actual.getText(), "не соответствует поле text");
        Assertions.assertEquals(expected.getCompleted(), actual.getCompleted(), "не соответствует поле completed");
    }

    public static void findEquals(List<Todo> expectedList, List<Todo> actualList) {
        Map<Long, Todo> actualMap = actualList.stream().collect(Collectors.toMap(Todo::getId, item -> item));

        for (Todo expectedTodo : expectedList) {
            checkEquals(expectedTodo, actualMap.get(expectedTodo.getId()));
        }
    }


}
