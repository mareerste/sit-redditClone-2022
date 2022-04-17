package rs.ac.uns.ftn.informatika.redditClone.service;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Administrator;

import java.util.List;

public interface AdministratorService {
    Administrator findOne(String username);
    List<Administrator> findAll();
}
