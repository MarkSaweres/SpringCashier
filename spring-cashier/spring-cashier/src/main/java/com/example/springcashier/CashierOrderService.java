package com.example.springcashier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashierOrderService {    
    @Autowired
    private CashierOrderRepository orderRepository;

    public CashierOrder createOrder(CashierOrder order) {
        return orderRepository.save(order);
    }

    public List<CashierOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public CashierOrder getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public CashierOrder updateOrder(Long id, CashierOrder order) {
        CashierOrder existingOrder = getOrderById(id);
        if (existingOrder != null) {
            existingOrder.setDrink(order.getDrink());
            existingOrder.setMilk(order.getMilk());
            existingOrder.setSize(order.getSize());
            existingOrder.setTotal(order.getTotal());
            existingOrder.setStatus(order.getStatus());
            return orderRepository.save(existingOrder);
        }
        return null;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
