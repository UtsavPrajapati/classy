package roviso.dominator.org.classy;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

public class Calender extends AppCompatActivity {

    private CalendarView CV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        CV = (CalendarView) findViewById(R.id.calendarView);

        CV.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date =(month + 1) + "/" + dayOfMonth + "/" + year;
            }
        });
    }
}
