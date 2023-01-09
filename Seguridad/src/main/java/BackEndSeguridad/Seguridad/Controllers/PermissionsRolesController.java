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

import BackEndSeguridad.Seguridad.Models.Permission;
import BackEndSeguridad.Seguridad.Models.PermissionsRoles;
import BackEndSeguridad.Seguridad.Models.Role;
import BackEndSeguridad.Seguridad.Repositories.PermissionRep;
import BackEndSeguridad.Seguridad.Repositories.PermissionRolesRep;
import BackEndSeguridad.Seguridad.Repositories.RoleRep;

@CrossOrigin
@RestController
@RequestMapping("/permisos-roles")
public class PermissionsRolesController {
    
    @Autowired
    private RoleRep myRoleRep;
    @Autowired
    private PermissionRep myPermissionRep;
    @Autowired
    private PermissionRolesRep myPermissionRolesRep;

    @GetMapping("")
    public List<PermissionsRoles> index(){
    return this.myPermissionRolesRep.findAll();
    }
    
    /**
    * Asignación rol y permiso
    * @param id_rol
    * @param id_permiso
    * @return
    */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("rol/{id_rol}/permiso/{id_permiso}")
    public PermissionsRoles create(@PathVariable String id_rol,@PathVariable String id_permiso){
        PermissionsRoles newPR= new PermissionsRoles();
        Role theRole = this.myRoleRep.findById(id_rol).get();
        Permission thePermission = this.myPermissionRep.findById(id_permiso).get();
        if(theRole != null && thePermission != null){
            newPR.setPermission(thePermission);
            newPR.setRole(theRole);
            return this.myPermissionRolesRep.save(newPR);
        }else{
            return null;
        } 
    }

    
    @GetMapping("{id}")
    public PermissionsRoles show(@PathVariable String id){
        PermissionsRoles currentPermissionRole =this.myPermissionRolesRep.findById(id).orElse(null);
        return currentPermissionRole;
    }

    /**
    * Modificación Rol y Permiso
    * @param id
    * @param id_rol
    * @param id_permiso
    * @return
    */
    @PutMapping("{id}/rol/{id_rol}/permiso/{id_permiso}")
    public PermissionsRoles update(@PathVariable String id,@PathVariable String id_rol,@PathVariable String id_permiso){
        PermissionsRoles currentPermissionRole =this.myPermissionRolesRep.findById(id).orElse(null);
        Role theRole = this.myRoleRep.findById(id_rol).get();
        Permission thePermission = this.myPermissionRep.findById(id_permiso).get();
        if(currentPermissionRole!=null && thePermission!=null && theRole!=null){
            currentPermissionRole.setPermission(thePermission);
            currentPermissionRole.setRole(theRole);
            return this.myPermissionRolesRep.save(currentPermissionRole);
        }
        else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        PermissionsRoles currentPermissionRole = this.myPermissionRolesRep.findById(id).orElse(null);
        if (currentPermissionRole!=null){
            this.myPermissionRolesRep.delete(currentPermissionRole);
        }
    }
}
