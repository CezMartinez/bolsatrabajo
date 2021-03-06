package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.catalog.Institution;
import com.bolsaTrabajo.repositories.InstitutionRepository;
import com.bolsaTrabajo.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class InstitutionServiceImpl implements InstitutionService {


    @Autowired
    private InstitutionRepository institutionRepository;

    @Override
    public void save(Institution institution) {
        institutionRepository.save(institution);
    }

    @Override
    public Optional<Institution> findInstitutionById(int id) {
        return institutionRepository.findById(id);
    }

    @Override
    public Institution findInstitutionByCode(String code) {
        return institutionRepository.findByInstitutionCode(code);
    }

    @Override
    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    @Override
    public void update(Institution currentInstitution) {
        this.save(currentInstitution);
    }
}
