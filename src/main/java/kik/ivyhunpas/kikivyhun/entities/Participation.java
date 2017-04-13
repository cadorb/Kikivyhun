package kik.ivyhunpas.kikivyhun.entities;

import android.provider.BaseColumns;

import kik.ivyhunpas.kikivyhun.entities.base.EntityBase;

/**
 * Created by Blaise on 11/04/2017.
 */

public class Participation extends EntityBase {
    private Boolean has_come;
    private User user;
    private Event event;


    public Boolean getHas_come() {
        return has_come;
    }

    public void setHas_come(Boolean has_come) {
        this.has_come = has_come;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public static class ParticipationEntry implements BaseColumns {
        public static final String TABLE_NAME = "participation";
        public static final String COLUMN_NAME_HAS_COME = "has_come";
        public static final String COLUMN_NAME_USER_ID = "user_id";
        public static final String COLUMN_NAME_EVENT_ID = "event_id";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + ParticipationEntry.TABLE_NAME + " (" +
                        EntityBase.EntityBaseEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                        ParticipationEntry.COLUMN_NAME_HAS_COME + " TEXT, " +
                        ParticipationEntry.COLUMN_NAME_USER_ID + " INTEGER, " +
                        ParticipationEntry.COLUMN_NAME_EVENT_ID + " INTEGER);";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + ParticipationEntry.TABLE_NAME + ";";
    }
}
