// HATEOAS realization
package ua.co.savchuk.REST_demo.rest.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.co.savchuk.REST_demo.rest.controller.WizardRestController;
import ua.co.savchuk.REST_demo.model.Wizard;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class WizardModelAssembler implements RepresentationModelAssembler<Wizard, EntityModel<Wizard>> {
    @Override
    public EntityModel<Wizard> toModel(Wizard wizard) {
        return new EntityModel<>(wizard,
                linkTo(
                        methodOn(WizardRestController.class)
                                .getWizardById(wizard.getId()))
                        .withSelfRel(),

                linkTo(
                        methodOn(WizardRestController.class)
                                .getWizards(wizard.getName()))
                        .withRel("wizards"),

                linkTo(
                        methodOn(WizardRestController.class)
                                .replaceWizard(new Wizard(), wizard.getId()))
                        .withRel("put"),

                linkTo(
                        methodOn(WizardRestController.class)
                                .patchWizard(wizard.getId(), "", ""))
                        .withRel("patch"),

                linkTo(WizardRestController.class)
                        .withRel("delete")
                        .withHref("http://localhost:8080/api/wizards/" + wizard.getId() + "/delete"));
    }
}
