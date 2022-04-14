package com.example.easy_team_up;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)

@LargeTest
public class ManageInvitationsBlackBoxTest {
    @Rule
    public ActivityScenarioRule<LoginActivity> logIn = new ActivityScenarioRule<>(LoginActivity.class);
    @Test
    public void myEventsDisplayed () {
        onView(withHint("User Name")).perform(typeText("testUser3"));
        onView(withHint("Password")).perform(typeText("123"));
        onView(withText("Sign in")).perform(click());
        //goes to User Portal
        onView(withText("User Portal")).check(matches(isDisplayed()));
        onView(withText("Manage My Events")).perform(click());
        //go to view my events
        onView(withText("Event 1")).check(matches(isDisplayed()));
        onView(withText("Event 2")).check(matches(isDisplayed()));
        //click on event 1
        onView(withText("Event 1")).perform(click());
        onView(withText("testUser3")).check(matches(isDisplayed()));
        onView(withText("USC")).check(matches(isDisplayed()));
        onView(withText("study")).check(matches(isDisplayed()));
        onView(withText("Back")).perform(click());
        onView(withText("Back")).perform(click());
        onView(withText("Log Out")).perform(click());
    }
    @Test
    public void myInvites () {
        onView(withHint("User Name")).perform(typeText("testUser3"));
        onView(withHint("Password")).perform(typeText("123"));
        onView(withText("Sign in")).perform(click());
        //goes to User Portal
        onView(withText("User Portal")).check(matches(isDisplayed()));
        onView(withText("View RSVPs and Invites")).perform(click());
        //go to view my invites
        onView(withText("Event 1")).check(matches(isDisplayed()));
        onView(withText("Event 2")).check(matches(isDisplayed()));
        //click on event 1
        onView(withText("Event 1")).perform(click());
        onView(withText("testUser3")).check(matches(isDisplayed()));
        onView(withText("USC")).check(matches(isDisplayed()));
        onView(withText("study")).check(matches(isDisplayed()));
        onView(withText("Back")).perform(click());
        onView(withText("Back")).perform(click());
        onView(withText("Log Out")).perform(click());
    }
    @Test
    public void myRSVPs () {
        onView(withHint("User Name")).perform(typeText("testUser3"));
        onView(withHint("Password")).perform(typeText("123"));
        onView(withText("Sign in")).perform(click());
        //goes to User Portal
        onView(withText("User Portal")).check(matches(isDisplayed()));
        onView(withText("View RSVPs and Invites")).perform(click());
        //go to view my invites
        onView(withText("Event 1")).check(matches(isDisplayed()));
        onView(withText("Event 2")).check(matches(isDisplayed()));
        //click on event 1
        onView(withText("View RSVPs")).perform(click());
        onView(withText("View RSVPs")).perform(click());
        onView(withText("My RSVPs")).check(matches(isDisplayed()));
        onView(withText("Event 3")).check(matches(isDisplayed()));
        onView(withText("Back")).perform(click());
        onView(withText("Log Out")).perform(click());
    }
    @Test
    public void acceptInvitation () {
        onView(withHint("User Name")).perform(typeText("testUser4"));
        onView(withHint("Password")).perform(typeText("123"));
        onView(withText("Sign in")).perform(click());
        //goes to User Portal
        onView(withText("User Portal")).check(matches(isDisplayed()));
        onView(withText("View RSVPs and Invites")).perform(click());
        //go to view my invites
        onView(withText("Event3")).check(matches(isDisplayed()));
        onView(withText("Accept")).perform(click());
        onView(withText("View RSVPs")).perform(click());
        onView(withText("View RSVPs")).perform(click());
        onView(withText("Event3")).check(matches(isDisplayed()));
        onView(withText("Back")).perform(click());
        onView(withText("Back")).perform(click());
        onView(withText("Log Out")).perform(click());
    }
    @Test
    public void manageMyNotifications () {
        onView(withHint("User Name")).perform(typeText("testUser3"));
        onView(withHint("Password")).perform(typeText("123"));
        onView(withText("Sign in")).perform(click());
        //goes to User Portal
        onView(withText("User Portal")).check(matches(isDisplayed()));
        onView(withText("View Notifications")).perform(click());
        //go to view my invites
        onView(withText("Test Notification")).check(matches(isDisplayed()));
        //delete notification
        onView(withText("Delete")).perform(click());
        onView(withText("Test Notification")).check(doesNotExist());
        onView(withText("Back")).perform(click());
        onView(withText("Log Out")).perform(click());
    }
}


