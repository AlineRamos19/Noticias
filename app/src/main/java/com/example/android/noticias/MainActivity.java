package com.example.android.noticias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tecnologia = (TextView) findViewById(R.id.txt_tecnologia);
        tecnologia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExibirListaNoticia.class);
                startActivity(intent);
                Util.opcaoNoticia(getString(R.string.opcao_tecnologia));
            }
        });

        TextView politica = (TextView) findViewById(R.id.txt_politica);
        politica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExibirListaNoticia.class);
                startActivity(intent);
                Util.opcaoNoticia(getString(R.string.opcao_politica));
            }
        });

        TextView negocios = (TextView) findViewById(R.id.txt_negocios);
        negocios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExibirListaNoticia.class);
                startActivity(intent);
                Util.opcaoNoticia(getString(R.string.opcao_negocios));
            }
        });

        TextView mundo = (TextView) findViewById(R.id.txt_mundo);
        mundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExibirListaNoticia.class);
                startActivity(intent);
                Util.opcaoNoticia(getString(R.string.opcao_mundo));
            }
        });

        TextView esportes = (TextView) findViewById(R.id.txt_esportes);
        esportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExibirListaNoticia.class);
                startActivity(intent);
                Util.opcaoNoticia(getString(R.string.opcao_esportes));
            }
        });
    }
}
