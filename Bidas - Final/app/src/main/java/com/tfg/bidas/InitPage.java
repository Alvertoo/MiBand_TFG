package com.tfg.bidas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InitPage extends AppCompatActivity {
    Button btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_page);
        btn = findViewById(R.id.buttonSaveDevice);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(connectDevice()) {
                    startActivity(new Intent(InitPage.this, UserInfo.class));
                }else{

                }
            }
        });
    }

    // Conecta el dispositivo
    private boolean connectDevice(){
        return true; // Pendiente
    }



}
