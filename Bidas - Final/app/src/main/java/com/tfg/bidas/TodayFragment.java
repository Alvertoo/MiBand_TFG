package com.tfg.bidas;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.EdgeDetail;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.charts.SeriesLabel;
import com.hookedonplay.decoviewlib.events.DecoEvent;

public class TodayFragment extends Fragment {

    private TextView numSteps;
    private int pasos = 10058;
    private int objetivo = 12000;

    private boolean animation;

    public TodayFragment(boolean b) {
        animation = b;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today,container,false);
        initGraph(view);
        return view;
    }


    // Prepara el gráfico circular
    public void initGraph(View view){
        final DecoView arcView = (DecoView)view.findViewById(R.id.dynamicArcView);
        final TextView tvPorciento = (TextView) view.findViewById(R.id.tv_porciento);
        final TextView objetivoPasos = (TextView) view.findViewById(R.id.objetivoPasos);

        objetivoPasos.setText(Integer.toString(objetivo));
        float endPosition = 0;
        if(!animation){
            endPosition = ((float)pasos/(float)objetivo)*100;
        }

        int series1Index = 0;

        final SeriesItem seriesItem1 = new SeriesItem.Builder(R.color.colorSecondary)
                .setRange(0, 100, endPosition)
                .setInitialVisibility(false)
                .setLineWidth(75f)
                .addEdgeDetail(new EdgeDetail(EdgeDetail.EdgeType.EDGE_OUTER, Color.parseColor("#22000000"), 0.4f))
                .setSeriesLabel(new SeriesLabel.Builder("Conseguido: %.0f%%").build())
                .setInterpolator(new DecelerateInterpolator())
                .setShowPointWhenEmpty(true)
                .setShadowSize(20)
                .setShadowColor(Color.DKGRAY)
                .setCapRounded(true)
                .setInset(new PointF(20f, 20f))
                .setDrawAsPoint(false)
                .setSpinClockwise(true)
                //.setSpinDuration(6000)
                .setChartStyle(SeriesItem.ChartStyle.STYLE_DONUT)
                .build();

        series1Index = arcView.addSeries(seriesItem1);

        int delay = 0;
        int duration = 0;
        if(animation) {
            delay = 1000;
            duration = 2000;
            endPosition=((float)pasos/(float)objetivo)*100;
        }
        arcView.addEvent(
                new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
                        .setDelay(delay)
                        .setDuration(duration)
                        .build()
        );

        //Marca la posición dependiento del porcentaje
        arcView.addEvent(new DecoEvent.Builder(endPosition).setIndex(series1Index).setDelay(delay).build());
        numSteps = view.findViewById(R.id.numSteps);
        numSteps.setText("Pasos");

        seriesItem1.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                //obtenemos el porcentaje a mostrar
                int percentFilled = (int) (pasos*percentComplete);
                //se lo pasamos al TextView
                tvPorciento.setText( Integer.toString(percentFilled));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });
    }


}
