package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button loginB;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        if (!sharedPreferences.getString("username", "").equals("")){
            startActivity(new Intent(this, NotesViewActivity.class));
        }else{
            setContentView(R.layout.activity_main);
        }
        loginB = (Button) findViewById(R.id.button);
        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Invalid username", Toast.LENGTH_SHORT).show();
                }else if(password.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Invalid password", Toast.LENGTH_SHORT).show();
                }else{
                    login();
                }

            }
        });
    }

    private void login(){
        String name = username.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", name).apply();
        Intent intent = new Intent(this, NotesViewActivity.class);
        startActivity(intent);
    }

}
