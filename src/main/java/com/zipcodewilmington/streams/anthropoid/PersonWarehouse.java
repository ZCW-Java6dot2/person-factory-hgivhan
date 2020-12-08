package com.zipcodewilmington.streams.anthropoid;

import com.zipcodewilmington.streams.tools.ReflectionUtils;
import com.zipcodewilmington.streams.tools.logging.LoggerHandler;
import com.zipcodewilmington.streams.tools.logging.LoggerWarehouse;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by leon on 5/29/17.
 * The warehouse is responsible for storing, retrieving, and filtering personSequence
 *
 * @ATTENTION_TO_STUDENTS You are FORBIDDEN from using loops of any sort within the definition of this class.
 */
public final class PersonWarehouse implements Iterable<Person> {
    private final LoggerHandler loggerHandler = LoggerWarehouse.getLogger(PersonWarehouse.class);
    private final List<Person> people = new ArrayList<>();

    /**
     * @param person the Person object to add to the warehouse
     * @ATTENTION_TO_STUDENTS You are FORBIDDEN from modifying this method
     */
    public void addPerson(Person person) {
        loggerHandler.disbalePrinting();
        loggerHandler.info("Registering a new person object to the person warehouse...");
        loggerHandler.info(ReflectionUtils.getFieldMap(person).toString());
        people.add(person);
    }

    /**
     * @return list of names of Person objects
     */ // TODO
    public List<String> getNames() {
        List <String> names = people.stream().map(Person::getName).collect(Collectors.toList());
        return names;
    } // map makes it so that I'm not returning the object Person, I'm the names of the persons


    /**
     * @return list of uniquely named Person objects
     */ //TODO
    public Stream<Person> getUniquelyNamedPeople() {
        Set<String> set = new HashSet<>(); //set removes duplicates, had people.size() in for the hashset size, but don't need that for the test to run
        return people.stream().filter(p -> set.add(p.getName()));


    }


    /**
     * @param character starting character of Person objects' name
     * @return a Stream of respective
     */ //TODO
    public Stream<Person> getUniquelyNamedPeopleStartingWith(Character character) {
        Stream<Person> uniquePeople = getUniquelyNamedPeople();
        return uniquePeople.filter(person -> person.getName().charAt(0) == character);
    }

    /**
     * @param n first `n` Person objects
     * @return a Stream of respective
     */ //TODO
    public Stream<Person> getFirstNUniquelyNamedPeople(int n) {

        return getUniquelyNamedPeople().limit(n);
    }

    /**
     * @return a mapping of Person Id to the respective Person name
     */ // TODO
    public Map<Long, String> getIdToNameMap() {
        return people.stream().collect(Collectors.toMap(Person::getPersonalId, Person::getName));
    } //collect turns the stream into something concrete and ends it, so you can't do anything else on the stream after collect, a terminal operation (you could turn what you collected into a new stream)


    /**
     * @return Stream of Stream of Aliases
     */ // TODO
    public Stream<Stream<String>> getNestedAliases() {
        return people.stream().map(person -> Arrays.stream(person.getAliases()));
    } //one person has multiple aliases (an array of aliases)
    //take array list of people, stream it and map it so we aren't returning people anymore
    //for each person, we are returning a stream of people, and within each person is a stream of aliases

    /**
     * @return Stream of all Aliases
     */ // TODO
    public Stream<String> getAllAliases() {
       return getNestedAliases().flatMap(Function.identity());
        // could this work with a lambda?: return getNestedAliases().flatMap(alias -> alias);
    } //flatmap un-nests things
    //what is Function.identity()?

    // DO NOT MODIFY
    public Boolean contains(Person p) {
        return people.contains(p);
    }

    // DO NOT MODIFY
    public void clear() {
        people.clear();
    }

    // DO NOT MODIFY
    public int size() {
        return people.size();
    }

    @Override // DO NOT MODIFY
    public Iterator<Person> iterator() {
        return people.iterator();
    }
}
