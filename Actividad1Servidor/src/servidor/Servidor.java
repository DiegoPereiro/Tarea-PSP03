/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import com.oracle.jrockit.jfr.DataType;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author Diego Castro Freijo
 */
public class Servidor {

    static final int puerto = 2000;

    public Servidor() {
        //Creamos un numero aleatorio
        int numeroSecreto = (int) (Math.random() * 100) + 1;
        System.out.println("El numero secreto es: " + numeroSecreto);

        try {
            //iniciamos la escuncha en el puerto 2000
            ServerSocket skServidor = new ServerSocket(puerto);
            String respuesta = "";
            //ponemos un bucle que se repite
            while (true) {                
                //aceptamos la conexion que proviene del cliente
                Socket skCliente = skServidor.accept();
                //Pregunta del cliente
                DataInputStream flujo_entrada = new DataInputStream(skCliente.getInputStream());
                int numeroRecibido = Integer.valueOf(flujo_entrada.readUTF());
                //conprobamos el numero del cliente
                if (numeroRecibido > numeroSecreto) {
                    respuesta = "El numero secreto es menor";
                }
                if (numeroRecibido < numeroSecreto) {
                    respuesta = "El numero secreto es Mayor";
                }
                if (numeroRecibido == numeroSecreto) {
                    respuesta = "El numero secreto correcto";
                }
                //respondemos al cliente
                DataOutputStream flujo_salida = new DataOutputStream(skCliente.getOutputStream());
                flujo_salida.writeUTF(respuesta);

                //cerramos el socket del cliente
                skCliente.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new Servidor();
    }

}
