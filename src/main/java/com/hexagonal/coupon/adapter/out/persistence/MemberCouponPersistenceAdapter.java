package com.hexagonal.coupon.adapter.out.persistence;

import com.hexagonal.coupon.application.port.out.CreateMemberCouponPort;
import com.hexagonal.coupon.application.port.out.LoadAllCouponsOfMemberPort;
import com.hexagonal.coupon.application.port.out.UpdateCouponStatePort;
import com.hexagonal.coupon.domain.MemberCoupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class MemberCouponPersistenceAdapter implements LoadAllCouponsOfMemberPort, UpdateCouponStatePort, CreateMemberCouponPort {

    private final CouponRepository couponRepository;
    private final MemberCouponRepository memberCouponRepository;
    private final MemberCouponMapper memberCouponMapper;

    @Override
    public List<MemberCoupon> loadAllCouponsOfMember(Long memberId) {
        return memberCouponRepository.findAllByMemberId(memberId)
                .stream()
                .map(memberCouponMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void decreaseRemainQuantity(Long couponId) {
        couponRepository.decreaseRemainQuantityById(couponId);
    }

    @Override
    public void createMemberCoupon(Long memberId, Long couponId) {
        MemberCouponJpaEntity memberCouponJpaEntity = MemberCouponJpaEntity.withMemberIdAndCouponId(memberId, couponId);
        memberCouponRepository.save(memberCouponJpaEntity);
    }
}
