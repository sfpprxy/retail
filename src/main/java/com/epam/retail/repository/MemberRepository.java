package com.epam.retail.repository;

import com.epam.retail.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Member findByName(String name);
}
