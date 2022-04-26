package rs.ac.uns.ftn.informatika.redditClone.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Administrator;
import rs.ac.uns.ftn.informatika.redditClone.repository.AdministratorRepository;
import rs.ac.uns.ftn.informatika.redditClone.service.AdministratorService;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;
    @Override
    public Administrator findOne(String username){return administratorRepository.findById(username).orElseGet(null);}
    @Override
    public List<Administrator>findAll(){return  administratorRepository.findAll();}

    @Override
    public Administrator save(Administrator administrator) {
        return administratorRepository.save(administrator);
    }
}
