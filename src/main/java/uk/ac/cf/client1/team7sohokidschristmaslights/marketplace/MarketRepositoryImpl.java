package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Indicates that this class is a Spring repository component
public class MarketRepositoryImpl implements MarketRepository {

    private final JdbcTemplate jdbc;  // Spring JDBC Template for database operations
    private RowMapper<Product> productMapper; // Mapper to convert SQL results to Product objects

    public MarketRepositoryImpl(JdbcTemplate Jdbc) {
        this.jdbc = Jdbc; // Initialize JDBC Template
        setProductMapper(); // Set up the product mapper
    }

    // Sets up the product mapper to map SQL rows to Product objects
    private void setProductMapper() {
        productMapper = (rs, i) -> new Product(
                rs.getLong("product_id"),
                rs.getString("filename"),
                rs.getString("filepath"),
                rs.getString("product_name"),
                rs.getFloat("price")
        );

    }

    // Saves checkout information to the database
    public void saveCheckout(Checkout checkout) {
        String sql = "INSERT INTO CheckoutInfo " +
                "(`customer_name`, `customer_email`, `delivery_address`, `total_price`, `note`)" +
                "VALUES (?,?,?,?,?)";

        // Execute the update with checkout details
        jdbc.update(sql,
                checkout.getCustomerName(),
                checkout.getCustomerEmail(),
                checkout.getDeliveryAddress(),
                checkout.getTotalPrice(),
                checkout.getNote()
        );
    }
    // Retrieves a single product by ID from the database
    public Product getProduct(Integer id) {
        String sql = "SELECT * FROM MarketplaceProducts WHERE product_id = ?";
        return jdbc.queryForObject(sql, productMapper, id);
    }

    // Retrieves a list of all products from the database
    public List<Product> getProductList(){
        String sql = "SELECT * FROM MarketplaceProducts";
        return jdbc.query(sql, productMapper);
    }

}
