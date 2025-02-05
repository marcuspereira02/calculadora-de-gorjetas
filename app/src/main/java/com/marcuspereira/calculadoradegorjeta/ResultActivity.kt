package com.marcuspereira.calculadoradegorjeta

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.marcuspereira.calculadoradegorjeta.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val finalAccount = intent.getFloatExtra("totalWithTips", 0f)
        val totalTable = intent.getFloatExtra("valueAccount", 0f)
        val numPeople = intent.getIntExtra("numPeople", 0)
        val percentage = intent.getIntExtra("percentage", 0)

        binding.tvTotalTableSummary.text = numFormat(totalTable)
        binding.tvNumPeopleSummary.text = numPeople.toString()
        binding.tvPercentageSummary.text = "$percentage%"
        binding.tvAccountFinal.text = numFormat(finalAccount)

        binding.btnRefresh.setOnClickListener {
            finish()
        }
    }

    private fun numFormat(num:Float): String {
        val formatNum = String.format("%.2f", num)
        return formatNum
    }
}