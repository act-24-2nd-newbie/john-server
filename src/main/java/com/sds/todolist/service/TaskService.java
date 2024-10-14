package com.sds.todolist.service;

import com.sds.todolist.domain.Task;
import com.sds.todolist.dto.TaskCreateRequestDTO;
import com.sds.todolist.dto.TaskResponseDto;
import com.sds.todolist.dto.TaskUpdateRequestDTO;
import com.sds.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public void createTask(TaskCreateRequestDTO taskCreateRequestDTO) {
        Task task = Task.builder().contents(taskCreateRequestDTO.getContents()).build();
        taskRepository.save(task);
    }

    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll().stream().map(Task::toResponse).toList();
    }

    public void updateTask(Long taskId, TaskUpdateRequestDTO taskUpdateRequestDTO) {
        taskRepository.findById(taskId).ifPresent(task -> {
            task.setContents(taskUpdateRequestDTO.getContents());
            task.setIsDone(taskUpdateRequestDTO.getIsDone());
            taskRepository.save(task);
        });
    }

    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
