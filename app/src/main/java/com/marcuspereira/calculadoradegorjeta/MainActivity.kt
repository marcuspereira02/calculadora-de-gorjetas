package com.marcuspereira.calculadoradegorjeta

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat
import java.util.Locale

const val KEY_MAIN_ACTIVITY = "MainActivity.Key"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val valorConta = findViewById<TextInputEditText>(R.id.ti_ValorConta)
        val qtdPessoas = findViewById<TextInputEditText>(R.id.ti_QtdPessoas)
        val porcentagem = findViewById<TextInputEditText>(R.id.ti_Porcentagem)
        val btnLimpar = findViewById<Button>(R.id.btn_Limpar)
        val btnCalcular = findViewById<Button>(R.id.btn_Calcular)

        btnLimpar.setOnClickListener {
            valorConta.setText("")
            qtdPessoas.setText("")
            porcentagem.setText("")
        }

        btnCalcular.setOnClickListener {
            val valorContaF = valorConta.text.toString()
            val qtdPessoasF = qtdPessoas.text.toString()
            val porcentagemF = porcentagem.text.toString()

            if (valorContaF == "" || qtdPessoasF == "" || porcentagemF == "") {
                Snackbar.make(valorConta, "Preencha todos os campos", Snackbar.LENGTH_LONG).show()
            } else {
                val totalGorjeta =
                    valorContaF.toFloat() * (porcentagemF.toFloat() / 100) / qtdPessoasF.toFloat()
                val valorTotal = (valorContaF.toFloat() / qtdPessoasF.toFloat()) + totalGorjeta

                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra(KEY_RESULT_ACTIVITY, valorTotal)
                intent.putExtra("VALOR_CONTA", valorContaF.toFloat())
                intent.putExtra("QTD_PESSOAS", qtdPessoasF.toInt())
                intent.putExtra("PORCENTAGEM", porcentagemF.toFloat())
                startActivity(intent)
            }

        }
    }
}