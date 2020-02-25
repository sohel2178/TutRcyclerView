package com.ibcs.tutrcyclerview.api;

import com.ibcs.tutrcyclerview.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiClient {

    @GET("/api/users")
    Call<List<User>> getAllUsers();

    @GET("/api/users/{email}")
    Call<User> getUser(@Path("email") String email);
}
