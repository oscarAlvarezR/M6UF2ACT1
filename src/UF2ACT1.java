import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


public class UF2ACT1 {

	public static void main(String[] args) {

		Connection connection = null;
		Statement stmt = null;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/alumnes", "root", "");

			stmt = connection.createStatement();

			Scanner teclado = new Scanner(System.in);
			boolean parar = false;
			while(!parar) {

				System.out.println("1 Inserta\n2 Modifica\n3 Esborra\n0 Finalitza el programa");
				int seleccio = teclado.nextInt();

				while (seleccio < 0 || seleccio > 3) {

					System.out.println("NUM INCORRECTE\n1 Inserta\n2 Modifica\n3 Esborra");
					seleccio = teclado.nextInt();
					teclado.nextLine();
				}


				if (seleccio == 0) {

					parar = true;

				} else if (seleccio == 1) {

					System.out.println("Inserta el nom:");
					String nom = teclado.nextLine();
					
					System.out.println("Inserta el dni (9caracteres)");
					String dni = teclado.nextLine();
					
					System.out.println("Inserta la fecha (yyyy-mm-dd)");
					String fecha = teclado.nextLine();
					
					System.out.println("Inserta la adreça");
					String adreca = teclado.nextLine();
					
					System.out.println("Inserta el codi postal (10 caracters maxim)");
					int codiPostal = Integer.valueOf(teclado.nextLine());
					
					System.out.println("Inserta la poblacio");
					String poblacio = teclado.nextLine();
					
					try {
						stmt.execute("INSERT INTO alumnes VALUES (" + nom + "," + dni + "," + fecha + "," + adreca + "," + codiPostal + "," + poblacio + ")");
					} catch (MySQLIntegrityConstraintViolationException e) {
						System.out.println("No existeix el codi postal");
					}
				} else if (seleccio == 2) {
					
					System.out.println("Inserta el dni (9caracteres) del alumne que vols editar");
					String condicion = teclado.nextLine();
					
					System.out.println("Inserta el nom:");
					String parametros = teclado.nextLine();
					
					System.out.println("Inserta la fecha (yyyy-mm-dd)");
					parametros += ", fecha = " + teclado.nextLine();

					System.out.println("Inserta la adreça");
					parametros += ", adrecaPostal = " + teclado.nextLine();

					System.out.println("Inserta el codi postal (10 caracters maxim)");
					parametros += ", codiPostal = " + teclado.nextLine();
					
					System.out.println("Inserta la poblacio");
					parametros += ", poblacio = " + teclado.nextLine();
					
					stmt.execute("UPDATE alumnes SET name = " + parametros + " WHERE dni = " + condicion);
				}
				
			}


		} 
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
