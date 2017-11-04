package com.example.summy.controlrendimiento.Triathlon;


import com.example.summy.controlrendimiento.model.Competencia;
import com.example.summy.controlrendimiento.model.CompetenciasRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface TriathlonDatabaseService {
/*
    @GET("authentication/token/new")
    Call<AuthResponse> newToken(@Query("api_key") String apiKey);

    @GET("authentication/session/new")
    Call<SessionResponse> newSession(@Query("api_key") String apiKey, @Query("request_token") String token);

    @GET("authentication/token/validate_with_login")
    Call<AuthResponse> validateLogIn(@Query("api_key") String apiKey, @Query("username") String username, @Query("password") String password, @Query("request_token") String token);

    @POST("movie/{movie_id}/rating")
    Call<AuthResponse> calificarPelicula(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("session_id") String sessionId);

    @GET("movie/{id}")
    Call<Competencia> obtenerDetallePelicula(@Path("id") int id, @Query("api_key") String apiKey);
*/
    @GET("events")
    Call<CompetenciasRespuesta> obtenerListaCompetencias (@Query("api_key") String apiKey);

}
