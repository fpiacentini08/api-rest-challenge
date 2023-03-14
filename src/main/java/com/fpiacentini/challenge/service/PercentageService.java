package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.exception.NoPercentageAvailableException;

public interface PercentageService {

    Integer getPercentage() throws NoPercentageAvailableException;
}
