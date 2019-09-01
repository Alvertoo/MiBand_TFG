package com.tfg.bidas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    private final int duracion = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        int counter=0;
        try {
            Thread.sleep(3000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        counter+=100;

        selectInitPage();
    }

    // Elige la página de inicio dependiendo si es la primera vez que entra
    private void selectInitPage(){
        Class page;
        if (isRegistered()){
            page = MainActivity.class;
        }else{
            page = InitPage.class;
        }
        Intent intent = new Intent(SplashScreen.this,page);
        startActivity(intent);
        finish();
    }

    // Comprueba si ya tiene una smartband vinculada
    private boolean isRegistered(){
        return false; // Pendiente
    }
}
