package com.ibcs.tutrcyclerview.api;

import com.ibcs.tutrcyclerview.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceHolderService {

    @GET("/posts")
    Call<List<Post>> getAllPost();
}
