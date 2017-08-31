package com.example.android.noticias;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class NoticiaLoader extends AsyncTaskLoader<List<DadosNoticia>> {

    private String mGuardianUrl = "https://content.guardianapis.com/" + Util.opcaoSelecionada
            + "?tag=" + Util.opcaoSelecionada + "/" + Util.opcaoSelecionada +
            "&show-tags=contributor&show-fields=thumbnail&api-key=test";


    public NoticiaLoader(Context contexto) {
        super(contexto);

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<DadosNoticia> loadInBackground() {
        if (mGuardianUrl == null) {
            return null;
        }

        List<DadosNoticia> informacoesNoticias = ConsultaRequisicao.buscarDadosNoticia(mGuardianUrl);
        return informacoesNoticias;
    }
}