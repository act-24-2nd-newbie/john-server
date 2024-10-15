package com.sds.todolist.service;

import com.sds.todolist.domain.Member;
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
    private final MemberService memberService;

    public void createTask(TaskCreateRequestDTO taskCreateRequestDTO) {
        Member member = memberService.getMemberById(taskCreateRequestDTO.getMemberId());
        Task task = Task.builder().contents(taskCreateRequestDTO.getContents()).member(member).build();
        taskRepository.save(task);
    }

    public List<TaskResponseDto> getTasks(Long memberId) {
        return taskRepository.findByMemberId(memberId).stream().map(Task::toResponse).toList();
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
