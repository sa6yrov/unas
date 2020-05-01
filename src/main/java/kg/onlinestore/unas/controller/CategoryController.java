package kg.onlinestore.unas.controller;

import kg.onlinestore.unas.entities.Category;
import kg.onlinestore.unas.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @PostMapping
    public Category save (@RequestBody Category category){
        return categoryService.save(category);
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable("id") Long id){
        return  categoryService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
    }
}

