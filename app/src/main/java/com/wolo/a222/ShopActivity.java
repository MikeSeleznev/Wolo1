package com.wolo.a222;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class ShopActivity extends AppCompatActivity {

    ImageButton closeMenuImageButton;
    Button buttonShop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);

        closeMenuImageButton = (ImageButton) findViewById(R.id.closeMenuImageButtonShopActivity);
        closeMenuImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}