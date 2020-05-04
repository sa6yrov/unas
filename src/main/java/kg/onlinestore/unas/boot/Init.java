package kg.onlinestore.unas.boot;

import kg.onlinestore.unas.entities.Category;
import kg.onlinestore.unas.entities.User;
import kg.onlinestore.unas.entities.Wallet;
import kg.onlinestore.unas.enums.Currency;
import kg.onlinestore.unas.models.ItemModel;
import kg.onlinestore.unas.services.CategoryService;
import kg.onlinestore.unas.services.ItemService;
import kg.onlinestore.unas.services.UserService;
import kg.onlinestore.unas.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Init implements CommandLineRunner {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        //Admin
        User admin = userService.save(new User().builder().login("chef").password("chef").email("chef@gmail.com").isActive(true).build());

        //Store's wallet
        walletService.save(new Wallet().builder().requisite("chef0102").balance(new BigDecimal(1000000)).currency(Currency.KGZ).user(admin).bankCard("VISA").build());

        //category initializing
        categoryService.save(new Category().builder().categoryName("computer hardware").build());
        categoryService.save(new Category().builder().categoryName("camera, photo & accessories").build());
        categoryService.save(new Category().builder().categoryName("mobile phones & accessories").build());
        categoryService.save(new Category().builder().categoryName("laptops").build());
        categoryService.save(new Category().builder().categoryName("earphone & headphone").build());

        //item initializing
        itemService.create(new ItemModel().builder()
                .itemName("cpu intel i3-10600k")
                .price(new BigDecimal(13000))
                .discountPercentages(0)
                .categoryId(1L)
                .build());

        itemService.create(new ItemModel().builder()
                .itemName("cpu intel i5-10600k")
                .price(new BigDecimal(19000))
                .discountPercentages(0)
                .categoryId(1L)
                .build());

        itemService.create(new ItemModel().builder()
                .itemName("cpu intel i7-10600k")
                .price(new BigDecimal(24000))
                .discountPercentages(0)
                .categoryId(1L)
                .build());

        itemService.create(new ItemModel().builder()
                .itemName("cpu intel i9-10600k")
                .price(new BigDecimal(35000))
                .discountPercentages(0)
                .categoryId(1L)
                .build());

        itemService.create(new ItemModel().builder()
                .itemName("cpu amd ryzen 3 3300")
                .price(new BigDecimal(9000))
                .discountPercentages(0)
                .categoryId(1L)
                .build());

        itemService.create(new ItemModel().builder()
                .itemName("cpu amd ryzen 5 3600")
                .price(new BigDecimal(13000))
                .discountPercentages(0)
                .categoryId(1L)
                .build());

        itemService.create(new ItemModel().builder()
                .itemName("cpu amd ryzen 5 2500x")
                .price(new BigDecimal(11000))
                .discountPercentages(0)
                .categoryId(1L)
                .build());

        itemService.create(new ItemModel().builder()
                .itemName("cpu amd ryzen 7 3700x")
                .price(new BigDecimal(23000))
                .discountPercentages(0)
                .categoryId(1L)
                .build());

        //camera, photo & accessories

        itemService.create(new ItemModel().builder()
                .itemName("canon camera")
                .price(new BigDecimal(15000))
                .discountPercentages(0)
                .categoryId(1L)
                .build());

        itemService.create(new ItemModel().builder()
                .itemName("nikon camera")
                .price(new BigDecimal(13000))
                .discountPercentages(0)
                .categoryId(1L)
                .build());

        itemService.create(new ItemModel().builder()
                .itemName("samsung camera")
                .price(new BigDecimal(13000))
                .discountPercentages(0)
                .categoryId(1L)
                .build());

        itemService.create(new ItemModel().builder()
                .itemName("cpu intel i9-10600k")
                .price(new BigDecimal(13000))
                .discountPercentages(0)
                .categoryId(1L)
                .build());

    }
}
