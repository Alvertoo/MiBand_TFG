package com.tfg.bidas;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

public class BeforeDaysFragment extends Fragment {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_before_days,container,false);
        initGraph(view);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initGraph(View view){
        BarChart barChart;
        barChart = view.findViewById(R.id.lineChart);
        //Obtiene el dia de hoy
        LocalDate today = LocalDate.now();
        SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MM");
        int days = 7;
        final String[] weekdays = new String[7];
        for (int i=0;i<days;i++){
            Date day = Date.from(today.minusDays(i).atStartOfDay(ZoneId.systemDefault()).toInstant());
            weekdays[days-(1+i)] = df.format(day);
        }

        // Activa cuando se pulsa un día
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener()
        {
            @Override
            public void onValueSelected(Entry e, Highlight h)
            {
                float x=e.getX();
                float y=e.getY();
            }

            @Override
            public void onNothingSelected()
            {

            }
        });

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 30f));
        entries.add(new BarEntry(1f, 80f));
        entries.add(new BarEntry(2f, 60f));
        entries.add(new BarEntry(3f, 50f));
        entries.add(new BarEntry(4f, 10f));
        entries.add(new BarEntry(5f, 70f));
        entries.add(new BarEntry(6f, 60f));

        BarDataSet set = new BarDataSet(entries, "BarDataSet");
        //Colorea el gráfico
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(ContextCompat.getColor(view.getContext(), R.color.mon));
        colors.add(ContextCompat.getColor(view.getContext(), R.color.tue));
        colors.add(ContextCompat.getColor(view.getContext(), R.color.wed));
        colors.add(ContextCompat.getColor(view.getContext(), R.color.thu));
        colors.add(ContextCompat.getColor(view.getContext(), R.color.fri));
        colors.add(ContextCompat.getColor(view.getContext(), R.color.sat));
        colors.add(ContextCompat.getColor(view.getContext(), R.color.sun));
        set.setColors(colors);
        set.setDrawValues(false); // Oculta los valores sobre las barras
        BarData data = new BarData(set);
        // Anchura de la barra
        data.setBarWidth(0.9f);
        // Establece los valores de la gráfica
        barChart.setData(data);

        // Añade los dias de la semana
        XAxis xAxis = barChart.getXAxis();
        xAxis.setTextSize(11.0f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(weekdays));
        // Oculta las lineas del fondo
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);
        // Elimina las descripciones
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        // Oculta los labels
        barChart.getAxisLeft().setDrawLabels(false);
        barChart.getAxisRight().setDrawLabels(false);
        // Evita poder hacer zoom
        barChart.setScaleEnabled(false);
        // Actualiza el grafico
        barChart.invalidate();

    }


}
