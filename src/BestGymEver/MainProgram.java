package BestGymEver;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MainProgram {

    public Path inputPath = Paths.get("src/INPUT/customers.txt");
    public Path outputPath = Paths.get("src/OUTPUT/returningCustomers.txt");
    public String loop = "turnsNullToBreakLoop";

    public void useProgram() {

        InputOutput io = new InputOutput();
        ConvertInput c = new ConvertInput();
        Filter f = new Filter();

        List<List<String>> allInfo = io.readFile(inputPath);
        List<Person> allPersons = c.createPersonList(allInfo);

        while (loop != null) {
            loop = io.validateUserInput("ForTesting", false);
            f.checkSsn(loop, allPersons, false);
        }
    }

    public MainProgram() {
        useProgram();
    }

    public static void main(String[] args) {
        new MainProgram();
    }
}
