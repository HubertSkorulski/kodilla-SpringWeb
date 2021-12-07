package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "First task", "Content");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        Task expectedTask = new Task(1L, "First task", "Content");
        //Then
        assertEquals(expectedTask.getId(), task.getId());
        assertEquals(expectedTask.getTitle(), task.getTitle());
        assertEquals(expectedTask.getContent(), task.getContent());
    }

    @Test
    void mapToTaskDto() {
        //Given
        Task task = new Task(1L, "First task", "Content");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        TaskDto expectedTaskDto = new TaskDto(1L, "First task", "Content");
        //Then
        assertEquals(expectedTaskDto.getId(), taskDto.getId());
        assertEquals(expectedTaskDto.getTitle(), taskDto.getTitle());
        assertEquals(expectedTaskDto.getContent(), taskDto.getContent());
    }

    @Test
    void mapToTaskList() {
        //Given
        Task task = new Task(1L, "First task", "Content");
        Task secondTask = new Task(2L, "Second task", "Content2");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        taskList.add(secondTask);
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        TaskDto firstTaskDto = taskDtoList.get(0);
        TaskDto secondTaskDto = taskDtoList.get(1);
        TaskDto expectedFirstTask = new TaskDto(1L, "First task", "Content");
        TaskDto expectedSecondTask = new TaskDto(2L, "Second task", "Content2");
        //Then
        assertEquals(expectedFirstTask.getTitle(),firstTaskDto.getTitle());
        assertEquals(expectedFirstTask.getId(),firstTaskDto.getId());
        assertEquals(expectedFirstTask.getContent(),firstTaskDto.getContent());
        assertEquals(expectedSecondTask.getTitle(),secondTaskDto.getTitle());
        assertEquals(expectedSecondTask.getId(),secondTaskDto.getId());
        assertEquals(expectedSecondTask.getContent(),secondTaskDto.getContent());
    }
}
