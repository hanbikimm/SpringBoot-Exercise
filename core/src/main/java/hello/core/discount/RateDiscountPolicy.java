package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    // command + shift + T: test 생성
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent/100;
        }else {
            return 0;
        }
    }

}
