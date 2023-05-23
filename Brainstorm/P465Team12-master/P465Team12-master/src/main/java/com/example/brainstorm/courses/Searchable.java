package com.example.brainstorm.courses;

public interface Searchable {

    /**
     * <p>Check if a specific string exists within a Searchable object</p>
     * @param searchedString the string used to search through Searchables
     * @return true if Searchable has searchedString in it
     */
    boolean contains(String searchedString);

}
