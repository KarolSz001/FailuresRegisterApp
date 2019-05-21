package dao;

import enums.Area;
import model.Failure;

import javax.xml.stream.events.Comment;
import java.util.Collection;
import java.util.Optional;

public interface FailureDao {

    Failure save(Failure failure);

    Collection<Failure> getAllFailures();
    Optional<Comment> getFailureById(Long id);
    Collection<Comment> getFailureByAres(Area are);
    Collection<Comment> getFailureByPriority(Area are);

    void deleteFailureById(Long id);
    void deleteFailureByOwner(Long id);

}
