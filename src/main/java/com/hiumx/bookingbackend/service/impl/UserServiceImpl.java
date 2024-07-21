package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.document.UserSaveDocument;
import com.hiumx.bookingbackend.dto.request.UserCreationRequest;
import com.hiumx.bookingbackend.dto.request.UserResetPasswordRequest;
import com.hiumx.bookingbackend.dto.request.UserSaveRequest;
import com.hiumx.bookingbackend.dto.response.*;
import com.hiumx.bookingbackend.entity.Gender;
import com.hiumx.bookingbackend.entity.Hotel;
import com.hiumx.bookingbackend.entity.Role;
import com.hiumx.bookingbackend.entity.User;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.mapper.*;
import com.hiumx.bookingbackend.repository.*;
import com.hiumx.bookingbackend.repository.document.HotelDocumentRepository;
import com.hiumx.bookingbackend.repository.document.UserSaveDocumentRepository;
import com.hiumx.bookingbackend.service.S3Service;
import com.hiumx.bookingbackend.service.UserService;
import com.hiumx.bookingbackend.utils.AvatarGenerator;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private HotelRepository hotelRepository;
    private UserSaveDocumentRepository userSaveDocumentRepository;
    private ReviewRepository reviewRepository;
    private RoomRepository roomRepository;
    private ImageRepository imageRepository;
    private S3Service s3Service;
    private final HotelDocumentRepository hotelDocumentRepository;

    @Override
    public UserCreationResponse createUser(UserCreationRequest request) {

        if(request.getEmail() != null) {
            User userFounded = userRepository.findByEmail(request.getEmail());
            if(userFounded != null) throw new ApplicationException(ErrorCode.EMAIL_OR_PHONE_EXISTED);
        } else {
            User userFounded = userRepository.findByPhone(request.getPhone());
            if(userFounded != null) throw new ApplicationException(ErrorCode.EMAIL_OR_PHONE_EXISTED);
        }

        if(request.getEmail() == null && request.getPhone() == null)
            throw new ApplicationException(ErrorCode.AUTHENTICATION_ERROR);

        if(request.getGenderId() == null)
            request.setGenderId(1L);

        if(request.getIsActive() == null)
            request.setIsActive(1);

        if(request.getImage() == null)
            request.setImage("https://d8271hh5ynwda.cloudfront.net/user-img.jpg");

//        String name = request.getEmail();
//        if(name == null) name = "A";
//
//        BufferedImage avatar = AvatarGenerator.generateAvatar(name);
//        String fileName = name.toLowerCase() + "-avatar.png";
//        String imageUrl = s3Service.u(fileName, avatarImage);
//        System.out.println("Buffer: " + avatar);

        User user = UserMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        List<Long> rolesId = new ArrayList<>();
        if(request.getRoleIds() == null) {
            rolesId = new ArrayList<>();
            rolesId.add(3L);
        } else {
            rolesId = new ArrayList<>(request.getRoleIds());
        }
        var roles = roleRepository.findAllById(rolesId);
        user.setRoles(new HashSet<>(roles));

        System.out.println(user);
        User userSaved = userRepository.save(user);
        return UserMapper.toUserResponse(userSaved);
    }

    @PostAuthorize("returnObject.email == authentication.name || returnObject.phone == authentication.name")
    @Override
    public UserCreationResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
        return UserMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<UserCreationResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::toUserResponse).toList();
    }

    @Override
    public UserCreationResponse updateUser(Long id, UserCreationRequest request) {
        User userFounded = userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        List<Role> roles = roleRepository.findAllById(request.getRoleIds());
        userFounded.setRoles(new HashSet<>(roles));
        return UserMapper.toUserResponse(userRepository.save(userFounded));
    }

    @Override
    public UserCreationResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String identity = context.getAuthentication().getName();

        User user = userRepository.findByEmail(identity);
        if(user == null)
            user = userRepository.findByPhone(identity);

        if(user == null)
            throw new ApplicationException(ErrorCode.USER_NOT_FOUND);

        return UserMapper.toUserResponse(user);
    }

    @Override
    public UserCreationResponse updateUserByField(Long id, Map<String, Object> updates) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    user.setName((String) value);
                    break;
                case "email":
                    user.setEmail((String) value);
                    break;
                case "phone":
                    user.setPhone((String) value);
                    break;
                case "address":
                    user.setAddress((String) value);
                    break;
                case "dob":
                    user.setDob(LocalDate.parse((String) value));
                    break;
                case "gender":
                    user.setGender(new Gender((Long) value));
                    break;
                case "image":
                    user.setImage((String) value);
                default:
                    throw new ApplicationException(ErrorCode.FIELD_INVALID);
            }
        });

        return UserMapper.toUserResponse(userRepository.save(user));
    }

    @Override
