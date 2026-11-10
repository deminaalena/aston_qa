package org.example;

import java.util.HashMap;
import java.util.HashSet;

public class PhoneBook {

    private HashMap<String, HashSet<String>> phonebook = new HashMap<>();

    public void add(String surname, String phoneNumber) {
        if (phonebook.containsKey(surname)) {
            phonebook.get(surname).add(phoneNumber);
        } else {
            phonebook.put(surname, new HashSet<>());
            phonebook.get(surname).add(phoneNumber);
        }
    }

    public HashSet<String> get(String surname) {
        if (phonebook.containsKey(surname)) {
            return phonebook.get(surname);
        } else {
            System.out.println("Фамилии не найдено.");
            return null;
        }

    }
}
