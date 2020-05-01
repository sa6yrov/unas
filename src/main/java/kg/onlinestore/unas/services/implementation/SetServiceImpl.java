package kg.onlinestore.unas.services.implementation;

import kg.onlinestore.unas.entities.Set;
import kg.onlinestore.unas.repositories.SetRepo;
import kg.onlinestore.unas.services.SetService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SetServiceImpl implements SetService {
    @Autowired
    private SetRepo setRepo;
    @Override
    public List<Set> getAll() {
        return setRepo.findAll();
    }

    @Override
    public Set getById(Long id) {
        Optional<Set> optionalSet = setRepo.findById(id);
        return optionalSet.orElse(null);
    }

    @Override
    public Set save(Set object) {
        return setRepo.save(object);
    }

    @Override
    public void deleteById(Long id) {
        if(getById(id) != null) setRepo.deleteById(id);
    }
}
