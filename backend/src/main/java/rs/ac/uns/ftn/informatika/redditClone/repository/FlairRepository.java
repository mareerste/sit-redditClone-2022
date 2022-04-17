package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Flair;

public interface FlairRepository extends JpaRepository<Flair,Integer> {
}
