package com.example.capacitacion.network


import com.example.capacitacion.models.Person
import retrofit2.Call
import retrofit2.http.*

interface SwapiService {
    // Obtener un personaje por su ID (Read)
    @GET("people/{id}/")
    fun getPerson(@Path("id") id: Int): Call<Person>

    // Crear un nuevo personaje (Create)
    @POST("people/")
    fun createPerson(@Body person: Person): Call<Person>

    // Actualizar la información de un personaje (Update)
    @PUT("people/{id}/")
    fun updatePerson(@Path("id") id: Int, @Body person: Person): Call<Person>

    // Eliminar un personaje (Delete)
    @DELETE("people/{id}/")
    fun deletePerson(@Path("id") id: Int): Call<Void>
}

