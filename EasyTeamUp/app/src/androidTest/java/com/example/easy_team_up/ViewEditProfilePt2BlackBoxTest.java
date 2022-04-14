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
public class ViewEditProfilePt2BlackBoxTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> ViewEditProfile = new ActivityScenarioRule<LoginActivity>(LoginActivity.class);
    @Before
    public void login() {
        onView(withHint("User Name")).perform(typeText("belle"));
        onView(withHint("Password")).perform(typeText("123"));
        closeSoftKeyboard();
        onView(allOf(withText("Sign in"))).perform(click());
        onView(allOf(withText("View / Edit Profile"))).perform(click());
        onView(allOf(withText("Edit Profile"))).perform(click());
    }
    @Test
    public void textDisplayed () {
        onView(withText("View / Edit Profile")).check(matches(isDisplayed()));
    }

    @Test
    public void textDisplayed1 () {
        onView(withText("Password: ")).check(matches(isDisplayed()));
    }
    @Test
    public void buttonDisplayed1() {
        onView(allOf(withText("Return to Portal"))).check(matches(isDisplayed()));
    }
    @Test
    public void textDisplayed2 () {
        onView(withText("Email: ")).check(matches(isDisplayed()));
    }
    @Test
    public void textDisplayed3 () {
        onView(withText("Age: ")).check(matches(isDisplayed()));
    }

    @Test
    public void textDisplayed4 () {
        onView(withText("Username: ")).check(matches(isDisplayed()));
    }
    @Test
    public void returnToPortal() {
        onView(allOf(withText("Return to Portal"))).perform(click());
        onView(withText("User Portal")).check(matches(isDisplayed()));
    }
    @Test
    public void submitChanges() {
        onView(allOf(withText("Submit"))).perform(click());
        onView(withText("User Portal")).check(matches(isDisplayed()));
    }
}