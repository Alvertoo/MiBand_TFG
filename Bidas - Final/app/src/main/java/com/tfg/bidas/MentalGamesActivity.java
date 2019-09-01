package com.tfg.bidas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MentalGamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mental_games);

    }

    public void openLumosity(View view){
        goToUrl ( "https://play.google.com/store/apps/details?id=com.lumoslabs.lumosity&hl=es_419");
    }
    public void openSkillz(View view){
        goToUrl ( "https://play.google.com/store/apps/details?id=net.rention.mind.skillz&hl=es");
    }
    public void openCandyCrush(View view){
        goToUrl ( "https://play.google.com/store/apps/details?id=com.king.candycrushsaga&hl=es");
    }
    public void openLeftVsRight(View view){
        goToUrl ( "https://play.google.com/store/apps/details?id=com.mochibits.google.leftvsright&hl=es");
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}
