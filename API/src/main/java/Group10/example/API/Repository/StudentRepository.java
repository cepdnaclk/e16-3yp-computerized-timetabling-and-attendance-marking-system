package Group10.example.API.Repository;
import Group10.example.API.Model.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student,String> {

    public Student findByuserName(String userName);

    @Override
    <S extends Student> List<S> saveAll(Iterable<S> iterable);

    @Override
    List<Student> findAll();

    @Override
    List<Student> findAll(Sort sort);

    @Override
    <S extends Student> S insert(S s);

    @Override
    <S extends Student> List<S> insert(Iterable<S> iterable);

    @Override
    <S extends Student> List<S> findAll(Example<S> example);

    @Override
    <S extends Student> List<S> findAll(Example<S> example, Sort sort);

    @Override
    Page<Student> findAll(Pageable pageable);

    @Override
    <S extends Student> S save(S s);

    @Override
    Optional<Student> findById(String s);

    @Override
    boolean existsById(String s);

    @Override
    Iterable<Student> findAllById(Iterable<String> iterable);

    @Override
    long count();

    @Override
    void deleteById(String s);

    @Override
    void delete(Student student);

    @Override
    void deleteAll(Iterable<? extends Student> iterable);

    @Override
    void deleteAll();

    @Override
    <S extends Student> Optional<S> findOne(Example<S> example);

    @Override
    <S extends Student> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends Student> long count(Example<S> example);

    @Override
    <S extends Student> boolean exists(Example<S> example);
}
