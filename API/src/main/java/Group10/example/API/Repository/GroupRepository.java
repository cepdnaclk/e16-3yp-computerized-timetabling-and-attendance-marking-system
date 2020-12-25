package Group10.example.API.Repository;

import Group10.example.API.Model.Group;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends MongoRepository<Group,String> {



    @Override
    <S extends Group> List<S> saveAll(Iterable<S> iterable);

    @Override
    List<Group> findAll();

    @Override
    List<Group> findAll(Sort sort);

    @Override
    <S extends Group> S insert(S s);

    @Override
    <S extends Group> List<S> insert(Iterable<S> iterable);

    @Override
    <S extends Group> List<S> findAll(Example<S> example);

    @Override
    <S extends Group> List<S> findAll(Example<S> example, Sort sort);

    @Override
    Page<Group> findAll(Pageable pageable);

    @Override
    <S extends Group> S save(S s);

    @Override
    Optional<Group> findById(String s);

    @Override
    boolean existsById(String s);

    @Override
    Iterable<Group> findAllById(Iterable<String> iterable);

    @Override
    long count();

    @Override
    void deleteById(String s);

    @Override
    void delete(Group group);

    @Override
    void deleteAll(Iterable<? extends Group> iterable);

    @Override
    void deleteAll();

    @Override
    <S extends Group> Optional<S> findOne(Example<S> example);

    @Override
    <S extends Group> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends Group> long count(Example<S> example);

    @Override
    <S extends Group> boolean exists(Example<S> example);
}
