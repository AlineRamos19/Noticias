package com.example.android.noticias;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ExibirListaNoticia extends AppCompatActivity
        implements android.app.LoaderManager.LoaderCallbacks<List<DadosNoticia>> {

    private TextView mEstadoVazioTxtView = null;
    private static final int DADOSNOTICIA_ID_LOADER = 1;
    private NoticiaAdapter mAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        listView = (ListView) findViewById(R.id.lista);
        mAdapter = new NoticiaAdapter(this, new ArrayList<DadosNoticia>());
        listView.setAdapter(mAdapter);

        mEstadoVazioTxtView = (TextView) findViewById(R.id.visualizacaoVazia);
        listView.setEmptyView(mEstadoVazioTxtView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View visualizacao,
                                    int posicao, long l) {

                DadosNoticia noticiaSelecionada = mAdapter.getItem(posicao);

                Uri informacaoNoticiaWeb = Uri.parse(noticiaSelecionada.getUrl().trim());
                Intent siteTheGuardian = new Intent(Intent.ACTION_VIEW, informacaoNoticiaWeb);
                if (siteTheGuardian.resolveActivity(getPackageManager()) != null) {
                    startActivity(siteTheGuardian);
                }
            }
        });

        ConnectivityManager verificarConexaoInternet = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo informacaoRede = verificarConexaoInternet.getActiveNetworkInfo();

        if (informacaoRede != null && informacaoRede.isConnected()) {

            final android.app.LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(DADOSNOTICIA_ID_LOADER, null, this);
        } else {
            View indicadorProcessamento = findViewById(R.id.indicadorProcessamento);
            indicadorProcessamento.setVisibility(View.GONE);
            mEstadoVazioTxtView.setText(getString(R.string.sem_conexao));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                finishAffinity();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public android.content.Loader<List<DadosNoticia>> onCreateLoader(int i, Bundle bundle) {

        return new NoticiaLoader(this);
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<DadosNoticia>> loader, List<DadosNoticia>
            informacoesNoticia) {

        View indicadorProcessamento = findViewById(R.id.indicadorProcessamento);
        indicadorProcessamento.setVisibility(View.GONE);

        mEstadoVazioTxtView.setText(getString(R.string.sem_novas_noticia));


        mAdapter.clear();

        if (informacoesNoticia != null && !informacoesNoticia.isEmpty()) {
            mAdapter.addAll(informacoesNoticia);
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<DadosNoticia>> loader) {
        mAdapter.clear();
    }
}

