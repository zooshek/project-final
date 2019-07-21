package pl.jbaranska.alpha.models;

import lombok.Getter;
import lombok.Setter;

import org.assertj.core.util.Lists;
import pl.jbaranska.alpha.entity.Item;

import java.util.List;

@Getter
@Setter
public class Cart {
    private List<Item> itemList = Lists.newArrayList();
}
