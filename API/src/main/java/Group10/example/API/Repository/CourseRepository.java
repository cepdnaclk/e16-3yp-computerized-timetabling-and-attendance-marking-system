package Group10.example.API.Repository;

import Group10.example.API.Model.Course;
import Group10.example.API.Model.LectureRoom;
import Group10.example.API.Model.LectureRoomRef;
<<<<<<< HEAD
=======
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
>>>>>>> 192fcb9ec3139857424c6715ac3b541397fdc0b4
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends MongoRepository<Course,String> {
    Collection<Course> findBySemester(int semester);

    Optional<Course> findByCourseNumber(String courseNumber);
<<<<<<< HEAD

    List<Course> findByLectureRoomIDsContains(LectureRoomRef roomRef);
=======

    List<Course> findByLectureRoomIDsContains(LectureRoomRef roomRef);

    @Override
    <S extends Course> List<S> saveAll(Iterable<S> iterable);

    @Override
    List<Course> findAll();

    @Override
    List<Course> findAll(Sort sort);

    @Override
    <S extends Course> S insert(S s);

    @Override
    <S extends Course> List<S> insert(Iterable<S> iterable);

    @Override
    <S extends Course> List<S> findAll(Example<S> example);

    @Override
    <S extends Course> List<S> findAll(Example<S> example, Sort sort);

    @Override
    Page<Course> findAll(Pageable pageable);

    @Override
    <S extends Course> S save(S s);

    @Override
    Optional<Course> findById(String s);

    @Override
    boolean existsById(String s);

    @Override
    Iterable<Course> findAllById(Iterable<String> iterable);

    @Override
    long count();

    @Override
    void deleteById(String s);

    @Override
    void delete(Course course);

    @Override
    void deleteAll(Iterable<? extends Course> iterable);

    @Override
    void deleteAll();

    @Override
    <S extends Course> Optional<S> findOne(Example<S> example);

    @Override
    <S extends Course> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends Course> long count(Example<S> example);

    @Override
    <S extends Course> boolean exists(Example<S> example);
>>>>>>> 192fcb9ec3139857424c6715ac3b541397fdc0b4
}
