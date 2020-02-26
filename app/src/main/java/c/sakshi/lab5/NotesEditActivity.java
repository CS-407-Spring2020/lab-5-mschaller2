package c.sakshi.lab5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesEditActivity extends AppCompatActivity {

    Button save;
    EditText noteText;
    int noteid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_edit);

        save = (Button) findViewById(R.id.saveB);
        noteText = (EditText) findViewById(R.id.editText3);
        noteid = getIntent().getIntExtra("noteid", -1);
        if (noteid != -1){
            Note note = NotesViewActivity.notes.get(noteid);
            String noteContent = note.getContent();
            noteText.setText(noteContent);
        }
    }

    public void saveNote(View view){
        String content = noteText.getText().toString();
        SQLiteDatabase db = getApplicationContext().openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(db);
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());
        if (noteid == -1){
            title = "NOTE_" + (NotesViewActivity.notes.size() + 1);
            dbHelper.saveNotes(username, title, content, date);
        }else{
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, content);
        }
        startActivity(new Intent(this, NotesViewActivity.class));
    }

}
