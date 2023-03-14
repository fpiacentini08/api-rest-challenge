package com.fpiacentini.challenge.model;

import java.util.List;

public record CustomPage<T>(List<T> content, long totalElements, int pageNumber, int pageSize) {
}
