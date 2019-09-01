package com.tfg.bidas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;

public class NutritionTipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_tips);
        ImageView btnImagen=(ImageView)findViewById(R.id.piramideAlimenticia);
        Button btnColesterol=(Button)findViewById(R.id.dietaColesterol);
        Button btnHipertension=(Button)findViewById(R.id.dietaHipertension);
        Button btnDiabetes=(Button)findViewById(R.id.dietaDiabetes);

        btnImagen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(NutritionTipsActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.piramide_alimenticia);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
        btnColesterol.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(NutritionTipsActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.colesterol);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
        btnHipertension.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(NutritionTipsActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.colesterol);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
        btnDiabetes.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(NutritionTipsActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.piramide_alimenticia);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

    }
}
