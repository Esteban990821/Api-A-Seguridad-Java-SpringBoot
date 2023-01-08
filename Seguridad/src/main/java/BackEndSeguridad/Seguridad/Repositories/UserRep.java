package BackEndSeguridad.Seguridad.Repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import BackEndSeguridad.Seguridad.Models.User;



public interface UserRep extends MongoRepository<User,String> {
    
}