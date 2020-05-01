package kg.onlinestore.unas.repositories;

import kg.onlinestore.unas.entities.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepo extends JpaRepository<Set, Long> {
}
