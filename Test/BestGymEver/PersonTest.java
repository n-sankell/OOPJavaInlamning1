package BestGymEver;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    String dateToParse1 = "2020-08-22";
    String dateToParse2 = "2021-09-21";
    String dateToParse3 = "2019-10-23";
    List<String> testList1 = List.of("test","test",dateToParse3);
    List<String> testList2 = List.of("test","test",dateToParse1);
    Person testPerson1 = new Person(testList1);
    Person testPerson2 = new Person(testList2);

    @Test
    void ConstructorAndParseDateTest() {
        assertTrue(testPerson1.getLastPayment().equals(testPerson1.parseDate(dateToParse3)));
        assertFalse(testPerson1.getLastPayment().equals(testPerson1.parseDate(dateToParse2)));
        assertTrue(testPerson2.getLastPayment().equals(testPerson2.parseDate(dateToParse1)));
    }


}