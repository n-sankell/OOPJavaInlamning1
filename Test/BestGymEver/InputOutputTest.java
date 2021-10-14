package BestGymEver;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputOutputTest {

    Path inputPathTest = Paths.get("Test/customersTest.txt");
    Path outputPathTest = Paths.get("Test/returningCustomersTest.txt");
    Path fileToCount = Paths.get("Test/returningCustomersTest.txt");
    InputOutput ioTest = new InputOutput();

    Person personOutputTest1 = new Person(List.of("1234567891", "Sven Svensson", "2021-08-07"));
    Person personOutputTest2 = new Person(List.of("1987654321", "Anja Andersson", "2021-02-10"));
    Person personOutputTest3 = new Person(List.of("1111111111", "Majvor Strömkvist", "2020-12-20"));
    List<Person> outputTestList = List.of(personOutputTest1, personOutputTest2, personOutputTest3);

    @Test
    void readFileTest() {
        int expectedSizeOfListIn = 2;

        assertTrue(ioTest.readFile(inputPathTest).get(1).get(1).equals("Nahema Ninsson"));
        assertFalse(ioTest.readFile(inputPathTest).get(1).get(1).equals("7907281234"));
        assertTrue(ioTest.readFile(inputPathTest).size() == expectedSizeOfListIn);
        assertFalse(ioTest.readFile(inputPathTest).size() == 3);
    }

    @Test
    void validateUserInputTest() {
        String input1 = "7809540908";
        String input2 = "8608220052";
        String felInput1 = "fel";
        String felInput2 = "17636932759827592";
        String felInput3 = "1234";


        assertTrue(ioTest.validateUserInput(input1,true).equals("7809540908"));
        assertFalse(ioTest.validateUserInput(input1,true).equals("7809540909"));
        assertTrue(ioTest.validateUserInput(input2,true).equals("8608220052"));
        System.out.println("FEL INPUT: felinput1: ");
        assertFalse(ioTest.validateUserInput(felInput1,true).equals("7809540909"));
        System.out.println();
        System.out.println("FEL INPUT: felinput2: ");
        assertFalse(ioTest.validateUserInput(felInput2,true).equals("7809540909"));
        System.out.println();
        System.out.println("FEL INPUT: felinput3: ");
        assertFalse(ioTest.validateUserInput(felInput3,true).equals("7809540909"));
        assertNull(ioTest.validateUserInput(null,true));
    }

    @Test
    void writeToFileTest() {
        int expectedLinesOfOutFile;

            expectedLinesOfOutFile = countLinesInFile(outputPathTest) + outputTestList.size();

        int expectedSizeOfList = 3;

        ioTest.writeToFile(outputPathTest, outputTestList);

        assertTrue(countLinesInFile(outputPathTest) == expectedLinesOfOutFile);
        assertFalse(countLinesInFile(outputPathTest) == 5);
        assertTrue(outputTestList.size()==expectedSizeOfList);
    }

    public final int countLinesInFile(Path fileToCount) {
        int lines = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(fileToCount)))) {
            while (reader.readLine() != null)
                lines++;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }




}