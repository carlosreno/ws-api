package ws.api.wsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_type")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserType implements GrantedAuthority {
    @Id
    @GeneratedValue(generator = "seq_user_type_id",strategy = GenerationType.AUTO)
    @Column(name = "user_type_id")
    private Long id;
    private String name;
    private String description;

    @Override
    public String getAuthority() {
        return name;
    }
}
