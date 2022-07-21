/****************************************************
 * Asignatura: Programación concurrente y distribuida
 * Profesor: José Delgado Pérez
 * Alumno: Sergio Montero Vadillo
 * DNI: 53426672-H 
 * Actividad : Actividad 1 / Hilos y Socket
 * Última modificación: 21/7/2022
 ****************************************************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor {

	public static void main(String[] args) throws IOException {
		
		//Creamos el servidor socket e indicamos el puerto que atendera
		ServerSocket conexion = new ServerSocket(9999);
		
		//Imprimimos mensaje de inicializacion del servidor
		System.out.println("Servidor Iniciado");
		
		try {
		
		//Bucle infinito para que el servidor este activo
		while (true) {
						
			Socket socket = conexion.accept();
			
			//Variable para leer número de cliente
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			//Variable que lee mensaje del cliente 
			String solicitud = input.readLine();
			
			//Creamos variable para enviar al cliente que generado comunicación
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
									
			//Condición que comprueba que la solicitud es número entero
			if (isNumeric(solicitud)) {
				
				//Convierte solicitud en entero
				int Nsolicitud = Integer.parseInt(solicitud);
				
				//Condicion para aceptar solicitudes que esten dentro de rango
				if (1 <= Nsolicitud && Nsolicitud <=5) {
					
					//Condicion para imprimir en buffer el mensaje a razon de solicitud introducida
					switch (Nsolicitud){
												
						case 1:	out.println("AVISO -> Debe de registrarse para poder ingresar.");
							break;
						case 2:	out.println("AVISO -> Lo sentimos, no se pueden realizar mas registros.");
							break;
						case 3:	out.println("AVISO -> Esta opción se encuetra en mantenimiento, disculpe las molestias.");
							break;
						case 4:	out.println("AVISO -> Para darse de baja, escriba un correo a bajas@chat.net.\n");
							break;
						case 5:	out.println("Hasta pronto");
							break;
					}
				}
				else {
					out.println("Solo se aceptan las solicitudes mostradas, por favor introduzca un número de la lista.");
				}
			}
			else {
				out.println("Por favor introduzca la solicitud de forma numérica.");
			}				
						
			socket.close();
		}
		}finally {
			conexion.close();
		}
		
	}
	
	//Metodo que comprueba que la solicitud es un numero entero 
	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}

}