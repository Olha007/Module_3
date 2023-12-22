package task_3_3_3_net;

import java.io.*;
import java.net.*;

class UdpEchoServer {
    public static void main(String[] args) throws IOException {
        int port = 7;
        DatagramSocket socket = new DatagramSocket(port);

        //масив байтів для зберігання вхідних даних
        byte[] receiveData = new byte[1024];
        // масив байтів для відправлення даних
        byte[] sendData;

        System.out.println("UDP Server listening on port " + port);

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            //прийом пакету
            socket.receive(receivePacket);
            //конвертація отриманих байтів у рядок
            String receivedText = new String(receivePacket.getData(), 0, receivePacket.getLength());

            //отримання IP-адреси та порту відправника
            InetAddress IPAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            String reversedText = new StringBuilder(receivedText).reverse().toString();

            //підготовка даних для відправлення
            sendData = reversedText.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, clientPort);
            // Відправлення пакету
            socket.send(sendPacket);
        }
    }
}

class UdpEchoClient {
        public static void main(String[] args) throws IOException {
            //для читання даних від користувача
            BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();

            // встановлення IP-адреси сервера
            InetAddress IPAddress = InetAddress.getByName("localhost");
            byte[] sendData;
            byte[] receiveData = new byte[1024];

            while (true) {
                System.out.print("Enter text to send: ");
                String sentence = userReader.readLine();
                // Конвертація рядка у масив байтів
                sendData = sentence.getBytes();

                //створення пакету для відправлення
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 7);
                clientSocket.send(sendPacket);

                // створення пакету для прийому відповіді
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);

                String reversedSentence = new String(receivePacket.getData(), 0, receivePacket.getLength());

                System.out.println("FROM SERVER: " + reversedSentence);

            }
        }
    }