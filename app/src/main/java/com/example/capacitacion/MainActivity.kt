package com.example.capacitacion

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.capacitacion.models.Person
import com.example.capacitacion.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textView)
        val editTextId: EditText = findViewById(R.id.editTextId)
        val buttonGet: Button = findViewById(R.id.buttonGet)

        buttonGet.setOnClickListener {
            val idText = editTextId.text.toString()
            val id = idText.toIntOrNull()  // Convertimos el texto a un número de forma segura

            if (id != null) {
                // Si el ID es válido, llamamos a la API
                getPersonById(id, textView)
            } else {
                // Si el ID no es válido, mostramos un mensaje de error
                textView.text = "Por favor, ingrese un ID válido."
            }
        }
    }

    private fun getPersonById(id: Int, textView: TextView) {
        val service = RetrofitInstance.api
        val personCall = service.getPerson(id)

        personCall.enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if (response.isSuccessful) {
                    val person = response.body()
                    textView.text = "Nombre: ${person?.name}\nAltura: ${person?.height}\nGénero: ${person?.gender}"
                } else {
                    textView.text = "No se encontró el personaje."
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                textView.text = "Error al obtener los datos: ${t.message}"
            }
        })
    }

}
