package roviso.dominator.org.classy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;

public class networkActivity extends AppCompatActivity {


    TextView MesText;
    //EditText id;
    EditText title;
    EditText description;
    EditText deadline;
    EditText group_id;
    EditText type;
    Spinner stype;


    NetworkManager network_manager = new NetworkManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        MesText= findViewById(R.id.View);
        //id = findViewById(R.id.edit);
        title = findViewById(R.id.title);
        description = findViewById(R.id.desc);
        deadline = findViewById(R.id.deadline);
        group_id = findViewById(R.id.group_id);
        stype = (Spinner) findViewById(R.id.stype);

        ArrayAdapter<String> s_type = new ArrayAdapter<String>(networkActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.type));
        s_type.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        stype.setAdapter(s_type);

        final Button recv_button = findViewById(R.id.Refresh);
        recv_button.setOnClickListener(new View.OnClickListener() {
                                           public void onClick(View v) {
                                               network_manager.recieve("assignment");

                                               while(true)
                                               {
                                                   if(! network_manager.waiting) {

                                                       //    MesText.setText(TextUtils.join("\n", TextUtils.split(network_manager.list(i),"~ZXZ~")));
                                                       MesText.setText(TextUtils.join("\n", network_manager.list));
                                                       Log.i("revied.message:", TextUtils.join("\n", network_manager.list));
                                                       break;
                                                   }
                                               }
                                           }
                                       }

        );

        final Button send_button = findViewById(R.id.Send);
        send_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String Stype = stype.getSelectedItem().toString();
                String[] list = { network_manager.user_id ,group_id.getText().toString(),title.getText().toString(),
                        description.getText().toString(),deadline.getText().toString(),Stype} ;
                network_manager.send(TextUtils.join("~ZXZ~", list ));
                while(true)
                {
                    if(! network_manager.waiting){
                        break;
                    }
                }
            }
        } );
    }
}



