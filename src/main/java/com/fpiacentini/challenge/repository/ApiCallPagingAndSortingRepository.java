package com.fpiacentini.challenge.repository;

import com.fpiacentini.challenge.entity.ApiCall;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiCallPagingAndSortingRepository extends CrudRepository<ApiCall, String> {
}
