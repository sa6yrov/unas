package kg.onlinestore.unas.services.implementation;

import kg.onlinestore.unas.entities.Category;
import kg.onlinestore.unas.repositories.CategoryRepo;
import kg.onlinestore.unas.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<Category> getAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getById(Long id) {
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        return optionalCategory.orElse(null);
    }

    @Override
    public Category save(Category object) {
        return categoryRepo.save(object);
    }

    @Override
    public void deleteById(Long id) {
        if(getById(id) != null) categoryRepo.deleteById(id);
    }
}
