package com.fpiacentini.challenge.model;

import jakarta.validation.constraints.Min;

public record NumbersToAdd (@Min(value = 0, message = "Numbers to add have to be zero or greater.") Integer firstNumber,
                            @Min(value = 0, message = "Numbers to add have to be zero or greater.") Integer secondNumber) {}
