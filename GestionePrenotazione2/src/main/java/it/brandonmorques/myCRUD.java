package it.brandonmorques;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.brandonmorques.repository.CittaRepository;
import it.brandonmorques.repository.EdificioRepository;
import it.brandonmorques.repository.PostazioneRepository;
import it.brandonmorques.repository.PrenotazioneRepository;
import it.brandonmorques.repository.RoleRepository;
import it.brandonmorques.repository.UserRepository;

@Component
public class myCRUD implements CommandLineRunner{
	@Autowired
	CittaRepository cittaRepository;
	@Autowired
	EdificioRepository edificioRepository;
	@Autowired
	PostazioneRepository postazioneRepository;
	@Autowired
	PrenotazioneRepository prenotazioneRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	
	
	@Override
	public void run(String... args) throws Exception {

//		Role user = new Role();
//		user.setRoleType(RoleType.ROLE_USER);		
//		Role admin = new Role();
//		admin.setRoleType(RoleType.ROLE_ADMIN);
//		roleRepository.save(user);
//		roleRepository.save(admin);
//		
//		User u1 = new User("chrysvnthemvm","brandon.morques@gmail.com","Brandon","brandon", true);
//		u1.getRoles().add(user);
//		u1.getRoles().add(admin);
//		userRepository.save(u1);
//		
//		Citta c1 = new Citta("Roma");
//		Edificio e1 = new Edificio("Patata","Via delle patate", c1, "patata");
//		Edificio e2 = new Edificio("Banana","Via delle banane", c1, "banana");
//		Postazione p1 = new Postazione("Hall", "007",10,TipoPostazione.PRIVATO,e1);
//		cittaRepository.save(c1);
//		edificioRepository.save(e1);
//		edificioRepository.save(e2);
//		postazioneRepository.save(p1);
//		LocalDate ld=LocalDate.of(2021, 10, 28);
//		Prenotazione pre = new Prenotazione(u1,p1,ld);
//		prenotazioneRepository.save(pre);

		
	    System.out.println(userRepository.findById((long)1).toString());      
		
		
	}

}
