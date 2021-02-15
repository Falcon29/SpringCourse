/*
 *
 * =======================================================================
 *
 * Copyright (c) 2009-2021 Sony Network Entertainment International, LLC. All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Sony Network Entertainment International, LLC.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * Sony Network Entertainment International, LLC.
 *
 * =======================================================================
 *
 * For more information, please see http://www.sony.com/SCA/outline/corporation.shtml
 *
 */

package ru.alishev.springcourse.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ru.alishev.springcourse.models.Person;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();        //условная база данных

        people.add( new Person(++PEOPLE_COUNT, "Tom", 27, "anus@dranus.com") );
        people.add( new Person(++PEOPLE_COUNT, "Bob", 33, "123@321.123") );
        people.add( new Person(++PEOPLE_COUNT, "Mike", 81, "ebobo@an.na") );
        people.add( new Person(++PEOPLE_COUNT, "Barbara", 41, "hobana!@!banaho.tu") );
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter( person -> person.getId() == id ).findAny().orElse( null );
    }

    public void save(Person person) {
        person.setId( ++PEOPLE_COUNT );
        people.add(person);
    }

    public void update(int id, Person updatePerson) {
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatePerson.getName());
        personToBeUpdated.setAge(updatePerson.getAge());
        personToBeUpdated.setEmail(updatePerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
