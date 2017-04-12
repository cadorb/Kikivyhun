package kik.ivyhunpas.kikivyhun.stub;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kik.ivyhunpas.kikivyhun.entities.Address;
import kik.ivyhunpas.kikivyhun.entities.Category;
import kik.ivyhunpas.kikivyhun.entities.Event;
import kik.ivyhunpas.kikivyhun.entities.Place;

/**
 * Created by Blaise on 12/04/2017.
 */

public class EventsStub {

    public static List<Event> createEvents(){
        List<Event> eventList = new ArrayList<>();
        Event event = new Event();
        Category category = new Category();
        Place place = new Place();
        Address address = new Address();

        address.setCity("Paris");
        place.setAddress(address);

        category.setName("Anniversaire");

        event.setCategory(category);
        event.setPlace(place);
        event.setTitle("Yolo");
        event.setStart_date(Calendar.getInstance().getTime());
        event.setEnd_date(Calendar.getInstance().getTime());

        for (int i = 0; i < 10; i++) {
            eventList.add(event);
        }

        return eventList;
    }
}
