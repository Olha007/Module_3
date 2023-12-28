package task_3_4_3_external;

import java.io.*;

public class SerializeDemo {
    public static void main(String[] args) {

        Employee employee = new Employee();

        employee.setName("Ken Di");
        employee.setZipcode(12345);
        employee.setAddress("Wood St");
        employee.setGender("male");
        employee.setSSN(1111111111);

        System.out.println("Original Employee: " + employee);

        try (FileOutputStream fileOut = new FileOutputStream("employee.ser3");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(employee);
            System.out.println("Serialization completed. Object saved to employee.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
