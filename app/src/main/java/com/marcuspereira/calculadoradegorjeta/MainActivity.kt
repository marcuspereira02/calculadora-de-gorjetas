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
            val totalTableTemp = binding.tieTotalAccount.text
            val nPeopleTemp = binding.tieNumPeople.text
            val percentageTemp = binding.tiePercentage.text

            if (totalTableTemp?.isEmpty() == true || nPeopleTemp?.isEmpty() == true || percentageTemp?.isEmpty() == true) {
                Snackbar.make(
                    binding.tieTotalAccount, "Preencha todos os campos", Snackbar.LENGTH_LONG
                ).show()
            } else {

                val totalTable = totalTableTemp.toString().toFloat()
                val nPeople = nPeopleTemp.toString().toInt()
                val percentage = percentageTemp.toString().toInt()

                val forPeople = totalTable / nPeople
                val tips = forPeople * percentage / 100
                val totalWithTips = forPeople + tips

                val intent = Intent(this, ResultActivity::class.java)
                intent.apply {
                    intent.putExtra("totalWithTips", totalWithTips)
                    intent.putExtra("valueAccount", totalTable)
                    intent.putExtra("numPeople", nPeople)
                    intent.putExtra("percentage", percentage)
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