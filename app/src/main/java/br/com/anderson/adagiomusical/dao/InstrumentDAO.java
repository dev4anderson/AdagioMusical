package br.com.anderson.adagiomusical.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.anderson.adagiomusical.model.Instrument;


/**
 * Created by andersonmacedo on 19/03/17.
 */

public class InstrumentDAO extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "TBINSTR";

    public InstrumentDAO(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "modelo TEXT NOT NULL," +
                "marca TEXT," +
                "qtde TEXT);";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {
        String sql = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(sql);
    }

    public void insereInstrument(Instrument instrument) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = buscaInstrument(instrument);

        db.insert(TABLE_NAME, null, dados);

    }

    private ContentValues buscaInstrument(Instrument instrument) {

        ContentValues dados = new ContentValues();

        dados.put("modelo", instrument.getModelo());
        dados.put("marca", instrument.getMarca());
        dados.put("qtde", instrument.getQtde());

        return dados;

    }

    public void alteraInstrument(Instrument instrument) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getInstrument(instrument);

        String[] params = {instrument.getId().toString()};

        db.update(TABLE_NAME, dados, "id = ?", params);

    }

    private ContentValues getInstrument(Instrument instrument) {

        ContentValues dados = new ContentValues();

        dados.put("modelo", instrument.getModelo());
        dados.put("marca", instrument.getMarca());
        dados.put("qtde", instrument.getQtde());

        return dados;

    }

    public List<Instrument> buscaInstrument() {

        String sql = "SELECT * FROM " + TABLE_NAME + ";";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Instrument> instruments = new ArrayList<Instrument>();
        while (c.moveToNext()) {
            Instrument instrument = new Instrument();

            instrument.setId(c.getLong(c.getColumnIndex("id")));
            instrument.setModelo(c.getString(c.getColumnIndex("modelo")));
            instrument.setMarca(c.getString(c.getColumnIndex("marca")));
            instrument.setQtde(c.getString(c.getColumnIndex("qtde")));

            instruments.add(instrument);

        }

        c.close();

        return instruments;


    }
}
