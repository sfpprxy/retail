package com.epam.retail.service;

import com.epam.retail.domain.Member;
import com.epam.retail.exception.InvalidFieldException;
import com.epam.retail.repository.MemberRepository;
import com.epam.retail.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository repository) {
        memberRepository = repository;
    }

    // Member name and type are mandatory, point is optional, duplicate name is acceptable
    @Transactional
    public Member create(Member member) {
        if (U.hasEmpty(member.getName(), member.getType())) {
            throw new InvalidFieldException("name, type");
        }
        if (U.isEmpty(member.getPoint())) {
            member.setPoint(0L);
        }
        member.setId(null);
        return memberRepository.save(member);
    }
}
