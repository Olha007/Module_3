package task_3_4_2_serialization;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SerializeDemo {
    public static void main(String[] args) {

        Employee_second employee = new Employee_second();

        employee.setName("Ken Di");
        employee.setZipcode(12345);
        employee.setAddress("Wood St");
        employee.setGender("male");
        employee.setSSN(1111111111);

        System.out.println("Employee: " + employee);

        try (FileOutputStream fileOut = new FileOutputStream("employee.ser1");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(employee);
            System.out.println("Serialization completed. Object saved to employee.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
