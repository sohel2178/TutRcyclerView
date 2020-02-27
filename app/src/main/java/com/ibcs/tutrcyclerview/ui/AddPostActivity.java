package com.ibcs.tutrcyclerview.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ibcs.tutrcyclerview.R;
import com.ibcs.tutrcyclerview.api.PlaceHolderService;
import com.ibcs.tutrcyclerview.api.ServiceGenerator;
import com.ibcs.tutrcyclerview.models.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPostActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etTitle,etBody;
    private Button btnSavePost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        initView();
    }

    private void initView(){
        etTitle = findViewById(R.id.title);
        etBody = findViewById(R.id.body);
        btnSavePost = findViewById(R.id.save_post);
        btnSavePost.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String title = etTitle.getText().toString().trim();
        String body = etBody.getText().toString().trim();

        if(title.equals("") || body.equals("")){
            return;
        }

        Post post = new Post();
        post.setTitle(title);
        post.setBody(body);

        PlaceHolderService placeHolderService = ServiceGenerator.createService(PlaceHolderService.class);

        placeHolderService.savePost(post)
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if(response.isSuccessful()){
                            Post p =response.body();

                            Log.d("YYYYY",p.getId()+"");
                            Log.d("YYYYY",p.getUserId()+"");
                            Log.d("YYYYY",p.getTitle()+"");
                            Log.d("YYYYY",p.getBody()+"");
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.d("YYYYY",t.getMessage()+"");
                    }
                });
    }
}
