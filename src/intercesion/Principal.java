/**
 * 
 */
package intercesion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Jonathan Arroyo
 *
 */
public class Principal {

	private String input;
	private StringTokenizer st;
	private int n;
	private boolean yes;

	private final int N = 100;
	private final int limite = 50000000;

	private BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		
		try {
			new Principal().iniciar();
		} catch (NumberFormatException e) {
			System.out.println("El valor ingresado no es un número");
		} catch (IOException e) {
			System.out.println("El valor no ha podido ser leido");
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error, detalle: " + e.getMessage());
		}
		
	}

	private void iniciar() throws Exception {
		int i, j, k, l, t;
		int[][][] d = new int[2][N][N];

		System.out.println("!!! Bienvenido a los Embotellamientos de Edgetown !!!");
		System.out.println("Para salir ingrese un (0)");
		System.out.println("");
		System.out.println("Ingrese la propuesta deseada");

		input = "";

		while (!input.equals("0")) {

			System.out.println("Ingrese el número de intercesiones");
			input = scanner.readLine();

			n = Integer.parseInt(input);

			for (t = 0; t <= 1; t++) {
				for (i = 0; i < n; i++) {
					for (j = i + 1; j < n; j++) {
						d[t][j][i] = d[t][i][j];
						d[t][j][i] = limite;

					}
				}
				for (l = 0; l < n; l++) {
					if (t == 1) {
						System.out.println("Ingrese la conexión " + (l + 1) + " de la intercesión propuesta");
					} else {
						System.out.println("Ingrese la conexión " + (l + 1) + " de la intercesión actual");
					}

					st = new StringTokenizer(scanner.readLine(), " ");

					i = Integer.parseInt(st.nextToken()) - 1;
					while (st.hasMoreTokens()) {
						j = Integer.parseInt(st.nextToken()) - 1;
						d[t][i][j] = 1;
					}
				}
				for (k = 0; k < n; k++) {
					for (i = 0; i < n; i++) {
						for (j = 0; j < n; j++) {
							if (d[t][i][k] < limite && d[t][k][j] < limite) {
								if ((d[t][i][k] + d[t][k][j]) < d[t][i][j]) {
									d[t][i][j] = d[t][i][k] + d[t][k][j];
								}
							}
						}
					}
				}
			}

			System.out.println("Ingrese la linea de la distancia A y B");
			st = new StringTokenizer(scanner.readLine(), " ");

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			validar(d, A, B);

			System.out.println("Fin de la propuesta!");

			System.out.println("La propuesta cumple con los requisitos: ");
			System.out.println(yes ? "Yes" : "No");
			System.out.println("");
			
			System.out.println("Si desea salir ingrese el número (0), de lo contrario solo presione Enter");
			input = scanner.readLine();
		}
	}

	private void validar(int[][][] d, int A, int B) {
		yes = true;
		int i, j;
		for (i = 0; i < n; i++) {
			if (!yes) {
				break;
			}
			for (j = 0; j < n; j++) {
				if (!yes) {
					break;
				}
				if (i != j && d[0][i][j] < limite) {

					if (d[1][i][j] > A * d[0][i][j] + B) {
						yes = false;
					}
				}
			}
		}
	}
}
