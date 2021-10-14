package BestGymEver;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilterTest {

    LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    LocalDateTime oneYearAgo = now.minusYears(1);

    Person testPerson1 = new Person (List.of("8608220052", "Niklas", "2021-09-21"));
    Person testPerson2 = new Person (List.of("8705030207", "Olle", "2019-12-12"));
    Person testPerson3 = new Person (List.of("1610025726", "Harriet", "2020-03-06"));
    List<Person> testList = List.of(testPerson1,testPerson2,testPerson3);
    Filter testFilter = new Filter();

    @Test
    void filterOutByDateTest() {
        int expectedSizeA = 1;
        assertTrue(testFilter.filterOutByDate(testList).get(0).equals(testPerson1));
        assertFalse(testFilter.filterOutByDate(testList).get(0).equals(testPerson2));
        assertTrue(testFilter.filterOutByDate(testList).size()==expectedSizeA);
        assertTrue(testPerson1.getLastPayment().isAfter(oneYearAgo));
    }

    @Test
    void checkSsnTest() {
        testFilter.checkSsn(testPerson1.getSsn(),testList,true);
        testFilter.checkSsn(testPerson2.getSsn(),testList,true);
        testFilter.checkSsn("1234512345",testList,true);
    }

    @Test
    void testCheckSsnReturn() {
        int expectedSizeB = 1;
        testFilter.checkSsn(testPerson1.getSsn(),testList,true);
        assertTrue(testFilter.getReturningCustomers().get(0).getLastVisited().equals(now));
        assertTrue(testFilter.getReturningCustomers().size()==expectedSizeB);
    }


}