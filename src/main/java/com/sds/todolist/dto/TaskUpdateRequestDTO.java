package com.sds.todolist.dto;

import lombok.Getter;

@Getter
public class TaskUpdateRequestDTO {
    String contents;
    Boolean isDone;
}
