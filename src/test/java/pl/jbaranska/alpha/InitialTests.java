package pl.jbaranska.alpha;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jbaranska.alpha.entity.Category;
import pl.jbaranska.alpha.entity.Role;
import pl.jbaranska.alpha.entity.User;
import pl.jbaranska.alpha.models.UserFormRegistration;
import pl.jbaranska.alpha.repositories.CategoryRepository;
import pl.jbaranska.alpha.repositories.ProductRepository;
import pl.jbaranska.alpha.repositories.RoleRepository;
import pl.jbaranska.alpha.repositories.UserRepository;
import pl.jbaranska.alpha.services.RoleServices;


import java.util.HashSet;
import java.util.Set;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

//@DataJpaTest
@RunWith(SpringRunner.class)
//@AutoConfigureTestDatabase(replace = NONE)
@SpringBootTest
public class InitialTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleServices roleServices;

    @Autowired
    ProductRepository productRepository;


    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void addDefaultUserRole() {
        new RoleServices(roleRepository);
         final String ROLE_USER = "ROLE_USER";
        Role role;
        if (roleRepository.findByRole(ROLE_USER).isPresent()) {
             role= roleRepository.findByRole(ROLE_USER).get();
        } else {
             role= roleRepository.save(new Role(ROLE_USER));
        }
        System.out.println(role);
    }

    @Test
    public void selectPizza(){
        Category categoryPizza = categoryRepository.findByCategory("Pizza").get();
        System.out.println(
                productRepository.findDistinctByCategoryId(categoryPizza.getId())
        );
    }
    @Test
    public void pricesForSize(String productName)
    {

    }


}
