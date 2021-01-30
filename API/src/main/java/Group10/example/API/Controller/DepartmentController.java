package Group10.example.API.Controller;

import Group10.example.API.Model.Department;
import Group10.example.API.Model.DepartmentUpdatePayLoad;
import Group10.example.API.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/all")//checked
    public Collection<Department> findAllDepartments(){
        return departmentService.findAllDepartments();
    }

    @GetMapping(value = "/find/{id}")//checked
    public Optional<Department> findDepartmentById(@PathVariable("id") String departmentId){
        return departmentService.findDepartmentById(departmentId);
    }

    @PostMapping(value = "/add")//checked
    public Department addDepartment(@Valid @RequestBody Department department){
        return departmentService.addDepartment(department);
    }

    @PutMapping(value = "/update/{id}")//checked
    public Optional<Department> updateDepartmentById(@PathVariable("id") String departmentId,@Valid @RequestBody DepartmentUpdatePayLoad departmentUpdatePayLoad){
        return departmentService.updateDepartment(departmentId,departmentUpdatePayLoad);
    }

    @DeleteMapping(value = "/delete/{id}")//checked
    public Optional<Department> deleteDepartmentById(@PathVariable("id") String departmentId){
        return departmentService.deleteDepartmentById(departmentId);
    }
}
