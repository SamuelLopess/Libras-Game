package br.memoria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MemoriaActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button novoJogo = (Button) findViewById(R.id.btnNovoJogo);
		Button ajuda = (Button) findViewById(R.id.btnAjuda);
		Button recordes = (Button) findViewById(R.id.btnRecordes);
		Button sobre = (Button) findViewById(R.id.btnSobre);
		Button sair = (Button) findViewById(R.id.btnSair);

		novoJogo.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO: abrir a tela novojogo
				startActivity(new Intent(MemoriaActivity.this, Novo.class));
			}
		});

		ajuda.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO: abrir a tela ajuda
				startActivity(new Intent(MemoriaActivity.this, Ajuda.class));
			}
		});

		recordes.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO: abrir a tela recordes
				startActivity(new Intent(MemoriaActivity.this, Recordes.class));
			}
		});

		sobre.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO: abrir a tela sobre
				startActivity(new Intent(MemoriaActivity.this, Sobre.class));
			}
		});

		sair.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO: sair
				System.exit(0);
			}
		});
	}

}