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

    public final static Path path = Paths.get("laboratorioDois/lab2-master/a_lab2/src/fortune-br.txt");
	private int NUM_FORTUNES = 0;

    public class FileReader { //abrir o arquivo
        
        public int countFortunes() throws FileNotFoundException{
            int lineCount = 0;
			InputStream is = new BufferedInputStream(new FileInputStream(path.toString()));
			try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
				String line = "";
				while (!(line == null)) {
					if (line.equals("%"))
						lineCount++;
					line = br.readLine();
				}// fim while
				System.out.println(lineCount);
			} catch (Exception e) {
				System.out.println("SHOW: Excecao na leitura do arquivo.");
			}
			return lineCount;
        }

        public void parser(HashMap<Integer, String> hm) throws FileNotFoundException {
			InputStream is = new BufferedInputStream(new FileInputStream(path.toString()));
			try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
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
					}
					hm.put(lineCount, fortune.toString());
				}// fim while
			} catch (Exception e) {
				System.out.println("SHOW: Excecao na leitura do arquivo.");
			}
		}

        public void read(HashMap<Integer, String> hm) throws Exception {
			List<Integer> keys = new ArrayList<Integer>(hm.keySet());// Obter uma lista das chaves do mapa
			int indexAleatorio = new SecureRandom().nextInt(keys.size());// Gerar um número aleatório entre 0 e o tamanho da lista de chaves menos 1			
			String fortune = hm.get(keys.get(indexAleatorio));// Obter a fortuna correspondente à chave gerada aleatoriamente
            System.out.println("Minha fortuna aleatoria = " + fortune);
			saida.writeBytes("{\"result\": \"" + fortune + "\"}");
		}

        public void write(String stringCliente) throws IOException {
		
			String fortune = stringCliente;// Obter a fortuna recebida pelo cliente
			try (PrintWriter out = new PrintWriter("output.txt")) {// Gravar a fortuna em um arquivo de saída
				out.println(fortune);
                
			}
            saida.writeBytes("{\"result\": \"" + fortune + "\"}");
		}
    }

	public void iniciar() {
		System.out.println("Servidor iniciado na porta: " + porta);
		try {
            FileReader fr = new FileReader();
			NUM_FORTUNES = fr.countFortunes();
            HashMap hm = new HashMap<Integer, String>();
			fr.parser(hm);

			// Criar porta de recepcao
			server = new ServerSocket(porta);
			socket = server.accept();  //Processo fica bloqueado, ah espera de conexoes

			// Criar os fluxos de entrada e saida
			entrada = new DataInputStream(socket.getInputStream());
			saida = new DataOutputStream(socket.getOutputStream());

			// Recebimento do valor inteiro
			int valor = entrada.readInt();
            String clienteEntrada = entrada.readLine().toString();

			// Processamento do valor
			String resultado = "";
			if (valor > 0){
                if(clienteEntrada.contains("read"))
                    fr.read(hm);
                else if(clienteEntrada.contains("write"))
                    fr.write(clienteEntrada);
                else
                    System.out.println("nao contem read e nem write");
            }
                
			else
				resultado = "O valor enviado eh menor que 0";

			// Envio dos dados (resultado)
			saida.writeUTF(resultado);

			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		new Servidor().iniciar();

	}

}