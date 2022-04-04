package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.informatika.redditClone.model.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator,String> {
}
