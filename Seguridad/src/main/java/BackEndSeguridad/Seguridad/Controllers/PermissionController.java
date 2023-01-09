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
import BackEndSeguridad.Seguridad.Repositories.PermissionRep;

@CrossOrigin
@RestController
@RequestMapping("/permisos")
public class PermissionController {
    @Autowired
    private PermissionRep myPermissionRep;

    @GetMapping("")
    public List<Permission> index(){
        return this.myPermissionRep.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Permission create(@RequestBody Permission permissionInfo){
        return this.myPermissionRep.save(permissionInfo);
    }

    @GetMapping("{id}")
    public Permission show(@PathVariable String id){
        Permission currentPermission =this.myPermissionRep.findById(id).orElse(null);
        return currentPermission;
    }

    @PutMapping("{id}")
    public Permission update(@PathVariable String id,@RequestBody Permission permissionInfo){
        Permission currentPermission=this.myPermissionRep.findById(id).orElse(null);
        if (currentPermission!=null){
            currentPermission.setUrl(permissionInfo.getUrl());
            currentPermission.setMethod(permissionInfo.getMethod());
            return this.myPermissionRep.save(currentPermission);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Permission currentPermission=this.myPermissionRep.findById(id).orElse(null);
        if (currentPermission!=null){
            this.myPermissionRep.delete(currentPermission);
        }
    }
}
