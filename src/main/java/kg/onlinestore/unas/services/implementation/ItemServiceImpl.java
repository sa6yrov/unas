package kg.onlinestore.unas.services.implementation;

import kg.onlinestore.unas.entities.Category;
import kg.onlinestore.unas.entities.Item;
import kg.onlinestore.unas.models.ItemModel;
import kg.onlinestore.unas.repositories.ItemRepo;
import kg.onlinestore.unas.services.CategoryService;
import kg.onlinestore.unas.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepo itemRepo;
    
    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Item> getAll() {
        return itemRepo.findAll();
    }

    @Override
    public Item getById(Long id) {
        Optional<Item> optionalItem = itemRepo.findById(id);
        return optionalItem.orElse(null);
    }

    @Override
    public Item save(Item object) {
        return itemRepo.save(object);
    }

    @Override
    public void deleteById(Long id) {
        if(getById(id) != null) itemRepo.deleteById(id);
    }

    @Override
    public Item create(ItemModel itemModel) {
        Item item = new Item();
        Category category = categoryService.getById(itemModel.getCategory());
        item.setItemName(itemModel.getItemName());
        item.setCategory(category);
        item.setPrice(itemModel.getPrice());
        item.setDiscountPercentages(itemModel.getDiscountPercentages());
        
        return save(item);
    }

    @Override
    public List<Item> findAllByCategory_Id(Long id) {
        return itemRepo.findAllByCategory_Id(id);
    }
}
