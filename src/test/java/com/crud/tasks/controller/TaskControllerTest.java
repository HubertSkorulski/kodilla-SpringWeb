package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskController taskController;


    @Test
    void getTasksTest() throws Exception {
        //Given
        when(taskController.getTasks()).thenReturn(List.of(
                new TaskDto(1L,"Task","Task desc"),
                new TaskDto(2L,"Task 2", "Task2 desc")));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/task/getTasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title", Matchers.is("Task 2")));
    }

    @Test
    void getTaskTest() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L,"Task","Task desc");
        when(taskController.getTask(1L)).thenReturn(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/task/getTask")
                .contentType(MediaType.APPLICATION_JSON)
                        .param("taskId","1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Task")));
    }

    @Test
    void deleteTaskTest() throws Exception {
        //Given
        doNothing().when(taskController).deleteTask(1L);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/task/deleteTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("taskId","1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateTaskTest() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Task", "Task content");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        when(taskController.updateTask(any(TaskDto.class))).thenReturn(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .put("/v1/task/updateTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content",Matchers.is("Task content")));
    }

    @Test
    void createTaskTest() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1L, "Task", "Task content");
        Task task = new Task(1l,"Task","Task content");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        when(taskController.createTask(any(TaskDto.class))).thenReturn(task);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .post("/v1/task/createTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content",Matchers.is("Task content")));
    }
}