package com.sds.todolist.service;

import com.sds.todolist.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @InjectMocks
    TaskService subject;

    @Mock
    TaskRepository taskRepository;

    @Test
    void createTask() {
    }

    @Test
    void getAllTasks() {
    }

    @Test
    void updateTask() {
    }

    @Test
    void deleteAllTasks() {
    }

    @Test
    void deleteTask() {
    }
}