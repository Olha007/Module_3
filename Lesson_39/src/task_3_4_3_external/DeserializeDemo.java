package task_3_4_3_external;

import java.io.*;

public class DeserializeDemo {
    public static void main(String[] args) {

        try (FileInputStream fileIn = new FileInputStream("employee.ser3");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            Employee employee = new Employee();
            in.readObject();

            System.out.println("Deserialization completed. Employee details:");
            System.out.println(employee);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}