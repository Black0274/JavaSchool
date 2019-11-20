package com.sbt.javaschool.losev.lesson6;

public class Person {
    public static final String OBAMA = "OBAM";
    static final String PUTIN = "PUTIN";
    protected static final String BONAPARTE = "BONAPARTE";
    private static final String BISMARK = "BISMARK";
    private static final int birthAge = 0;

    private final boolean man;
    private final String name;
    private Person spouse;
    private int age;

    public boolean isMan() {
        return man;
    }

    public String getName() {
        return name;
    }

    public String getSpouseName() {
        if (this.spouse != null) {
            return spouse.name;
        }
        return "—";
    }

    public int getAge() {
        return age;
    }

    public void getNothing() {}

    public void increaseAge() {
        this.age += 1;
    }

    public Person(boolean man, String name, int age) {
        this.man = man;
        this.name = name;
        this.age = age;
    }

    /**
     * This method checks gender of persons. If genders are not equal - tries to marry.
     * If one of them has another spouse - execute divorce(sets spouse = null for husband and wife. Example: if both persons have spouses - then divorce will set 4 spouse to null) and then executes marry().
     * @param person - new husband/wife for this person.
     * @return - returns true if this person has another gender than passed person they are adults and they are not husband and wife, false otherwise
     */
    public boolean marry(Person person) {
        if (this.age < 18 || person.getAge() < 18){
            return false;
        }
        if (this.isMan() != person.isMan()){
            if (this.spouse != null) {
                if (this.spouse.equals(person)){
                    return false;
                }
                this.spouse.divorce();
                this.divorce();
            }
            if (person.spouse != null){
                person.spouse.divorce();
                person.divorce();
            }
            this.spouse = person;
            person.spouse = this;
            return true;
        }
        return false;
    }

    /**
     * Sets spouse = null if spouse is not null
     * @return true - if person status has been changed
     */
    public boolean divorce() {
        if (this.spouse == null) {
            return false;
        }
        this.spouse = null;
        return true;
    }
}

