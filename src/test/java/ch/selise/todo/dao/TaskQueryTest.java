package ch.selise.todo.dao;

import ch.selise.todo.dto.TaskFilterDTO;
import ch.selise.todo.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Rhidoy
 * @created 06/05/2024
 * @package ch.selise.todo.dao
 */
@DataJpaTest
public class TaskQueryTest {
    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testGetQueryForAll() {
        TaskFilterDTO filterDTO = new TaskFilterDTO();
        filterDTO.setDescription("Task 1");
        filterDTO.setUserId(100L);

        Specification<Task> specification = TaskQuery.getQuery(filterDTO);
        Page<Task> filteredTasks = taskRepository.findAll(specification, Pageable.ofSize(1));

        assertThat(filteredTasks.getContent()).isEmpty();
    }
    @Test
    void testGetQueryForNone() {
        TaskFilterDTO filterDTO = new TaskFilterDTO();

        Specification<Task> specification = TaskQuery.getQuery(filterDTO);
        Page<Task> filteredTasks = taskRepository.findAll(specification, Pageable.ofSize(1));

        assertThat(filteredTasks.getContent()).isNotEmpty();
    }

}
