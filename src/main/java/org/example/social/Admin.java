package org.example.social;

import java.util.List;

public class Admin {

    private static List<Person> people;

    public static void printPersonsOlderThan(List<Person> roster, int age) {
        for (Person p : roster) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

    // more general but
    public static void printPersonsWithinAgeRange( List<Person> roster, int low, int high) {
        for (Person p : roster) {
            if (low <= p.getAge() && p.getAge() < high) {
                p.printPerson();
            }
        }
    }

    //  trying to create a separate method for each possible search query can still lead to brittle code.
    // You can instead separate the code that specifies the criteria for which you want to search in a different class.
    public static void printPersons(List<Person> roster, CheckPerson tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    public static void main(String[] args) {
        printPersons(people, new CheckPersonEligibleForSelectiveService());
    }

}
