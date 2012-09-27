package br.memoria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Fim extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.fim);

		// instancia os textviews e os botões
		TextView clicks = (TextView) findViewById(R.id.tvClicks);
		TextView tempo = (TextView) findViewById(R.id.tvTempo);
		Button recordes = (Button) findViewById(R.id.btnRecordes);
		Button novoJogo = (Button) findViewById(R.id.btnNovoJogo);

		// calcula o tempo em segundos e em minutos
		// busca o tempo do Intent que foi passado pela outra classe
		long segundos = getIntent().getLongExtra("tempo", 0) % 60; // pega o resto da divisão por 60
		long minutos = getIntent().getLongExtra("tempo", 0) / 60; // divide os segundos por 60 e acha os minutos
		
		// pega a quantidade de clicks, do intent que foi passado da outra classe
		clicks.append(String.valueOf(getIntent().getIntExtra("contador", 0)));
		tempo.append(minutos + ":" + segundos);

		recordes.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO: abrir a tela recordes
				startActivity(new Intent(Fim.this, Recordes.class));
				finish();
			}
		});

		novoJogo.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO: abrir a tela novojogo
				startActivity(new Intent(Fim.this, Novo.class));
				finish();
			}
		});

	}
}
