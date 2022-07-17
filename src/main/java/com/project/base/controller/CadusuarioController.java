package com.project.base.controller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.base.model.Cadusuario;
import com.project.base.repository.CadusuarioRepository;

@RestController
@RequestMapping("/api/user")
public class CadusuarioController {
	
	@Autowired
	CadusuarioRepository cadusuarioRepository;
	
	@GetMapping
	public ResponseEntity<List<Cadusuario>> getAllUsers(@RequestParam(required = false) String usuario) {
		try {
			List<Cadusuario> user = new ArrayList<Cadusuario>();
			if (usuario == null)
				cadusuarioRepository.findAll().forEach((usr) -> {
					if(usr.getDat_desativacao() == null) {
						user.add(usr);
					}
				});
			
			if (user.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cadusuario> getTutorialById(@PathVariable("id") long id) {
		Optional<Cadusuario> cadusuario = cadusuarioRepository.findById(id);
		if (cadusuario.isPresent()) {
			return new ResponseEntity<>(cadusuario.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity createUser(@RequestBody Cadusuario cadusuario) {
		try {
			List<Cadusuario> user = cadusuarioRepository.findAll();
			if (user.stream().anyMatch(e -> e.getDcr_login().equals(cadusuario.getDcr_login()))) {
				return new ResponseEntity<>("Login já existe", HttpStatus.IM_USED);
			};
			
			Pattern textPattern = Pattern.compile("^(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$");
			
			if(textPattern.matcher(cadusuario.getDcr_senha()).matches()) {
				Cadusuario _user = cadusuarioRepository
						.save(new Cadusuario(cadusuario.getDcr_usuario(), cadusuario.getDcr_login(), cadusuario.getDcr_senha(), LocalDate.now()));
				return new ResponseEntity<>(_user, HttpStatus.CREATED);
			}
			
			return new ResponseEntity<>("Senha deve conter 8 dígitos, um número e um caracter especial", HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cadusuario> updateUsuario(@PathVariable("id") long id, @RequestBody Cadusuario usuario) {
		Optional<Cadusuario> cadusuario = cadusuarioRepository.findById(id);
		if (cadusuario.isPresent()) {
			Cadusuario _cadusuario = cadusuario.get();
			if(usuario.getDcr_usuario() != null) {
				_cadusuario.setDcr_usuario(usuario.getDcr_usuario());
			}
			if(usuario.getDcr_login() != null) {
				_cadusuario.setDcr_login(usuario.getDcr_login());
			}
			if(usuario.getDcr_senha() != null) {
				_cadusuario.setDcr_senha(usuario.getDcr_senha());
			}
			return new ResponseEntity<>(cadusuarioRepository.save(_cadusuario), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cadusuario> deleteUsuario(@PathVariable("id") long id) {
		try {
			Optional<Cadusuario> cadusuario = cadusuarioRepository.findById(id);
			if (cadusuario.isPresent()) {
				Cadusuario _cadusuario = cadusuario.get();
				_cadusuario.setDat_desativacao(LocalDate.now());
				
				return new ResponseEntity<>(cadusuarioRepository.save(_cadusuario), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
