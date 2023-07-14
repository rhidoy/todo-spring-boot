package ch.selise.todo.dao;

import ch.selise.todo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Rhidoy
 * @created 14/07/2023
 * @package ch.selise.todo.sevice
 */
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository dao;

    @Test
    void createUser() {
        User newUser = new User();
        newUser.setUsername("1000");
        final var user = dao.save(newUser);
        final var createdUser = dao.findById(user.getId());

        // Assert
        assertThat(createdUser).isNotEmpty();
        assertThat(newUser.getUsername())
                .isEqualTo(createdUser.get().getId());
    }
}
