package Group10.example.API.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;

@Document(collection = "Admin")
public class Admin {

        @Id
        private String adminID;

        @NotNull(message = "User Name is mandatory")
        private String userName;

        @NotNull(message = "password is mandatory")
        private String password;

        private String role;

        public String getAdminID() {
                return adminID;
        }

        public String getUserName() {
                return userName;
        }

        public String getPassword() {
                return password;
        }

        public String getRole() {
                return role;
        }

        public void setAdminID(String adminID) {
                this.adminID = adminID;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public void setRole(String role) {
                this.role = role;
        }
}
