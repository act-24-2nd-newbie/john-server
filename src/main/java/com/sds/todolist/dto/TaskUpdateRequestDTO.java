package com.sds.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskUpdateRequestDTO {
    String contents;
    Boolean isDone;
}
