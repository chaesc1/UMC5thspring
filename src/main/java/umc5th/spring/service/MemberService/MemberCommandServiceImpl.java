package umc5th.spring.service.MemberService;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc5th.spring.apiPayload.code.exception.handler.FoodCategoryHandler;
import umc5th.spring.apiPayload.code.status.ErrorStatus;
import umc5th.spring.converter.MemberConverter;
import umc5th.spring.converter.MemberPreferConverter;
import umc5th.spring.domain.FoodCategory;
import umc5th.spring.domain.Member;
import umc5th.spring.domain.mapping.MemberPrefer;
import umc5th.spring.repository.FoodCategoryRepository;
import umc5th.spring.repository.MemberRepository;
import umc5th.spring.web.dto.MemberRequestDTO;
import umc5th.spring.web.dto.MemberResponseDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(
                            ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }
}
