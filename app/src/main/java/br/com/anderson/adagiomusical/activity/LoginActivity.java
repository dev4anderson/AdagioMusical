package br.com.anderson.adagiomusical.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.com.anderson.adagiomusical.R;
import br.com.anderson.adagiomusical.dao.LoginDAO;
import br.com.anderson.adagiomusical.model.Login;

public class LoginActivity extends AppCompatActivity {

    private static final String PREF_NAME = "LoginPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public void Logar(View v){

        EditText etUsuario = (EditText) findViewById(R.id.etUsuario);
        EditText etSenha   = (EditText) findViewById(R.id.etSenha);
        CheckBox cbLogado  = (CheckBox) findViewById(R.id.cbLogado);

        LoginDAO dao = new LoginDAO(this);
        Login login = new Login();

        login.setUsuario(etUsuario.getText().toString());
        login.setSenha(etSenha.getText().toString());

        if (dao.existeLogin(login)){

            if(cbLogado.isChecked()){

                SharedPreferences sp = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

                editor.putString("usuario", login.getUsuario());
                editor.putString("senha", login.getSenha());
                editor.commit();

            }

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Erro ao logar", Toast.LENGTH_SHORT).show();
        }


    }
}
