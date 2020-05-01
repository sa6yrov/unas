package kg.onlinestore.unas.controller;

import kg.onlinestore.unas.entities.Item;
import kg.onlinestore.unas.models.ItemModel;
import kg.onlinestore.unas.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> getAll(){
        return itemService.getAll();
    }

    @PostMapping
    public Item save (@RequestBody ItemModel itemModel){
        return itemService.create(itemModel);
    }

    @GetMapping("/{id}")
    public Item getById(@PathVariable("id") Long id){
        return itemService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable("id") Long id) {
        itemService.deleteById(id);
    }
}
