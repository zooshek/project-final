package pl.jbaranska.alpha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jbaranska.alpha.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
