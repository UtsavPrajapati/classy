package roviso.dominator.org.classy;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import android.os.Handler;

import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity {

    public void addNotice(View view) {
        Intent intent = new Intent(getApplicationContext(), add_notice.class);
        startActivity(intent);
    }

    private DrawerLayout mDrayerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private NavigationView navigationView;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//-----------------------------------------------------------Navigation Drawer------------------------------------------------------------------//
        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        mDrayerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrayerLayout, R.string.open, R.string.close);


        mDrayerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Profile: {
                        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.Notices: {
                        Intent intent1 = new Intent(getApplicationContext(), notices.class);
                        startActivity(intent1);
                        break;
                    }
                    case R.id.Notes: {
                        Intent intent1 = new Intent(getApplicationContext(), notes.class);
                        startActivity(intent1);
                        break;
                    }

                    case R.id.groups: {
                        Intent intent2 = new Intent(getApplicationContext(), networkActivity.class);
                        startActivity(intent2);
                        break;
                    }
                    case R.id.assignment: {
                        Intent intent3 = new Intent(getApplicationContext(), assignment.class);
                        startActivity(intent3);
                        break;
                    }
                    case R.id.Calender: {
                        Intent intent4 = new Intent(getApplicationContext(), Calender.class);
                        startActivity(intent4);
                        break;
                    }
                    case R.id.logout: {
                        Intent intent5 = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent5);
                        break;
                    }
                    default:
                        return false;

                }

                return false;
            }
        });
//-----------------------------------------------------------Body Part------------------------------------------------------------------//

        ListView mylistview = (ListView) findViewById(R.id.myListView);


        final ArrayList<String> mainlist = new ArrayList<String>();
        mainlist.add("Profile");
        mainlist.add("Notices!");
        mainlist.add("Notes");
        mainlist.add("Assignment");
        mainlist.add("Groups");
        mainlist.add("reminders");
        mainlist.add("Calender");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mainlist);
        mylistview.setAdapter(arrayAdapter);

        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //-----------------------------------------------------------------------------------------------------
                switch (i) {
                    case 0:
                        Intent intent0 = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getApplicationContext(), notices.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getApplicationContext(), networkActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(getApplicationContext(), assignment.class);
                        startActivity(intent3);
                        break;
                    default:
                        Intent intent4 = new Intent(getApplicationContext(), Calender.class);
                        startActivity(intent4);
                        break;
                }
            }
        });

    }

    boolean back;
    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        if (back == true){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }
        Toast.makeText(SecondActivity.this,"Please press Back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                back = false;
            }
        },3000);
                back = true;

    }
}

