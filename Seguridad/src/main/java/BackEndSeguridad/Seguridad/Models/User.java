package BackEndSeguridad.Seguridad.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document()
public class User {
    @Id
    private String _id;
    private String pseudonym;
    private String email;
    private String password;
    @DBRef
    private Role role;
    
    public User(String pseudonym, String email, String password) {
        this.pseudonym = pseudonym;
        this.email = email;
        this.password = password;
        }

    public String get_Id() {
        return _id;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }
        
    public String getEmail() {
        return email;
    }

    public void setCorreo(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}