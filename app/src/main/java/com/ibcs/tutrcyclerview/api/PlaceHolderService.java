package com.ibcs.tutrcyclerview.api;

import com.ibcs.tutrcyclerview.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PlaceHolderService {

    @GET("/posts")
    Call<List<Post>> getAllPost();

    @POST("/posts")
    Call<Post> savePost(@Body Post post);
}
