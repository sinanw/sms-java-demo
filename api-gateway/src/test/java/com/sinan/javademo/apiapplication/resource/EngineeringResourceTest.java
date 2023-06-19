package com.sinan.javademo.apiapplication.resource;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EngineeringResourceTest {

    @InjectMocks
    private EngineeringResource engineeringResource;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPing() {
        //Arrange
        String expected = "pong";

        //Act
        String actual = engineeringResource.ping();

        //Assert
        assertEquals(actual, expected);
    }
}