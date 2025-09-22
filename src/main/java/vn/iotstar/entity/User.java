package vn.iotstar.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private int userId;

    @Column(name = "Username", columnDefinition = "nvarchar(100)", nullable = false, unique = true)
    private String username;

    @Column(name = "Password", columnDefinition = "nvarchar(100)", nullable = false)
    private String password;

    @Column(name = "Email", columnDefinition = "nvarchar(200)", nullable = false, unique = true)
    private String email;

    @Column(name = "Role")
    private String role;  // admin / user

    @Column(name = "Status")
    private int status;   // 1 = active, 0 = inactive

    // getter/setter
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
}
