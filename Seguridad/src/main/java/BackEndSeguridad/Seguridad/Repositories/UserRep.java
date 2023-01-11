package BackEndSeguridad.Seguridad.Repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import BackEndSeguridad.Seguridad.Models.User;



public interface UserRep extends MongoRepository<User,String> {
    @Query("{'email': ?0}")
    public User getUserByEmail(String email);
}