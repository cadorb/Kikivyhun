package kik.ivyhunpas.kikivyhun.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import kik.ivyhunpas.kikivyhun.R;
import kik.ivyhunpas.kikivyhun.adapters.EventsAdapter;
import kik.ivyhunpas.kikivyhun.entities.Event;
import kik.ivyhunpas.kikivyhun.stub.EventsStub;

public class EventsActivity extends AppCompatActivity {

    private ListView eventsListView;
    private List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        this.eventsListView = (ListView) findViewById(R.id.listViewEvents);
        this.eventList = EventsStub.createEvents();


        final EventsAdapter adapter = new EventsAdapter(EventsActivity.this, eventList);
        this.eventsListView.setAdapter(adapter);

        this.eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(EventsActivity.this, EventDetailsActivity.class));
            }
        });

        this.eventsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Event item = adapter.getItem(position);
                Toast.makeText(EventsActivity.this, "long click on " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }
}
