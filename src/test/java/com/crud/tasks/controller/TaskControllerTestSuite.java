package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class TaskControllerTestSuite {

    @Autowired
    private TaskController taskController;

    @Autowired
    private DbService service;

    @Test
    void getTasksTest() {
        //Given
        //When
        List<TaskDto> taskDtoList = taskController.getTasks();
        //Then
        assertEquals(0,taskDtoList.size());
    }

    @Test
    void createTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"Title","Content" );
        //When
        taskController.createTask(taskDto);
        //Then
        assertEquals(1,taskController.getTasks().size());
        //CleanUp
        service.deleteAll();
    }

    @Test
    void getTaskTest() throws TaskNotFoundException {
        //Given
        TaskDto taskDto = new TaskDto(1L,"Title","Content" );
        Task createdTask = taskController.createTask(taskDto);
        //When
        TaskDto task = taskController.getTask(createdTask.getId());
        //Then
        assertEquals("Title",task.getTitle());
        //ClenUp
        service.deleteAll();
    }

    @Test
    void deleteTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"Title","Content" );
        Task createdTask = taskController.createTask(taskDto);
        //When
        taskController.deleteTask(createdTask.getId());
        List<TaskDto> taskDtoList = taskController.getTasks();
        //Then
        assertEquals(0,taskDtoList.size());
        //CleanUp
        service.deleteAll();
    }

    @Test
    void updateTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"Title","Content" );
        Task task = taskController.createTask(taskDto);
        //When
        TaskDto updatedTask = taskController.updateTask(
                new TaskDto(task.getId(),"Updated title", task.getContent()));
        //Then
        assertNotEquals("Title",updatedTask.getTitle());
        assertEquals(task.getId(),updatedTask.getId());
        //CleanUp
        service.deleteAll();
    }
}
