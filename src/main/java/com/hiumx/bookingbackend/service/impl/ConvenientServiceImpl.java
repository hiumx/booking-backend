package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.document.ConvenientDocument;
import com.hiumx.bookingbackend.dto.request.ConvenientRequest;
import com.hiumx.bookingbackend.dto.response.ConvenientResponse;
import com.hiumx.bookingbackend.entity.Convenient;
import com.hiumx.bookingbackend.mapper.ConvenientMapper;
import com.hiumx.bookingbackend.repository.ConvenientRepository;
import com.hiumx.bookingbackend.repository.document.ConvenientDocumentRepository;
import com.hiumx.bookingbackend.service.ConvenientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConvenientServiceImpl implements ConvenientService {

    private ConvenientRepository convenientRepository;
    private ConvenientDocumentRepository convenientDocumentRepository;
    @Override
    public List<ConvenientResponse> getAll() {
        List<Convenient> convenients = convenientRepository.findAll();
//        for(Convenient c : convenients) {
//            ConvenientDocument convenientDocument = ConvenientDocument.
//                    builder()
//                    .id(c.getId())
//                    .name(c.getName())
//                    .build();
//            convenientDocumentRepository.save(convenientDocument);
//        }
        return convenients.stream().map(ConvenientMapper::toConvenientResponse).toList();

    }

    @Override
    public ConvenientResponse create(ConvenientRequest request) {
         Convenient convenient = Convenient.builder()
                 .name(request.getName())
                 .build();
         Convenient convenientSaved = convenientRepository.save(convenient);
         convenientDocumentRepository.save(
                 ConvenientDocument.builder().id(convenientSaved.getId()).name(request.getName()).build()
         );
         return ConvenientMapper.toConvenientResponse(convenientSaved);
    }

}
