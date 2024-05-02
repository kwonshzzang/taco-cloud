package kr.co.kwonshzzang.tacocloud.repository;

import kr.co.kwonshzzang.tacocloud.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
