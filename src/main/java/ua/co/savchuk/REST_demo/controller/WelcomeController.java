package ua.co.savchuk.REST_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ua.co.savchuk.REST_demo.model.Spell;
import ua.co.savchuk.REST_demo.model.Wizard;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Controller
public class WelcomeController {
    RestTemplate restTemplate = new RestTemplate();
    Random random = new Random();
    int randomIndex;

    @GetMapping("/")
    public String welcome(@RequestParam(name="name", required=false, defaultValue="Doe") String name, Model model) {
        model.addAttribute("name", name);

        Wizard[] wizards = restTemplate.getForObject("http://localhost:8080/api/wizards/", Wizard[].class);
        randomIndex = random.nextInt(wizards.length);
        model.addAttribute("wizard", wizards[randomIndex].getName());

        Spell[] spells = restTemplate.getForObject("http://localhost:8080/api/spells/", Spell[].class);
        randomIndex = random.nextInt(spells.length);
        model.addAttribute("spell", spells[randomIndex].name());

        int maxAge = Arrays.stream(wizards)
                .mapToInt(Wizard::getAge)
                .max()
                .getAsInt();
        model.addAttribute("maxAge", maxAge);

        int minAge = Arrays.stream(wizards)
                .mapToInt(Wizard::getAge)
                .min()
                .getAsInt();
        model.addAttribute("minAge", minAge);

        if (name.toLowerCase().equals("voldemort"))
            throw new InvalidParameterException();

        return "welcome";
    }



}
