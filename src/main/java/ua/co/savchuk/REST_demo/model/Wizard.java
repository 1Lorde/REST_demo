package ua.co.savchuk.REST_demo.model;


import javax.persistence.*;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

@Entity
public class Wizard {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    private int age;

    @ElementCollection(targetClass = Spell.class)
    public Set<Spell> spells;

    public Wizard() { }

    public Wizard(String name, int age, Spell ...spells) {
        this.name = name;
        this.age = age;
        this.spells = EnumSet.noneOf(Spell.class);
        this.spells.addAll(Arrays.asList(spells));
    }

    public Long getId() { return id; }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Set<Spell> getSpells() { return spells; }

    public void setName(String name) { this.name = name; }

    public void setAge(int age) { this.age = age; }

    public void setSpells(Set<Spell> spells) { this.spells = spells; }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder("Wizard{ " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age + ", spells=");
        for (Spell spell : spells)
            description.append(spell).append(" ");

        description.append("}");
        return description.toString();
    }
}
