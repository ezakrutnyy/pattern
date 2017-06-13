package ru.pattern.behavioral.state.example1;

/**
 * Created by Евгений on 19.01.2017.
 */
public class DeveloperLifeToGit {
    public static void main(String[] args) {
        StateGit checkOut = new CheckOut();
        WorkGit work = new WorkGit();
        work.setState(checkOut);
        for (int i = 0; i <10; i++) {
            work.execute();
            work.changeStateGit();
        }
    }
}

//Class Context
class WorkGit {
    StateGit state;
    public void setState(StateGit state) {
        this.state = state;
    }

    void changeStateGit() {
        if (state instanceof CheckOut) {
            setState(new Pull());
        } else if (state instanceof Pull) {
            setState(new Commit());
        } else if (state instanceof Commit) {
            setState(new Push());
        } else if (state instanceof  Push) {
            setState(new Pull());
        }
    }

    void execute() {
        state.execute();
    }
}

//Interface State
interface StateGit {
    public void execute();
}

//Concreate State
class CheckOut implements StateGit {
    @Override
    public void execute() {
        System.out.println("CheckOut branch...");
    }
}

class Pull implements StateGit {
    @Override
    public void execute() {
        System.out.println("Pull branch...");
    }
}

class Commit implements StateGit {
    @Override
    public void execute() {
        System.out.println("Commit local...");
    }
}

class Push implements StateGit {
    @Override
    public void execute() {
        System.out.println("Push branch...");
    }
}