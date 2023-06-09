package com.suhun.guess0317

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.suhun.guess0317.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var tag = MainActivity::class.java.simpleName
    var secretNumber:SecretNumber = SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle("Replay Game")
                .setMessage("Are you sure?")
                .setPositiveButton("ok", {dialog, which->
                    secretNumber.resetAll()
                    binding.contentLayout.userInput.text = null
                    binding.contentLayout.count.text = "0"
                })
                .setNeutralButton("Cancel", null)
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun checkGuess(view: View){
        var userInput:Int = binding.contentLayout.userInput.text.toString().toInt()
        var resutMessage:String = secretNumber.verifyResult(resources, userInput)
        var bingo:Boolean = if(secretNumber.verify(userInput)==0) true else false

       binding.contentLayout.count.text = "${secretNumber.guessCount} times"
        AlertDialog.Builder(this)
            .setTitle("Guess Result")
            .setMessage(resutMessage).setPositiveButton("ok", {dialog, which->

                if(bingo){
                    val intent:Intent = Intent(this, RecordActivity::class.java)
                    intent.putExtra("COUNT", secretNumber.guessCount)
                    intent.putExtra("NAME", binding.contentLayout.nameInput.text.toString())
                    startActivity(intent)
                }else{
                    binding.contentLayout.userInput.text = null
                }
            })
            .show()
    }
}