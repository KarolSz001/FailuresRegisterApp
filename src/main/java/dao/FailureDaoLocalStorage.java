package dao;

import enums.Area;
import enums.Priority;
import model.Failure;

import javax.xml.stream.events.Comment;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FailureDaoLocalStorage implements FailureDao {

    private List<Failure> failures = new LinkedList<>();

    @Override
    public Failure save(Failure failure) {
        failures.add(failure);
        return failure;
    }

    @Override
    public Collection<Failure> getAllFailures() {
        return failures;
    }

    @Override
    public Optional<Failure> getFailureById(Long id) {
        return failures.stream().filter(failure -> failure.getId().equals(id)).findFirst();
    }

    @Override
    public Collection<Failure> getFailureByAres(Area are) {
        return failures.stream().filter(failure -> failure.getArea().equals(are)).collect(Collectors.toList());
    }

    @Override
    public Collection<Failure> getFailureByPriority(Priority priority) {
        return failures.stream().filter(failure -> failure.getPriority().equals(priority)).collect(Collectors.toList());
    }

    @Override
    public void deleteFailureById(Long id) {
        failures.removeIf(failure -> failure.getId().equals(id));
    }

    @Override
    public void deleteFailureByOwner(String owner) {
        failures.removeIf(failure -> failure.getOwner().equals(owner));
    }

    @Override
    public Failure update(Failure failure) {
        deleteFailureById(failure.getId());
        save(failure);
        return failure;
    }
}
