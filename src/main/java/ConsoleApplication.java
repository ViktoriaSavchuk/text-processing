import runner.ApplicationRunner;

public class ConsoleApplication {

    public static void main(String[] args) {

        ApplicationRunner applicationRunner = new ApplicationRunner();
        applicationRunner.run(args[1]);
    }

}
