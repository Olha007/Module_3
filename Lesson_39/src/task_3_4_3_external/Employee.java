package task_3_4_3_external;

/**
 * 1. Додайте пакет external та скопіюйте до нього клас Employee з пакету serialization, створеного при виконанні
 * попередніх завдань.
 * 2. Виконайте рефакторинг класу Employee так, щоб він імплементував інтерфейс Externalizable.
 * 3. Реалізуйте метод public void writeExternal(ObjectOutput out) throws IOException цього інтерфейсу так, щоб він
 * модифікував поле name об’єкта, додаючи префікс "Mr. " або "Mrs. " в залежності від статі об'єкта, поле address -
 * додавав префікс "Address: " та збільшував значення трансієнтного поля SSN на одиницю.
 * 4. Також реалізуйте метод public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
 * інтерфейсу Externalizable, щоб він читав поля серілізованого об’єкта.
 * 5. Не забудьте додати конструктор за замовчуванням до класу Employee.
 * 6. Виконайте серіалізацію та десеріалізацію об'єкта Employee.
 *
 * @version 1.0
 * @author Olha Nozdriukhina
 */

import java.io.Externalizable;
import java.io.*;

public class Employee implements Externalizable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int zipcode;
    private String address;
    private transient int SSN;
    private String gender;

    public Employee() {
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

    public void writeExternal(ObjectOutput out) throws IOException {
        name = (gender.equalsIgnoreCase("male") ? "Mr. " : "Mrs. ") + name;
        address = "Address: " + address;
        SSN += 1;

        out.writeObject(name);
        out.writeInt(zipcode);
        out.writeObject(address);
        out.writeInt(SSN);
        out.writeObject(gender);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        zipcode = in.readInt();
        address = (String) in.readObject();
        SSN = in.readInt();
        gender = (String) in.readObject();
    }
}


