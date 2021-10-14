package BestGymEver;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConvertInputTest {

    LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    LocalDateTime oneYearAgo = now.minusYears(1);
    ConvertInput cTest = new ConvertInput();
    Path pathTest = Paths.get ("Test/customersTest.txt");
    InputOutput ioTest = new InputOutput();
    List<List<String>> testList = List.of(List.of("198608220052", "Niklas Sankell", "2020-04-20"),List.of("1610025726","Harriet Sandström","2021-07-04"));

    @Test
    void createPersonListTest() {
        assertTrue(cTest.createPersonList(ioTest.readFile(pathTest)).get(0).getName().equals("Mitsuko Mayotte"));
        assertFalse(cTest.createPersonList(ioTest.readFile(pathTest)).get(1).getSsn().equals("7907281234"));
        assertTrue(cTest.createPersonList(testList).get(0).getName().equals("Niklas Sankell"));
        assertTrue(cTest.createPersonList(testList).get(1).getSsn().equals("1610025726"));
        assertTrue(cTest.createPersonList(testList).get(0).getLastPayment().isBefore(oneYearAgo));
    }
}