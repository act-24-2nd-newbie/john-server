package com.sds.todolist.service;

import com.sds.todolist.domain.Task;
import com.sds.todolist.dto.TaskCreateRequestDTO;
import com.sds.todolist.dto.TaskUpdateRequestDTO;
import com.sds.todolist.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @InjectMocks
    TaskService subject;

    @Mock
    TaskRepository taskRepository;

    @Captor
    ArgumentCaptor<Task> taskCaptor;

    Long SOME_ID = 1L;
    String SOME_CONTENTS = "SOME_CONTENTS";
    Boolean SOME_IS_DONE = true;

    @Test
    void createTask() {
        TaskCreateRequestDTO taskCreateRequestDTO = new TaskCreateRequestDTO();

        subject.createTask(taskCreateRequestDTO);

        then(taskRepository).should(times(1)).save(any());
    }

    @Test
    void getAllTasks() {
        subject.getAllTasks();

        then(taskRepository).should(times(1)).findAll();
    }

    @Test
    void updateTask() {
        TaskUpdateRequestDTO taskUpdateRequestDTO = new TaskUpdateRequestDTO(SOME_CONTENTS, SOME_IS_DONE);
        Optional<Task> optionalTask = Optional.ofNullable(Task.builder().build());
        given(taskRepository.findById(eq(SOME_ID))).willReturn(optionalTask);

        subject.updateTask(SOME_ID, taskUpdateRequestDTO);

        then(taskRepository).should(times(1)).findById(SOME_ID);
        then(taskRepository).should(times(1)).save(taskCaptor.capture());
        assertEquals(taskCaptor.getValue().getContents(), SOME_CONTENTS);
    }

    @Test
    void deleteAllTasks() {
        subject.deleteAllTasks();

        then(taskRepository).should(times(1)).deleteAll();
    }

    @Test
    void deleteTask() {
        subject.deleteTask(SOME_ID);

        then(taskRepository).should(times(1)).deleteById(SOME_ID);
    }
}