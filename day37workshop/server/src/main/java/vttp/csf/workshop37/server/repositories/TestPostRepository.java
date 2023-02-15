package vttp.csf.workshop37.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static vttp.csf.workshop37.server.repositories.Queries.*;

import java.io.InputStream;

@Repository
public class TestPostRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void savePost(String postId, String comments, InputStream image){
        jdbcTemplate.update(SQL_INSERT_TEST_POST, postId, comments, image );
    }
}
