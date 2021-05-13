package Group10.example.API.Service;

import Group10.example.API.DAO.DepartmentDAO;
import Group10.example.API.Model.Department;
import Group10.example.API.Model.DepartmentUpdatePayLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentDAO departmentDAO;

    @Autowired
    public DepartmentService(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    public Collection<Department> findAllDepartments() {
        return departmentDAO.findAllDepartments();
    }

    public Department addDepartment(Department department) {
        return departmentDAO.addDepartment(department);
    }

    public Optional<Department> updateDepartment(String departmentId, DepartmentUpdatePayLoad departmentUpdatePayLoad) {
        return departmentDAO.updateDepartment(departmentId,departmentUpdatePayLoad);
    }

    public Optional<Department> findDepartmentById(String departmentId) {
        return departmentDAO.findDepartmentById(departmentId);
    }

    public Optional<Department> deleteDepartmentById(String departmentId) {
        return departmentDAO.deleteDepartmentById(departmentId);
    }
}
