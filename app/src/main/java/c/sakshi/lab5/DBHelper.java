package c.sakshi.lab5;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBHelper {

    SQLiteDatabase db;
    public DBHelper(SQLiteDatabase db){
        this.db = db;
    }

    public void createTable() {
        db.execSQL("CREATE TABLE IF NOT EXISTS notes " +
                "(id INTEGER PRIMARY KEY, username TEXT, date TEXT, title TEXT, content TEXT, src TEXT)");
    }

    public ArrayList<Note> readNotes(String username){
        createTable();
        Cursor c = db.rawQuery(String.format("SELECT * from notes where username like '%s'", username), null);

        int dateIndex = c.getColumnIndex("date");
        int titleIndex = c.getColumnIndex("title");
        int contentIndex = c.getColumnIndex("content");

        c.moveToFirst();
        ArrayList<Note> notesList = new ArrayList<>();
        while(!c.isAfterLast()){
            notesList.add(new Note(c.getString(dateIndex), username, c.getString(titleIndex), c.getString(contentIndex)));
            c.moveToNext();
        }
        c.close();
        db.close();
        return notesList;
    }
    public void saveNotes(String username, String title, String content, String date){
        createTable();
        db.execSQL(String.format("INSERT INTO notes (username, date, title, content) VALUES ('%s', '%s', '%s', '%s')", username, date, title, content));
    }

    public void updateNote(String title, String date, String content){
        createTable();
        db.execSQL(String.format("UPDATE notes set content = '%s', date = '%s' where title = '%s'", content, date, title));
    }
}
