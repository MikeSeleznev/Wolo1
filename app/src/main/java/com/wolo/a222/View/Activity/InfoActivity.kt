package com.wolo.a222.View.Activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.wolo.a222.R


class InfoActivity : AppCompatActivity() {
    private lateinit var buttonOK: Button
    private lateinit var closeMenuImageButton: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info)


        buttonOK = findViewById(R.id.buttonOK)
        buttonOK.setOnClickListener { finish() }

        closeMenuImageButton = findViewById<View>(R.id.closeMenuImageButtonInfoActivity) as ImageButton
        closeMenuImageButton.setOnClickListener { finish() }

    }

}
