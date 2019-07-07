package com.wisilvadev.date;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {
    private SharedPreferences mSharedPreferences;


    /*  Cria uma instancia de forma diferente o SharedPrefereces, Primeiro
        vc chama-o e chama o contexto no construtor, pega o nome ( pode ser qualquer nome )
        e define se é privado ou não
        com o getSharedPreferences pronto foi criado a instãncia...
     */

    public SecurityPreferences(Context mContext) {
        this.mSharedPreferences = mContext.getSharedPreferences("Festa fim de Ano", Context.MODE_PRIVATE);
    }


    // Metodo ou função que define os valores

    public void storeString(String key, String value) {
        this.mSharedPreferences.edit().putString(key, value).apply();

    }

    // metodo ou funcão que retorna o valor
    public String getStorePreferences(String key) {
        return this.mSharedPreferences.getString(key, "");
    }
}
