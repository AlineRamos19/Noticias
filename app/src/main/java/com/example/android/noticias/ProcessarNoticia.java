package com.example.android.noticias;


import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProcessarNoticia {

    private static final String LOG_TAG = ProcessarNoticia.class.getSimpleName();

    public static List<DadosNoticia> extrairDadosJson(String dadosNoticiaJson) {
        if (TextUtils.isEmpty(dadosNoticiaJson)) {
            return null;
        }

        List<DadosNoticia> informacoesNoticia = new ArrayList<>();

        try {
            JSONObject respostaJson = new JSONObject(dadosNoticiaJson);
            JSONObject inforNoticia = respostaJson.optJSONObject("response");

            JSONArray resultadosNoticia = inforNoticia.optJSONArray("results");

            for (int i = 0; i < resultadosNoticia.length(); i++) {

                JSONObject noticiaAtual = resultadosNoticia.optJSONObject(i);

                JSONObject imagensNoticia = null;
                if (noticiaAtual.has("fields")) {
                    imagensNoticia = noticiaAtual.optJSONObject("fields");
                }

                String imagemUrl = " ";
                if (imagensNoticia.has("thumbnail")) {
                    imagemUrl = imagensNoticia.optString("thumbnail");
                }


                String nomeSecaoNoticia = " ";
                if (noticiaAtual.has("sectionName")) {
                    nomeSecaoNoticia = noticiaAtual.optString("sectionName");
                }

                String tituloNoticia = " ";
                if (noticiaAtual.has("webTitle")) {
                    tituloNoticia = noticiaAtual.optString("webTitle");
                }

                String dataPublicacao = " ";
                if (noticiaAtual.has("webPublicationDate")) {
                    dataPublicacao = noticiaAtual.optString("webPublicationDate");
                }

                String urlGuardian = " ";
                if (noticiaAtual.has("webUrl")) {
                    urlGuardian = noticiaAtual.optString("webUrl");
                }

                JSONArray dadosAutor = noticiaAtual.optJSONArray("tags");

                String autor = " ";
                for (int j = 0; j < dadosAutor.length(); j++) {
                    JSONObject inforAutor = dadosAutor.optJSONObject(j);

                    if (inforAutor.has("webTitle")) {
                        autor = inforAutor.optString("webTitle");
                    }
                }

                DadosNoticia noticias = new DadosNoticia(imagemUrl, nomeSecaoNoticia, dataPublicacao,
                        tituloNoticia, autor, urlGuardian);
                informacoesNoticia.add(noticias);
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problema ao analisar os resultados JSON", e);
        }

        return informacoesNoticia;
    }
}
