package BestGymEver;

import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Filter {

    public LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    public LocalDateTime oneYearAgo = now.minusYears(1);
    public Path outputPath = Paths.get("src/OUTPUT/returningCustomers.txt");
    InputOutput writePerson = new InputOutput();
    private List<Person> returningCustomers = new ArrayList<>();

    public List<Person> filterOutByDate(List<Person> allPersons) {
        List<Person> payingCustomers = new ArrayList<>();
        for (Person person:allPersons) {
            if (person.getLastPayment().isAfter(oneYearAgo)) {
                payingCustomers.add(person);
            }
        }
        return payingCustomers;
    }

    public void checkSsn(String inputSsn, List<Person> allPersons, boolean isTest) {
        int count = 1;
        for (Person person : allPersons) {
            if (person.getSsn().equals(inputSsn) && person.getLastPayment().isAfter(oneYearAgo)) {
                person.setLastVisited(now);
                if (!isTest) {
                    JOptionPane.showMessageDialog(null, "Välkommen tillbaks, "+person.getName()+"! ");
                    writePerson.writePersonToFile(outputPath, person);
                } else {
                    returningCustomers.add(person);
                    System.out.println("Välkommen tillbaks, "+person.getName()+"! ");
                }
                    break;
            } else if (person.getSsn().equals(inputSsn)) {
                if (!isTest) {
                    JOptionPane.showMessageDialog(null, "Ditt medlemskap har gått ut, " + person.getName() + "! ");
                } else {
                    System.out.println("Ditt medlemskap har gått ut, "+person.getName()+"! ");
                }
                break;
            } else if (!person.getSsn().equals(inputSsn) && count == allPersons.size() && inputSsn != null) {
                if (!isTest) {
                    JOptionPane.showMessageDialog(null, "Du har aldrig varit medlem här! ");
                } else {
                    System.out.println("Du har aldrig varit medlem här! ");
                }
            }
            count++;
        }
    }

    public List<Person> getReturningCustomers() {
        return returningCustomers;
    }


}
