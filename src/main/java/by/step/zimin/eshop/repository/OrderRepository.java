package by.step.zimin.eshop.repository;

import by.step.zimin.eshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
