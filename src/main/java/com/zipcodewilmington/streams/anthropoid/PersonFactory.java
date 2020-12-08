package com.zipcodewilmington.streams.anthropoid;

import com.zipcodewilmington.streams.tools.RandomUtils;
import com.zipcodewilmington.streams.tools.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by leon on 5/1/17.
 *
 * @ATTENTION_TO_STUDENTS You are FORBIDDEN from using loops of any sort within the definition of this class.
 */
public final class PersonFactory {

    public PersonFactory() {
        /** this class is not to be instantiated */
    }

    /**
     * @return a new instance of a person with fields of random values
     */
    public Person createRandomPerson() {
        String name = StringUtils.capitalizeFirstChar(RandomUtils.createString('a', 'e', 3));
        String[] aliases = RandomUtils.createStrings('a', 'z', 3, 5);
        boolean isMale = RandomUtils.createBoolean(50);
        long personalId = System.nanoTime();
        Date birthDate = RandomUtils.createDate(1950, 2010);

        Person randomPerson = new Person(name, isMale, personalId, birthDate, aliases);
        return randomPerson;
    }

    /**
     * Section 8.8
     *
     * @param listSize - number of Person objects to create
     * @return - ArrayList of Person objects
     */ // TODO
    public List<Person> createPersonList(int listSize) {
        return new ArrayList<Person>(Collections.nCopies(listSize, createRandomPerson()));
    }

//        this works, but maybe I shouldn't be looping at all, even with stream version below?
//        List<Person> personList = new ArrayList<>();
//        IntStream.range(0, listSize)
//                .mapToObj(p -> createRandomPerson())
//                .forEach(personList::add);
//        return personList;


    /**
     * @param arrayLength - number of Person objects to create
     * @return - Array of Person object
     */ // TODO
    public Person[] createPersonArray(int arrayLength) {
        return createPersonStream(arrayLength).toArray(Person[]::new); // wow, can use method below
//        Below does not work.
//        Person[] personArray = new Person[arrayLength];
//        List<Person> personList = Arrays.asList(personArray);
//        IntStream.range(0, personList.size())
//                .mapToObj(p -> createRandomPerson())
//                .forEach(personList::add);
//                //Stream<Person> stream =
//        //Arrays.stream(personArray).forEach(p-> createRandomPerson());
//        return (Person[]) personList.toArray();

    }


    /**
     * Section 8.2
     *
     * @param streamCount - number of Person objects to create
     * @return - Stream representation of collection of Person objects
     */ // TODO
    public Stream<Person> createPersonStream(int streamCount) {
        Stream<Person> personStream = createPersonList(streamCount).stream();
    return personStream;
    }
}
