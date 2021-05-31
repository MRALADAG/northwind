package kodlamaio.northwind.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
	// Dış dünya ile iletişim kurulan yerdir.
	// Bir nevi merkezi işlemci görevi görür.

	private ProductService productService;

	@Autowired
	public ProductsController(ProductService productService) {

		this.productService = productService;
	}

	@GetMapping("/getall")
	public DataResult<List<Product>> getAll() {
		return this.productService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody Product product) {
		return this.productService.add(product);
	}

	// Burada @RequestBody verilen veriyi JSON verisine dönüştürüp gönderiyor.
	// Bu işleme Mapping(eşleştirme) de deniliyor.

	@GetMapping("/getbyproductname")
	public DataResult<Product> getByProductName(@RequestParam String productName) {
		return this.productService.getByProductName(productName);
		// Burada @RequestParam anotasyonuyla getByProductName() metoduna gönderilen
		// productName parametresini okuyup productService deki getByProductName
		// metoduna gönderme işlemini gerçekleştirir.
	}

	@GetMapping("/getByProductNameAndCategoryId")
	public DataResult<Product> getByProductNameAndCategory(@RequestParam("productName") String productName,
			@RequestParam("categoryId") int categoryId) {

		return this.productService.getByProductNameAndCategoryId(productName, categoryId);

	}

	@GetMapping("/getByProductNameContains")
	public DataResult<List<Product>> getByProductNameContains(@RequestParam String productName) {

		return this.productService.getByProductNameContains(productName);

	}

	@GetMapping("/getAllByPage")
	public DataResult<List<Product>> getAll(int pageNo, int pageSize) {

		return this.productService.getAll(pageNo - 1, pageSize);

	}

	@GetMapping("/getAllSorted")
	public DataResult<List<Product>> getAllSorted() {

		return this.productService.getAllSorted();

	}

	@GetMapping("/getProductWithCategoryDetails")
	public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
		return this.productService.getProductWithCategoryDetails();
	}

}
