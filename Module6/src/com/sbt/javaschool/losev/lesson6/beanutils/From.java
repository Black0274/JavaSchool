package com.sbt.javaschool.losev.lesson6.beanutils;

public class From {
    private int intVar = 1;
    private String stringVar = "from";
    private float numberVar = 1f;
    private String closedStringVar = "closedFrom";
    private int onlyInFromVar = 99;

    public int getIntVar() {
        return intVar;
    }

    public void setIntVar(int intVar) {
        this.intVar = intVar;
    }

    public String getStringVar() {
        return stringVar;
    }

    public void setStringVar(String stringVar) {
        this.stringVar = stringVar;
    }

    public Number getNumberVar() {
        return numberVar;
    }

    public void setNumberVar(float numberVar) {
        this.numberVar = numberVar;
    }

    private String getClosedStringVar() {
        return closedStringVar;
    }

    private void setClosedStringVar(String closedStringVar) {
        this.closedStringVar = closedStringVar;
    }

    public int getOnlyInFromVar() {
        return onlyInFromVar;
    }

    public void setOnlyInFromVar(int onlyInFromVar) {
        this.onlyInFromVar = onlyInFromVar;
    }
}
