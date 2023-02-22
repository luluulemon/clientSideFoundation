package vttp2022.assessment.csf.orderbackend.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import vttp2022.assessment.csf.orderbackend.models.Order;
import vttp2022.assessment.csf.orderbackend.models.OrderSummary;
import vttp2022.assessment.csf.orderbackend.repositories.OrderSQLRepo;

@Service
public class OrderService {

	@Autowired
	private PricingService priceSvc;

	@Autowired
	private OrderSQLRepo orderRepo;

	// POST /api/order
	// Create a new order by inserting into orders table in pizzafactory database
	// IMPORTANT: Do not change the method's signature
	public void createOrder(Order order) {
		order.setOrderId( orderRepo.getID());
		orderRepo.saveOrder(order);
		System.out.println("Order created");
	}

	// GET /api/order/<email>/all
	// Get a list of orders for email from orders table in pizzafactory database
	// IMPORTANT: Do not change the method's signature
	public List<OrderSummary> getOrdersByEmail(String email) {
		// Use priceSvc to calculate the total cost of an order
		List<OrderSummary> sums = new LinkedList<>();
		
		SqlRowSet rs = orderRepo.getOrders(email);

		while(rs.next()){	// get price for each 
			float toppingPrice = 0f;
			for(String top: rs.getString("toppings").split(","))
			{	toppingPrice += priceSvc.topping(top); }	// topping Price
			
			int thickC = ( rs.getBoolean("thick_crust") ) ? 0 : 1 ;	// 1 if boolean=true
			float totalPrice = priceSvc.size( rs.getInt("pizza_size") ) +  // size price
								priceSvc.sauce( rs.getString("sauce"))	+	// sauce price
								priceSvc.thickCrust()*thickC + // crust price
								priceSvc.thinCrust()*thickC +
								toppingPrice;

			sums.add( OrderSummary.create(rs, totalPrice) );
		}
		System.out.println("Size of sums is : " + sums.size());
		return sums;
	}
}
