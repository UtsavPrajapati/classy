package roviso.dominator.org.classy;


import java.io.*;
import java.net.*;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;

public class NetworkManager {
    public boolean waiting = false;
    public static List<String> list = new ArrayList<String>();
    public String message = "F";
    private String ip = "192.168.43.84";
    public static String user_id = "CE201606";
    public boolean error = false;
    public boolean in = false;


    public class Reciver extends AsyncTask<Void, Void, Void> {

        String type;

        public Reciver(String typ) {
            super();
            type = typ;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            waiting = true;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                InetAddress addr = InetAddress.getByName(ip);
                Socket client = new Socket(addr, 1234);
                BufferedReader Server = new BufferedReader(new InputStreamReader(client.getInputStream()));
                DataOutputStream Oclient = new DataOutputStream(client.getOutputStream());
                Oclient.writeBytes("Receive\n");
                Oclient.writeBytes(user_id + "\n");
                Oclient.writeBytes(type + "\n");
                int a = Integer.valueOf(Server.readLine());
                list.clear();
                for (int x = 0; x < a; x++) {
                    String s = Server.readLine();
                    //list.add(s);

                    list.add(TextUtils.join("\n", TextUtils.split(s, "~ZXZ~")));


                }
                waiting = false;
            } catch (Exception e) {
                error = true;
                waiting = false;
            }
            return null;
        }
    }

    private class Sender extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            waiting = true;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                InetAddress addr = InetAddress.getByName(ip);
                Socket client = new Socket(addr, 1234);
                DataOutputStream Oclient = new DataOutputStream(client.getOutputStream());
                Oclient.writeBytes("Send\n");
                Oclient.writeBytes(message + "\n");
                waiting = false;
            } catch (Exception e) {
                error = true;
                waiting = false;
            }
            return null;
        }
    }

    private class Verifier extends AsyncTask<Void, Void, Void> {
        String id, password;

        public Verifier(String Id, String Password) {
            super();
            id = Id;
            password = Password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            waiting = true;
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                message = "F";
                InetAddress addr = InetAddress.getByName(ip);
                Socket client = new Socket(addr, 1234);
                DataOutputStream Oclient = new DataOutputStream(client.getOutputStream());
                BufferedReader Server = new BufferedReader(new InputStreamReader(client.getInputStream()));
                Oclient.writeBytes("Login\n");
                Oclient.writeBytes(id + "\n");
                Oclient.writeBytes(password + "\n");
                message = Server.readLine();
                Log.i("message",message);
                waiting = false;
            } catch (Exception e) {
                 //send false if some error like internet problem occurs
                waiting = false;
            }
            return null;
        }
    }

    private class Registration extends AsyncTask<Void, Void, Void> {
        public Registration(String pm) {
            super();
            message = pm;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            waiting = true;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                InetAddress addr = InetAddress.getByName(ip);
                Socket client = new Socket(addr, 1234);
                DataOutputStream Oclient = new DataOutputStream(client.getOutputStream());
                BufferedReader Server = new BufferedReader(new InputStreamReader(client.getInputStream()));
                Oclient.writeBytes("Register\n");
                Oclient.writeBytes(message + "\n");
                message = Server.readLine();
                waiting = false;
            } catch (Exception e) {
                error = true;
                message = "F";
                waiting = false;
            }
            return null;
        }
    }


    public void recieve(String type) {
        error = false;
        //list.clear();
        new Reciver(type).execute();
    }

    public void send(String b) {
        error = false;
        message = b;
        new Sender().execute();
    }

    public void login(String Id, String Password) {
        user_id = Id;
        new Verifier(Id, Password).execute();
        if (message.equals("F"))
            in = true;

    }

    public boolean register(String pm) {
        new Registration(pm).execute();
        if (message.equals("F"))
            return false;
        else
            user_id = message;
        return true;
    }
}

