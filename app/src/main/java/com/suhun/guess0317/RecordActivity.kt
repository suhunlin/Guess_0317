package com.suhun.guess0317

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.suhun.guess0317.databinding.ActivityRecordBinding

class RecordActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var playerName:String? = intent.getStringExtra("NAME")
        var scoreCount:Int = intent.getIntExtra("COUNT", -1)
        binding.gameResult.text = "$playerName \t $scoreCount"
    }

    fun goBack(view: View){
        val intent:Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}