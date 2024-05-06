package ch.selise.todo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testMainMethod() {
        TodoApplication springApplicationMock = Mockito.mock(TodoApplication.class);

        String[] args = new String[]{};

        Mockito.verify(springApplicationMock).main(args);
    }

}
