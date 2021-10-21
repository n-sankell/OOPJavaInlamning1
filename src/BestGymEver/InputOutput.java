package BestGymEver;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InputOutput {

    DateTimeFormatter formatWithTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    DateTimeFormatter formatOnlyDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<List<String>> readFile(Path inputPath) {
        String a;
        String b;
        String ab;
        List<List<String>> fromFile = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(String.valueOf(inputPath)))) {
            while ((a = read.readLine()) != null) {
                b = read.readLine();
                if (b != null) {
                    ab = a + ", " + b;
                    fromFile.add(List.of(ab.split(", ")));
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
            System.out.println("Kunde inte läsa från fil. ");
            System.exit(0);

        } catch (Exception ex) {
            System.out.println("Okänt fel. ");
            ex.printStackTrace();
            System.exit(0);
        }
        return fromFile;
    }

    public String validateUserInput(String forTesting, boolean isTest) {
        String input;
        while (true) {
            if (!isTest) {
                input = JOptionPane.showInputDialog(null, "Ange besökarens personnummer (tio siffror). ");
            } else {
                input = forTesting;
            }
            if (input == null) {
                if (!isTest) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Vill du avsluta? ", "Avsluta?", JOptionPane.YES_NO_OPTION);
                    if (confirm == 0) {
                        return null;
                    }
                } else {
                    return null;
                }

            } else {
                input = input.trim();
                if (!input.matches("^[0-9]*$")) {
                    if (!isTest) {
                        JOptionPane.showMessageDialog(null, "Ange enbart siffror i personnumret. ");
                    } else {
                        System.out.println("Ange enbart siffror i personnumret. ");
                        return input;
                    }
                } else if (input.length() < 10) {
                    if (!isTest) {
                        JOptionPane.showMessageDialog(null, "För få siffror i personnumret. ");
                    } else {
                        System.out.println("För få siffror i personnumret. ");
                        return input;
                    }
                } else if (input.length() > 10) {
                    if (!isTest) {
                        JOptionPane.showMessageDialog(null, "För många siffror i personnumret. ");
                    } else {
                        System.out.println("För många siffror i personnumret. ");
                        return input;
                    }
                } else {
                    return input;
                }
            }
        }
    }

    public void writeToFile(Path outputPath, List<Person> returningCustomers) {
        try (PrintWriter write = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(String.valueOf(outputPath), true))))) {
            for (Person customer : returningCustomers) {
                if (customer.getLastVisited().equals(customer.getLastPayment())) {
                    write.println(customer.getName() +
                            "\tPersonnummer: " + customer.getSsn() +
                            "\tMedlem sedan: " + customer.getLastPayment().format(formatOnlyDate) +
                            "\tSenaste besök: Okänt");
                } else {
                    write.println(customer.getName() +
                            "\tPersonnummer: " + customer.getSsn() +
                            "\tMedlem sedan: " + customer.getLastPayment().format(formatOnlyDate) +
                            "\tSenaste besök: " + customer.getLastVisited().format(formatWithTime));
                }
            }
            write.flush();
        } catch (FileNotFoundException fn) {
            System.out.println("Filen kunde inte hittas. ");
            fn.printStackTrace();
            System.exit(0);

        } catch (Exception ex) {
            System.out.println("Det gick inte att skriva till filen. ");
            ex.printStackTrace();
            System.exit(0);
        }
    }

    public void writePersonToFile(Path outputPath, Person customer) {
        try (PrintWriter write = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(String.valueOf(outputPath), true))))) {
            if (customer.getLastVisited().equals(customer.getLastPayment())) {
                write.println(customer.getName() +
                        "\tPersonnummer: " + customer.getSsn() +
                        "\tMedlem sedan: " + customer.getLastPayment().format(formatOnlyDate) +
                        "\tSenaste besök: Okänt");
            } else {
                write.println(customer.getName() +
                        "\tPersonnummer: " + customer.getSsn() +
                        "\tMedlem sedan: " + customer.getLastPayment().format(formatOnlyDate) +
                        "\tSenaste besök: " + customer.getLastVisited().format(formatWithTime));
            }
            write.flush();

    } catch (FileNotFoundException fn) {
        System.out.println("Filen kunde inte hittas. ");
        fn.printStackTrace();
        System.exit(0);

    } catch (Exception ex) {
        System.out.println("Okänt fel. ");
        ex.printStackTrace();
        System.exit(0);
    }
}

}
