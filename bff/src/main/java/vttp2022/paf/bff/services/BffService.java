package vttp2022.paf.bff.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.paf.bff.models.Contact;
import vttp2022.paf.bff.repositories.BffRepository;

@Service
public class BffService {

    @Autowired
    private BffRepository bRepo;
    
    public void addNewContact(Contact c) throws BffException {
        
        Optional<Contact> opt = bRepo.findContactByEmail(c.getEmail());
        if (opt.isPresent())
            throw new BffException("%s is already in your BFF list!".formatted(c.getName()));
        
        if (!bRepo.insertContact(c))
            throw new BffException("Cannot add %s to BFF list".formatted(c.getEmail()));
    }

    public List<Contact> getAllContacts() {
        return bRepo.selectAllContacts();
    }
}
