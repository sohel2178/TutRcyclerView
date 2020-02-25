package com.ibcs.tutrcyclerview.api;

import com.ibcs.tutrcyclerview.models.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface GitHubService {

    @GET("/users/{username}/repos")
    Call<List<Repo>> getRepoList(@Path("username") String username);
}
