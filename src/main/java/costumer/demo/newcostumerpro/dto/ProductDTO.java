package costumer.demo.newcostumerpro.dto;

import costumer.demo.newcostumerpro.model.Category;
import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private int categoryId;
    private double price;
    private double discount;
    private String discription;

}
