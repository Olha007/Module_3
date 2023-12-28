package task_3_4_1_serialization;

/**
 * 1. Додайте пакет serialization.
 * 2. Додайте до цього пакету клас Employee із приватними полями: String name, int zipcode, String address, int SSN
 * (SSN - Social Security number - номер соціального страхування). Додайте до цього класу геттери та сетери
 * та перевизначте метод toString(). Додайте до поля int SSN модифікатор transient.
 * 3. Додайте до цього ж пакету клас SerializeDemo з методом main(String[] args), у якому створюється екземпляр
 * об’єкта Employee з довільними значеннями полів та виконується його серіалізація до файлу.
 * 4. Додайте до цього ж пакету клас DeserializeDemo, з методом main(String[] args), у якому виконується десеріалізація
 * об’єкта Employee, серіалізованого у класі SerializeDemo, та виведення його даних на екран.
 * 5. Виконайте серіалізацію та десеріалізацію об'єкта Employee. Зверніть увагу на значення поля int SSN десеріалізованого об'єкта.
 * 6. За допомогою шістнадцяткового редактора вивчіть вміст файлу серіалізації. Наведіть інформацію зі значеннями полів
 * об'єкта, збережених у файлі серіалізації.
 *
 * @version 1.0
 * @author Olha Nozdriukhina
 */

import java.io.Serializable;
public class Employee implements Serializable {
    private String name;
    private int zipcode;
    private String address;
    private transient int SSN;

    public Employee() {
        this.name = name;
        this.zipcode = zipcode;
        this.address = address;
        this.SSN = SSN;
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

    @Override
    public String toString() {
        return "Employee{" +
                "name = " + name + '\'' +
                ", zipcode = " + zipcode +
                ", address = " + address + '\'' +
                ", SSN = " + SSN +
                '}';
    }
}
