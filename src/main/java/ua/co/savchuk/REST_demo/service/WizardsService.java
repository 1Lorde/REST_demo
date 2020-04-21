package ua.co.savchuk.REST_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.co.savchuk.REST_demo.model.Wizard;
import ua.co.savchuk.REST_demo.repository.WizardsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WizardsService {
    @Autowired
    private WizardsRepository wizardsRepository;

    public WizardsService() {
    }

    public List<Wizard> findAll() {
        var it = wizardsRepository.findAll();
        var wizards = new ArrayList<Wizard>();
        it.forEach(wizards::add);
        return wizards;
    }
    public Optional<Wizard> findById(Long id) {
        return wizardsRepository.findById(id);
    }
    public Wizard save(Wizard wizard){
        return wizardsRepository.save(wizard);
    }
    public Long count() {
        return wizardsRepository.count();
    }
    public void deleteById(Long userId) {
        wizardsRepository.deleteById(userId);
    }
}
