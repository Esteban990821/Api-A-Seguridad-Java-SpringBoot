package BackEndSeguridad.Seguridad.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import BackEndSeguridad.Seguridad.Models.Permission;

public interface PermissionRep extends MongoRepository<Permission,String> {
    @Query("{'url':?0,'method':?1}")
    Permission getPermission(String url, String method);
}
