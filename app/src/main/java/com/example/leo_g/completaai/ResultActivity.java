package com.example.leo_g.completaai;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    ViewHolder viewHolder = new ViewHolder();
    DecimalFormat dfReal = new DecimalFormat("0.0000");
    DecimalFormat dfKm = new DecimalFormat("0.000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //busca valores da tela
        this.viewHolder.resultado = findViewById(R.id.textViewResultado);
        this.viewHolder.qtdKmGasolina = findViewById(R.id.textViewGasolinaKm1Real);
        this.viewHolder.qtdKmEtanol = findViewById(R.id.textViewEtanolKm1Real);
        this.viewHolder.economia = findViewById(R.id.textViewEconomia);

        //pega parametros da tela anterior
        Intent intent = getIntent();

        Double gasolinaKmPorReal = (Double) intent.getSerializableExtra("gasolinaKmPorReal");
        Double etanolKmPorReal = (Double) intent.getSerializableExtra("etanolKmPorReal");
        Double valor1KmGasolina = (Double) intent.getSerializableExtra("valor1KmGasolina");
        Double valor1KmEtanol = (Double) intent.getSerializableExtra("valor1KmEtanol");
        Double economia;

        //calcula resultado e troca valores da tela
        if(gasolinaKmPorReal.doubleValue() == etanolKmPorReal.doubleValue()){
            this.viewHolder.resultado.setText("a que você preferir!");
            this.viewHolder.economia.setText("A economia será igual para qualquer combustível.");

        }else if(gasolinaKmPorReal > etanolKmPorReal){
            this.viewHolder.resultado.setText("GASOLINA");
            this.viewHolder.resultado.setTextColor(Color.parseColor("#f44242"));

            economia = valor1KmEtanol - valor1KmGasolina;
            this.viewHolder.economia.setText("R$ " + dfReal.format(economia));

        }else{
            this.viewHolder.resultado.setText("ETANOL");
            this.viewHolder.resultado.setTextColor(Color.parseColor("#30a532"));

            economia = valor1KmGasolina - valor1KmEtanol;
            this.viewHolder.economia.setText("R$ " + dfReal.format(economia));
        }

        this.viewHolder.qtdKmGasolina.setText(String.valueOf(dfKm.format(gasolinaKmPorReal)));
        this.viewHolder.qtdKmEtanol.setText(String.valueOf(dfKm.format(etanolKmPorReal)));


    }

    public static class ViewHolder{
        TextView resultado, qtdKmGasolina, qtdKmEtanol, economia;

        public ViewHolder(){

        }
    }
}
