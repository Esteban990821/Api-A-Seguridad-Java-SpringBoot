package BackEndSeguridad.Seguridad.Controllers;

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
import BackEndSeguridad.Seguridad.Repositories.RoleRep;

@CrossOrigin
@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleRep myRoleRep;

    @GetMapping("")
    public List<Role> index(){
        return this.myRoleRep.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Role create(@RequestBody Role roleInfo){
        return this.myRoleRep.save(roleInfo);
    }

    @GetMapping("{id}")
    public Role show(@PathVariable String id){
        Role currentRole =this.myRoleRep.findById(id).orElse(null);
        return currentRole;
    }

    @PutMapping("{id}")
    public Role update(@PathVariable String id,@RequestBody Role roleInfo){
        Role currentRole=this.myRoleRep.findById(id).orElse(null);
        if (currentRole!=null){
            currentRole.setName(roleInfo.getName());
            currentRole.setDescription(roleInfo.getDescription());
            return this.myRoleRep.save(currentRole);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Role currentRole=this.myRoleRep.findById(id).orElse(null);
        if (currentRole!=null){
            this.myRoleRep.delete(currentRole);
        }
    }


}
