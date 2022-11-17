import Console.Colors;
import Console.ConsoleManager;
import views.WindowsManager;

public class App {
    public static void main(String[] args) throws Exception {
        ConsoleManager.println(Colors.GREEN, "started");
        WindowsManager.handleLauncher();
    }
}