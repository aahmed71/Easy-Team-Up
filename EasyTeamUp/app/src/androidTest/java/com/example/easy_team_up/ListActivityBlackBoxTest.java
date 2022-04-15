package com.example.easy_team_up;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.click;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import android.nfc.Tag;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ListActivityBlackBoxTest {

    @Rule
    public ActivityScenarioRule<ListActivity> createNewUserTest = new ActivityScenarioRule<ListActivity>(ListActivity.class);

    @Test
    public void titleDisplayed () {
        onView(allOf(withText("Public Events"))).check(matches(isDisplayed()));
    }

    @Test
    public void eventDisplayed () {
        onView(allOf(withText("Event 1"))).check(matches(isDisplayed()));
    }
    @Test
    public void buttonDisplayed () {
        onView(allOf(withText("Accept"))).check(matches(isDisplayed()));
    }

    @Test
    public void eventClicked(){
        onView(allOf(withText("Event 1"))).perform(click());
        onView(withText("Event 1")).check(matches(isDisplayed()));
    }

    @Test
    public void acceptEvent () {
        onView(allOf(withText("Accept"))).perform(click());
        onView(withText("Accept")).check(matches(not(isDisplayed())));
    }

}