package com.example.tictactoe


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {
    private lateinit var playerNameInp:TextView
    private lateinit var player2NameInp:TextView
    private lateinit var playBtn:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerNameInp = findViewById(R.id.editTextTextPersonName)
        player2NameInp = findViewById(R.id.editTextTextPersonName2)
        playBtn = findViewById(R.id.button)

        playBtn.setOnClickListener {
            if (playerNameInp.text.toString().length>2 && player2NameInp.text.toString().length>2){
                intent = Intent(applicationContext, GameActivity::class.java)
                intent.putExtra("name1", playerNameInp.text.toString())
                intent.putExtra("name2", player2NameInp.text.toString())
                startActivity(intent)
            }
        }
    }
}