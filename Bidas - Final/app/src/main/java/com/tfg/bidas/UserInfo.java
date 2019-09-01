package com.tfg.bidas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.*;
import java.util.Calendar;

public class UserInfo extends AppCompatActivity {

    // Cantidad de valores que habrán
    private final static int numberSteps = 10;
    private final static int numberHeights = 56;
    private final static int numberWeights = 66;

    // Valor más bajo
    private final static int initSteps = 2000;
    private final static int initHeights = 145;
    private final static int initWeights = 50;

    // Valor por defecto
    private int initialValueStep = 6000;
    private int initialValueHeight = 170;
    private int initialValueWeight = 70;

    // Unidades de medida
    private final static String measurementHeight = " cm.";
    private final static String measurementWeight = " kg.";
    private final static String measurementStep = " pasos ";

    //Valores para el calendario
    private static final String CERO = "0";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    EditText ageText ;


    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        loadSpinners();
        btn = findViewById(R.id.buttonSaveInfo);
        ageText = (EditText) findViewById(R.id.ageText);
        ageText.setText("01/01/1965");
        ageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerFecha();
            }
        });

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
        Spinner stepSpinner = (Spinner) findViewById(R.id.stepSpinner);
        Spinner heightSpinner = (Spinner) findViewById(R.id.heightSpinner);
        Spinner weightSpinner = (Spinner) findViewById(R.id.weightSpinner);
        String[] steps = new String[numberSteps];
        String[] heights = new String[numberHeights];
        String[] weights = new String[numberWeights];
        int a = initSteps;
        int h = initHeights;
        int w = initWeights;
        String recomended = " (recomendado)";
        for(int i = 0; i<Math.max(numberSteps,Math.max(numberHeights,numberWeights)); i++){
            if(i<numberSteps) {
                if(a == initialValueStep){
                    steps[i] = Integer.toString(a)+ measurementStep + recomended;
                }else {
                    steps[i] = Integer.toString(a) + measurementStep;
                }
                a+= 2000;
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
        stepSpinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,steps));
        heightSpinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,heights));
        weightSpinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,weights));

        stepSpinner.setSelection((initialValueStep/initSteps)-1);
        heightSpinner.setSelection(initialValueHeight - initHeights);
        weightSpinner.setSelection(initialValueWeight - initWeights);
    }


    private void obtenerFecha(){

        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                ageText.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual

        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }


    // Guarda la información del usuario
    private boolean saveInfo(){
        return true; // Pendiente
    }

}
