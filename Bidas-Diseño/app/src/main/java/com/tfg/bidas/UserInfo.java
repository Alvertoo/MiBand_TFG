package com.tfg.bidas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import java.lang.*;

public class UserInfo extends AppCompatActivity {

    // Cantidad de valores que habrán
    private final static int numberAges = 81;
    private final static int numberHeights = 56;
    private final static int numberWeights = 66;

    // Valor más bajo
    private final static int initAges = 15;
    private final static int initHeights = 145;
    private final static int initWeights = 50;

    // Valor por defecto
    private int initialValueAge = 50;
    private int initialValueHeight = 170;
    private int initialValueWeight = 70;

    // Unidades de medida
    private final static String measurementHeight = " cm.";
    private final static String measurementWeight = " kg.";

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        loadSpinners();
        btn = findViewById(R.id.buttonSaveInfo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saveInfo()) {
                    startActivity(new Intent(UserInfo.this, MainActivity.class));
                }else{

                }
            }
        });
    }

    private void loadSpinners(){
        Spinner ageSpinner = (Spinner) findViewById(R.id.ageSpinner);
        Spinner heightSpinner = (Spinner) findViewById(R.id.heightSpinner);
        Spinner weightSpinner = (Spinner) findViewById(R.id.weightSpinner);
        String[] ages = new String[numberAges];
        String[] heights = new String[numberHeights];
        String[] weights = new String[numberWeights];
        int a = initAges;
        int h = initHeights;
        int w = initWeights;
        for(int i = 0; i<Math.max(numberAges,Math.max(numberHeights,numberWeights)); i++){
            if(i<numberAges) {
                ages[i] = Integer.toString(a);
                a++;
            }
            if(i<numberHeights) {
                heights[i]=Integer.toString(h) + measurementHeight;
                h++;
            }
            if(i<numberWeights) {
                weights[i] = Integer.toString(w) + measurementWeight;
                w++;
            }
        }
        ageSpinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,ages));
        heightSpinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,heights));
        weightSpinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,weights));

        ageSpinner.setSelection(initialValueAge - initAges);
        heightSpinner.setSelection(initialValueHeight - initHeights);
        weightSpinner.setSelection(initialValueWeight - initWeights);
    }

    // Guarda la información del usuario
    private boolean saveInfo(){
        return true; // Pendiente
    }

}
