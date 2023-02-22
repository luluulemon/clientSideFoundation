package vttp2022.assessment.csf.orderbackend.controllers;

import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.assessment.csf.orderbackend.models.Order;
import vttp2022.assessment.csf.orderbackend.models.OrderSummary;
import vttp2022.assessment.csf.orderbackend.services.OrderService;

@Controller
public class OrderRestController {

    @Autowired
    private OrderService orderSvc;

    @PostMapping("api/order")
    @ResponseBody
    public ResponseEntity<String> createOrder(@RequestBody String orderDetails){
        JsonReader reader = Json.createReader(new StringReader(orderDetails));
        JsonObject results = reader.readObject(); 
        Order order = Order.create(results);

        orderSvc.createOrder(order);
        return null;
    }


    @GetMapping("/api/order/{email}/all")
    @ResponseBody
    public ResponseEntity<String> getOrders(@PathVariable String email){
        List<OrderSummary> sums = orderSvc.getOrdersByEmail(email);
        if(sums.size()==0)
        {   return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body( Json.createObjectBuilder()
            .add("message", "No Orders yet!")
            .build().toString() );
        }

        JsonArrayBuilder sumsArray = Json.createArrayBuilder();
        sums.stream().forEach(sum -> sumsArray.add(sum.toJson()));

        return ResponseEntity.ok(sumsArray.build().toString());
    }
}
