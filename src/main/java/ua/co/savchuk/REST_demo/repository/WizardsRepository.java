package ua.co.savchuk.REST_demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.co.savchuk.REST_demo.model.Wizard;

import java.util.List;

@Repository
public interface WizardsRepository extends CrudRepository<Wizard, Long> {
}
