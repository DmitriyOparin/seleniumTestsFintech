package ru.tests.fintech.tests;

import org.junit.After;
import org.junit.Before;
import ru.tests.fintech.app.Application;

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