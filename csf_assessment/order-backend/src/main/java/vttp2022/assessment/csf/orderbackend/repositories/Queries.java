package vttp2022.assessment.csf.orderbackend.repositories;

public class Queries {
    
    public static String SQL_INSERT_ORDER = 
        "insert into orders values(?,?,?,?,?,?,?,?)";

    public static String SQL_GET_ID =
        "SELECT * FROM orders WHERE order_id=(SELECT max(order_id) FROM orders)";

    public static String SQL_GET_ORDERS =
        "select * from orders where email=?";
}
