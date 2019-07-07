package com.wisilvadev.festa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wisilvadev.Constants.FestaFimAnoConstants;
import com.wisilvadev.date.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;
    private static SimpleDateFormat SIMPLE_DATE_FORMATE = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSecurityPreferences = new SecurityPreferences(this);
        mViewHolder.textDate = findViewById(R.id.txtDate);
        mViewHolder.dias = findViewById(R.id.dias);
        mViewHolder.btnConfirmar = findViewById(R.id.btn_confirmar);

        mViewHolder.btnConfirmar.setOnClickListener(this);
        this.mViewHolder.textDate.setText(SIMPLE_DATE_FORMATE.format(Calendar.getInstance().getTime()));
        String daysLeft = String.format("%s %s", String.valueOf(this.lastDays()), getString(R.string.dias));

        this.mViewHolder.dias.setText(daysLeft);

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_confirmar) {
            /*Intent tem uma intenção que recebe  o Contexto que é a classe mainActivity
             e recebe a activity que quer abrir
              */
            String presence = mSecurityPreferences.getStorePreferences(FestaFimAnoConstants.PRESENCE_KEY);

            Intent intent = new Intent(this, detallsActivity.class);
            // startActivity executa a intencão
            intent.putExtra(FestaFimAnoConstants.PRESENCE_KEY, presence);
            startActivity(intent);

        }
    }

    public void verifyPresence() {

        String presence = mSecurityPreferences.getStorePreferences(FestaFimAnoConstants.PRESENCE_KEY);

        if (presence.equals("")) {
            this.mViewHolder.btnConfirmar.setText(getText(R.string.nao_confirmado));
        } else if (presence.equals(FestaFimAnoConstants.PRESENCE_YES)) {
            this.mViewHolder.btnConfirmar.setText(getText(R.string.sim));
        } else {
            this.mViewHolder.btnConfirmar.setText(R.string.nao);
        }
    }

    public int lastDays() {
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);
        int dayMax = calendarToday.getActualMaximum(Calendar.DAY_OF_YEAR);
        return dayMax - today;
    }

    private class ViewHolder {
        TextView textDate;
        TextView dias;
        Button btnConfirmar;


    }
}
