package br.com.anderson.adagiomusical.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.anderson.adagiomusical.FormHelper;
import br.com.anderson.adagiomusical.R;
import br.com.anderson.adagiomusical.dao.InstrumentDAO;
import br.com.anderson.adagiomusical.model.Instrument;


public class AddActivity extends AppCompatActivity {

    private FormHelper helper;
    private Button btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btCadastrar = (Button) findViewById(R.id.btCadastrar);

        helper = new FormHelper(AddActivity.this);

        Intent intent = getIntent();
        Instrument instrument = (Instrument) intent.getSerializableExtra("instrument");

        if (instrument != null) {
            helper.preencheFormulario(instrument);
        }

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Instrument instrument = helper.setInstrument();

                InstrumentDAO dao = new InstrumentDAO(AddActivity.this);

                if (instrument.getId() == null) {
                    dao.insereInstrument(instrument);

                    Toast.makeText(AddActivity.this, getString(R.string.instrument)
                                    + " " + instrument.getModelo()
                                    + " " + getString(R.string.created),
                            Toast.LENGTH_SHORT).show();

                } else {
                    dao.alteraInstrument(instrument);
                }

                dao.close();

                finish();

            }
        });

    }

}
