package rs.ac.uns.ftn.informatika.redditClone.service;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Flair;

import java.util.List;

public interface FlairService {
    Flair findOne(Integer id);
    List<Flair> findAll();
    Flair save (Flair flair);
    void delete (Flair flair);
}
