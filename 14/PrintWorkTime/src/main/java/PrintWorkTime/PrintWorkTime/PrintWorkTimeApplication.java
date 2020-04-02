package PrintWorkTime.PrintWorkTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class PrintWorkTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrintWorkTimeApplication.class, args);
	}

}
