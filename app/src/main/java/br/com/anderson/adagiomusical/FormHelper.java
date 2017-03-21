package br.com.anderson.adagiomusical;

import android.widget.EditText;

import br.com.anderson.adagiomusical.activity.AddActivity;
import br.com.anderson.adagiomusical.model.Instrument;

/**
 * Created by andersonmacedo on 19/03/17.
 */

public class FormHelper {

    private EditText etModelo;
    private EditText etMarca;
    private EditText etQtde;
    private Instrument instrument;

    public FormHelper(AddActivity activity) {

        instrument = new Instrument();

        etModelo = (EditText) activity.findViewById(R.id.etModelo);
        etMarca  = (EditText) activity.findViewById(R.id.etMarca);
        etQtde   = (EditText) activity.findViewById(R.id.etQtde);

    }

    public Instrument setInstrument(){

        instrument.setModelo(etModelo.getText().toString());
        instrument.setMarca(etMarca.getText().toString());
        instrument.setQtde(etQtde.getText().toString());

        return instrument;

    }

    public void preencheFormulario(Instrument instrument) {

        this.instrument = instrument;

        etModelo.setText(instrument.getModelo());
        etMarca.setText(instrument.getMarca());
        etQtde.setText(instrument.getQtde());

    }
}
