package BestGymEver;

import java.util.ArrayList;
import java.util.List;

public class ConvertInput {

    public List<Person> createPersonList(List<List<String>> fromFile) {

        List<Person> allPersons = new ArrayList<>();
        int count = 0;
        for (List<String> toPerson : fromFile) {
            allPersons.add(new Person(fromFile.get(count)));
            count++;
        }

        return allPersons;
    }


}
