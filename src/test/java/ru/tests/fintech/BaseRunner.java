package ru.tests.fintech;

import org.junit.After;
import org.junit.Before;

public class BaseRunner {
    public Application app;

    @Before
    public void setUp() {
        app = new Application();
    }

    @After
    public void tearDown() {
        app.quit();
    }
}