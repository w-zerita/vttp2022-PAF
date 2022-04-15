package vttp2022.paf.bff.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.paf.bff.models.Bff;
import vttp2022.paf.bff.repositories.BffRepository;

@Service
public class BffService {

    @Autowired
    private BffRepository bRepo;
    
    public void addNewBff(Bff bff) throws BffException {
        
        Optional<Bff> opt = bRepo.findBffByEmail(bff.getEmail());
        if (opt.isPresent())
            throw new BffException("%s is already in your BFF list!".formatted(bff.getName()));
        
        if (!bRepo.insertBff(bff))
            throw new BffException("Cannot add %s to BFF list".formatted(bff.getEmail()));
    }

    public List<Bff> getAllBffs() {
        return bRepo.selectAllBff();
    }
}
