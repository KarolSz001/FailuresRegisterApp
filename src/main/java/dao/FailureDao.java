package dao;

import enums.Area;
import enums.Priority;
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
    Collection<Failure> getFailureByPriority(Priority priority);

    void deleteFailureById(Long id);
    void deleteFailureByOwner(String owner);

    Failure update(Failure failure);
}
