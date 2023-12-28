package task_3_4_1_serialization;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SerializeDemo {
    public static void main(String[] args) {

        Employee employee = new Employee();

        employee.setName("Ken Di");
        employee.setZipcode(12345);
        employee.setAddress("Wood St");
        employee.setSSN(1111111111);

        System.out.println(employee);

        try (FileOutputStream fileOut = new FileOutputStream("employee.ser");

             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(employee);
            System.out.println("Serialization completed. Object saved to employee.ser");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
