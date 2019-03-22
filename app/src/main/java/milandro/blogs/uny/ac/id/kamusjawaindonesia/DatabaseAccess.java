package milandro.blogs.uny.ac.id.kamusjawaindonesia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milstrike on 23/02/2016.
 */
public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    //Private Constructor to avoid object creation from outside classes

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    //return a singleton instance of Database Access
    public static DatabaseAccess getInstance(Context context){
        if(instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    //Open Database Connection
    public void open(){
        this.database = openHelper.getWritableDatabase();
    }

    public void close(){
        if(database != null){
            this.database.close();
        }
    }

//    public List<String> getKamusJawa(){
//        List<String> list = new ArrayList<>();
//        Cursor cursor = database.rawQuery("SELECT * FROM inggrisindonesiagkamus", null);
//        cursor.moveToFirst();
//        while(!cursor.isAfterLast()){
//            list.add(cursor.getString(0));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return list;
//    }

    public List<String> getKamusIndonesia(){
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM paribasan", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(2));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

//    public List<String> getSearchJawa(String keyword){
//        List<String> list = new ArrayList<>();
//        String queryString = "SELECT * FROM inggrisindonesiagkamus WHERE kata LIKE '%" + keyword +"%'";
//        Cursor cursor = database.rawQuery(queryString, null);
//        cursor.moveToFirst();
//        while(!cursor.isAfterLast()){
//            list.add(cursor.getString(0));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return list;
//    }

    public List<String> getSearchIndonesia(String keyword){
        List<String> list = new ArrayList<>();
        String queryString = "SELECT * FROM paribasan WHERE paribasan LIKE '%" + keyword +"%'";
        Cursor cursor = database.rawQuery(queryString, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(2));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

//    public String getSelectedJawa(String kataJawa){
//        String queryString = "SELECT * FROM inggrisindonesiagkamus WHERE kata='"+ kataJawa +"'";
//        Cursor cursor = database.rawQuery(queryString, null);
//        cursor.moveToFirst();
//        String arti = cursor.getString(1);
//        cursor.close();
//        return arti;
//    }

    public String getSelectedIndonesia(String kataIndonesia){
        String queryString = "SELECT * FROM paribasan WHERE paribasan='"+ kataIndonesia +"'";
        Cursor cursor = database.rawQuery(queryString, null);
        cursor.moveToFirst();
        String arti = cursor.getString(3);
        cursor.close();
        return arti;
    }

    public String getSelectedIndonesia2(String kataIndonesia){
        String queryString = "SELECT * FROM paribasan WHERE paribasan='"+ kataIndonesia +"'";
        Cursor cursor = database.rawQuery(queryString, null);
        cursor.moveToFirst();
        String arti = cursor.getString(4);
        cursor.close();
        return arti;
    }

}
