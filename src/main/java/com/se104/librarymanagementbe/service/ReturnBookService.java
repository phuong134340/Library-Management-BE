package com.se104.librarymanagementbe.service;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateReturnBookRequest;
import com.se104.librarymanagementbe.dto.response.*;
import com.se104.librarymanagementbe.entity.Rent;
import com.se104.librarymanagementbe.entity.Return_Book;
import com.se104.librarymanagementbe.repository.RentRepository;
import com.se104.librarymanagementbe.repository.ReturnBookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@AllArgsConstructor

public class ReturnBookService {
    private final ReturnBookRepository returnBookRepository;
    private final ModelMapper mapper;
    private final RentRepository rentRepository;

    public RestResponse<GetOneReturnBookResponse> getOneReturnBook(Long id) {
        Optional<Return_Book> returnBook = returnBookRepository.findById(id);
        List<Rent> rents = rentRepository.findAllByReturnBookId(id);
        if (returnBook.isPresent()) {
            GetOneReturnBookResponse res = mapper.map(returnBook, GetOneReturnBookResponse.class);
            for(int i = 0; i < rents.size(); i++) { res.getRentIds().add( rents.get(i).getId() );}

            return RestResponse.<GetOneReturnBookResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(res)
                    .build();
        } else {
            return null;
        }
    }


    public RestResponse<CreateReturnBookResponse> createReturnBook(CreateReturnBookRequest returnBook) {
        Return_Book newReturnBook = mapper.map(returnBook, Return_Book.class);
        returnBookRepository.save(newReturnBook);
        for(int i = 0; i < returnBook.getRentIds().size(); i++){
            Optional<Rent> rent = rentRepository.findById( returnBook.getRentIds().get(i) );
            rent.get().setStatus("returned");
            rent.get().setReturnBook(newReturnBook);
            rentRepository.save(rent.get());

        }
        return RestResponse.<CreateReturnBookResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(mapper.map(newReturnBook, CreateReturnBookResponse.class))
                .build();
    }

    public RestResponse<List<GetListReturnBookResponse>> getListReturnBooks() {
        List<Return_Book> returnBooks = returnBookRepository.findAll();

        return RestResponse.<List<GetListReturnBookResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(returnBooks.stream()
                        .map(returnBook -> mapper.map(returnBook, GetListReturnBookResponse.class))
                        .collect(Collectors.toList()))
                .build();
    }


//    public void deleteReturnBook(Long id) {
//        returnBookRepository.deleteById(id);
//    }
}
