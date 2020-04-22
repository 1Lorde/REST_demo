package ua.co.savchuk.REST_demo.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import ua.co.savchuk.REST_demo.rest.assembler.WizardModelAssembler;
import ua.co.savchuk.REST_demo.exceptions.WizardNotFoundException;
import ua.co.savchuk.REST_demo.model.Spell;
import ua.co.savchuk.REST_demo.model.Wizard;
import ua.co.savchuk.REST_demo.service.WizardsService;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WizardRestController {
    @Autowired
    WizardsService wizardsService;
    @Autowired
    WizardModelAssembler assembler;

    @GetMapping("api/wizards")
    public List<Wizard> getWizards(@RequestParam(value = "filter", defaultValue = "") String filter) {
        if (filter.isEmpty())
            return wizardsService.findAll();

        List<Wizard> result = wizardsService.findAll()
                .stream()
                .filter(wizard -> (wizard.getName().toLowerCase())
                        .contains(filter.toLowerCase()))
                .collect(Collectors.toList());

        if (result.isEmpty())
            throw new WizardNotFoundException(filter);
        return result;
    }

    @GetMapping("api/wizards/{id}")
    public EntityModel<Wizard> getWizardById(@PathVariable Long id) {
        return assembler.toModel(
                wizardsService.findById(id).orElseThrow(
                        () -> new WizardNotFoundException(id)
                )
        );
    }

    @PostMapping("/api/wizards")
    public void addWizard(@RequestBody Wizard newWizard) {
        wizardsService.save(newWizard);
    }

    @PutMapping("/api/wizards/{id}")
    public Wizard replaceWizard(@RequestBody Wizard newWizard, @PathVariable Long id) {
        return wizardsService.findById(id)
                .map(wizard -> {
                    wizard.setName(newWizard.getName());
                    wizard.setAge(newWizard.getAge());
                    wizard.setSpells(newWizard.getSpells());
                    return wizardsService.save(wizard);
                })
                .orElseGet(() -> wizardsService.save(newWizard));
    }

    @PatchMapping("/api/wizards/{id}")
    public Wizard patchWizard(@PathVariable Long id,
                              @RequestParam(value = "name", defaultValue = "") String name,
                              @RequestParam(value = "age", defaultValue = "0") String age) {
        return wizardsService.findById(id)
                .map(wizard -> {
                    if(!name.isEmpty()) wizard.setName(name);
                    if(!age.isEmpty()) wizard.setAge(Integer.parseInt(age));
                    return wizardsService.save(wizard);
                })
                .orElseThrow( () -> new WizardNotFoundException(id) );
    }

    @DeleteMapping("/api/wizards/{id}")
    public void deleteWizardById(@PathVariable Long id) {
        wizardsService.deleteById(id);
    }

}



