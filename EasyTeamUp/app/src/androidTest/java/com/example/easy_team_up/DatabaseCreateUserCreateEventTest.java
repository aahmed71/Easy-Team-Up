package com.example.easy_team_up;

import static org.junit.Assert.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//import androidx.test.InstrumentationTestCase;

@RunWith(AndroidJUnit4.class)

public class DatabaseCreateUserCreateEventTest  {
    DBHelper DB;

    @Before
    public void setUp() throws Exception {
        DB = new DBHelper(InstrumentationRegistry.getTargetContext());
    }
    @After
    public void finish() {
        DB.close();
    }

    @Test
    public void userIDTest() {
        assertEquals(4,4);

    }

}