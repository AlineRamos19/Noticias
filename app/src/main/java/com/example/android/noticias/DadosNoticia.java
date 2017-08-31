package com.example.android.noticias;

public class DadosNoticia {

    private String mImagemUrl;
    private String mSecao;
    private String mAutor;
    private String mTitulo;
    private String mData;
    private String mUrl;

    public DadosNoticia(String imagemUrl, String secao, String data, String titulo, String autor, String url) {
        mImagemUrl = imagemUrl;
        mSecao = secao;
        mTitulo = titulo;
        mData = data;
        mAutor = autor;
        mUrl = url;

    }

    public String getImagemUrl() {
        return mImagemUrl;
    }

    public String getSecao() {
        return mSecao;
    }

    public String getData() {
        return mData;
    }

    public String getTitulo() {
        return mTitulo;
    }

    public String getAutor() {
        return mAutor;
    }

    public String getUrl() {
        return mUrl;
    }


    @Override
    public String toString() {
        return "Nome da secção: " + mSecao + "Data de Publicação: " + mData + "Título: " +
                mTitulo + "Autor: " + mAutor;
    }
}
