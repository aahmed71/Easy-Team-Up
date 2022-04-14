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
public class LoginActivityBlackBoxTest {

    @Rule
    //private static final String TAG = CreateNewUser.class.getSimpleName();
    public ActivityScenarioRule<LoginActivity> createNewUserTest = new ActivityScenarioRule<LoginActivity>(LoginActivity.class);

    @Test
    public void textDisplayed () {
        onView(withHint("User Name")).check(matches(isDisplayed()));
    }
    @Test
    public void textDisplayed1 () {
        onView(withHint("Password")).check(matches(isDisplayed()));
    }
    @Test
    public void buttonDisplayed () {
        onView(allOf(withText("Sign in"))).check(matches(isDisplayed()));
    }
    @Test
    public void buttonClickLogin () {
        onView(allOf(withText("Sign in"))).perform(click());
    }
    @Test
    public void buttonClick () {
        onView(allOf(withText("Sign Up"))).perform(click());
    }
    @Test
    public void succesfulLogin () {
        onView(withHint("User Name")).perform(typeText("belle"));
        onView(withHint("Password")).perform(typeText("123"));
        closeSoftKeyboard();

        onView(allOf(withText("Sign in"))).perform(click());
        onView(withText("User Portal")).check(matches(isDisplayed()));
    }

    // Test should fail
    @Test
    public void invalidUsername () {
        onView(withHint("User Name")).perform(typeText("invalid"));
        onView(withHint("Password")).perform(typeText("123"));
        closeSoftKeyboard();

        onView(allOf(withText("Sign in"))).perform(click());
        onView(withText("User Portal")).check(matches(isDisplayed()));
    }

    // This test should fail
    @Test
    public void unsuccesfulLogin () {
        onView(withHint("User Name")).perform(typeText("belle"));
        onView(withHint("Password")).perform(typeText("000"));
        closeSoftKeyboard();

        onView(allOf(withText("Sign in"))).perform(click());
        onView(withText("User Portal")).check(matches(isDisplayed()));

        //onView(allOf(withText("Login"))).perform(click());
    }
}