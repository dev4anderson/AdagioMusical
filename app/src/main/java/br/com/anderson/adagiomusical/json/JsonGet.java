package br.com.anderson.adagiomusical.json;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.anderson.adagiomusical.activity.SplashScreenActivity;
import br.com.anderson.adagiomusical.dao.LoginDAO;
import br.com.anderson.adagiomusical.model.Login;

import static br.com.anderson.adagiomusical.R.string.login;

/**
 * Created by andersonmacedo on 12/03/17.
 */

public class JsonGet extends AsyncTask<String, Void, String> {

    private Context context;

    public JsonGet(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(String registro) {

        try {
            JSONObject json = new JSONObject(registro);

            LoginDAO dao = new LoginDAO(context);
            Login login = new Login();

            login.setUsuario(json.getString("usuario"));
            login.setSenha(json.getString("senha"));

            if (!dao.existeLogin(login)){
                dao.insereLogin(login);
            }

            dao.close();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... strings) {

        URL url;

        try {
            url = new URL("http://www.mocky.io/v2/58b9b1740f0000b614f09d2f");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() == 200){
                BufferedReader stream =
                        new BufferedReader(new InputStreamReader(
                                connection.getInputStream()
                        ));

                String linha = "";

                StringBuilder resposta = new StringBuilder();

                while ((linha = stream.readLine()) != null){
                    resposta.append(linha);
                }

                connection.disconnect();

                return resposta.toString();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}

