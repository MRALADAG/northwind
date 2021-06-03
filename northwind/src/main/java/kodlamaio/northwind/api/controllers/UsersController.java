package kodlamaio.northwind.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.ErrorDataResult;

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
	public ResponseEntity<?> add(@Valid @RequestBody User user) {
		return ResponseEntity.ok(this.userService.add(user));
	}
	// Burada ResponseEntity ile işlem sonucu test edilmek istenilmektedir.
	// Burada ResponseEntity<?> içindeki ? parametresi işlem sonucunda ne döneceği
	// kestirilemediğinden yani hata da döndürebileceğinden bilinmeyen (?) yani
	// unknown değer döndürüyor manasındadır.

	// Get işlemi için 200 kodu kullanılır.
	// add işlemi için 201 kodu kullanılır.
	// 400'lü kodlar hata kodlarıdır.

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		// Bu ifade bir dictionary yapısıdır.
		for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları. ");
		return errors;
	}

	// JAVA da bütün Class'ların base sınıfı Object sınıfıdır.
	// Yukarıda .class ifadesiyle verinin tipi veriliyor.
	// Yukarıdaki method ile olşabilecek bütün hatalar kontrol edilmek
	// istenilmektedir.

}
