package com.scg.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/19/13
 * Time: 11:16 AM
 * <p/>
 * Encapsulates the concept of a billable skill.
 */
@SuppressWarnings({"serial", "unchecked"})
public enum Skill implements Serializable {
    PROJECT_MANAGER("Project Manager", 250),
    SOFTWARE_ENGINEER("Software Engineer", 150),
    SOFTWARE_TESTER("Software Tester", 100),
    SYSTEM_ARCHITECT("System Architect", 200),
    UNKNOWN_SKILL("UNKNOWN", 0);

    private final String name;
    private final int rate;

    private Skill(String name, int rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return name;
    }
}
