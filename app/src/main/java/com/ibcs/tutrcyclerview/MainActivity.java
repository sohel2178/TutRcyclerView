package com.ibcs.tutrcyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ibcs.tutrcyclerview.api.ApiClient;
import com.ibcs.tutrcyclerview.api.GitHubService;
import com.ibcs.tutrcyclerview.api.PlaceHolderService;
import com.ibcs.tutrcyclerview.api.ServiceGenerator;
import com.ibcs.tutrcyclerview.models.Post;
import com.ibcs.tutrcyclerview.models.Repo;
import com.ibcs.tutrcyclerview.models.User;
import com.ibcs.tutrcyclerview.ui.AddPostActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements UserClickHandler, View.OnClickListener {

    private static final int REQUEST_CODE=50000;

    private RecyclerView recyclerView;
    private Button btnAddPost;

    private UserAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        adapter = new UserAdapter(this,this);

        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        btnAddPost = findViewById(R.id.add_post);

        btnAddPost.setOnClickListener(this);

        ApiClient client = ServiceGenerator.createService(ApiClient.class);

        client.getAllUsers()
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if(response.isSuccessful()){
                            List<User> userList = response.body();

                            for(User user: userList){
                                adapter.addUser(user);
                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {

                    }
                });

//        PlaceHolderService placeHolderService = ServiceGenerator.createService(PlaceHolderService.class);
//
//        client.getAllPost()
//                .enqueue(new Callback<List<Post>>() {
//                    @Override
//                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                        if(response.isSuccessful()){
//                            List<Post> postList = response.body();
//
//                            for (Post x: postList){
//                                adapter.addPost(x);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Post>> call, Throwable t) {
//
//                    }
//                });

//        btnAddUser = findViewById(R.id.add_user);
//        btnAddUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),AddUserActivity.class);
//                startActivityForResult(intent,REQUEST_CODE);
//
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK){
            User user = (User) data.getSerializableExtra("user");

//            adapter.addUser(user);

            //Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(User user) {
        Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(final User user) {

        ApiClient client = ServiceGenerator.createService(ApiClient.class);

        client.deleteUser(user.get_id())
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            Log.d("YYYYY","User Deleted");

                            adapter.removeUser(user);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("YYYYY","Error "+t.getMessage());
                    }
                });

        /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do You Want to Delete This User??")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
//                        adapter.removeUser(position);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

//        adapter.removeUser(position);*/
    }

    @Override
    public void onEditClick(User user) {
        Intent intent = new Intent(this, AddUserActivity.class);
        intent.putExtra("USER",user);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, AddUserActivity.class);
        startActivity(intent);
    }
}
