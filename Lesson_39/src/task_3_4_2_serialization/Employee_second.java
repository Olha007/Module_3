package task_3_4_2_serialization;

/**
 * 1. Додайте до класу Employee у пакеті serialization, створеного при виконанні попереднього завдання, приватний
 * метод void writeObject(ObjectOutputStream out) throws IOException, який модифікує поле name об’єкта, додаючи префікс
 * "Mr. " або "Mrs. " в залежності від статі об'єкта, поле address - додаючи префікс "Address: ", та збільшує значення
 * трансієнтного поля SSN на одиницю.
 * 2. Також додайте до класу Employee приватний метод void readObject(ObjectInputStream in) throws IOException, який
 * читає поля серілізованого об’єкта.
 * 3. Виконайте серіалізацію та десеріалізацію об'єкта Employee.
 *
 * @author Olha Nozdriukhina
 * @version 1.0
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Employee_second implements Serializable {
    private String name;
    private int zipcode;
    private String address;
    private transient int SSN;

    private String gender;

    public Employee_second() {
        this.name = name;
        this.zipcode = zipcode;
        this.address = address;
        this.SSN = SSN;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getAddress() {
        return address;
    }

    public int getSSN() {
        return SSN;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee {" +
                "name = " + name + '\'' +
                ", zipcode = " + zipcode +
                ", address = " + address + '\'' +
                ", SSN = " + SSN +
                ", gender = " + gender + '\'' +
                '}';
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        name = (gender.equalsIgnoreCase("male") ? "Mr. " : "Mrs. ") + name;
        address = "Address: " + address;
        SSN += 1;
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}


