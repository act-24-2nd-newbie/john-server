package com.sds.todolist.controller;

import com.sds.todolist.dto.TaskCreateRequestDTO;
import com.sds.todolist.dto.TaskResponseDto;
import com.sds.todolist.dto.TaskUpdateRequestDTO;
import com.sds.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public void createTask(@RequestBody TaskCreateRequestDTO taskCreateRequestDTO) {
        taskService.createTask(taskCreateRequestDTO);
    }

    @GetMapping
    public List<TaskResponseDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PatchMapping("/{taskId}")
    public void updateTask(@PathVariable Long taskId, @RequestBody TaskUpdateRequestDTO taskUpdateRequestDTO) {
        taskService.updateTask(taskId, taskUpdateRequestDTO);
    }

    @DeleteMapping
    public void deleteAllTasks() {
        taskService.deleteAllTasks();
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }
}
