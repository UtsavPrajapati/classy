package roviso.dominator.org.classy;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.sql.SQLData;

public class ProfileActivity extends AppCompatActivity {
    TextView name,dept,batch,roll;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        db = new DatabaseHelper(this);

        name = (TextView) findViewById(R.id.pName);
        dept = (TextView) findViewById(R.id.pDepartment);
        batch = (TextView)findViewById(R.id.pBatch);
        roll = (TextView) findViewById(R.id.pRollno);
        String getname = db.getpname();
        String getdept = db.getpdept();
        String gerbatch = db.getpbatch();
        String getroll = db.getproll();

        name.setText(getname);
        dept.setText(getdept);
        batch.setText(gerbatch);
        roll.setText(getroll);



    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
        startActivity(intent);
    }
}
