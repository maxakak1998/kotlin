package com.example.kiet_kotlin

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.coroutines.*
import java.lang.Runnable

class GameScreen : AppCompatActivity() {
    lateinit var switchMode: Switch
    var isATurn:Boolean=true;
    var isLoading:Boolean=false;
    var round:Int=0;
    lateinit var  toolBar:Toolbar;
    lateinit var txtScoreA: TextView;
    lateinit var  txtScoreB:TextView;
    lateinit var  txtCurrentTurn:TextView;
    lateinit var imgvDice:ImageView;
    lateinit var btnPlayGame:Button;
    lateinit var dice:Dice;
    lateinit var txtScoreStateA:TextView;
    lateinit var txtScoreStateB:TextView;
    var limit:Int=5;

    lateinit var txtRound:TextView;
    val dices= intArrayOf(
        R.drawable.dice1,
        R.drawable.dice2,
        R.drawable.dice3,
        R.drawable.dice4,
        R.drawable.dice5,
        R.drawable.dice6
    );
    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity);
        dice =  Dice(1000);
        mapId();
        configActionBar();
        configDiceAction();
        configSwitch()
        txtRound.text="Round 1";

    }

    private fun configDiceAction() {
            imgvDice.setOnClickListener{
                if(isLoading) return@setOnClickListener;

            val runnable:Runnable = object:Runnable{
                override fun run() {
                    isLoading=true;
                    imgvDice.setImageResource(dices[(0..5).random()]);
                    handler.postDelayed(this, 200)
                }

            }

                 handler.postDelayed(runnable, 0);

            GlobalScope.launch {
                withContext(Dispatchers.Default){
                     val side=async{
                        dice.roll();
                    }.await();



                    MainScope().launch {
                        withContext(Dispatchers.Main){
                            handler.removeCallbacks(runnable);
                            imgvDice.setImageResource(dices[side-1])
                            if(isATurn){
                                txtScoreStateA.text="+$side";
                                txtScoreA.text=(txtScoreA.text.toString().toInt()+side).toString();
                                isATurn=false;
                                if(switchMode.text=="Difficulty" && round>limit){
                                    txtScoreB.text=(txtScoreB.text.toString().toInt()-side).toString();
                                    txtScoreStateB.text="-$side";

                                }
                                delay(500);
                                txtScoreStateA.text="";
                                txtScoreStateB.text="";
                                txtCurrentTurn.text="B";




                            }else{
                                txtScoreStateB.text="+$side";
                                txtScoreB.text=(txtScoreB.text.toString().toInt()+side).toString();
                                isATurn=true;
                                if(switchMode.text=="Difficulty"&& round>limit){
                                    txtScoreA.text=(txtScoreB.text.toString().toInt()-side).toString();
                                    txtScoreStateA.text="-$side";

                                }
                                delay(500);
                                txtScoreStateB.text="";
                                txtScoreStateA.text="";
                                txtCurrentTurn.text="A";


                            }
                            round++;
                            txtRound.text="Round $round";
                            isLoading=false;

                        }

                    }
                }




            }


        }

    }


    private fun configSwitch(){
        switchMode.visibility=View.VISIBLE;

        switchMode.setOnClickListener{
            println(switchMode.isSelected);
            switchMode.isSelected=!switchMode.isSelected;
            if(switchMode.isSelected){

                switchMode.text = "Difficulty";
                switchMode.setTextColor(Color.RED);
            }else{
                switchMode.text = "Easy";
                switchMode.setTextColor(Color.parseColor("#FF1100"));


            }
        }
    }
    private fun mapId(){
        toolBar= findViewById( R.id.oke1);
        txtScoreA=findViewById(R.id.txt_scoreA);
        txtScoreB=findViewById(R.id.txt_scoreB);
        txtCurrentTurn=findViewById(R.id.txt_currentTurn);
        imgvDice=findViewById(R.id.img_dice);
        btnPlayGame = findViewById( R.id.btn_playGame);
        txtScoreStateA=findViewById(R.id.txt_scoreStateA);
        txtScoreStateB=findViewById(R.id.txt_scoreStateB);
        switchMode=findViewById(R.id.switch_mode);
        txtRound=findViewById(R.id.txt_round);

    }

    private fun configActionBar(){

        setSupportActionBar(toolBar);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        supportActionBar?.setHomeButtonEnabled(true);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.title="Roll a dice"
        btnPlayGame.visibility= View.GONE;

    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->super.onBackPressed();
        }
        return true;
    }
}