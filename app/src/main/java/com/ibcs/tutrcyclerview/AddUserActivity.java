package com.ibcs.tutrcyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ibcs.tutrcyclerview.models.User;

public class AddUserActivity extends AppCompatActivity {

    private EditText etName,etEmail;
    private Button btnAddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        etName = findViewById(R.id.name);
        etEmail = findViewById(R.id.email);
        btnAddUser = findViewById(R.id.add_user);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();

                User user = new User(name,email);
                Intent intent = new Intent();
                intent.putExtra("user",user);
                setResult(RESULT_OK,intent);
                finish();

//                Bundle bundle = new Bundle();
//                bundle.putSerializable("user",user);
//                intent.putExtras(bundle);
            }
        });
    }
}
