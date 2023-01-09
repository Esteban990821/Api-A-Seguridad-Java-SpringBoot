package BackEndSeguridad.Seguridad.Controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import BackEndSeguridad.Seguridad.Models.Role;
import BackEndSeguridad.Seguridad.Models.User;
import BackEndSeguridad.Seguridad.Repositories.RoleRep;
import BackEndSeguridad.Seguridad.Repositories.UserRep;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UserController {
    @Autowired
    private UserRep myUserRep;

    @Autowired
    private RoleRep myRoleRep;

    @GetMapping("")
    public List<User> index(){
        return this.myUserRep.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User create(@RequestBody User UserInfo){
        UserInfo.setPassword(convertSHA256(UserInfo.getPassword()));
        return this.myUserRep.save(UserInfo);
    }

    @GetMapping("{id}")
    public User show(@PathVariable String id){
        User currentUser =this.myUserRep.findById(id).orElse(null);
        return currentUser;
    }

    @PutMapping("{id}")
    public User update(@PathVariable String id,@RequestBody User UserInfo){
        User currentUser=this.myUserRep.findById(id).orElse(null);
        if (currentUser!=null){
            currentUser.setPseudonym(UserInfo.getPseudonym());
            currentUser.setEmail(UserInfo.getEmail());
            currentUser.setPassword(convertSHA256(UserInfo.getPassword()));
            return this.myUserRep.save(currentUser);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        User currentUser=this.myUserRep.findById(id).orElse(null);
        if (currentUser!=null){
            this.myUserRep.delete(currentUser);
        }
    }

    public String convertSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    //--------------------------------- Metodo Rol-Usuario relacion [1-n]------------------------------

    /**
    * Relación (1 a n) entre rol y usuario
    * @param id
    * @param id_rol
    * @return
    */
    @PutMapping("{id}/rol/{id_rol}")
    public User asignarRolAUsuario(@PathVariable String id,@PathVariable String id_rol){
        User currentUser=this.myUserRep.findById(id).orElseThrow(RuntimeException::new);
        Role currentRole=this.myRoleRep.findById(id_rol).orElseThrow(RuntimeException::new);
        currentUser.setRole(currentRole);
        return this.myUserRep.save(currentUser);
    }

    
}
