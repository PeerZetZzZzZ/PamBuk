package uzytkownik;

/* Klasa wyjatku Braku uzupelnionych danych na temat uzytkownika */
/* W zasadzie nie potrzebna, aczkolwiek ladnie wyglada */
public class UserException extends Throwable{
	public UserException(){
		
	}
	public String podajPrzyczyne(){
		return "Dane dotyczące użytkownika są nie uzupełnione!";
	}
}
