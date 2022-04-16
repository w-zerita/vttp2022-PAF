package vttp2022.paf.bff;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp2022.paf.bff.models.Contact;
import vttp2022.paf.bff.repositories.BffRepository;
import vttp2022.paf.bff.services.BffException;
import vttp2022.paf.bff.services.BffService;

@SpringBootTest
class BffAppApplicationTests {

	@Autowired
	private BffService bSvc;

	@Autowired
	private BffRepository bRepo;

	private Contact barney;

	public BffAppApplicationTests() {

		Calendar cal = Calendar.getInstance();
		cal.set(1995, 3, 26);
		
		// email, name, phone, dob, status, passphrase
		barney = new Contact("barney@gmail.com", "barney", 
			"12345678", cal.getTime(), "friend", "hibarney");
	}

	@BeforeEach
	public void setup() {
		bRepo.insertContact(barney);
	}

	@AfterEach
	public void tearDown() {
		bRepo.deleteContactByEmail(barney.getEmail());
	}

	@Test
	void insertBarneyShouldFail() {
		try {
			bSvc.addNewContact(barney);
		} catch (BffException e) {
			assertTrue(true);
			return;
		}
		fail("Did not throw BFFException when email exists");
	}
}
