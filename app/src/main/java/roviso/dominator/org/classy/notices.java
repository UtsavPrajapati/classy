package roviso.dominator.org.classy;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

import roviso.dominator.org.classy.R;

public class notices extends AppCompatActivity {

    static ArrayList<String> notice = new ArrayList<>();
    static ArrayAdapter arrayAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.notice_menu, menu);


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
                Intent intent1 = new Intent(getApplicationContext(), add_notice.class);
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
            case R.id.addNotice:
                Intent intent6 = new Intent(getApplicationContext(), add_notice.class);
                startActivity(intent6);
                return true;
            default:
                return false;



        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);

        ListView noticeList = (ListView) findViewById(R.id.noticeList);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("roviso.dominator.org.classy", Context.MODE_PRIVATE);

        HashSet<String> set = (HashSet<String>)sharedPreferences.getStringSet("notices",null);

        if (set == null){
            notice.add("Add Notice Here!!");
        } else {
            notice = new ArrayList(set);


        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notice);
        noticeList.setAdapter(arrayAdapter);

        noticeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), add_notice.class);
                intent.putExtra("noticeId", i);
                //i = row number which is tapped
                startActivity(intent);
            }
        });

        noticeList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {


            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int itemToDelete = i;
                new AlertDialog.Builder(notices.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete the notice!")
                        .setMessage("Do you want to delete this notice")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        notice.remove(itemToDelete);
                                        arrayAdapter.notifyDataSetChanged();

                                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("roviso.dominator.org.classy", Context.MODE_PRIVATE);

                                        HashSet<String> set = new HashSet<>(notices.notice);
                                        sharedPreferences.edit().putStringSet("notices", set).apply();


                                    }
                                }
                        )
                        .setNegativeButton("No", null)
                        .show();
                return true;

            }
        });
    }
}
