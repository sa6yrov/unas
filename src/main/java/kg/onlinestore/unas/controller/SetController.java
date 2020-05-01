package kg.onlinestore.unas.controller;

import kg.onlinestore.unas.entities.Set;
import kg.onlinestore.unas.services.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/set")
public class SetController {
    @Autowired
    private SetService setService;

    @GetMapping
    public List<Set> getAll(){
        return setService.getAll();
    }

    @GetMapping("/{id}")
    public Set getById(@PathVariable("id")Long id){
        return setService.getById(id);
    }

    @PostMapping
    public Set save (@RequestBody Set set){
        return setService.save(set);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSet(@PathVariable("id") Long id) {
        setService.deleteById(id);
    }
}
