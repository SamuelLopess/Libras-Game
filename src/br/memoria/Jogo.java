package br.memoria;

import java.util.HashMap;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageView;

public class Jogo extends Activity {

	/* cria duas listas, contendo um inteiro que representa a posição, e um objeto do tipo Carta */
	final private HashMap<Integer, Carta> lista = new HashMap<Integer, Carta>();
	final private HashMap<Integer, Carta> listaEmbaralhada = new HashMap<Integer, Carta>();
	private Chronometer chrono; // cronometro
	private Handler handler = new Handler(); // handler para executar a comparação em outro processo
	private View view = null; // view que abriga a carta que fica a mostra enquanto nao abre a segunda
	private int contador = 0; // contador de clicks

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// tela cheia
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.jogo);

		/* cria os objetos carta passando a imagem e o par dela, e joga numa lista */
		lista.put(0, new Carta(R.drawable.canguru1, 1));
		lista.put(1, new Carta(R.drawable.canguru2, 1));
		lista.put(2, new Carta(R.drawable.capivara1, 2));
		lista.put(3, new Carta(R.drawable.capivara2, 2));
		lista.put(4, new Carta(R.drawable.coruja1, 3));
		lista.put(5, new Carta(R.drawable.coruja2, 3));
		lista.put(6, new Carta(R.drawable.corvo1, 4));
		lista.put(7, new Carta(R.drawable.corvo2, 4));
		lista.put(8, new Carta(R.drawable.elefante1, 5));
		lista.put(9, new Carta(R.drawable.elefante2, 5));
		lista.put(10, new Carta(R.drawable.galinha1, 6));
		lista.put(11, new Carta(R.drawable.galinha2, 6));

		/* embaralhar os itens dessa lista em outra lista */
		for (int i = 0; i < 12; i++) {
			int posicao;
			while (true) {
				/*
				 * gambiarra: faz um random de 1 a 12, ve se existe na outra lista. se existir ele remove e
				 * adiciona na outra lista embaralhada. se não existir, ele busca outro numero randomico
				 * entre 1 a 12 e verifica de novo se existe.
				 */
				Random r = new Random();
				posicao = r.nextInt(12);
				if (lista.containsKey(posicao))
					break;
				else
					continue;
			}

			Carta carta = lista.get(posicao); // busca a carta na primeira lista
			listaEmbaralhada.put(i, carta); // insere na lista embaralhada
			/* seta a posição que ela está no objeto, para ficar mais fácil de encontrar */
			carta.setPosicao(i);
			lista.remove(posicao);
		}

		// instancia o gridview
		final GridView gridview = (GridView) findViewById(R.id.gridview);
		// adiciona as imagens no gridview
		final ImageAdapter imageAdapter = new ImageAdapter(this);
		gridview.setAdapter(imageAdapter);

		// inicia o contador de tempo
		chrono = new Chronometer(this);
		chrono.setBase(SystemClock.elapsedRealtime());
		chrono.start();

		// click listener da imagem
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, final View v, int position, long id) {
				contador++;

				// busca a carta que estiver na mesma posição clicada, na lista embaralhada
				final Carta cartaAtual = getCartaPorPosicao(position);

				// verifica se a carta já está virada. se não estiver executa o código abaixo:
				if (!cartaAtual.isFlip) {
					// exibe a imagem da carta daquela posição e marca como virada
					((ImageView) v).setImageResource(cartaAtual.getImagem());
					cartaAtual.isFlip = true;

					handler.post(new Runnable() {
						public void run() {
							comparar(cartaAtual, v); // executa o metodo de comparar as cartas que estiverem
																				// viradas
							view = v; // armazena a view que abriga a carta que acabou de ser virada
						}
					});
				}
			}
		});
	}

	public Carta getCartaPorPosicao(int i) {
		// retorna a carta que está na lista embaralhada na posição informada
		return listaEmbaralhada.get(i);
	}

	public void comparar(Carta cartaAtual, View v) {
		/*
		 * faz uma busca na lista para verificar se encontra alguma carta virada que não tenha sido
		 * encontrada. se encontrar verifica se forma par com a que foi virada agora. Se formar, marca
		 * as duas como "match" e deixa elas exibindo. se não for ele vira as duas novamente.
		 */

		for (int i = 0; i < 12; i++) {
			Carta cartaVirada = listaEmbaralhada.get(i);

			if (!cartaVirada.equals(cartaAtual)) {
				if (cartaVirada.isFlip && !cartaVirada.isMatch)
					if (cartaVirada.getPar() == cartaAtual.getPar()) {
						cartaVirada.isMatch = true;
						cartaAtual.isMatch = true;
						verificaFim();
					} else {

						try {
							Thread.sleep(1000); // da um tempo de 1s
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						// desvira a carta atual
						((ImageView) v).setImageResource(R.drawable.ic_launcher);
						// desvira a carta que ja estava virada
						((ImageView) view).setImageResource(R.drawable.ic_launcher);

						cartaAtual.isFlip = false; // seta que não está virada
						cartaVirada.isFlip = false; // seta que não está virada
					}
			}
		}
	}

	private void verificaFim() {
		// cria uma variavel "vitoria" como verdadeira
		boolean vitoria = true;

		/*
		 * passa por todas as cartas, e verifica se o campo isMatch é falso. se for falso se for falso,
		 * é porque ainda tem cartas para serem encontradas, e seta a variavel "vitoria" como falsa.
		 */
		for (int i = 0; i < 12; i++) {
			Carta cartaVirada = listaEmbaralhada.get(i);
			if (!cartaVirada.isMatch)
				vitoria = false;
		}

		/*
		 * se a variavel vitoia continuar verdadeira, ele para o cronometro, transforma o tempo recebido
		 * em milisegundos em segundos e grava no banco. Depois disso, chama a outra tela e passa as
		 * outras informações
		 */
		if (vitoria) {
			chrono.stop(); // para o cronometro

			// divide o tempo em milisegundos por 1000 para encontrar os segundos
			long elapsedTime = (SystemClock.elapsedRealtime() - chrono.getBase()) / 1000;

			DBAdapter db = new DBAdapter(this); // nova instancia da classe que gerencia o banco de dados
			db.open(); // abre o banco
			// insere o nome, a quantidade de clicks e o tempo no banco de dados
			db.insereRanking(getIntent().getStringExtra("nome").toString(), contador, elapsedTime);
			db.close(); // fecha o banco

			// cria uma nova intent, indicando que deverá abrir a tela FIM
			Intent intent = new Intent(Jogo.this, Fim.class);
			intent.putExtra("contador", contador); // passa o valor do contador para a outra classe
			intent.putExtra("tempo", elapsedTime); // passa o tempo para a outra classe
			startActivity(intent); // inicia a outra classe
			finish(); // fecha essa tela
		}
	}
}
