package vttp2022.paf.bff;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp2022.paf.bff.models.Bff;
import vttp2022.paf.bff.repositories.BffRepository;
import vttp2022.paf.bff.services.BffException;
import vttp2022.paf.bff.services.BffService;

@SpringBootTest
class BffAppApplicationTests {

	@Autowired
	private BffService bSvc;

	@Autowired
	private BffRepository bRepo;

	private Bff barney;

	public BffAppApplicationTests() {

		Calendar cal = Calendar.getInstance();
		cal.set(1995, 3, 26);
		
		// email, name, phone, dob, status, passphrase
		barney = new Bff("barney@gmail.com", "barney", 
			"12345678", cal.getTime(), "friend", "hibarney");
	}

	@BeforeEach
	public void setup() {
		bRepo.insertBff(barney);
	}

	@AfterEach
	public void tearDown() {
		bRepo.deletBffByEmail(barney.getEmail());
	}

	@Test
	void insertBarneyShouldFail() {
		try {
			bSvc.addNewBff(barney);
		} catch (BffException e) {
			assertTrue(true);
			return;
		}
		fail("Did not throw BFFException when email exists");
	}
}
