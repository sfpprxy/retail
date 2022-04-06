package com.epam.retail.service;

import com.epam.retail.domain.Member;
import com.epam.retail.domain.Type;
import com.epam.retail.exception.InvalidFieldException;
import com.epam.retail.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// Unit Test
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    MemberService memberService;

    @Mock
    MemberRepository mockMemberRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        assertThrows(
                InvalidFieldException.class,
                () -> memberService.create(new Member().setName("Joe").setType(null))
        );
        assertThrows(
                InvalidFieldException.class,
                () -> memberService.create(new Member().setName(null).setType(null))
        );
        assertThrows(
                InvalidFieldException.class,
                () -> memberService.create(new Member().setName("").setType(Type.GOLD))
        );
        assertThrows(
                InvalidFieldException.class,
                () -> memberService.create(new Member().setName(null).setType(Type.GOLD))
        );
        Member joe1 = new Member().setName("Joe").setType(Type.GOLD);
        when(mockMemberRepository.save(any(Member.class))).thenReturn(joe1);
        memberService.create(joe1);
        verify(mockMemberRepository).save(joe1);
    }
}
