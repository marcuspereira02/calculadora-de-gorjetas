package com.marcuspereira.calculadoradegorjeta

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.NumberFormat
import java.util.Locale

const val KEY_RESULT_ACTIVITY = "ResultActivity.Key"

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val contaFinal = intent.getFloatExtra(KEY_RESULT_ACTIVITY, 0f)
        val inputValor = intent.getFloatExtra("VALOR_CONTA", 0f)
        val inputPessoas = intent.getIntExtra("QTD_PESSOAS", 0)
        val inputPorcentagem = intent.getFloatExtra("PORCENTAGEM", 0f)

        val tv_ContaValor = findViewById<TextView>(R.id.tv_ResConta)
        val tv_QtdPessoas = findViewById<TextView>(R.id.tv_ResPessoas)
        val tv_Porcentagem = findViewById<TextView>(R.id.tv_ResPorcentagem)
        val tv_Resultado = findViewById<TextView>(R.id.tv_ResTotal)
        val btnNovoCalculo = findViewById<Button>(R.id.btn_NovoCalculo)

        val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

        val valorMesaFormatado = formatador.format(inputValor)
        val valorTotalFormatado = formatador.format(contaFinal)

        tv_ContaValor.text = valorMesaFormatado
        tv_QtdPessoas.text = inputPessoas.toString()
        tv_Porcentagem.text = "$inputPorcentagem %"
        tv_Resultado.text = valorTotalFormatado

        btnNovoCalculo.setOnClickListener {
           val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(KEY_MAIN_ACTIVITY, "")
            startActivity(intent)
        }

    }
}