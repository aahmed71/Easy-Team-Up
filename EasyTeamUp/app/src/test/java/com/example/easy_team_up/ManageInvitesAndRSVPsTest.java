package com.example.easy_team_up;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

import android.content.Context;
import android.os.Looper;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import android.database.Cursor;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ManageInvitesAndRSVPsTest {
    DBHelper db;
    private Looper mLooper;

    @Mock
    private Context mContext;

    @Before
    public void setup(){
        mLooper = mock(Looper.class);
        Context ctx = mock(Context.class);
        when(ctx.getMainLooper()).thenReturn(mLooper);
        db = new DBHelper(mContext);
    }

    @Test
    public void manageInvites(){
        Cursor beforeInvites = db.getInvitations(4);
        System.out.println(beforeInvites.getCount());
        //count number of rsvps is 0
        Integer numInvitesBefore = beforeInvites.getCount();
        assertEquals(0, Long.parseLong(String.valueOf(numInvitesBefore)));

        //now add rsvps: accept invitation
        //invite eventid, userid
        db.addToInvitations (100, 4);
        db.addToInvitations (200, 4);
        //now retrieve invitations from db
        Cursor invites = db.getInvitations(4);
        assertEquals(2, invites.getCount());
        invites.moveToFirst();
        Integer eventId1 = invites.getInt(1);
        Integer inviteId1 = invites.getInt(0);
        invites.moveToNext();
        Integer eventId2 = invites.getInt(1);

        assertEquals(java.util.Optional.of(100), eventId1);
        assertEquals(java.util.Optional.of(200), eventId2);

        System.out.println("Success: added two matching invitations to user 4");

        //test delete invitations
        boolean d = db.deleteInvitation(new Invite(inviteId1, 100, 4));
        if (d && db.getInvitations(4).getCount() == 1){
            System.out.println("Success: deleted user 4's first invite");
        }
        else{
            System.out.println("Failure: Cannot delete user 4's first invite");
            return;
        }
    }
    @After
    public void nullifyDB(){
        db = null;
    }
    @Before
    public void createAnotherDBInstance() {
        db = new DBHelper(new InvitationActivity());
    }
    @Test
    public void manageRSVPs(){
        System.out.println("hello");
        Cursor beforeRSVPs = db.getRSVPs(4);
        System.out.println(beforeRSVPs.getCount());
        //count number of rsvps is 0
        Integer numRSVPsBefore = beforeRSVPs.getCount();
        assertEquals(0, Long.parseLong(String.valueOf(numRSVPsBefore)));

        //now add rsvps: accept invitation
        //invite id, eventid, userid
        Invite invite1 = new Invite(100, 50, 4);
        Invite invite2 = new Invite(200, 40, 4);
        Boolean a = db.acceptInvitation(invite1);
        Boolean b = db.acceptInvitation(invite2);
        if (a && b){
            System.out.println("Success: added two RSVPs to user 4");
            Cursor rsvps = db.getRSVPs(4);
            assertEquals(2, rsvps.getCount());
            //check contents of rsvp
            rsvps.moveToFirst();
            if(rsvps.getInt(1) == 50){
                rsvps.moveToNext();
                if(rsvps.getInt(1) == 40){
                    System.out.println("Success: RSVPs match database");
                    //test deletion from rsvp table
                    if(db.deleteRSVP(invite1) && db.deleteRSVP(invite2)){
                        //after deleting the two rsvps check for 0 rsvps
                        Cursor rsvpsAfter = db.getRSVPs(4);
                        assertEquals(0, rsvpsAfter.getCount());
                    };
                    return;
                }
                else{
                    System.out.println("Failure: RSVPs do not match database");
                    return;
                }

            }
            else {
                System.out.println("Failure: RSVPs do not match database");
                return;
            }
        }
        else{
            System.out.println("Failure: cannot add two RSVPs to user 4");
            return;
        }
    }

}