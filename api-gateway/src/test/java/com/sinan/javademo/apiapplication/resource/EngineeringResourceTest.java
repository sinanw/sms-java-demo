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
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPing() {
        assertEquals(engineeringResource.ping(), "pong");
    }
}