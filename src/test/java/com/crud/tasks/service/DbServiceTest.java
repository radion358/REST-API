package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void getAllTasks() {
        //When
        dbService.getAllTasks();

        //Then
        verify(taskRepository, only()).findAll();
    }

    @Test
    public void getTask() {
        //When
        dbService.getTask(1L);

        //Then
        verify(taskRepository, only()).findById(1L);
    }

    @Test
    public void saveTask() {
        //Given
        Task task = new Task(1, "test_name", "test_description");

        //When
        dbService.saveTask(task);

        //Then
        verify(taskRepository, only()).save(task);
    }

    @Test
    public void deleteTask() {
        //When
        dbService.deleteTask(1L);

        //Then
        verify(taskRepository, only()).delete(1L);
    }
}