package ru.pattern.creational.prototype.example1;

/**
 * Created by Евгений on 15.12.2016.
 */
public class AddressBookPartCopyPrototypeDemo {
    public static void main(String[] args) {
        AddressBookPartCopyPrototype note = new AddressBookPartCopyPrototype("Ivanov Ivan Ivanovich","Yaroslavl",25,"+7 222-222-3323");
        AddressBookPartCopyPrototype noteCopy =  note.copy();
        noteCopy.setOld(34);
        System.out.println(note);
        System.out.println(noteCopy);
    }
}

class AddressBookPartCopyPrototype{
    private String name;
    private String city;
    private int old;
    private  String phone;
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public AddressBookPartCopyPrototype(String name, String city, int old, String phone) {
        this.name = name;
        this.city = city;
        this.old = old;
        this.phone = phone;
    }
    public AddressBookPartCopyPrototype(String name, String city) {
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("Имя %s, город %s, возраст %d, номер телефона %s, пол %s",name,city,old,phone,gender);
    }

    public AddressBookPartCopyPrototype copy() {
        return new AddressBookPartCopyPrototype(name, city);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}