package org.example.social;

import java.util.List;
import java.util.function.Predicate;

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

        // podria usar una clase anonima para no tener que crear una nueva claes por cada condicion
        // pero la sintaxis es pesada
        printPersons( people,
                new CheckPerson() {
                    public boolean test(Person p) {
                        return p.getGender() == Person.Sex.MALE
                                && p.getAge() >= 18
                                && p.getAge() <= 25;
                    }
                }
        );


        // using lamdba expressions
        printPersons( people,
                (Person p) -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25
        );

        // con el predicado
        printPersonsWithPredicate( people,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25
        );
    }

    // we dont even need to declare check person since we have a predicate interface
    public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

}
