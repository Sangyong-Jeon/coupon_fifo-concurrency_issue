package com.hexagonal.coupon.application.port.out;

import com.hexagonal.coupon.domain.Member;

public interface FindMemberPort {

    Member findMemberByName(String name);
}
