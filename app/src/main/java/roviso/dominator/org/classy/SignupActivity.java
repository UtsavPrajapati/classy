package roviso.dominator.org.classy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText name, batch, rollno , password, cpassword;
    Button signupbtn;
    Spinner department;
    NetworkManager networkManager = new NetworkManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = new DatabaseHelper(this);

        name = (EditText) findViewById(R.id.pName);
        department = (Spinner) findViewById(R.id.pDepartment);


        ArrayAdapter<String> deptadapter = new ArrayAdapter<String>(SignupActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.dept));
        deptadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        department.setAdapter(deptadapter);

        batch = (EditText) findViewById(R.id.pBatch);
        rollno = (EditText) findViewById(R.id.pRollno);
        password = (EditText) findViewById(R.id.pPassword);
        cpassword = (EditText) findViewById(R.id.pPasswordC);
        signupbtn = (Button) findViewById(R.id.signupbtn);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Sname = name.getText().toString();
                String Sdept = department.getSelectedItem().toString();
                String Sbatch = batch.getText().toString();
                String Srollno = rollno.getText().toString();
                String Spassword = password.getText().toString();
                String SCpassword = cpassword.getText().toString();


                if(Sname.equals("")||Sdept.equals("")||Sbatch.equals("")||Srollno.equals("")||Spassword.equals("")||SCpassword.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please fill up all the fields!",Toast.LENGTH_SHORT).show();
                }

            else{
                    String id = Sdept + Sbatch + Srollno;
                    //int roll = Integer.parseInt(rollno.getText().toString());
                    if(Spassword.equals(SCpassword)) {
                        String[] list = {id, Sname, Sbatch, Sdept, Srollno, Spassword};
                        networkManager.register(TextUtils.join("~ZXZ~", list));
                        while (true) {
                            if (!networkManager.waiting) {
                                break;
                            }
                        }
                        Toast.makeText(getApplicationContext(),"Your new id is: " + id,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    }
                    else{
                            Toast.makeText(getApplicationContext(), "Roll NO already exists,Please try again!", Toast.LENGTH_SHORT).show();

                        }
                    }
                    Toast.makeText(getApplicationContext(),"Password do not match, Please try again",Toast.LENGTH_SHORT).show();
                }
        });

    }
}
