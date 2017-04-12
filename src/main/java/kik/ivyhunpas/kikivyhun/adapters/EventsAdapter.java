package kik.ivyhunpas.kikivyhun.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;

import kik.ivyhunpas.kikivyhun.R;
import kik.ivyhunpas.kikivyhun.entities.Event;

/**
 * Created by Blaise on 12/04/2017.
 */

public class EventsAdapter extends ArrayAdapter<Event> {


    private SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm");


    public EventsAdapter(@NonNull Context context, List<Event> events) {
        super(context, 0, events);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {






        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_events, parent, false);
        }

        TweetViewHolder viewHolder = (TweetViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new TweetViewHolder();
            viewHolder.check_participation = (Switch) convertView.findViewById(R.id.switch_event);
            viewHolder.title = (TextView) convertView.findViewById(R.id.event_title);
            viewHolder.category = (TextView) convertView.findViewById(R.id.event_category);
            viewHolder.city = (TextView) convertView.findViewById(R.id.event_city);
            viewHolder.date_start = (TextView) convertView.findViewById(R.id.event_date_start);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Event> events
        Event event = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.title.setText(event.getTitle());
        viewHolder.category.setText(event.getCategory().getName());
        viewHolder.city.setText(event.getPlace().getAddress().getCity());
        viewHolder.date_start.setText(DATE_FORMAT.format(event.getStart_date()));
        viewHolder.check_participation.setText(R.string.switch_participation);


        // Switch Listeners

        viewHolder.check_participation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()

        {
            @Override
            public void onCheckedChanged (CompoundButton buttonView,boolean isChecked){
                if (isChecked) {
                    Toast.makeText(getContext(), "Checked", Toast.LENGTH_SHORT).show();
                    buttonView.setText(R.string.switch_participation_ok);
                } else {
                    buttonView.setText(R.string.switch_participation);
                }
            }
        });


        return convertView;
    }

    private class TweetViewHolder {
        public TextView title;
        public TextView category;
        public TextView city;
        public TextView date_start;
        public Switch check_participation;
    }

}
