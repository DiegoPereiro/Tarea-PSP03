/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Castro Freijo
 */
public class Servidor {

    static final int Puerto = 1500;

    public Servidor() {

        try {
            //Poner el servidor en escucha
            ServerSocket serverSocket = new ServerSocket(Puerto);
            while (true) {
                //Aceptamos las conexiones entrantes
                Socket clienteSocket = serverSocket.accept();

                DataInputStream flujo_entrada = new DataInputStream(clienteSocket.getInputStream());

                File archvo = new File(flujo_entrada.readUTF());

                String respuesta = null;
                if (!archvo.exists()) {
                    DataOutputStream flujo_salida = new DataOutputStream(clienteSocket.getOutputStream());
                    flujo_salida.writeUTF("no existe");
                } else {
                    DataOutputStream flujo_salida = new DataOutputStream(clienteSocket.getOutputStream());
                    flujo_salida.writeUTF("existe");

                    
                    
                    
                    
                    DataInputStream input;
                    BufferedInputStream bis;
                    BufferedOutputStream bos;
                    int in;
                    byte[] byteArray;

                    try {
                        bis = new BufferedInputStream(new FileInputStream(archvo));
                        bos = new BufferedOutputStream(clienteSocket.getOutputStream());
                        //Enviamos el nombre del fichero
                        DataOutputStream dos = new DataOutputStream(clienteSocket.getOutputStream());
                        dos.writeUTF(archvo.getName());
                        //Enviamos el fichero
                        byteArray = new byte[8192];
                        while ((in = bis.read(byteArray)) != -1) {
                            bos.write(byteArray, 0, in);
                        }
                        bis.close();
                        bos.close();

                    } catch (Exception e) {
                        System.err.println(e);
                    }
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                }

            }

//            BufferedInputStream bufferArchivoEntrada = new BufferedInputStream(new FileInputStream(archvo));
//
//            
//            //Enviamos el nombre del fichero
//            DataOutputStream flujo_salida = new DataOutputStream(clienteSocket.getOutputStream());
//            BufferedOutputStream bufferArchivoSalida=new BufferedOutputStream(clienteSocket.getOutputStream());
//            flujo_salida.writeUTF(archvo.getName());
//            //Enviamos el fichero
//            int in;
//            byte[] tamaño_bytes = new byte[8192];
//            while ((in = bufferArchivoEntrada.read(tamaño_bytes)) != -1) {
//                bufferArchivoSalida.write(tamaño_bytes, 0, in);
//            }
//
//            bufferArchivoEntrada.close();
//            bufferArchivoSalida.close();
//            clienteSocket.close();
//        BufferedInputStream bis;
//        BufferedOutputStream bos;
//        byte[] receivedData;
//        int in;
//        String file;
//
//        try {
//            //iniciamos la escuncha en el puerto 2000
//            ServerSocket server = new ServerSocket(Puerto);
//            while (true) {
//                //Aceptar conexiones
//                Socket clienteSocket = server.accept();
//                //Buffer de 1024 bytes
//                receivedData = new byte[1024];
//                bis = new BufferedInputStream(clienteSocket.getInputStream());
//                DataInputStream flujo_entrada = new DataInputStream(clienteSocket.getInputStream());
//                //Recibimos el nombre del fichero
//                file = flujo_entrada.readUTF();
//                file = file.substring(file.indexOf('\\') + 1, file.length());
//                //Para guardar fichero recibido
//                bos = new BufferedOutputStream(new FileOutputStream(file));
//                while ((in = bis.read(receivedData)) != -1) {
//                    bos.write(receivedData, 0, in);
//                }
//                bos.close();
//                flujo_entrada.close();
//            }
//        } catch (Exception e) {
//            System.err.println(e);
//        }
        } catch (IOException ex) {
            System.out.println("No encontrado");
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new Servidor();
    }

}
