package com.example.leo_g.completaai;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ViewHolder viewHolder = new ViewHolder();
    private AlertDialog alerta;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //busca valores da tela
        this.viewHolder.precoGasolina = findViewById(R.id.editTextPrecoGasolina);
        this.viewHolder.precoEtanol = findViewById(R.id.editTextPrecoEtanol);
        this.viewHolder.kmLGasolina = findViewById(R.id.editText2KmLGasolina);
        this.viewHolder.kmLEtanol = findViewById(R.id.editText2KmLEtanol);

        //this.viewHolder.resultado = findViewById(R.id.textViewResultado);

        this.viewHolder.calcular = findViewById(R.id.buttonCalcular);
        this.viewHolder.calcular.setOnClickListener(this);

        //cria mensagem
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Opa...");
        builder.setMessage("Por favor, preencha todos os campos.");
    }

    @Override
    public void onClick(View v){

        if(v.getId() == R.id.buttonCalcular){

            if( TextUtils.isEmpty(this.viewHolder.precoGasolina.getText()) ||
                TextUtils.isEmpty(this.viewHolder.precoEtanol.getText()) ||
                TextUtils.isEmpty(this.viewHolder.kmLGasolina.getText()) ||
                TextUtils.isEmpty(this.viewHolder.kmLEtanol.getText())){


                alerta = builder.create();
                alerta.show();

            }else{
                Double precoGasolina = Double.valueOf(this.viewHolder.precoGasolina.getText().toString());
                Double precoEtanol = Double.valueOf(this.viewHolder.precoEtanol.getText().toString());
                Double kmLGasolina = Double.valueOf(this.viewHolder.kmLGasolina.getText().toString());
                Double kmLEtanol = Double.valueOf(this.viewHolder.kmLEtanol.getText().toString());

                Double gasolinaKmPorReal = kmLGasolina / precoGasolina;
                Double etanolKmPorReal = kmLEtanol / precoEtanol;

                Double valor1KmGasolina = precoGasolina / kmLGasolina;
                Double valor1KmEtanol = precoEtanol / kmLEtanol;

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);

                intent.putExtra("gasolinaKmPorReal", gasolinaKmPorReal);
                intent.putExtra("etanolKmPorReal", etanolKmPorReal);

                intent.putExtra("valor1KmGasolina", valor1KmGasolina);
                intent.putExtra("valor1KmEtanol", valor1KmEtanol);
                startActivity(intent);
            }
        }
    }

    public static class ViewHolder{
        EditText precoGasolina, precoEtanol, kmLGasolina, kmLEtanol;
        //TextView resultado;
        Button calcular;

        public ViewHolder(){

        }
    }

}
