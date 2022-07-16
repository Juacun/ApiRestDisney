package com.jpcode.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jpcode.model.Usuario;
import com.jpcode.repository.UsuarioRepository;

public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByNombre(username);
		
		if(usuario == null) throw new UsernameNotFoundException("Usuario Inexistente!");
		
		User user = this.userBuilder(usuario);
		return user;
	}

	@Override
	public Usuario findByEmail(String email) {
		
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public Usuario findByNombre(String nombre) {
		
		return usuarioRepository.findByNombre(nombre);
	}

	public User userBuilder(Usuario usuario) {
		
		boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean acountNonLocked = true;

        List<GrantedAuthority> authorithies = new ArrayList<GrantedAuthority>();
        authorithies.add(new SimpleGrantedAuthority(usuario.getRole()));

        return new User(usuario.getNombre(), usuario.getPassword(), enabled, accountNonExpired, credentialsNonExpired, acountNonLocked, authorithies);
	}
	
	@Override
	public Usuario userRegister(Usuario usuario) {
		
		if(!(usuarioRepository.findByEmail(usuario.getEmail()) == null)) {
			
		}
		
		return null;
	}
}
