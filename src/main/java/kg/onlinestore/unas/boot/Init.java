package kg.onlinestore.unas.boot;

import kg.onlinestore.unas.entities.*;
import kg.onlinestore.unas.enums.Currency;
import kg.onlinestore.unas.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Init implements CommandLineRunner {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private WalletRepo walletRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Override
    public void run(String... args) throws Exception {
        //Admin
        User admin = userRepo.save(new User().builder().login("chef").password("chef").email("chef@gmail.com").isActive(true).build());
        UserRole userRole = userRoleRepo.save(new UserRole().builder().roleName("ROLE_ADMIN").user(admin).build());

        //Store's wallet
        walletRepo.save(new Wallet().builder().requisite("chef0102").balance(new BigDecimal(1000000)).currency(Currency.KGZ).user(admin).bankCard("VISA").build());

        //category initializing
        Category computer = categoryRepo.save(new Category().builder().categoryName("computer hardware").build());
        Category camera = categoryRepo.save(new Category().builder().categoryName("camera, photo & accessories").build());
        Category mobile = categoryRepo.save(new Category().builder().categoryName("mobile phones & accessories").build());
        Category laptop = categoryRepo.save(new Category().builder().categoryName("laptops").build());
        Category earphone = categoryRepo.save(new Category().builder().categoryName("earphone & headphone").build());

        //item initializing

        //computer
        itemRepo.save(new Item().builder()
                .itemName("cpu intel i3-10600k")
                .price(new BigDecimal(13000))
                .discountPercentages(0)
                .category(computer)
                .build());

        itemRepo.save(new Item().builder()
                .itemName("cpu intel i5-10600k")
                .price(new BigDecimal(19000))
                .discountPercentages(0)
                .category(computer)
                .build());

        itemRepo.save(new Item().builder()
                .itemName("cpu intel i7-10600k")
                .price(new BigDecimal(24000))
                .discountPercentages(0)
                .category(computer)
                .build());

        itemRepo.save(new Item().builder()
                .itemName("cpu intel i9-10600k")
                .price(new BigDecimal(35000))
                .discountPercentages(0)
                .category(computer)
                .build());

        itemRepo.save(new Item().builder()
                .itemName("cpu amd ryzen 3 3300")
                .price(new BigDecimal(9000))
                .discountPercentages(0)
                .category(computer)
                .build());

        itemRepo.save(new Item().builder()
                .itemName("cpu amd ryzen 5 3600")
                .price(new BigDecimal(13000))
                .discountPercentages(0)
                .category(computer)
                .build());

        itemRepo.save(new Item().builder()
                .itemName("cpu amd ryzen 5 2500x")
                .price(new BigDecimal(11000))
                .discountPercentages(0)
                .category(computer)
                .build());

        itemRepo.save(new Item().builder()
                .itemName("cpu amd ryzen 7 3700x")
                .price(new BigDecimal(23000))
                .discountPercentages(0)
                .category(computer)
                .build());

        //camera, photo & accessories

        itemRepo.save(new Item().builder()
                .itemName("canon camera")
                .price(new BigDecimal(15000))
                .discountPercentages(0)
                .category(camera)
                .build());

        itemRepo.save(new Item().builder()
                .itemName("nikon camera")
                .price(new BigDecimal(13000))
                .discountPercentages(0)
                .category(camera)
                .build());

        itemRepo.save(new Item().builder()
                .itemName("samsung camera")
                .price(new BigDecimal(13000))
                .discountPercentages(0)
                .category(camera)
                .build());

        itemRepo.save(new Item().builder()
                .itemName("hidden camera")
                .price(new BigDecimal(13000))
                .discountPercentages(0)
                .category(camera)
                .build());

        //mobile phones & accessories

        itemRepo.save(new Item().builder()
                .itemName("Xiaomi Note 7")
                .price(new BigDecimal(15000))
                .discountPercentages(2)
                .category(mobile)
                .build());
        itemRepo.save(new Item().builder()
                .itemName("Iphone SE2")
                .price(new BigDecimal(33000))
                .discountPercentages(2)
                .category(mobile)
                .build());

        itemRepo.save(new Item().builder()
                .itemName("Samsung Galaxy s9")
                .price(new BigDecimal(30000))
                .discountPercentages(2)
                .category(mobile)
                .build());

        itemRepo.save(new Item().builder()
                .itemName("Nokia 6300I")
                .price(new BigDecimal(1000000))
                .discountPercentages(2)
                .category(mobile)
                .build());

        itemRepo.save(new Item().builder()
                .itemName("Huawei p30")
                .price(new BigDecimal(23000))
                .discountPercentages(2)
                .category(mobile)
                .build());

        //laptops

        itemRepo.save(new Item().builder()
                .itemName("Acer")
                .price(new BigDecimal(38000))
                .discountPercentages(0)
                .category(laptop)
                .build());

        itemRepo.save(new Item().builder()
                .itemName("Asus")
                .price(new BigDecimal(40000))
                .discountPercentages(20)
                .category(laptop)
                .build());

        itemRepo.save(new Item().builder()
                .itemName("Dell")
                .price(new BigDecimal(45000))
                .discountPercentages(10)
                .category(laptop)
                .build());

        itemRepo.save(new Item().builder()
                .itemName("Lenovo")
                .price(new BigDecimal(35000))
                .discountPercentages(10)
                .category(laptop)
                .build());

        //earphone & headphone

        itemRepo.save(new Item().builder()
                .itemName("EarPods")
                .price(new BigDecimal(1500))
                .discountPercentages(0)
                .category(earphone)
                .build());

        itemRepo.save(new Item().builder()
                .itemName("AirPods Pro")
                .price(new BigDecimal(15000))
                .discountPercentages(0)
                .category(earphone)
                .build());

        itemRepo.save(new Item().builder()
                .itemName("HyperX Stinger clouds")
                .price(new BigDecimal(15000))
                .discountPercentages(0)
                .category(earphone)
                .build());

        itemRepo.save(new Item().builder()
                .itemName("A4Tech Bloody G520")
                .price(new BigDecimal(15000))
                .discountPercentages(0)
                .category(earphone)
                .build());
    }
}
