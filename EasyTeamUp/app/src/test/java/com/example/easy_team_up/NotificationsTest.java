package com.example.easy_team_up;

import org.junit.Rule;

public class NotificationsTest<IntentsTestRule> {
    @Rule
    public IntentsTestRule<ViewNotifications> intentsTestRule =
            new IntentsTestRule<>(ViewNotifications.class);
}
