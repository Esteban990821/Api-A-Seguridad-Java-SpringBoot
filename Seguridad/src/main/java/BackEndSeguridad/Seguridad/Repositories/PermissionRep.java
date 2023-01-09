package BackEndSeguridad.Seguridad.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import BackEndSeguridad.Seguridad.Models.Permission;

public interface PermissionRep extends MongoRepository<Permission,String> {
    
}
