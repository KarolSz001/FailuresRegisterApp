package service;

import model.Failure;

public class FailureServiceImpl implements FailureService {

    private Failure failure;

    public FailureServiceImpl(Failure failure) {
        this.failure = failure;
    }

    @Override
    public void addFailure(Failure failure) {
//        failureDao.save(failure);
    }

    @Override
    public void removeFailure(Failure failure) {
//        failureDao.delete(failure);
    }

    @Override
    public void editFailure(Failure failure) {
//        commentDao.update(failure);
    }
}
