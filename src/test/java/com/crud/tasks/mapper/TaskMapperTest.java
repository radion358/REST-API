package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TaskMapperTest {
    private final TaskMapper taskMapper = new TaskMapper();

    @Test
    public void mapToTask() {
        //Given
        Task expectedTask = new Task(1,"test title", "test content");
        TaskDto taskDto = new TaskDto(1,"test title", "test content");
        //When
        Task actualTask = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(expectedTask, actualTask);
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task(1,"test title", "test content");
        TaskDto expectedTaskDto = new TaskDto(1,"test title", "test content");
        //When
        TaskDto actualTaskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(expectedTaskDto, actualTaskDto);
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        Task task0 = new Task(0,"test title 0", "test content 0");
        Task task1 = new Task(1,"test title 1", "test content 1");
        Task task2 = new Task(2,"test title 2", "test content 2");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task0);
        tasks.add(task1);
        tasks.add(task2);
        TaskDto taskDto0 = new TaskDto(0,"test title 0", "test content 0");
        TaskDto taskDto1 = new TaskDto(1,"test title 1", "test content 1");
        TaskDto taskDto2 = new TaskDto(2,"test title 2", "test content 2");
        List<TaskDto> expectedTaskDtos = new ArrayList<>();
        expectedTaskDtos.add(taskDto0);
        expectedTaskDtos.add(taskDto1);
        expectedTaskDtos.add(taskDto2);
        //When
        List<TaskDto> actualTaskDtos = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertEquals(3, actualTaskDtos.size());
        for (int i = 0; i < actualTaskDtos.size(); i++) {
            assertEquals(expectedTaskDtos.get(i), actualTaskDtos.get(i));
        }
    }
}