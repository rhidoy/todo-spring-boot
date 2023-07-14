package ch.selise.todo.sevice;

import ch.selise.todo.dao.UserRepository;
import ch.selise.todo.dto.UserCreateDTO;
import ch.selise.todo.entity.User;
import ch.selise.todo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Rhidoy
 * @created 14/07/2023
 * @package ch.selise.todo.sevice
 */
@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository dao;

    @Test
    void createUser() {
        // Arrange
        when(dao.save(any(User.class))).thenReturn(new User());
        when(dao.findByUsernameOrEmail(anyString(), anyString())).thenReturn(Optional.empty());

        // Act
        UserCreateDTO dto = new UserCreateDTO();
        dto.setEmail("example@gmail.com");
        dto.setGender(User.Gender.MALE);
        dto.setPassword("111111");
        dto.setRepeatPassword("111111");
        dto.setUsername("usertest");
        dto.setFirstName("User");
        dto.setLastName("Test");
        dto.setDateOfBirth(OffsetDateTime.of(2010,11,7, 0,0,0,0, ZoneOffset.UTC));
        final var actual = service.create(dto);

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(new User());
        verify(dao, times(1)).save(any(User.class));
        verifyNoMoreInteractions(dao);
    }
}
