package Group10.example.API.DAO;

import Group10.example.API.Model.Department;
import Group10.example.API.Model.DepartmentUpdatePayLoad;
import Group10.example.API.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class DepartmentDAO {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentDAO(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Collection<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department addDepartment(Department department) {
        return departmentRepository.insert(department);
    }

    public Optional<Department> updateDepartment(String departmentId, DepartmentUpdatePayLoad departmentUpdatePayLoad) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        department.ifPresent(d -> d.setDepartmentName(departmentUpdatePayLoad.getDepartmentName()));
        department.ifPresent(departmentRepository::save);
        return department;
    }

    public Optional<Department> findDepartmentById(String departmentId) {
        return departmentRepository.findById(departmentId);
    }

    public Optional<Department> deleteDepartmentById(String departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        department.ifPresent(departmentRepository::delete);
        return department;
    }
}
