package vttp.csf.oldAssess.carousellPage.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp.csf.oldAssess.carousellPage.models.ItemPost;

import static vttp.csf.oldAssess.carousellPage.repositories.Queries.*;

@Repository
public class PostSQLRepository {
    
    @Autowired
    private JdbcTemplate template;

    public boolean savePostSQL(ItemPost post){
        return template.update(SQL_INSERT_POST, post.getId(), 
                    post.getDate(), post.getName(), post.getEmail(), post.getPhone(),
                    post.getTitle(), post.getDescription(), post.getImage()) > 0;
    }
}
