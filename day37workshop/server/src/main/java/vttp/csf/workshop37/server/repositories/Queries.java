package vttp.csf.workshop37.server.repositories;

public class Queries {
    
    public static String SQL_INSERT_TRADE = 
        "Insert into posts(postId, ticker, entryDate, entryImage, entryPrice, positionSize, status, ) values(?, ?, ?, ?, ?, ?, open)";

    public static String SQL_INSERT_TEST_POST =
        "Insert into testpost(postId, comments, image) values(?, ?, ?)";
}
