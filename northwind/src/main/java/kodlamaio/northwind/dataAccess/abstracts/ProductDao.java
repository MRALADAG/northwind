package kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

public interface ProductDao extends JpaRepository<Product, Integer> {

	Product getByProductName(String productName);
	// Buarada herhangi bir implementasyona gerek kalmaksızın ilgili kolona ait
	// Where query'leri oluşturuyor.

	Product getByProductNameAndCategory_CategoryId(String productName, int categoryId);
	// Burada get query'si için find ifadesi de kullanılabilir.

	List<Product> getByProductNameOrCategory(String productName, int categoryId);

	List<Product> getByCategoryIn(List<Integer> categories);
	// select * from products where category_id in(1,2,3,4)

	List<Product> getByProductNameContains(String productName);

	List<Product> getByProductNameStartsWith(String productName);

	@Query("From Product where productName=:productName and category.categoryId=:categoryId")
	List<Product> getByNameAndCategory(String productName, int categoryId);
	// Bu yapıya JPQL deniliyor.
	// select * from products where product_name=bisey and category_id=bisey

	@Query("Select new kodlamaio.northwind.entities.dtos.ProductWithCategoryDto(p.id, p.productName, c.categoryName) From Category c Inner Join c.products p")
	List<ProductWithCategoryDto> getProductWithCategoryDetails();
	// Burada productDto için yazmış olduğumuz Dto sınıfının package yolu
	// gösterilir.

	// select * from category c inner join product p on c.categoryId = p.categoryId
	// select p.productId, p.productName, c.categoryName

}
