package br.memoria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class Novo extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.novo);

		final EditText editNome = (EditText) findViewById(R.id.editText1);
		Button enviar = (Button) findViewById(R.id.button1);

		enviar.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(Novo.this, Jogo.class);
				intent.putExtra("nome", editNome.getText().toString());
				startActivity(intent);
				finish();
			}
		});
	}
}
