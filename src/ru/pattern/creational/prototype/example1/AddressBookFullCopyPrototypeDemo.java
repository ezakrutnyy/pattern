package ru.pattern.creational.prototype.example1;

/**
 * Created by Евгений on 15.12.2016.
 */
public class AddressBookFullCopyPrototypeDemo {
    public static void main(String[] args) {
        AddressBookFullCopyPrototype note = new AddressBookFullCopyPrototype("Petrov Petr Petrovich","Moscow",24,"+7 111-222-3323");
        AddressBookFullCopyPrototype noteCopy =  note.clone();
        note.setGender("Жен");
        noteCopy.setGender("Муж");
        System.out.println(note);
        System.out.println(noteCopy);
    }
}

class AddressBookFullCopyPrototype implements  Cloneable {
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

    public AddressBookFullCopyPrototype(String name, String city, int old, String phone) {
        this.name = name;
        this.city = city;
        this.old = old;
        this.phone = phone;
    }

    public AddressBookFullCopyPrototype clone() {
        try {
            AddressBookFullCopyPrototype p = (AddressBookFullCopyPrototype) super.clone();
            return p;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    @Override
    public String toString() {
        return String.format("Имя %s, город %s, возраст %d, номер телефона %s, пол %s",name,city,old,phone,gender);
    }
}
