package com.marcuspereira.calculadoradegorjeta

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.marcuspereira.calculadoradegorjeta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnClean.setOnClickListener {
            clean()
        }

        binding.btnCalculate.setOnClickListener {
            val valueAccountTemp = binding.tieTotalAccount.text.toString()
            val numPeopleTemp = binding.tieNumPeople.text.toString()
            val percentageTemp = binding.tiePercentage.text.toString()

            if (valueAccountTemp == "" || numPeopleTemp == "" || percentageTemp == "") {
                Snackbar.make(
                    binding.tieTotalAccount,
                    "Preencha todos os campos",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                val totalTip =
                    valueAccountTemp.toFloat() * (percentageTemp.toInt() / 100) / numPeopleTemp.toInt()
                val totalAccountWithTip =
                    (valueAccountTemp.toFloat() / numPeopleTemp.toInt()) + totalTip

                val intent = Intent(this, ResultActivity::class.java)
                intent.apply {
                    intent.putExtra("totalWithTips", totalAccountWithTip)
                    intent.putExtra("valueAccount", valueAccountTemp.toFloat())
                    intent.putExtra("numPeople", numPeopleTemp.toInt())
                    intent.putExtra("percentage", percentageTemp.toInt())
                }
                clean()
                startActivity(intent)
            }

        }
    }

    private fun clean() {
        binding.tieTotalAccount.setText("")
        binding.tieNumPeople.setText("")
        binding.tiePercentage.setText("")
    }

}