package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.terra.assignment.dao.OrderRepository;
import com.terra.assignment.entity.Address;
import com.terra.assignment.entity.Item;
import com.terra.assignment.entity.Order;
import com.terra.assignment.entity.OrderLine;
import com.terra.assignment.entity.OrderLineStatus;
import com.terra.assignment.entity.OrderStatus;
import com.terra.assignment.service.OrderService;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
	
	@InjectMocks
	OrderService orderService;
	
	@Mock
	OrderRepository repository;
	
	@Test
	void testSaveOrder() {
		List<Item> itmList = new ArrayList<>();
		List<Address> addList = new ArrayList<Address>();
		Item item = new Item();
		item.setItemId("1");
		item.setItemName("iphone");
		item.setPrice(150);
		item.setQuantity("7");
		itmList.add(item);
		Address address = new Address();
		address.setCity("Banglore");
		address.setCountry("India");
		address.setPinCode(23456);
		addList.add(address);
		OrderLine orderLine = new OrderLine();
		orderLine.setEta(new Date());
		orderLine.setOrderLineStatus(OrderLineStatus.OPEN);
		orderLine.setListOfAddresses(addList);
		orderLine.setListOfItems(itmList);
		Order order = new Order();
		order.setTotalAmount(150);
		order.setOrderStatus(OrderStatus.OPEN);
		order.setOrderDate(new Date());
		when(repository.save(order)).thenReturn(order);
		int result = orderService.saveOrder(order);
		assertEquals(1, result);
	}
}
