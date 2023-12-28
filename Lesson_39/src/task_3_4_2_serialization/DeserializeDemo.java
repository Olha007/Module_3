package task_3_4_2_serialization;


import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DeserializeDemo {
    public static void main(String[] args) {
        try (FileInputStream fileIn = new FileInputStream("employee.ser1");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            Employee_second employee = (Employee_second) in.readObject();

            System.out.println("Deserialization completed. Employee details:");
            System.out.println(employee);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}