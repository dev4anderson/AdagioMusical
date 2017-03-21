package br.com.anderson.adagiomusical.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.anderson.adagiomusical.model.Login;

import static android.R.attr.track;
import static android.R.attr.version;

/**
 * Created by andersonmacedo on 12/03/17.
 */

public class LoginDAO extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "TBLOGIN";


    public LoginDAO(Context context) {
        super(context, TABLE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " +  TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "usuario TEXT NOT NULL," +
                "senha TEXT NOT NULL);";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {
        String sql = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(sql);
    }


    public void insereLogin(Login login) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = buscaLogin(login);

        db.insert(TABLE_NAME, null, dados);

    }

    private ContentValues buscaLogin(Login login) {

        ContentValues dados = new ContentValues();

        dados.put("usuario", login.getUsuario());
        dados.put("senha", login.getSenha());

        return dados;

    }

    public boolean existeLogin(Login login) {

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE usuario = ?;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, new String[]{login.getUsuario()});
        boolean existe = false;

        List<Login> alunos = new ArrayList<Login>();

        try {
            c.moveToNext();

            String userDB = c.getString(c.getColumnIndex("usuario"));
            String passDB = c.getString(c.getColumnIndex("senha"));

            String user = login.getUsuario();
            String pass = login.getSenha();


            if (userDB.equals(user) && passDB.equals(pass)) {
                existe = true;
            }


        } catch (Exception e){
            Log.d("ADAGIO", e.getMessage());
        }

        c.close();
        return existe;
    }
}
