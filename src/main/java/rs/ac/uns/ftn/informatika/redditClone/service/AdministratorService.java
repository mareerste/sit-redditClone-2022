package rs.ac.uns.ftn.informatika.redditClone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.Administrator;
import rs.ac.uns.ftn.informatika.redditClone.repository.AdministratorRepository;

import java.util.List;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    public Administrator findOne(String username){return administratorRepository.findById(username).orElseGet(null);}
    public List<Administrator>findAll(){return  administratorRepository.findAll();}
}
