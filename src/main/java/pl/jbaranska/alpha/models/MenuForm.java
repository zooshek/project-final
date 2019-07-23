package pl.jbaranska.alpha.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class MenuForm {

    private List<ItemForm> items = new ArrayList<>();
}
