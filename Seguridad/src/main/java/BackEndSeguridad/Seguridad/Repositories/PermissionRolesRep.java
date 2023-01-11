package BackEndSeguridad.Seguridad.Repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import BackEndSeguridad.Seguridad.Models.PermissionsRoles;
public interface PermissionRolesRep extends MongoRepository<PermissionsRoles,String>{
    @Query("{'role.$id': ObjectId(?0),'permission.$id': ObjectId(?1)}")
    PermissionsRoles getPermisoRol(String id_role,String id_permission);
    
}
