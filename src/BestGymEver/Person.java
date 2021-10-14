package BestGymEver;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Person {

    private String ssn;
    private String name;
    DateTimeFormatter formatWithTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    DateTimeFormatter formatOnlyDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDateTime lastPayment;
    private LocalDateTime lastVisited;

    public Person(List <String> fromFile) {
        this.ssn = fromFile.get(0);
        this.name = fromFile.get(1);
        this.lastPayment = this.parseDate(fromFile.get(2));
        this.lastVisited = this.lastPayment;
    }

    public LocalDateTime parseDate(String dateToParse) {
        LocalDateTime lastPayment = null;
        try {
            lastPayment = LocalDateTime.parse(dateToParse+" 23:59",formatWithTime);
        } catch (DateTimeException d ) {
            d.printStackTrace();
            System.out.println("Kunde inte tolka datum. ");
        }
        return lastPayment;
    }

    public String getSsn() {
        return this.ssn;
    }
    public String getName() {
        return this.name;
    }
    public LocalDateTime getLastPayment() {
        return this.lastPayment;
    }
    public void setLastVisited(LocalDateTime lastVisited) {
        this.lastVisited = lastVisited;
    }
    public LocalDateTime getLastVisited() {

        return lastVisited;
    }

    @Override
    public String toString() {
        if (this.lastVisited.equals(lastPayment)) {
            return name + "\n" + ssn +
                    "\nMedlem från: " + lastPayment.format(formatOnlyDate)+
                    "\nSenaste besök: Okänt. \n";
        } else {
            return name + "\n" + ssn +
                    "\nMedlem från: " + lastPayment.format(formatOnlyDate) +
                    "\nSenaste besök: " + lastVisited.format(formatWithTime)+"\n";
        }
    }

}
