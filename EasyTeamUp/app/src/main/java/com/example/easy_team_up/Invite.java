package com.example.easy_team_up;

public class Invite {
        Integer inviteId;
        Integer userId;
        Integer eventId;
        String title;
        public Invite(Integer inviteId, Integer userId, Integer eventId, String title){
                this.inviteId = inviteId;
                this.userId = userId;
                this.eventId = eventId;
                this.title = title;
        }
}
