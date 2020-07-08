package roviso.dominator.org.classy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String pname, pdept, pbat, proll, ppass;

    public static final String DATABASE_NAME = "Users.db";
    public static final String TABLE_NAME = "user_table";
    public static  String COL_1 = "user_id";
    public static final String COL_2 = "user_name";
    public static final String COL_3 = "user_department";
    public static final String COL_4 = "user_batch";
    public static final String COL_5 = "user_roll";
    public static String COL_6 = "user_password";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table "+ TABLE_NAME +"(user_id varchar(20) primary key," +
                " user_name varchar(20)," +
                " user_department varchar(20),"+
                " user_batch varchar(20),"+
                " user_roll varchar(20),"+
                " user_password varchar(20))");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insert (String username, String department, String batch, String rollno, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String id = department + batch + rollno;

        contentValues.put(COL_1, id);
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, department);
        contentValues.put(COL_4, batch);
        contentValues.put(COL_5, rollno);
        contentValues.put(COL_6, password);

        long ins = db.insert(TABLE_NAME, null, contentValues);

        if (ins== -1){
            Log.i("sign up or not","signed up not done");
            Log.i("department name:",department);
            return false;}
        else
            Log.i("department name:",department);
            return true;
    }

    public Boolean validityChk(String rollno , String dept){
       // String roll = Integer.toString(rollno);
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from user_table where user_roll = ? and user_department = ?",new String[]{rollno, dept});

        if(c.getCount()>0)
            return false;
        else
            return true;
    }

    public Boolean loginChk(String id, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from user_table where user_id = ?and user_password = ?",new String[]{id,pass} );
        COL_1 = id;
        COL_6 = pass;

        if (c.getCount()>0) {
            Log.i("Login:", "loging successfull");
            return true;
        }
        else {
            Log.i("Login:", "loging fail");
            return false;
        }
    }

    public String getpname(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from user_table where user_id = ? ",new String[]{COL_1} );
        int nameIndex = c.getColumnIndex("user_name");
        c.moveToFirst();
        pname = c.getString(nameIndex);
        return pname;
    }
    public String getpdept(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from user_table where user_id = ?",new String[]{COL_1} );
        int deptIndex = c.getColumnIndex("user_department");
        c.moveToFirst();
        pdept = c.getString(deptIndex);
        return pdept;
    }
    public String getpbatch(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from user_table where user_id = ?",new String[]{COL_1} );
        int batIndex = c.getColumnIndex("user_batch");
        c.moveToFirst();
        pbat = c.getString(batIndex);
        return pbat;
    }
    public String getproll(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from user_table where user_id = ?",new String[]{COL_1} );
        int rollIndex = c.getColumnIndex("user_roll");
        c.moveToFirst();
        proll = c.getString(rollIndex);
        return proll;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res  = db.rawQuery("select * from "+ TABLE_NAME,null);
        return res;
    }

    public Integer deleteUser (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"user_id = ?",new String[]{id});
    }


}












