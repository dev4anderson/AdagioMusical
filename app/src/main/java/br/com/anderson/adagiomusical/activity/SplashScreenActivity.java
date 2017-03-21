package br.com.anderson.adagiomusical.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import br.com.anderson.adagiomusical.R;
import br.com.anderson.adagiomusical.dao.LoginDAO;
import br.com.anderson.adagiomusical.json.JsonGet;
import br.com.anderson.adagiomusical.model.Login;

public class SplashScreenActivity extends AppCompatActivity{

    private ImageView ivSplashLogo;
    private Animation animation;
    private static final String PREF_NAME = "LoginPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Verificar Shared Preferences
        SharedPreferences sp = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        String usuario = sp.getString("usuario", "");
        String senha = sp.getString("senha", "");

        LoginDAO dao = new LoginDAO(getApplicationContext());
        Login login = new Login();

        login.setUsuario(usuario);
        login.setSenha(senha);

        if (dao.existeLogin(login)){
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            // Prosseguir com o processo normal caso não exista o SharedPreference do login
            callAnimation();
        }

    }

    private void callAnimation() {
        ivSplashLogo = (ImageView) findViewById(R.id.ivSplashLogo);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);

        ivSplashLogo.setAnimation(animation);
        ivSplashLogo.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                JsonGet jsonGet = new JsonGet(getApplicationContext());
                jsonGet.execute();

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
