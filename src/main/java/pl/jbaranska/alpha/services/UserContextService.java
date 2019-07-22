package pl.jbaranska.alpha.services;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import pl.jbaranska.alpha.entity.Item;
import pl.jbaranska.alpha.entity.Product;
import pl.jbaranska.alpha.models.Basket;
import pl.jbaranska.alpha.models.ItemForm;

import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class UserContextService {

    @Getter
    private Basket basket = new Basket();


}
