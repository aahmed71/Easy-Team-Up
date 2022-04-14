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
public class CreateNewUserBlackBoxTest {

    @Rule
    //private static final String TAG = CreateNewUser.class.getSimpleName();
    public ActivityScenarioRule<CreateNewUser> createNewUserTest = new ActivityScenarioRule<CreateNewUser>(CreateNewUser.class);

    @Test
    public void textDisplayed () {
        onView(withHint("User Name")).check(matches(isDisplayed()));
    }
    @Test
    public void textDisplayed1 () {
        onView(withHint("Password")).check(matches(isDisplayed()));
    }
    @Test
    public void textDisplayed2 () {
        onView(withHint("Email")).check(matches(isDisplayed()));
    }
    @Test
    public void textDisplayed3 () {
        onView(withHint("Age")).check(matches(isDisplayed()));
    }
    @Test
    public void typeTextTest () {
        onView(withHint("User Name")).perform(typeText("belle"));
        onView(withHint("Password")).perform(typeText("belle"));
        onView(withHint("Email")).perform(typeText("belle"));
        onView(withHint("Age")).perform(typeText("21"));
        closeSoftKeyboard();

        onView(allOf(withText("Register"))).perform(click());
    }
}