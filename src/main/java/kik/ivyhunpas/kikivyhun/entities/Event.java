package kik.ivyhunpas.kikivyhun.entities;

import java.util.ArrayList;
import java.util.Date;

import kik.ivyhunpas.kikivyhun.entities.base.EntityBase;

/**
 * Created by Blaise on 11/04/2017.
 */

public class Event extends EntityBase {

    private String title;
    private Date start_date;
    private Date end_date;
    private int max_participants;
    private ArrayList<User> users;
    private ArrayList<Participation> participations;
    private Category category;
    private Place place;

    public Event(String title, Date start_date, Date end_date, int max_participants, ArrayList<User> users, ArrayList<Participation> participations, Category category, Place place) {
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.max_participants = max_participants;
        this.users = users;
        this.participations = participations;
        this.category = category;
        this.place = place;
    }

    public Event() {
        this.users = new ArrayList<User>();
        this.participations = new ArrayList<Participation>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getMax_participants() {
        return max_participants;
    }

    public void setMax_participants(int max_participants) {
        this.max_participants = max_participants;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(ArrayList<Participation> participations) {
        this.participations = participations;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
