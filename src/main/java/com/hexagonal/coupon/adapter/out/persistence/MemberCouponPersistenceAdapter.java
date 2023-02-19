package com.hexagonal.coupon.adapter.out.persistence;

import com.hexagonal.coupon.application.port.out.CreateMemberCouponPort;
import com.hexagonal.coupon.application.port.out.FindCouponOfMemberPort;
import com.hexagonal.coupon.application.port.out.LoadAllCouponsOfMemberPort;
import com.hexagonal.coupon.application.port.out.UseMemberCouponPort;
import com.hexagonal.coupon.common.exception.MemberCouponNotExistException;
import com.hexagonal.coupon.domain.MemberCoupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class MemberCouponPersistenceAdapter implements LoadAllCouponsOfMemberPort, CreateMemberCouponPort, FindCouponOfMemberPort, UseMemberCouponPort {

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
    public MemberCoupon createMemberCoupon(Long memberId, Long couponId) {
        MemberCouponJpaEntity memberCouponJpaEntity = MemberCouponJpaEntity.withMemberIdAndCouponId(memberId, couponId);
        memberCouponRepository.save(memberCouponJpaEntity);
        return memberCouponMapper.mapToDomainEntity(memberCouponJpaEntity);
    }

    @Override
    public MemberCoupon findCouponOfMember(Long memberId, Long couponId) {
        return memberCouponMapper.mapToDomainEntity(
                memberCouponRepository.findByMemberIdAndCouponId(memberId, couponId)
                        .orElseThrow(MemberCouponNotExistException::new));
    }

    @Override
    public void useMemberCoupon(MemberCoupon memberCoupon) {
        MemberCouponJpaEntity memberCouponJpaEntity = memberCouponMapper.mapToJpaEntity(memberCoupon);
        memberCouponRepository.save(memberCouponJpaEntity);
    }
}
