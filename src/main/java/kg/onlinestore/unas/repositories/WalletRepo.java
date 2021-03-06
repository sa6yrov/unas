package kg.onlinestore.unas.repositories;

import kg.onlinestore.unas.entities.User;
import kg.onlinestore.unas.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Long> {
    Wallet findByUser(User user);
}
