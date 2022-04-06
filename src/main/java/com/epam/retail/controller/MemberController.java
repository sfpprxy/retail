package com.epam.retail.controller;

import com.epam.retail.domain.Member;
import com.epam.retail.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/member")
public class MemberController {

	MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	// System can create member with three different member types (Gold, Silver, Copper)
	@PostMapping("/create")
	public Member create(@RequestBody Member member) {
		if (member.getName().equals("joe")) {
			throw new RuntimeException("Not implemented");
		}
		return memberService.create(member);
	}

}
