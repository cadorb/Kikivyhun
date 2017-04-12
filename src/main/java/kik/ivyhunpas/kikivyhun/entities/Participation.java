package kik.ivyhunpas.kikivyhun.entities;

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
}
