package com.hexagonal.coupon.application.port.in;

public interface FindMemberUseCase {

    FindMemberResponse findMemberByName(String name);
}
