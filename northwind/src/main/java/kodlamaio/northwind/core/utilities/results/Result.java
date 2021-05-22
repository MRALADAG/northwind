package kodlamaio.northwind.core.utilities.results;

public class Result {

	// Result sınıfı diğer sınıflar için bir super type görevini görüyor.
	// Core katmanında bütün projelerimizde kullanabileceğimiz kodları yazıyoruz.
	private boolean success;
	private String message;

	public Result(boolean success) {
		// Bu kısım yapılan operasyonun başarılı olup olmadığnı döndürmek için
		// kullanılır.
		this.success = success;

	}

	public Result(boolean success, String message) {
		this(success);
		this.message = message;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public String getMessage() {
		return this.message;
	}

}
