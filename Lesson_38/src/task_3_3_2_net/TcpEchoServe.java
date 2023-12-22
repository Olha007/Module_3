package task_3_3_2_net;

/**
 * 1. Додайте до пакету net клас TcpEchoServer з методом main.
 * 2. Напишіть програму сервера, який буде прослуховувати на порту 7 TCP запити від клієнтів, що передають рядок
 * тексту і будуть повертати клієнтам той же рядок, але з символами у зворотньому порядку.
 * 3. Напишіть програму TCP-клієнта, що буде відправляти на сервер рядок, введений користувачем з консолі.
 * 4. Перевірте роботу сервера та клієнта.
 *
 * @version 1.0
 * @author Olha Nozdriukhina
 */

import java.io.*;
import java.net.*;

class TcpEchoServer {
    public static void main(String[] args) throws IOException {
        int port = 7;
        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Server listening on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();//прийняття з*єднання від клієнта
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));//читання даних від клієнта
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));//запис

            //построкове читання даних від клієнта
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.write(new StringBuilder(inputLine).reverse().toString());//перевертання рядка і відправка назад до клієнта
                out.newLine();
                out.flush();//примусовий запис у потік
            }

            clientSocket.close();
        }
    }
}

class TcpEchoClient {
    public static void main(String[] args) throws IOException {
        String serverName = "localhost";
        int port = 7;

        Socket socket = new Socket(serverName, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        String userInput;
        while ((userInput = stdIn.readLine()) != null) {
            out.write(userInput);
            out.newLine();
            out.flush();
            System.out.println("Server says: " + in.readLine());
        }

        socket.close();
    }
}