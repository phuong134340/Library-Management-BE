package com.se104.librarymanagementbe.service;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateReturnBookRequest;
import com.se104.librarymanagementbe.dto.response.*;
import com.se104.librarymanagementbe.entity.Fine;
import com.se104.librarymanagementbe.entity.Rent;
import com.se104.librarymanagementbe.entity.Return_Book;
import com.se104.librarymanagementbe.repository.FineRepository;
import com.se104.librarymanagementbe.repository.RentRepository;
import com.se104.librarymanagementbe.repository.ReturnBookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.cfg.CoercionInputShape.Array;
import static java.util.Collections.max;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@AllArgsConstructor

public class ReturnBookService {
    private final ReturnBookRepository returnBookRepository;
    private final ModelMapper mapper;
    private final RentRepository rentRepository;
    private final FineRepository fineRepository;
    private final ConfigLibraryService configLibraryService;

    public RestResponse<GetOneReturnBookResponse> getOneReturnBook(Long id) {
        Optional<Return_Book> returnBook = returnBookRepository.findById(id);
        List<Rent> rents = rentRepository.findAllByReturnBookId(id);
        if (returnBook.isPresent()) {
            GetOneReturnBookResponse res = mapper.map(returnBook, GetOneReturnBookResponse.class);
            res.setRentIds( new ArrayList<Long>() );
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
        GetOneConfigLibraryResponse lastConfig = configLibraryService.getLastConfig();
        for(int i = 0; i < returnBook.getRentIds().size(); i++){
            Optional<Fine> fine = fineRepository.findById(returnBook.getRentIds().get(i));
            Optional<Rent> rent = rentRepository.findById( returnBook.getRentIds().get(i) );
            rent = rentRepository.findById(returnBook.getRentIds().get(i));
            if(!rent.isEmpty() && rent.get().getStatus().equals("rented")){
                fine = fineRepository.findOneByReaderId(rent.get().getReader().getId());
                Instant rentDay = rent.get().getStartDate();
                Instant returnDay = returnBook.getReturnDate();
                Duration duration = Duration.between(rentDay, returnDay);
                long daysBetween = duration.toDays();
                long dayDelay = 0;
                if(daysBetween - lastConfig.getDayMax() > 0){
                    dayDelay = daysBetween - lastConfig.getDayMax();
                }
                if(!fine.isEmpty()){
                    fine.get().setTotal( fine.get().getTotal() + dayDelay*1000 );
                    fineRepository.save(fine.get());
                }else{
                    Fine newFine = new Fine();
                    newFine.setTotal(dayDelay*1000);
                    newFine.setReader(rent.get().getReader());
                    fineRepository.save(newFine);
                }
                rent.get().setStatus("returned");
                rent.get().setReturnBook(newReturnBook);
                rentRepository.save(rent.get());
            }


        }
        return RestResponse.<CreateReturnBookResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(mapper.map(newReturnBook, CreateReturnBookResponse.class))
                .build();
    }

    public RestResponse<List<GetListReturnBookResponse>> getListReturnBooks() {
        List<Return_Book> returnBooks = returnBookRepository.findAll();
        List<GetListReturnBookResponse> response = new ArrayList<GetListReturnBookResponse>();
        for (int i = 0; i < returnBooks.size(); i++) {
            List<Rent> rents = rentRepository.findAllByReturnBookId(returnBooks.get(i).getId());
            GetListReturnBookResponse returnBook = mapper.map(returnBooks.get(i), GetListReturnBookResponse.class);
            returnBook.setRentIds(new ArrayList<Long>());
            for(int j = 0; j < rents.size(); j++) {returnBook.getRentIds().add( rents.get(j).getId());}
            response.add(returnBook);
        }
        return RestResponse.<List<GetListReturnBookResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(response)
                .build();
    }

//    public void deleteReturnBook(Long id) {
//        returnBookRepository.deleteById(id);
//    }
}
