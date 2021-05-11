package ru.pattern.behavioral.state;

public class DeveloperGitCycleStateRunner {

    public static void main(String[] args) {
        Context context = new Context();
        for (int i = 0; i < 10; i++) {
            context.start();
        }
    }
}

class Context {

    private StateGit stateGit;

    public Context() {
        this.stateGit = new CheckOut();
    }

    public void start() {
        stateGit.execute();
    }

    private class CheckOut implements StateGit {

        @Override
        public void execute() {
            System.out.println("Checkout repository......");
            stateGit = new Pull();
        }
    }

    private class Pull implements StateGit {

        @Override
        public void execute() {
            System.out.println("Pull repository......");
            stateGit = new Commit();
        }
    }

    private class Commit implements StateGit {

        @Override
        public void execute() {
            System.out.println("Commit repository......");
            stateGit = new Push();
        }
    }

    private class Push implements StateGit {

        @Override
        public void execute() {
            System.out.println("Push repository......");
            stateGit = new CheckOut();
        }
    }
}


interface StateGit {
    void execute();
}
