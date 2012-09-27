package br.memoria;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class DBAdapter {
	private SQLiteDatabase database;
	private DBHelper dbHelper;
	private final Context nContext;

	public DBAdapter(Context context) {
		nContext = context;
		dbHelper = new DBHelper(context);
	}

	// metodo para inserir os dados no banco. Recebe o nome, os cliks e o tempo
	public void insereRanking(String nome, int clicks, long tempo) {
		// insere os dados num ContentValue, associando ao nome do campo na tabela
		ContentValues values = new ContentValues();
		values.put("NOME", nome);
		values.put("CLICKS", clicks);
		values.put("TEMPO", tempo);
		// insere no banco, informando o nome da tabela e o ContentValue com os dados
		database.insert("ranking", null, values);
	}

	public ArrayList<TableRow> getRanking() {
		String[] colunas = { "nome", "clicks", "tempo" }; // nome das colunas no banco de dados
		// Cursor com os resultados da busca. Ordena por clicks, e depois pelo tempo.
		Cursor c = database.query("ranking", colunas, null, null, null, null, "clicks, tempo");

		// armazena o indice de cada coluna, para facilitar para inserir os dados depois 
		int iNome = c.getColumnIndex("NOME");
		int iClicks = c.getColumnIndex("CLICKS");
		int iTempo = c.getColumnIndex("TEMPO");

		// array de tablerow que será preenchido com os dados do banco de dados
		ArrayList<TableRow> linhas = new ArrayList<TableRow>();

		TableRow tr;
		TextView tv;

		if (c.equals(null))
			linhas = null;

		// for que passa por cada linha do cursor
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

			long segundos = c.getLong(iTempo) % 60; // calcula os segundos
			long minutos = c.getLong(iTempo) / 60; // calcula os minutos

			tr = new TableRow(nContext); // cria uma linha da tabela
			// seta os parametros dela como wrap_content para altura e largura
			// LEMBRANDO QUE: 
			// wrap_content ajusta deixando com apenas o tamanho necessário.
			// fill_parent ajusta o conteúdo liberando todo o espaço disponível pra ele. 
			tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

			// cria um textview com os dados do nome, e seta os parametros como FILL_PARENT 
			tv = new TextView(nContext);
			tv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, 10));
			tv.setTextColor(Color.parseColor("#7d7c7a")); // seta a cor
			tv.setText(c.getString(iNome)); // busca a string da coluna do nome
			tr.addView(tv); // adiciona na linha que criamos acima

			tv = new TextView(nContext);
			tv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, 1));
			tv.setTextColor(Color.parseColor("#7d7c7a")); // seta a cor
			tv.setText(c.getString(iClicks)); // busca a string da coluna dos clicks
			tr.addView(tv); // adiciona na linha que criamos acima

			tv = new TextView(nContext);
			tv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, 10));
			tv.setTextColor(Color.parseColor("#7d7c7a")); // seta a cor
			tv.setText(minutos + ":" + segundos); // insere o tempo no formato MM:ss
			tr.addView(tv); // adiciona na linha que criamos acima

			linhas.add(tr); // adiciona essa linha no Array 
		}
		return linhas;
	}

	public DBAdapter open() throws SQLException {
		dbHelper = new DBHelper(nContext); 
		database = dbHelper.getWritableDatabase(); // abre o banco de dados para receber dados
		return this;
	}

	public void close() {
		dbHelper.close(); // fecha o banco de dados, não permitindo mais inserções
	}

}
