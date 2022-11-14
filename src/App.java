import Console.Colors;
import Console.ConsoleManager;
import Views.Game;
import Views.Launcher;

public class App {
    public static void main(String[] args) throws Exception {
        ConsoleManager.println(Colors.GREEN, "started");
        new Launcher();
    }
}
