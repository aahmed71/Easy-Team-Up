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
public class CreateEventBlackBoxTest {

    @Rule
    //private static final String TAG = CreateNewUser.class.getSimpleName();
    public ActivityScenarioRule<LoginActivity> createNewEventTest = new ActivityScenarioRule<LoginActivity>(LoginActivity.class);

    @Before
    public void login() {
        onView(withHint("User Name")).perform(typeText("belle"));
        onView(withHint("Password")).perform(typeText("123"));
        closeSoftKeyboard();
        onView(allOf(withText("Sign in"))).perform(click());
        onView(allOf(withText("Create Event"))).perform(click());
    }

    @Test
    public void succesfulEvent () {
        onView(withHint("Type Event Name")).perform(typeText("successful"));
        onView(withHint("Type Event Description")).perform(typeText("description"));
        closeSoftKeyboard();

        onView(allOf(withText("Next"))).perform(click());
        onView(allOf(withText("Create Event"))).check(matches(isDisplayed()));
    }
    @Test
    public void textDisplayed () {
        onView(withHint("Type Event Name")).check(matches(isDisplayed()));
    }
    @Test
    public void textDisplayed1 () {
        onView(withHint("Type Event Description")).check(matches(isDisplayed()));
    }
    @Test
    public void buttonDisplayed () {
        onView(allOf(withText("Next"))).check(matches(isDisplayed()));
    }
    @Test
    public void textDisplayed2 () {

        onView(withText("Select Event Type: ")).check(matches(isDisplayed()));
    }
    @Test
    public void textDisplayed3 () {
        onView(withText("Select Event Start Time: ")).check(matches(isDisplayed()));
    }


    // This test should fail
    @Test
    public void unsuccesfulEvent () {
        onView(withHint("Type Event Name")).perform(typeText("unsuccesful event test"));
        closeSoftKeyboard();

        onView(allOf(withText("Next"))).perform(click());
        onView(allOf(withText("Submit"))).check(matches(isDisplayed()));
    }
}