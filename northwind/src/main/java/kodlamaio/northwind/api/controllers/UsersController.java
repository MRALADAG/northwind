package kodlamaio.northwind.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.Result;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	private UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody User user) {
		return ResponseEntity.ok(this.userService.add(user));
	}
	// Burada ResponseEntity ile işlem sonucu test edilmek istenilmektedir.
	// Burada ResponseEntity<?> içindeki ? parametresi işlem sonucunda ne döneceği
	// kestirilemediğinden yani hata da döndürebileceğinden bilinmeyen (?) yani
	// unknown değercdöndürüyor manasındadır.

	// Get işlemi için 200 kodu kullanılır.
	// add işlemi için 201 kodu kullanılır.
	// 400'lü kodlar hata kodlarıdır.

	
	
}
