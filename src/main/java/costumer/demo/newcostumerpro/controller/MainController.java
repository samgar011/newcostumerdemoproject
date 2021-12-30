package costumer.demo.newcostumerpro.controller;

import costumer.demo.newcostumerpro.dto.ProductDTO;
import costumer.demo.newcostumerpro.model.Category;
import costumer.demo.newcostumerpro.model.Product;
import costumer.demo.newcostumerpro.service.CategoryService;
import costumer.demo.newcostumerpro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;


@Controller
public class MainController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    // access admin page to manage items
    @GetMapping("/admin")
    public String adminPage(){
        return "adminHome";
    }

    // show category page
    @GetMapping("/admin/categories")
    public String getCat(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";

    }
    // adding
    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";

    }

    //Category Section
    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute ("category") Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";

    }
    @GetMapping("/admin/categories/delete/{id}")
    public String deleteItem(@PathVariable int id){
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";

    }
    @GetMapping("/admin/categories/update{id}")
    public String updateItem(@PathVariable int id, Model model){
        Optional<Category> category = categoryService.getCategoryById(id);
        if(category.isPresent()){
            model.addAttribute("category", category.get());
            return "categoriesAdd";
        }else
            return "404";

    }


    // Product Section
    @GetMapping("/admin/products")
    public String products(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return "products";

    }
    @GetMapping("/admin/products/add")
    public String productsAdd(Model model){
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());

        return "productsAdd";
    }

    @PostMapping("admin/products/add")
        public String productPost(@ModelAttribute("productDTO") ProductDTO productDTO){
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setDiscount(productDTO.getDiscount());
        product.setDiscription(productDTO.getDiscription());
        productService.addProduct(product);

        return "redirect:/admin/products";
    }

}
