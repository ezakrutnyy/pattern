package ru.pattern.behavioral.mediator;

import com.google.common.collect.Lists;

import java.util.List;

public class MediatorChatRunner {

    public static void main(String[] args) {

        // создаем посредника
        SimpleTextChat chat = new SimpleTextChat();

        User admin = new Admin("Evgeny", chat);
        User user1 = new SimpleUser("Alex", chat);
        User user2 = new SimpleUser("Michael", chat);

        chat.addUser(admin);
        chat.addUser(user1);
        chat.addUser(user2);

        user1.send("Hello! My name is Alex. I'm from Saint-Petersburg.");
        System.out.println("********************");
        user2.send("Hello! My name is Michael. I come from Cheboksary.");
        System.out.println("********************");
    }
}

interface Chat {

    void addUser(User user);

    void sendMessage(String msg, User user);

}

class SimpleTextChat implements Chat {

    private final List<User> users;

    public SimpleTextChat() {
        this.users = Lists.newArrayList();
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void sendMessage(String msg, User user) {
        for (User u : this.users) {
            if (u != user) {
                u.receive(msg);
            }
        }
    }
}

abstract class User {

    protected final String name;

    protected final Chat chat;

    public User(String name, Chat chat) {
        this.name = name;
        this.chat = chat;
    }

    abstract void send(String msg);

    abstract void receive(String msg);

}


class Admin extends User {

    public Admin(String name, Chat chat) {
        super(name, chat);
    }

    @Override
    void send(String msg) {
        super.chat.sendMessage(msg, this);
    }

    @Override
    void receive(String msg) {
        System.out.println(this.name + " receiving message{ " + msg + "}");
    }
}

class SimpleUser extends User {

    public SimpleUser(String name, Chat chat) {
        super(name, chat);
    }

    @Override
    void send(String msg) {
        super.chat.sendMessage(msg, this);
    }

    @Override
    void receive(String msg) {
        System.out.println(this.name + " receiving message{ " + msg + "}");
    }
}
