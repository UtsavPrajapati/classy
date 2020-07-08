package roviso.dominator.org.classy;
import android.content.SharedPreferences;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class assignment extends AppCompatActivity {
    static ArrayList<String> notice_AList = new ArrayList<>();
    static ArrayAdapter noticeAdapter;
    public static final String MY_PREFS_NAME = "roviso.dominator.org.classyserver";
    NetworkManager network_manager = new NetworkManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        final SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);



        ListView noticeList = (ListView) findViewById(R.id.Notices);


        final Button recv_button = findViewById(R.id.refresh);
        recv_button.setOnClickListener(new View.OnClickListener() {
                                           public void onClick(View v) {
                                               network_manager.recieve("assignment");

                                               while(true)
                                               {
                                                   if(! network_manager.waiting) {

                                                       HashSet<String> set = new HashSet<>(network_manager.list);

                                                       sharedPreferences.edit().putStringSet("notices", set).apply();
                                                       break;
                                                   }
                                               }
                                           }
                                       }

        );


        Set<String> set = sharedPreferences.getStringSet("notices",new HashSet<String>());
        HashSet<String> set2 = new HashSet<>(notice_AList);
        HashSet<String> addedset = new HashSet<String>(set);
        addedset.addAll(set2);



        notice_AList = new ArrayList(addedset);

        Log.i("notice",notice_AList.toString());




//        noticeAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notice_AList);
//        noticeList.setAdapter(noticeAdapter);
//
//        noticeAdapter.notifyDataSetChanged();
        ArrayList<String> notice_List_senderid = new ArrayList<>();
        ArrayList<String> notice_List_groupid = new ArrayList<>();
        ArrayList<String> notice_List_title = new ArrayList<>();
        ArrayList<String> notice_List_message = new ArrayList<>();
        ArrayList<String> notice_List_deadline = new ArrayList<>();
        ArrayList<String> notice_List_timestamp = new ArrayList<>();

        String[] listArray = new String[notice_AList.size()];
        String[] senderidArray = new String[notice_AList.size()];
        String[] groupidArray = new String[notice_AList.size()];
        String[] titleArray = new String[notice_AList.size()];
        String[] messageArray = new String[notice_AList.size()];
        String[] deadlineArray = new String[notice_AList.size()];
        String[] timestampeArray = new String[notice_AList.size()];


        listArray = notice_AList.toArray(listArray);

        if (listArray.length != 0) {

            for (int i = 0; i < listArray.length; i++) {

                String[] separatedNotice = listArray[i].split("\n");
                String sender_id = separatedNotice[0];
                String group_id = separatedNotice[1];
                String title = separatedNotice[2];
                String timestamp = separatedNotice[3];
                String message = separatedNotice[4];
                String deadline = separatedNotice[5];
                notice_List_senderid.add(sender_id);
                notice_List_groupid.add(group_id);
                notice_List_title.add(title);
                notice_List_timestamp.add(timestamp);
                notice_List_message.add(message);
                notice_List_deadline.add(deadline);


            }
            senderidArray = notice_List_senderid.toArray(senderidArray);
            groupidArray = notice_List_groupid.toArray(groupidArray);
            titleArray = notice_List_title.toArray(titleArray);
            messageArray = notice_List_message.toArray(messageArray);
            deadlineArray = notice_List_deadline.toArray(deadlineArray);
            timestampeArray = notice_List_timestamp.toArray(timestampeArray);

            ListAdapter listAdapter = new CustomAdaptor(this, senderidArray, groupidArray, titleArray,timestampeArray, messageArray, deadlineArray);

            noticeList.setAdapter(listAdapter);
        }

        else
        {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, notice_AList);
            noticeList.setAdapter(adapter);
        }


//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, notice_AList);
//        noticeList.setAdapter(adapter);
    }


}



