package br.memoria;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;

public class Recordes extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.recordes); // monta o layout definido no arquivo recordes.xml

	  /* instancia um novo DBAdapter, para usarmos para fazer a busca do ranking */
		DBAdapter info = new DBAdapter(this); 

		/* instancia o TableLayout definido no xml */ 
		TableLayout tl = (TableLayout) findViewById(R.id.tl1);

		info.open(); // abre o banco de dados
		ArrayList<TableRow> data = info.getRanking(); // retorna um array com os dados do bd
		info.close(); // fecha o banco de dados

		TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT); // seta o Layout para as linhas da tabela
		
		/* faz um for para adicionar linha por linha, do array recebido com os dados do BD */
		for (TableRow tr : data) {
			tl.addView(tr, layoutParams);
		}
	}

}
