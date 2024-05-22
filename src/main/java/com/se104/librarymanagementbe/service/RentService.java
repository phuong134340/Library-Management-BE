package com.se104.librarymanagementbe.service;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateRentRequest;
import com.se104.librarymanagementbe.dto.request.UpdateRentRequest;
import com.se104.librarymanagementbe.dto.response.CreateRentResponse;
import com.se104.librarymanagementbe.dto.response.GetListRentResponse;
import com.se104.librarymanagementbe.dto.response.GetOneRentResponse;
import com.se104.librarymanagementbe.dto.response.UpdateRentResponse;
import com.se104.librarymanagementbe.entity.Book;
import com.se104.librarymanagementbe.entity.Reader;
import com.se104.librarymanagementbe.entity.Rent;
import com.se104.librarymanagementbe.repository.BookRepository;
import com.se104.librarymanagementbe.repository.ReaderRepository;
import com.se104.librarymanagementbe.repository.RentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentService {
    private final RentRepository rentRepository;
    private final ModelMapper mapper;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    public RestResponse<GetOneRentResponse> getOneRent(Long id) {
        Optional<Rent> rent = rentRepository.findById(id);
        if (rent.isPresent()) {
            GetOneRentResponse res = mapper.map(rent, GetOneRentResponse.class);
            return RestResponse.<GetOneRentResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(res)
                    .build();
        } else {
            return null;
        }
    }


    public RestResponse<CreateRentResponse> createRent(CreateRentRequest rent) {
        Optional<Book> book = bookRepository.findById(rent.getBookId());
        if(book.isEmpty()){
            return null;
        }
        Optional<Reader> reader = readerRepository.findById(rent.getReaderId());
        return RestResponse.<CreateRentResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(mapper.map(res, CreateRentResponse.class))
                .build();
    }

    public RestResponse<UpdateRentResponse> updateRent(UpdateRentRequest rent, Long id) {
        Optional<Rent> oldRent = rentRepository.findById(id);
        if (oldRent.isPresent()) {

            rentRepository.save(oldRent.get());
            return RestResponse.<UpdateRentResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(mapper.map(oldRent, UpdateRentResponse.class))
                    .build();
        } else {
            return null;
        }
    }

    public void deleteRent(Long id) {
        rentRepository.deleteById(id);
    }

    public RestResponse<List<GetListRentResponse>> getListRents() {
        List<Rent> rents = rentRepository.findAll();

        return RestResponse.<List<GetListRentResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(rents.stream()
                        .map(user -> mapper.map(rents, GetListRentResponse.class))
                        .collect(Collectors.toList()))
                .build();
    }
}
