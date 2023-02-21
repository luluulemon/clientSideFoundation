package vttp2022.assessment.csf.orderbackend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.assessment.csf.orderbackend.models.Order;

import static vttp2022.assessment.csf.orderbackend.repositories.Queries.*;

@Repository
public class OrderSQLRepo {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveOrder(Order order){
        StringBuilder builder = new StringBuilder();
        for(String t: order.getToppings()){
            builder.append(t);
            builder.append(",");
        }
        System.out.println("Check string build" + builder.toString());
        String toppingsCsv = String.join(",", order.getToppings());
        
        jdbcTemplate.update(SQL_INSERT_ORDER, order.getOrderId(), 
                    order.getName(), order.getEmail(), order.getSize(), order.isThickCrust(),
                    order.getSauce(), toppingsCsv, order.getComments());
        
    }

    public int getID(){
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_ID);
        if(rs.next()){  return rs.getInt("order_id") + 1;    }

        return 1;   // return 1 if no orders so far
    }

    public SqlRowSet getOrders(String email){
        return jdbcTemplate.queryForRowSet(SQL_GET_ORDERS, email);
    }
}
