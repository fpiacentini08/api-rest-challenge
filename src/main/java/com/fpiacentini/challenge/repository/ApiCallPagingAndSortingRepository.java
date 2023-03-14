package com.fpiacentini.challenge.repository;

import com.fpiacentini.challenge.entity.ApiCall;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiCallPagingAndSortingRepository extends PagingAndSortingRepository<ApiCall, String> {
}
