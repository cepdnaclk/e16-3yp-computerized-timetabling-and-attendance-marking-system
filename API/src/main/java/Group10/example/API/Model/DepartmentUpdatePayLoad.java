package Group10.example.API.Model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DepartmentUpdatePayLoad {

    @NotNull(message = "Department Name cannot be null")
    @NotBlank(message = "Department Name cannot be blank")
    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
