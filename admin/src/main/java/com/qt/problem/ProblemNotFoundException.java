package com.qt.problem;

public class ProblemNotFoundException extends RuntimeException {
    public ProblemNotFoundException() {
        super();
    }

    public ProblemNotFoundException(String message) {
        super(message);
    }
}
