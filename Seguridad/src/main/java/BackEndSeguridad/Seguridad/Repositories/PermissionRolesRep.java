package BackEndSeguridad.Seguridad.Repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import BackEndSeguridad.Seguridad.Models.PermissionsRoles;
public interface PermissionRolesRep extends MongoRepository<PermissionsRoles,String>{
    
}
