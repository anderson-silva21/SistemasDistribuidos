import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Servidor {

	private static Socket socket;
	private static ServerSocket server;

	private static DataInputStream entrada;
	private static DataOutputStream saida;

	private int porta = 1025;

	
	public final static Path path = Paths			
			.get("lab1-master/src/fortune-br.txt");
	private int NUM_FORTUNES = 0;

	public class FileReader {

		public int countFortunes() throws FileNotFoundException {

			int lineCount = 0;

			InputStream is = new BufferedInputStream(new FileInputStream(
					path.toString()));
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					is))) {

				String line = "";
				while (!(line == null)) {

					if (line.equals("%"))
						lineCount++;

					line = br.readLine();

				}// fim while

				System.out.println(lineCount);
			} catch (IOException e) {
				System.out.println("SHOW: Excecao na leitura do arquivo.");
			}
			return lineCount;
		}

		public void parser(HashMap<Integer, String> hm)
				throws FileNotFoundException {

			InputStream is = new BufferedInputStream(new FileInputStream(
					path.toString()));
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					is))) {

				int lineCount = 0;

				String line = "";
				while (!(line == null)) {

					if (line.equals("%"))
						lineCount++;

					line = br.readLine();
					StringBuffer fortune = new StringBuffer();
					while (!(line == null) && !line.equals("%")) {
						fortune.append(line + "\n");
						line = br.readLine();
						// System.out.print(lineCount + ".");
					}

					hm.put(lineCount, fortune.toString());
					//System.out.println(fortune.toString());

					//System.out.println(lineCount);
				}// fim while

			} catch (IOException e) {
				System.out.println("SHOW: Excecao na leitura do arquivo.");
			}
		}

		public void read(HashMap<Integer, String> hm) 
			throws FileNotFoundException {
				
			List<Integer> keys = new ArrayList<Integer>(hm.keySet());// Obter uma lista das chaves do mapa
			int indexAleatorio = new SecureRandom().nextInt(keys.size());// Gerar um número aleatório entre 0 e o tamanho da lista de chaves menos 1			
			String fortune = hm.get(keys.get(indexAleatorio));// Obter a fortuna correspondente à chave gerada aleatoriamente
			System.out.println(fortune);// Exibir a fortuna obtida
		}

        public void write(HashMap<Integer, String> hm) 
			throws FileNotFoundException {
			
			List<Integer> keys = new ArrayList<Integer>(hm.keySet());// Obter uma lista das chaves do mapa
			int indexAleatorio = new SecureRandom().nextInt(keys.size());// Gerar um número aleatório entre 0 e o tamanho da lista de chaves menos 1			
			String fortune = hm.get(keys.get(indexAleatorio));// Obter a fortuna correspondente à chave gerada aleatoriamente
			try (PrintWriter out = new PrintWriter("output.txt")) {// Gravar a fortuna em um arquivo de saída
				out.println(fortune);
			}
		}
	}

	public void iniciar() {
		System.out.println("Servidor iniciado na porta: " + porta);
		try {

			// Criar porta de recepcao
			server = new ServerSocket(porta);
			socket = server.accept();  //Processo fica bloqueado, ah espera de conexoes

			// Criar os fluxos de entrada e saida
			entrada = new DataInputStream(socket.getInputStream());
			saida = new DataOutputStream(socket.getOutputStream());

			// Recebimento do valor inteiro
			int valor = entrada.readInt();
			System.out.println(valor);

			// Processamento do valor
			String resultado = "";
			if (valor > 0)
				resultado = "O valor enviado eh maior que 0";
			else
				resultado = "O valor enviado eh menor que 0";

			// Envio dos dados (resultado)
			saida.writeUTF(resultado);

			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		FileReader fr = new FileReader();
		try {
			NUM_FORTUNES = fr.countFortunes();
			HashMap hm = new HashMap<Integer, String>();
			fr.parser(hm);
			fr.read(hm);
			fr.write(hm);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		new Servidor().iniciar();

	}

}
