package ua.co.savchuk.REST_demo.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.co.savchuk.REST_demo.model.Spell;

import java.util.EnumSet;

@RestController
public class SpellRestController {

    @GetMapping("api/spells")
    public EnumSet<Spell> getSpells() {
        return EnumSet.allOf(Spell.class);
    }

}
