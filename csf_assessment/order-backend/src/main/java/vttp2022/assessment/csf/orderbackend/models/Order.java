package vttp2022.assessment.csf.orderbackend.models;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import jakarta.json.JsonObject;

// IMPORTANT: You can add to this class, but you cannot delete its original content

public class Order {

	private Integer orderId;
	private String name;
	private String email;
	private Integer size;
	private String sauce;
	private Boolean thickCrust;
	private List<String> toppings = new LinkedList<>();
	private String comments;

	public void setOrderId(Integer orderId) { this.orderId = orderId; }
	public Integer getOrderId() { return this.orderId; }

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setEmail(String email) { this.email = email; }
	public String getEmail() { return this.email; }

	public void setSize(Integer size) { this.size = size; }
	public Integer getSize() { return this.size; }

	public void setSauce(String sauce) { this.sauce = sauce; }
	public String getSauce() { return this.sauce; }

	public void setThickCrust(Boolean thickCrust) { this.thickCrust = thickCrust; }
	public Boolean isThickCrust() { return this.thickCrust; }

	public void setToppings(List<String> toppings) { this.toppings = toppings; }
	public List<String> getToppings() { return this.toppings; }
	public void addTopping(String topping) { this.toppings.add(topping); }

	public void setComments(String comments) { this.comments = comments; }
	public String getComments() { return this.comments; }

	public static Order create(JsonObject orderJson){
		Order order = new Order();
		
		order.setOrderId(1);
		order.setName(orderJson.getString("name"));
		order.setEmail(orderJson.getString("email"));
		order.setSauce(orderJson.getString("sauce"));
		order.setThickCrust(orderJson.getString("base").equals("thick"));
		order.setComments(orderJson.getString("comments"));
		order.setSize(orderJson.getInt("pizzaSize"));
		orderJson.getJsonArray("toppings").stream()
											.forEach(v -> order.getToppings().add(v.toString().substring(1, v.toString().length()-1)));

		return order;
	}
}
