package com.flowmate.repository;

import com.flowmate.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue,Long> {

    public List<Issue> findByProjectID(Long id);
}
