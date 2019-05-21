package service;

import model.Failure;

public interface FailureService {
    void addFailure(Failure failure);
    void removeFailure(Failure failure);
    void editFailure(Failure failure);
    }

