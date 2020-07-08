package roviso.dominator.org.classy;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class CustomAdaptor extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] sender_id;
    private final String[] group_id;
    private final String[] title;
    private final String[] timeStamp;
    private final String[] message;
    private final String[] deadline;

    public CustomAdaptor(@NonNull Activity context,String[] sender_id, String[] group_id, String[] title, String[] timeStamp,String[] message,String[] deadline) {
        super(context, R.layout.custom_row ,sender_id);

        this.context = context;
        this.sender_id = sender_id;
        this.group_id = group_id;
        this.title = title;
        this.timeStamp = timeStamp;
        this.message = message;
        this.deadline = deadline;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // return super.getView(position, convertView, parent);
        LayoutInflater classyInflater = context.getLayoutInflater();
        View customView = classyInflater.inflate(R.layout.custom_row,null,true);

        //String singleNoticeItem = getItem(position);
        TextView sender = (TextView) customView.findViewById(R.id.sender_id);
        TextView G_id = (TextView) customView.findViewById(R.id.group_id);
        TextView Title = (TextView) customView.findViewById(R.id.title);
        TextView TimeStamp = (TextView) customView.findViewById(R.id.timeStamp);
        TextView Message = (TextView) customView.findViewById(R.id.message);
        TextView DeadLine = (TextView) customView.findViewById(R.id.dead_line);


        sender.setText(sender_id[position]);
        G_id.setText(group_id[position]);
        Title.setText(title[position]);
        TimeStamp.setText(timeStamp[position]);
        Message.setText(message[position]);
        DeadLine.setText(deadline[position]);

        return customView;
    }
}
