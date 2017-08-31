package com.example.android.noticias;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

public class ConsultaRequisicao {

    private static final String LOG_TAG = ConsultaRequisicao.class.getSimpleName();

    private ConsultaRequisicao() {
    }

    public static List<DadosNoticia> buscarDadosNoticia(String pedidoUrl) {

        URL url = criarUrl(pedidoUrl);

        String jsonResposta = null;
        try {
            jsonResposta = fazerPedidoHttp(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problemar ao criar um pedido HTTP.", e);
        }

        List<DadosNoticia> noticias = ProcessarNoticia.extrairDadosJson(jsonResposta);
        return noticias;
    }

    private static URL criarUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problema na contrução da URL.", e);
        }
        return url;
    }

    private static String fazerPedidoHttp(URL url) throws IOException {

        String jsonResposta = "";

        if (url == null) {
            return jsonResposta;
        }

        HttpURLConnection conexao = null;
        InputStream inputStream = null;

        try {
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setReadTimeout(3000);
            conexao.setConnectTimeout(7000);
            conexao.setRequestMethod("GET");
            conexao.connect();

            if (conexao.getResponseCode() == 200) {
                inputStream = conexao.getInputStream();
                jsonResposta = converterInputStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Erro na resposta do código: " + conexao.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problemas ao recuperar o resultado dos livros - JSON " + e);
        } finally {
            if (conexao != null) {
                conexao.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResposta;
    }

    private static String converterInputStream(InputStream inputStream) throws IOException {
        StringBuilder saida = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader ler = new BufferedReader(inputStreamReader);

            String linha = ler.readLine();
            while (linha != null) {
                saida.append(linha);
                linha = ler.readLine();
            }
        }
        return saida.toString();
    }
}


