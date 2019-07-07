package com.wisilvadev.festa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.wisilvadev.Constants.FestaFimAnoConstants;
import com.wisilvadev.date.SecurityPreferences;

public class detallsActivity extends AppCompatActivity implements View.OnClickListener {

    private SecurityPreferences mSecurityPreferences;

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalls);
        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkParticipate = findViewById(R.id.check_participate);
        this.mViewHolder.checkParticipate.setOnClickListener(this);

        this.loadFromActivity();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.check_participate) {
            if (mViewHolder.checkParticipate.isChecked()) {
                this.mSecurityPreferences.storeString(FestaFimAnoConstants.PRESENCE_KEY, FestaFimAnoConstants.PRESENCE_YES);
            } else {
                this.mSecurityPreferences.storeString(FestaFimAnoConstants.PRESENCE_KEY, FestaFimAnoConstants.PRESENCE_NOT);

            }
        }
    }

    public void loadFromActivity() {

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            String presence = extra.getString(FestaFimAnoConstants.PRESENCE_KEY);
            if (presence != null && presence.equals(FestaFimAnoConstants.PRESENCE_YES)) {
                this.mViewHolder.checkParticipate.setChecked(true);
            } else {
                this.mViewHolder.checkParticipate.setChecked(false);

            }
        }
    }

    public static class ViewHolder {
        CheckBox checkParticipate;
    }
}
