package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.request.HistorySearchRequest;
import com.hiumx.bookingbackend.dto.response.HistorySearchResponse;
import com.hiumx.bookingbackend.dto.response.HotelResponse;
import com.hiumx.bookingbackend.entity.HistorySearch;
import com.hiumx.bookingbackend.entity.Hotel;
import com.hiumx.bookingbackend.entity.User;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.mapper.HistorySearchMapper;
import com.hiumx.bookingbackend.repository.HistorySearchRepository;
import com.hiumx.bookingbackend.repository.HotelRepository;
import com.hiumx.bookingbackend.repository.UserRepository;
import com.hiumx.bookingbackend.service.HistorySearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HistorySearchServiceImpl implements HistorySearchService {

    private HistorySearchRepository historySearchRepository;
    private UserRepository userRepository;
    private HotelRepository hotelRepository;

    @Override
    public HistorySearchResponse create(HistorySearchRequest request) {

        User userFounded = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        Hotel hotelFounded = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.HOTEL_NOT_FOUND));

        List<HistorySearch> historySearchFounded = historySearchRepository.findTop4Recent(request.getUserId());
        historySearchFounded.forEach(h -> {
            if(h.getStartDate().equals(request.getStartDate()) && h.getEndDate().equals(request.getEndDate())
                && h.getHotel().getId().equals(request.getHotelId()) && h.getAdult() == request.getAdult()
                  && h.getChildren() == request.getChildren()  && h.getRooms() == request.getRooms()
            ) {
                throw new ApplicationException(ErrorCode.HISTORY_SEARCH_ALREADY_EXIST);
            }
        });

        HistorySearch historySearch = HistorySearchMapper.toHistorySearch(request);
        historySearch.setUser(userFounded);
        historySearch.setHotel(hotelFounded);
        return HistorySearchMapper.toHistorySearchResponse(historySearchRepository.save(historySearch));
    }


    @Override
    public List<HistorySearchResponse> getHistorySearches(Long userId) {
        List<HistorySearch> results = historySearchRepository.findTop4Recent(userId);
        return results.stream().map(HistorySearchMapper::toHistorySearchResponse).toList();
    }
}
