package com.allanfelipe.alconougasolina

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var textInputAlcool: TextInputLayout
    private lateinit var editAlcool: TextInputEditText

    private lateinit var textInputGasolina: TextInputLayout
    private lateinit var editGasolina: TextInputEditText

    private lateinit var btnCalcular: Button
    private lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializarComponenteDeInterface()
        btnCalcular.setOnClickListener {
            calcularMelhorPreco()
        }
    }

    private fun calcularMelhorPreco() {
        val precoAlcool = editAlcool.text.toString()
        val precoGasolina = editGasolina.text.toString()

        val resultadoValidacao = validarCampos(precoAlcool, precoGasolina)

        // se (valorAlcool / valorGasolina) >= 0.7 (70%) é melhor ultilizar gasolina.
        // se não é melhor ultilizar alcool.
        if(resultadoValidacao as Boolean){
            val resultado = precoAlcool.toDouble() / precoGasolina.toDouble()
            if(resultado >= 0.7){
                textResultado.text = "Melhor ultilizar Gasolina!"
            }else{
                textResultado.text = "Melhor ultlizar Álcool."
            }
        }
    }

    private fun validarCampos(pAlcool: String, pGasolina: String): Any {

        textInputAlcool.error = null
        textInputGasolina.error = null

        if (pAlcool.isEmpty()) {
            textInputAlcool.error = "Digite o preço do álcool."
            return false
        } else if (pGasolina.isEmpty()) {
            textInputGasolina.error = "Digite o preço da Gasolina."
            return false
        }
        return true
    }

    private fun inicializarComponenteDeInterface() {
        textInputAlcool = findViewById(R.id.text_input_alcool)
        editAlcool = findViewById(R.id.edit_alcool)

        textInputGasolina = findViewById(R.id.text_input_gasolina)
        editGasolina = findViewById(R.id.edit_gasolina)

        btnCalcular = findViewById(R.id.btn_calcular)
        textResultado = findViewById(R.id.text_resultado)

    }
}