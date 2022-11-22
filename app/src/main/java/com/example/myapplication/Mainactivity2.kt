package com.example.tictactoe

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView

class GameActivity : AppCompatActivity() {
    private lateinit var playerScoreView:TextView
    private lateinit var player2ScoreView: TextView
    private lateinit var box1x1:TextView
    private lateinit var box1x2:TextView
    private lateinit var box1x3:TextView
    private lateinit var box2x1:TextView
    private lateinit var box2x2:TextView
    private lateinit var box2x3:TextView
    private lateinit var box3x1:TextView
    private lateinit var box3x2:TextView
    private lateinit var box3x3:TextView
    private lateinit var resetBtn:Button
    private lateinit var drawBtn:Button
    private lateinit var row1: View
    private lateinit var row2: View
    private lateinit var row3: View
    private lateinit var col1: View
    private lateinit var col2: View
    private lateinit var col3: View
    private lateinit var cross1: View
    private lateinit var cross2: View
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val bundle: Bundle? = intent.extras
        val name1 = bundle?.get("name1")
        val name2 = bundle?.get("name2")
        var toggleChar:String = "X"
        var switcher:Int = 0
        var winner:String = ""
        var playerPts:Int = 0
        var player2Pts:Int = 0
        playerScoreView = findViewById(R.id.playerScore)
        player2ScoreView = findViewById(R.id.player2Score)
        box1x1 = findViewById(R.id.textView)
        box1x2 = findViewById(R.id.textView2)
        box1x3 = findViewById(R.id.textView3)
        box2x1 = findViewById(R.id.textView4)
        box2x2 = findViewById(R.id.textView5)
        box2x3 = findViewById(R.id.textView6)
        box3x1 = findViewById(R.id.textView7)
        box3x2 = findViewById(R.id.textView8)
        box3x3 = findViewById(R.id.textView9)
        resetBtn = findViewById(R.id.resetBtn)
        drawBtn = findViewById(R.id.drawBtn)
        row1 = findViewById(R.id.row1)
        row2 = findViewById(R.id.row2)
        row3 = findViewById(R.id.row3)
        col1 = findViewById(R.id.col1)
        col2 = findViewById(R.id.col2)
        col3 = findViewById(R.id.col3)
        cross1 = findViewById(R.id.cross1)
        cross2 = findViewById(R.id.cross2)
        fun setPoints(){
            playerScoreView.text = "$name1: $playerPts"
            player2ScoreView.text = "$name2: $player2Pts"
        }
        fun checkWin():Boolean{
            var res:Boolean = false
            if(box1x1.text.toString() == box1x2.text.toString() && box1x2.text.toString() == box1x3.text.toString() && box1x3.text.toString() != ""){
                row1.visibility = View.VISIBLE
                winner = box1x1.text.toString()
                res = true
            }
            if(box2x1.text.toString() == box2x2.text.toString() && box2x2.text.toString() == box2x3.text.toString() && box2x3.text.toString() != ""){
                row2.visibility = View.VISIBLE
                winner = box2x1.text.toString()
                res = true
            }
            if(box3x1.text.toString() == box3x2.text.toString() && box3x2.text.toString() == box3x3.text.toString() && box3x3.text.toString() != ""){
                row3.visibility = View.VISIBLE
                winner = box3x1.text.toString()
                res = true
            }
            if(box1x1.text.toString() == box2x1.text.toString() && box2x1.text.toString() == box3x1.text.toString() && box3x1.text.toString() != ""){
                col1.visibility = View.VISIBLE
                winner = box1x1.text.toString()
                res = true
            }
            if(box1x2.text.toString() == box2x2.text.toString() && box2x2.text.toString() == box3x2.text.toString() && box3x2.text.toString() != ""){
                col2.visibility = View.VISIBLE
                winner = box1x2.text.toString()
                res = true
            }
            if(box1x3.text.toString() == box2x3.text.toString() && box2x3.text.toString() == box3x3.text.toString() && box3x3.text.toString() != ""){
                col3.visibility = View.VISIBLE
                winner = box1x3.text.toString()
                res = true
            }
            if(box1x1.text.toString() == box2x2.text.toString() && box2x2.text.toString() == box3x3.text.toString() && box3x3.text.toString() != ""){
                cross1.visibility = View.VISIBLE
                winner = box1x1.text.toString()
                res = true
            }
            if(box1x3.text.toString() == box2x2.text.toString() && box2x2.text.toString() == box3x1.text.toString() && box3x1.text.toString() != ""){
                cross2.visibility = View.VISIBLE
                winner = box1x3.text.toString()
                res = true
            }
            return res
        }
        var boxArr = arrayOf(box1x1, box1x2, box1x3, box2x1, box2x2, box2x3, box3x1, box3x2, box3x3)
        var linesArr = arrayOf(row1, row2, row3, col1, col2, col3, cross1, cross2)
        fun isEmpty():Boolean{
            var emptyStatus:Boolean=false
            for (i in 0 until boxArr.size){
                if(boxArr[i].text.toString()==""){
                    emptyStatus=true
                    break
                }
            }
            return emptyStatus
        }
        fun clearBoard(){
            for(i in 0 until boxArr.size){
                boxArr[i].text = ""
            }
            switcher = 0
        }
        drawBtn.setOnClickListener {
            clearBoard()
        }
        resetBtn.setOnClickListener {
            playerPts = 0
            player2Pts = 0
            setPoints()
            clearBoard()
        }
        fun clearLines(){
            for (i in 0 until linesArr.size){
                linesArr[i].visibility = View.GONE
            }
        }
        for (i in 0 until boxArr.size){
            boxArr[i].setOnClickListener{
                if(!checkWin()){
                    if(boxArr[i].text.toString()==""){
                        toggleChar = "X"
                        if (switcher%2==1){
                            toggleChar="O"
                        }
                        boxArr[i].text = toggleChar
                        switcher++
                    }

                    if (checkWin()){
                        checkWin()
                        if(winner=="X"){
                            playerPts++
                        }else{
                            player2Pts++
                        }
                        Handler(Looper.getMainLooper()).postDelayed(
                            {
                                clearBoard()
                                clearLines()
                                setPoints()
                            },
                            800
                        )

                    }else if(!isEmpty()){
                        Handler(Looper.getMainLooper()).postDelayed(
                            {
                                clearBoard()
                            },
                            800
                        )
                    }

                }
            }
        }
        setPoints()
    }
}