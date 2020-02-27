package com.ibcs.tutrcyclerview.api;

import com.ibcs.tutrcyclerview.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiClient {

    @GET("/api/users")
    Call<List<User>> getAllUsers();

    @POST("/api/users")
    Call<User> saveUser(@Body User user);

    @GET("/api/users/{email}")
    Call<User> getUser(@Path("email") String email);

    @PUT("/api/users/{userid}")
    Call<Void> updateUser(@Path("userid") String uid,@Body User user);

    @DELETE("/api/users/{uid}")
    Call<Void> deleteUser(@Path("uid") String uid);
}
