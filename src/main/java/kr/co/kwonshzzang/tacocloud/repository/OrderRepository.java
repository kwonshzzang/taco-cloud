package kr.co.kwonshzzang.tacocloud.repository;

import kr.co.kwonshzzang.tacocloud.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}
