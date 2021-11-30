/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto1;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.orm.jpa.JpaSystemException;

/**
 *
 * @author USUARIO
 */
@Service 
public class UserService {
   @Autowired
   private UserRepository userRepository;
   
   public List<User> getAll(){
       return userRepository.getAll();
   }
     public Optional<User> getUser(int id) {
        return userRepository.getUser(id);
    }
     
    
     public User registrar(User user) {
        if (user.getId() == null) {
            if (existeEmail(user.getEmail()) == false) {
                return userRepository.save(user);
            } else {
                return user;
            }
        } else {
            return user;
        }
    }
    
    public boolean existeEmail(String email) {
        return userRepository.existeEmail(email);
    }
    
       public User autenticarUsuario(String email, String password) {
        Optional<User> usuario = userRepository.autenticarUsuario(email, password);
   
        
        if (usuario.isEmpty()) {
        	User user2 = new User();
        	user2.setEmail(email);
        	user2.setPassword(password);
        	user2.setName("NO DEFINIDO");
          
           
        	return user2;
        	
        } else {
            return usuario.get();
        }
    }    
}
