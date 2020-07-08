package roviso.dominator.org.classy;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;


public class MainActivity extends AppCompatActivity {


    private EditText password;
    private EditText id;
    private Button login;
    private Button signIn;
    private Button deletebtn;

    private Button show;
    DatabaseHelper db;

    NetworkManager networkManager = new NetworkManager();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_signup, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.sign_up){
            Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        id = (EditText) findViewById(R.id.id);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        signIn = (Button) findViewById(R.id.signInbutn);
        show = (Button) findViewById(R.id.show);
        deletebtn = (Button) findViewById(R.id.deletebtn);
        viewAll();
        DeleteUser();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ID = id.getText().toString();
                String pass = password.getText().toString();
                networkManager.login(ID,pass);
                while(true)
                {
                    if(! networkManager.waiting) {
                            break;
                    }
                }




                if (networkManager.in){
                    Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"Please Try again",Toast.LENGTH_SHORT).show();


/*
                Boolean loginChk = db.loginChk(ID,pass);
                if (loginChk == true) {
                    Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"Please Try again",Toast.LENGTH_SHORT).show();
                    */

            }

        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

    }
    public void viewAll(){
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getAllData();
                if(res.getCount() == 0) {
                    Log.i("data", "no data");
                    showMessage("Error","NO data");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()) {
                    buffer.append("id: " + res.getString(0) + "\n");
                    buffer.append("name: " + res.getString(1) + "\n");
                    buffer.append("department: " + res.getString(2) + "\n");
                    buffer.append("batch: " + res.getString(3) + "\n");
                    buffer.append("roll: " + res.getString(4) + "\n");
                    buffer.append("password: " + res.getString(5) + "\n\n");
                }
                showMessage("DATA:",buffer.toString());
            }
        });
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void DeleteUser(){
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows = db.deleteUser(id.getText().toString());
                if (deleteRows > 0)
                    Toast.makeText(MainActivity.this, "User Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "User Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
