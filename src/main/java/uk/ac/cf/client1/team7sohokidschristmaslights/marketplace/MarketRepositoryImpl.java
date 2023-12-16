package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MarketRepositoryImpl implements MarketRepository {

    private final JdbcTemplate jdbc;
    private RowMapper<Product> productMapper;

    public MarketRepositoryImpl(JdbcTemplate Jdbc) {
        this.jdbc = Jdbc;
        setProductMapper();
    }

    private void setProductMapper() {
        productMapper = (rs, i) -> new Product(
                rs.getLong("product_id"),
                rs.getString("filename"),
                rs.getString("filepath")
        );
    }

    public Product getProduct(Integer id) {
        String sql = "SELECT * FROM MarketplaceProducts WHERE product_id = ?";
        return jdbc.queryForObject(sql, productMapper, id);
    }

    public List<Product> getProductList(){
        String sql = "SELECT * FROM MarketplaceProducts";
        return jdbc.query(sql, productMapper);
    }

}
