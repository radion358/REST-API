package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldGetTasks() throws Exception {
        //Given
        List<TaskDto> taskDtos = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        taskDtos.add(new TaskDto(1L, "test task", "test..."));
        taskDtos.add(new TaskDto(2L, "test task 2", "test......."));

        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);
        when(dbService.getAllTasks()).thenReturn(tasks);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    public void shouldGetNotFoundResponse() throws Exception {
        //Given
        when(dbService.getTask(1)).thenReturn(Optional.empty());

        //When & Then
        mockMvc.perform(get("/v1/task/getTask").param("taskId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1, "test", "test...");
        Task task = new Task();

        when(dbService.getTask(1)).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTask").param("taskId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test")))
                .andExpect(jsonPath("$.content", is("test...")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask").param("taskId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(dbService, only()).deleteTask(1);
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1, "test", "test...");
        Task task = new Task();

        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test")))
                .andExpect(jsonPath("$.content", is("test...")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1, "test", "test...");
        Task task = new Task();

        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonContent))
                .andExpect(status().isOk());
    }
}