/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tram.depot;

/**
 *
 * @author Martin
 */
public class Driver {
    
    private String name,surname;
    private int experience;
    
    public Driver(String name, String surname, int experience) {
        this.name = name;
        this.surname = surname;
        this.experience = experience;
    }
    
    @Override
    public String toString() {
        return "Driver with name: " + name.toLowerCase() + ", surname: " + surname.toLowerCase() + " and experience: " + Experience.intToString(experience);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getExperience() {
        return experience;
    }
    
}
