package dao;

import enums.Area;
import model.Failure;

import javax.xml.stream.events.Comment;
import java.security.acl.Owner;
import java.util.Collection;
import java.util.Optional;

public interface FailureDao {

    Failure save(Failure failure);

    Collection<Failure> getAllFailures();
    Optional<Failure> getFailureById(Long id);
    Collection<Failure> getFailureByAres(Area are);
    Collection<Failure> getFailureByPriority(Area are);

    void deleteFailureById(Long id);
    void deleteFailureByOwner(String owner);


}
