import java.io.*;
import java.net.*;

class Cliente
{
   private static int portaServidor = 9871;
   private static byte[] sendData = new byte[1024];
   private static byte[] receiveData = new byte[1024];

   public static byte[] lerString () throws Exception {
      System.out.println("Insira a mensagem que deseja criptografar");
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      return in.readLine().getBytes();
   }

   public static void main(String args[]) throws Exception
   {
      DatagramSocket clientSocket = new DatagramSocket();
      InetAddress ipServidor = InetAddress.getByName("192.168.0.8");
      sendData = lerString();

      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipServidor, portaServidor);
      clientSocket.send(sendPacket);

      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      clientSocket.receive(receivePacket);
      clientSocket.close();

      System.out.println("Mensagem  criptografada recebida do servidor:"+"\n"+new String(receivePacket.getData()));
   }
}
