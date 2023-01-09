package BackEndSeguridad.Seguridad.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import BackEndSeguridad.Seguridad.Models.Role;

public interface RoleRep extends MongoRepository<Role,String>{
    
}
