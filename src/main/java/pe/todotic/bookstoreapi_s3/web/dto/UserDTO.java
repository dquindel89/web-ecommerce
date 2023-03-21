package pe.todotic.bookstoreapi_s3.web.dto;

import lombok.Data;
import pe.todotic.bookstoreapi_s3.model.User;


@Data
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private User.Role role;
}