//    @PostAuthorize("returnObject.email == authentication.name || returnObject.phone == authentication.name")
    public void resetPassword(Long id, UserResetPasswordRequest request) {
        if(!request.getNewPassword().equals(request.getNewPasswordConfirm()))
            throw new ApplicationException(ErrorCode.CONFIRM_PASSWORD_NOT_MATCH);

        if(request.getCurrentPassword().equals(request.getNewPassword()))
            throw new ApplicationException(ErrorCode.NEW_PASSWORD_INVALID);

        User userFounded = userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean isMatched = passwordEncoder.matches(request.getCurrentPassword(), userFounded.getPassword());
        if(!isMatched) throw new ApplicationException(ErrorCode.CURRENT_PASSWORD_INVALID);

        userFounded.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(userFounded);

        userRepository.save(userFounded);
    }

    @Override
    public UserSaveResponse saveHotel(UserSaveRequest request) {
        Long userId = request.getUserId();
        Long hotelId = request.getHotelId();

        User userFounded = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
        Hotel hotelFounded = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.HOTEL_NOT_FOUND));

        UserSaveDocument userSaveDocument = userSaveDocumentRepository.findByUserId(userId);
        if(userSaveDocument != null) {
            if(userSaveDocument.getHotelsId().contains(hotelId)) {
                userSaveDocument.getHotelsId().remove(hotelId);
                userFounded.setHotelsSaved(new HashSet<>());
                userRepository.save(userFounded);
                userFounded.setHotelsSaved(new HashSet<>(hotelRepository.findAllById(userSaveDocument.getHotelsId())));
            } else {
                userFounded.getHotelsSaved().add(hotelFounded);
                userSaveDocument.getHotelsId().add(hotelId);
            }
        } else {
            Set<Long> hotel = new HashSet<>();
            hotel.add(hotelId);
            userSaveDocument = UserSaveDocument.builder().id(userId).userId(userId).hotelsId(hotel).build();
            userFounded.getHotelsSaved().add(hotelFounded);
        }

        userRepository.save(userFounded);
        userSaveDocumentRepository.save(userSaveDocument);
        return UserSaveResponse.builder().userId(userId).hotelId(hotelId).build();
    }

    @Override
    public UserSaveGetResponse getHotelSaveByUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        UserSaveDocument userSaveDocument = userSaveDocumentRepository.findByUserId(userId);
        List<Hotel> hotels = hotelRepository.findAllById(userSaveDocument.getHotelsId());
        List<HotelSearchAllResponse> hotelResponses = hotels.stream().map(HotelMapper::toHotelSearchAllResponse).toList();
        hotelResponses.forEach(h -> {
            List<ReviewGetAllHotelResponse> reviewResponse =
                    reviewRepository.findByHotelId(
                            h.getId()
                    ).stream().map(ReviewMapper::toReviewGetAllHotelResponse).toList();
            h.setReviews(reviewResponse);
            List<RoomCreationResponse> rooms = roomRepository.findByHotelId(h.getId()).stream().map(
                    RoomMapper::toRoomCreationResponse
            ).toList();
            ImageResponse imageResponse = ImageMapper.toImageResponse(imageRepository.findByHotelId(h.getId()).getFirst());
            h.setRooms(rooms);
            h.setImage(imageResponse);
        });
        return UserSaveGetResponse.builder()
                .userId(userId)
                .hotelResponses(hotelResponses)
                .build();
    }
}
