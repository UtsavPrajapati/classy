package roviso.dominator.org.classy;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;



public class add_notice extends AppCompatActivity {

    public void homescreen (View view){
        final Intent intent = new Intent(getApplicationContext(), notices.class);
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Go Back!!")
                .setMessage(("Do you want to go back?"))
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No",null)
                .show();

    }
    int noticeId;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.Profile:
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.Notices:
                Intent intent1 = new Intent(getApplicationContext(), notices.class);
                startActivity(intent1);
                return true;

            case R.id.groups:
                Intent intent2 = new Intent(getApplicationContext(), groups.class);
                startActivity(intent2);
                return true;
            case R.id.assignment:
                Intent intent3 = new Intent(getApplicationContext(), assignment.class);
                startActivity(intent3);
                return true;
            case R.id.Calender:
                Intent intent4 = new Intent(getApplicationContext(), Calender.class);
                startActivity(intent4);
                return true;
            case R.id.logout:
                Intent intent5 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent5);
                return true;

            default:
                return false;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);

        EditText newNotice = (EditText) findViewById(R.id.newNotice);
        Intent intent = getIntent();
        noticeId = intent.getIntExtra("noticeId", -1);

        if(noticeId != -1){
            newNotice.setText(notices.notice.get(noticeId));
        } else{
            notices.notice.add("");
            noticeId = notices.notice.size() - 1;
            // if we add 3rd notice, its size will be 3, it will have id of 2
            notices.arrayAdapter.notifyDataSetChanged();
        }

        newNotice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                notices.notice.set(noticeId, String.valueOf(charSequence));
                notices.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("roviso.dominator.org.classy", Context.MODE_PRIVATE);

                HashSet<String> set = new HashSet<>(notices.notice);
                sharedPreferences.edit().putStringSet("notices", set).apply();


            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable editable) {

                Intent intent = new Intent(getApplicationContext(),notices.class);

                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),1,intent,0);
                Notification notification = new Notification.Builder(getApplicationContext())
                        .setContentTitle("Notice")
                        .setContentText("New Notice has been added.")
                        .setContentIntent(pendingIntent)
                        .addAction(android.R.drawable.sym_action_chat,"View Notice",pendingIntent)
                        .setSmallIcon(android.R.drawable.sym_def_app_icon)
                        .build();


                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(1,notification);

            }
        });





    }
}















