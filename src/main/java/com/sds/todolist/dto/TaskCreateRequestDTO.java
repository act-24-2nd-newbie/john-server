package com.sds.todolist.dto;

import lombok.Getter;

@Getter
public class TaskCreateRequestDTO {
    Long memberId;
    String contents;
}
