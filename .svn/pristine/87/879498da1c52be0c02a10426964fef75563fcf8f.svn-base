package br.memoria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	
	public DBHelper(Context context) {
		// cria o banco de dados, com o nome ranking.db
		super(context, "ranking.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL que será executado na hora que instalar o app. 
		// Cria a tabela Ranking passando três campos: Nome, Clicks e tempo.
		db.execSQL("CREATE TABLE RANKING (NOME TEXT, CLICKS INTEGER, TEMPO LONG)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
