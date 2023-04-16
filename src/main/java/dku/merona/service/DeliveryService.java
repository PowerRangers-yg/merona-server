package dku.merona.service;

import dku.merona.config.UserDetailsImpl;
import dku.merona.domain.Member;
import dku.merona.domain.Post;
import dku.merona.domain.Delivery;
import dku.merona.dto.DeliveryDto;
import dku.merona.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final PostService postService;
    private final MemberService memberService;

    public Delivery findRequestById(Long requestId) {
        return deliveryRepository.findById(requestId)
                .orElseThrow(() -> new NoSuchElementException("해당 요청이 없습니다"));
    }

    public DeliveryDto.Response createRequest(UserDetailsImpl user, DeliveryDto.Request request) {
        Post post = postService.findPostById(request.getPostId());
        Member member = user.getMember();

        request.setMemberAndPost(member, post);
        Delivery newDelivery = deliveryRepository.save(request.toEntity());
        return new DeliveryDto.Response(newDelivery);
    }

    public List<DeliveryDto.Response> getAllRequestByPost(UserDetailsImpl user, Long postId) {
        Post post = postService.findPostById(postId);
        memberService.validateMember(user.getId(), post.getMember());
        return deliveryRepository.findAllByPost(post)
                .stream().map(DeliveryDto.Response::new).collect(Collectors.toList());
    }

    public void cancelRequest(UserDetailsImpl user, Long requestId) {
        Delivery delivery = findRequestById(requestId);
        memberService.validateMember(user.getId(), delivery.getMember());
        delivery.setStatusCancelled();
    }

    public void deleteRequest(UserDetailsImpl user, Long requestId) {
        Delivery delivery = findRequestById(requestId);
        memberService.validateMember(user.getId(), delivery.getMember());
        delivery.deleteRelation();
        deliveryRepository.deleteById(requestId);
    }
}
