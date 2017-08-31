package com.example.android.noticias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoticiaAdapter extends ArrayAdapter<DadosNoticia> {

    public NoticiaAdapter(Context contexto, List<DadosNoticia> informacoesNoticia) {
        super(contexto, 0, informacoesNoticia);
    }

    @Override
    public View getView(int posicao, View converteVisualizacao, ViewGroup parente) {

        View visualizacao = converteVisualizacao;
        if (visualizacao == null) {
            visualizacao = LayoutInflater.from(getContext()).inflate(R.layout.lista_noticia,
                    parente, false);
        }

        DadosNoticia listaNoticia = getItem(posicao);

        TextView secao = (TextView) visualizacao.findViewById(R.id.secao_noticia);
        secao.setText(listaNoticia.getSecao());

        TextView dataNoticia = (TextView) visualizacao.findViewById(R.id.data_noticia);
        String dataAtual = listaNoticia.getData();
        int inicio = dataAtual.indexOf("2");
        int termino = dataAtual.lastIndexOf("T");

        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
        String dataCorrigida = dataAtual.substring(inicio, termino);
        dataCorrigida = (dataFormatada.format(new Date()));
        dataNoticia.setText(dataCorrigida);

        TextView titulo = (TextView) visualizacao.findViewById(R.id.titulo_noticia);
        titulo.setText(listaNoticia.getTitulo());

        TextView autor = (TextView) visualizacao.findViewById(R.id.autor);
        autor.setText(listaNoticia.getAutor());

        ImageView imagemLivro = (ImageView) visualizacao.findViewById(R.id.imagem_noticia);
        String urlImagemLivro = listaNoticia.getImagemUrl();
        URL urlImagem;
        try {
            urlImagem = new URL(urlImagemLivro);
            new ImagemUrlTask(imagemLivro).execute(urlImagem);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return visualizacao;
    }

}
