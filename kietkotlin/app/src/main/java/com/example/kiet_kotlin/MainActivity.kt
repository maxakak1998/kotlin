package com.example.kiet_kotlin

import android.app.ActionBar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import androidx.appcompat.widget.Toolbar

import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*
import java.io.Console

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolBar= findViewById<Toolbar>( R.id.oke);
        setSupportActionBar(toolBar);


        val btnPlayGame = findViewById<Button>( R.id.btn_playGame);
            btnPlayGame.setOnClickListener {
                 val intent:Intent=Intent(this,GameScreen::class.java);
                startActivity(intent);
            }






    }


}