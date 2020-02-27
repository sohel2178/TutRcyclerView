package com.ibcs.tutrcyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ibcs.tutrcyclerview.api.ApiClient;
import com.ibcs.tutrcyclerview.api.ServiceGenerator;
import com.ibcs.tutrcyclerview.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserActivity extends AppCompatActivity {

    private EditText etName,etEmail,etContact;
    private Button btnAddUser;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        user = (User) getIntent().getSerializableExtra("USER");

        etName = findViewById(R.id.name);
        etEmail = findViewById(R.id.email);
        etContact = findViewById(R.id.contact);
        btnAddUser = findViewById(R.id.add_user);


        if(user!=null){
            etName.setText(user.getName());
            etEmail.setText(user.getEmail());
            etContact.setText(user.getContact());
            btnAddUser.setText("Update");
        }

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String contact = etContact.getText().toString().trim();

                if(user==null){
                    user = new User();
                }

                user.setName(name);
                user.setEmail(email);
                user.setContact(contact);

                ApiClient apiClient = ServiceGenerator.createService(ApiClient.class);

                if(user.get_id()==null){
                    apiClient.saveUser(user)
                            .enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {

                                    if(response.isSuccessful()){
                                        User user1 = response.body();

                                        Log.d("YYYYY",user1.getContact()+"");
                                        Log.d("YYYYY",user1.get_id()+"");
                                    }

                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    Log.d("YYYYY",t.getMessage()+"");
                                }
                            });

                }else{
                    apiClient.updateUser(user.get_id(),user)
                            .enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if(response.isSuccessful()){
                                        Log.d("YYYYY","User Updated");
                                    }
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {

                                }
                            });
                }


//                Intent intent = new Intent();
//                intent.putExtra("user",user);
//                setResult(RESULT_OK,intent);
//                finish();

//                Bundle bundle = new Bundle();
//                bundle.putSerializable("user",user);
//                intent.putExtras(bundle);
            }
        });
    }
}
