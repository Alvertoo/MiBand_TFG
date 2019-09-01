package com.tfg.bidas;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;

import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BeforeDaysFragment extends Fragment {

    private final int days = 7;
    private TextView date;
    private View view;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_before_days,container,false);
        this.view = view;
        initGraph();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initGraph(){
        BarChart barChart;
        barChart = view.findViewById(R.id.lineChart);
        //Obtiene el dia de hoy
        LocalDate today = LocalDate.now();
        SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MM");

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
                setTextValues(e, h);
            }

            @Override
            public void onNothingSelected()
            {

            }
        });

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 3000f));
        entries.add(new BarEntry(1f, 13000f));
        entries.add(new BarEntry(2f, 6000f));
        entries.add(new BarEntry(3f, 5000f));
        entries.add(new BarEntry(4f, 1000f));
        entries.add(new BarEntry(5f, 14000f));
        entries.add(new BarEntry(6f, 6000f));



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

        //Añade la línea de objetivo
        int maxCapacity = 12000;
        LimitLine ll = new LimitLine(maxCapacity, "Objetivo Diario");
        ll.setLineColor(Color.BLACK);
        barChart.getAxisLeft().addLimitLine(ll);
        ll.setLineWidth(4f);
        ll.setTextSize(14f);

        //Ajusta el tamaño de los labels
        barChart.getAxisLeft().setTextSize(14f);

        // Añade los dias de la semana
        XAxis xAxis = barChart.getXAxis();
        xAxis.setTextSize(11.0f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(weekdays));
        // Oculta las lineas del fondo
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        // Elimina las descripciones
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        // Oculta los labels
        barChart.getAxisRight().setDrawLabels(false);
        // Evita poder hacer zoom
        barChart.setScaleEnabled(false);
        // Actualiza el grafico
        barChart.invalidate();


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setTextValues(Entry e, Highlight h){
        int pos = days - ( (int) e.getX() +1);

        LocalDate today = LocalDate.now();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date day = Date.from(today.minusDays(pos).atStartOfDay(ZoneId.systemDefault()).toInstant());


        date = view.findViewById(R.id.dateValue);
        date.setText(df.format(day));
    }


}
