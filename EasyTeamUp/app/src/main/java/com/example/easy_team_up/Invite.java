package com.example.easy_team_up;

public class Invite {
        Integer inviteId;
        Integer userId;
        Integer eventId;
        public Invite(Integer inviteId, Integer eventId, Integer userId){
                this.inviteId = inviteId;
                this.userId = userId;
                this.eventId = eventId;
        }
}
