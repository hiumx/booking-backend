package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.document.HotelDocument;
import com.hiumx.bookingbackend.document.RoomDocument;
import com.hiumx.bookingbackend.dto.request.SearchFilterRequest;
import com.hiumx.bookingbackend.dto.request.SearchRequest;
import com.hiumx.bookingbackend.dto.response.HotelSearchAllResponse;
import com.hiumx.bookingbackend.dto.response.ImageResponse;
import com.hiumx.bookingbackend.dto.response.ReviewGetAllHotelResponse;
import com.hiumx.bookingbackend.dto.response.RoomCreationResponse;
import com.hiumx.bookingbackend.entity.Hotel;
import com.hiumx.bookingbackend.entity.Image;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.mapper.HotelMapper;
import com.hiumx.bookingbackend.mapper.ImageMapper;
import com.hiumx.bookingbackend.mapper.ReviewMapper;
import com.hiumx.bookingbackend.mapper.RoomMapper;
import com.hiumx.bookingbackend.repository.HotelRepository;
import com.hiumx.bookingbackend.repository.ImageRepository;
import com.hiumx.bookingbackend.repository.ReviewRepository;
import com.hiumx.bookingbackend.repository.RoomRepository;
import com.hiumx.bookingbackend.repository.document.BookingCustomRepository;
import com.hiumx.bookingbackend.repository.document.HotelCustomRepository;
import com.hiumx.bookingbackend.repository.document.HotelDocumentRepository;
import com.hiumx.bookingbackend.repository.document.RoomDocumentRepository;
import com.hiumx.bookingbackend.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

    private HotelDocumentRepository hotelDocumentRepository;
    private RoomDocumentRepository roomDocumentRepository;
    private BookingCustomRepository bookingCustomRepository;
    private HotelRepository hotelRepository;
    private RoomRepository roomRepository;
    private ReviewRepository reviewRepository;
    private ImageRepository imageRepository;
    private HotelCustomRepository hotelCustomRepository;

    @Override
    public List<HotelSearchAllResponse> search(SearchRequest request) {
        String searchString = request.getLocation().isEmpty() ? request.getName() : request.getLocation();
        List<HotelDocument> hotelDocuments = hotelCustomRepository.searchHotelMatchLocationOrName(searchString);
        System.out.println(hotelDocuments);
        return getSearchHotel(getHotelRoomSearch(hotelDocuments, request.getStartDate(), request.getEndDate()));
    }

    @Override
    public List<HotelSearchAllResponse> filterByCheckbox(SearchFilterRequest request) {
        Set<HotelDocument> result = new HashSet<>();

        String searchTerm = request.getLocation().isEmpty() ? request.getLocation() : request.getName();

        if(request.getChecksType().isEmpty() && request.getChecksConvenient().isEmpty()
            && request.getLowestPrice() == 0 && request.getHighestPrice() == 1000
        ) {
            return search(
                    SearchRequest.builder()
                            .startDate(request.getStartDate())
                            .name(request.getName())
                            .endDate(request.getEndDate())
                            .location(request.getLocation())
                            .options(request.getOptions())
                            .build()
            );
        } else if(
                request.getChecksConvenient().isEmpty() && request.getLowestPrice() == 0
                && request.getHighestPrice() == 1000) {
            request.getChecksType().forEach(c -> {
                List<HotelDocument> hotelDocuments = hotelCustomRepository.findByTypeHotelId(c, searchTerm);
                result.addAll(hotelDocuments);
            });
        } else if(
                request.getChecksType().isEmpty() && request.getLowestPrice() == 0
                        && request.getHighestPrice() == 1000
        ) {
            request.getChecksConvenient().forEach(c -> {
                List<HotelDocument> hotelDocuments = hotelCustomRepository.findByConvenient(c, searchTerm);
                result.addAll(hotelDocuments);
            });
        } else if(request.getLowestPrice() == 0 && request.getHighestPrice() == 1000) {
            for (int i = 0; i < request.getChecksType().size(); i++) {
                for (int j = 0; j < request.getChecksConvenient().size(); j++) {
                    List<HotelDocument> hotelDocuments = hotelCustomRepository.findByTypeHotelAndConvenient(
                            request.getChecksType().get(i), request.getChecksConvenient().get(j), searchTerm
                    );
                    result.addAll(hotelDocuments);
                }
            }
        } else {
            for (int i = 0; i < request.getChecksType().size(); i++) {
                for (int j = 0; j < request.getChecksConvenient().size(); j++) {
                    List<HotelDocument> hotelDocuments = hotelCustomRepository.findByTypeHotelConvenientPrice(
                            request.getChecksType().get(i), request.getChecksConvenient().get(j), searchTerm,
                            request.getLowestPrice(), request.getHighestPrice()
                    );
                    result.addAll(hotelDocuments);
                }
            }
        }



        return getSearchHotel((getHotelRoomSearch(result.stream().toList(), request.getStartDate(), request.getEndDate())));
    }

    private Map<Long, Long> getHotelRoomSearch(List<HotelDocument> hotelDocuments, LocalDate startDate, LocalDate endDate) {
        Map<Long, Long> map = new LinkedHashMap<>();

        for(HotelDocument h: hotelDocuments) {
            List<RoomDocument> roomDocuments = roomDocumentRepository.findByHotelId(h.getId());
            for (RoomDocument r : roomDocuments) {
                map.put(r.getId(), h.getId());
            }
        }

        Map<Long, Long> resultMap = new LinkedHashMap<>();
        for (Long key : map.keySet()) {
            Long value = map.get(key);
            if(!bookingCustomRepository.isBookingDateRangeOverlap(key, startDate, endDate)) {
                resultMap.put(key, value);
            }
        }

        return resultMap;
    }

    private List<HotelSearchAllResponse> getSearchHotel(Map<Long, Long> resultSearch) {
        Set<Long> hotelsId = new LinkedHashSet<>();
        for (Long key: resultSearch.keySet()) {
            hotelsId.add(resultSearch.get(key));
        }
        return getHotelsResultByIds(hotelsId);
    }

    private List<HotelSearchAllResponse> getHotelsResultByIds(Set<Long> hotelsId) {
        List<Long> hotelId = hotelsId.stream().toList();
        List<Hotel> hotels = new ArrayList<>();
        hotelId.forEach(id -> {
            Hotel hotelFounded = hotelRepository.findById(id)
                    .orElseThrow(() -> new ApplicationException(ErrorCode.HOTEL_NOT_FOUND));
            hotels.add(hotelFounded);
        });
        List<HotelSearchAllResponse> res = hotels.stream().map(HotelMapper::toHotelSearchAllResponse).toList();

        for (int i = 0; i < hotels.size(); i++) {
            RoomCreationResponse room =
                    RoomMapper.toRoomCreationResponse(roomRepository.findByHotelId(hotels.get(i).getId()).getFirst());
            res.get(i).setRoom(room);

            List<ReviewGetAllHotelResponse> reviewResponse =
                    reviewRepository.findByHotelId(
                            hotels.get(i).getId()).stream().map(ReviewMapper::toReviewGetAllHotelResponse
                    ).toList();
            res.get(i).setReviews(reviewResponse);

            List<Image> images = imageRepository.findByHotelId(hotels.get(i).getId());
            ImageResponse imageResponse = ImageResponse.builder()
                    .url("https://d8271hh5ynwda.cloudfront.net/1716987384235-491152033.jpg")
                    .build();
            if(!images.isEmpty()) {
                imageResponse = ImageMapper.toImageResponse(images.getFirst());
            }
            res.get(i).setImage(imageResponse);

        }
        return res;
    }


}
