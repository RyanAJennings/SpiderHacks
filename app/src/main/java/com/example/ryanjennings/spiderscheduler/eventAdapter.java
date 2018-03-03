import java.util.*;
import java.time.LocalTime;

import java.io.*;
import java.text.*;

import java.time.format.DateTimeFormatter;

public class eventAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater mInflater;
	private ArrayList<Event> mDataSource;

	public RecipeAdapter(Context context, ArrayList<Recipe> items) {
	  mContext = context;
	  mDataSource = items;
	  mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	// 1
	@Override
	public int getCount() {
	  return mDataSource.size();
	}

	// 2
	@Override
	public Object getItem(int position) {
	  return mDataSource.get(position);
	}

	// 3
	@Override
	public long getItemId(int position) {
	  return position;
	}

	// 4
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get view for row item
		View rowView = mInflater.inflate(R.layout.list_item_event, parent, false);
		// Get title element
		TextView titleTextView = 
		  (TextView) rowView.findViewById(com.ryanjennings.spiderscheduler.R.id.event_list_title);

		// Get subtitle element
		TextView subtitleTextView = 
		  (TextView) rowView.findViewById(com.ryanjennings.spiderscheduler.R.id.event_list_subtitle);

		// Get detail element
		TextView detailTextView = 
		  (TextView) rowView.findViewById(com.ryanjennings.spiderscheduler.R.id.event_list_detail);

		// Get thumbnail element
		// ImageView thumbnailImageView = 
		//   (ImageView) rowView.findViewById(com.ryanjennings.spiderscheduler.R.id.event_list_thumbnail);
		

		Event event = (Event) getItem(position);

		// 2
		titleTextView.setText(event.event_name);
		DateFormat df = new SimpleDateFormat("MM-dd-yy", Locale.ENGLISH);
		subtitleTextView.setText(event.location + " " + df.format(event.date));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:m a");
		detailTextView.setText(event.start_time.format(formatter) + " - " + event.end_time.format(formatter));

	// Picasso.with(mContext).load(event.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);
	// 	 	return rowView;
	}
}