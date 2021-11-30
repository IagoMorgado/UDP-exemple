import java.io.*;
import java.net.*;

class Servidor
{
   private static int portaServidor = 9871;
   private static byte[] receiveData;
   private static byte[] sendData;
   
   //Criptografa a mensagem recebida por meio do ciframento de césar com chave igual a 3
   public static String cifraString(String str){
		String str2="";
		for(int i=0;i<str.length();i++){
			char ch=str.charAt(i);
         int i2=0;
         if ((int)ch!=0){
			   i2=ch+3;
         }
			str2+=(char)i2; 
		}
		return str2;
	}

   public static void main(String args[]) throws Exception
   {
      DatagramSocket serverSocket = new DatagramSocket(portaServidor);

      while(true) 
      {
         receiveData = new byte[1024];
         sendData = new byte[1024];

         DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

         System.out.println("Aguardando datagrama do cliente....");
         serverSocket.receive(receivePacket);

         System.out.println("Mensagem recebida para executar criptografia: " + new String(receivePacket.getData()));
         InetAddress ipCliente = receivePacket.getAddress();
         int portaCliente = receivePacket.getPort();
         //executa a criptografia 
         sendData= (cifraString(new String(receivePacket.getData()))).getBytes();

         DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipCliente, portaCliente);
         serverSocket.send(sendPacket);
         System.out.println("Enviado...");
      }
   }
}
