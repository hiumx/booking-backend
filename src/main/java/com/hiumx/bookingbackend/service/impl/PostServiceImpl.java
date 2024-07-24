package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.request.PostRequest;
import com.hiumx.bookingbackend.dto.response.PostResponse;
import com.hiumx.bookingbackend.entity.Hotel;
import com.hiumx.bookingbackend.entity.Post;
import com.hiumx.bookingbackend.entity.TypePost;
import com.hiumx.bookingbackend.entity.User;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.mapper.PostMapper;
import com.hiumx.bookingbackend.repository.HotelRepository;
import com.hiumx.bookingbackend.repository.PostRepository;
import com.hiumx.bookingbackend.repository.TypePostRepository;
import com.hiumx.bookingbackend.repository.UserRepository;
import com.hiumx.bookingbackend.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private HotelRepository hotelRepository;
    private TypePostRepository typePostRepository;

    @Override
    public PostResponse create(PostRequest request) {
        Post post = PostMapper.toPost(request);
        if(post.getImage().isEmpty())
            post.setImage("https://d8271hh5ynwda.cloudfront.net/booking_post_demo.jpg");
        post.setIsConfirm(false);

        User user = userRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.HOTEL_NOT_FOUND));

        TypePost typePost = typePostRepository.findById(request.getTypePostId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.TYPE_POST_NOT_FOUND));

        post.setUser(user);
        post.setHotel(hotel);
        post.setTypePost(typePost);

        return PostMapper.toPostResponse(postRepository.save(post));
    }

    @Override
    public PostResponse confirmAPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.POST_NOT_FOUND));

        post.setIsConfirm(true);
        return PostMapper.toPostResponse(postRepository.save(post));
    }

    @Override
    public List<PostResponse> getPostsByHotel(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.HOTEL_NOT_FOUND));

        List<Post> posts = postRepository.findByHotel(hotel);
        return posts.stream().map(PostMapper::toPostResponse).toList();
    }

    @Override
    public PostResponse getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ApplicationException(ErrorCode.POST_NOT_FOUND));

        return PostMapper.toPostResponse(post);
    }

    @Override
    public PostResponse getPostConfirmedById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ApplicationException(ErrorCode.POST_NOT_FOUND));

        if(!post.getIsConfirm())
            throw new ApplicationException(ErrorCode.POST_NOT_CONFIRMED_YET);

        return PostMapper.toPostResponse(post);
    }

    @Override
    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostMapper::toPostResponse).toList();
    }

    @Override
    public List<PostResponse> getPostsConfirmed() {
        List<Post> posts = postRepository.findByIsConfirm(true);
        return posts.stream().map(PostMapper::toPostResponse).toList();
    }
}
