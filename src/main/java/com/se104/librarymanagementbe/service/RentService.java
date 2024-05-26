package com.se104.librarymanagementbe.service;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateRentRequest;
import com.se104.librarymanagementbe.dto.request.UpdateRentRequest;
import com.se104.librarymanagementbe.dto.response.*;
import com.se104.librarymanagementbe.entity.Book;
import com.se104.librarymanagementbe.entity.ConfigLibrary;
import com.se104.librarymanagementbe.entity.Reader;
import com.se104.librarymanagementbe.entity.Rent;
import com.se104.librarymanagementbe.repository.BookRepository;
import com.se104.librarymanagementbe.repository.ConfigLibraryRepository;
import com.se104.librarymanagementbe.repository.ReaderRepository;
import com.se104.librarymanagementbe.repository.RentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentService {
    @Autowired
    private final RentRepository rentRepository;
    private final ModelMapper mapper;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final ConfigLibraryService configLibraryService;

    public RestResponse<GetOneRentResponse> getOneRent(Long id) {
        Optional<Rent> rent = rentRepository.findById(id);
        if (rent.isPresent()) {
            GetOneRentResponse res = mapper.map(rent, GetOneRentResponse.class);
            res.setBookId(rent.get().getBook().getId());
            res.setReaderId(rent.get().getReader().getId());
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
        if(!rent.getStatus().equals("rented") && !rent.getStatus().equals("returned")){
            return null;
        }
        if(book.isEmpty()){
            return null;
        }
        Optional<Reader> reader = readerRepository.findById(rent.getReaderId());
        if(reader.isEmpty()){
            return null;
        }
        List<Rent> totalRent = rentRepository.findAllByReaderIdAndStatus(rent.getReaderId(), rent.getStatus());
        GetOneConfigLibraryResponse lastConfig = configLibraryService.getLastConfig();
        System.out.println("-----------");
        System.out.println(totalRent.size());
        System.out.println(lastConfig.getLimitBook());
        if(totalRent.size()  >= lastConfig.getLimitBook() ){
            return null;
        }
        List<Rent> bookRent = rentRepository.findAllByBookIdAndStatus(rent.getBookId(), rent.getStatus());
        if(bookRent.size() > 0){
            return null;
        }
        Rent newRent = mapper.map(rent, Rent.class);
        newRent.setBook(book.get());
        newRent.setReader(reader.get());
        rentRepository.save(newRent);
        return RestResponse.<CreateRentResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(mapper.map(newRent, CreateRentResponse.class))
                .build();
    }

//    public RestResponse<UpdateRentResponse> updateRent(UpdateRentRequest rent, Long id) {
//        Optional<Rent> oldRent = rentRepository.findById(id);
//
//        if (oldRent.isPresent()) {
//
//            if(rent.getBookId() != 0){
//                Optional<Book> book = bookRepository.findById(rent.getBookId());
//                if(book.isEmpty()){
//                    return null;
//                }else{
//                    oldRent.get().setBook(book.get());
//                }
//            }
//            if(rent.getReaderId() != 0){
//                Optional<Reader> reader = readerRepository.findById(rent.getReaderId());
//                if(reader.isEmpty()){
//                    return null;
//                }else{
//                    oldRent.get().setReader(reader.get());
//                }
//            }
//            if(rent.getStartDate() != null){
//                oldRent.get().setStartDate(rent.getStartDate());
//            }
//            if(rent.getEndDate() != null){
//                oldRent.get().setEndDate(rent.getStartDate());
//            }
//            if(rent.getStatus() != null){
//                if(rent.getStatus() != "rented" && rent.getStatus() != "returned"){
//                return null;
//            }
//                oldRent.get().setStatus(rent.getStatus());
//            }
//            rentRepository.save(oldRent.get());
//            return RestResponse.<UpdateRentResponse>builder()
//                    .status(HttpStatus.OK.value())
//                    .data(mapper.map(oldRent, UpdateRentResponse.class))
//                    .build();
//        } else {
//            return null;
//        }
//    }

    public void deleteRent(Long id) {
        rentRepository.deleteById(id);
    }

    public RestResponse<List<GetListRentResponse>> getListRents() {
        List<Rent> rents = rentRepository.findAll();
        List<GetListRentResponse> rentsResponse = new ArrayList<GetListRentResponse>();
        for (int i = 0; i < rents.size(); i++) {
            GetListRentResponse rent = mapper.map(rents.get(i), GetListRentResponse.class);
            rent.setBookId(rents.get(i).getBook().getId());
            rent.setReaderId(rents.get(i).getReader().getId());
            rentsResponse.add(rent);
        }

        return RestResponse.<List<GetListRentResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(rentsResponse)
                .build();
    }
    public RestResponse<List<GetListRentResponse>>getListRentByReaderId(long readerId){
        List<Rent> rents = rentRepository.findAllByReaderId(readerId);
        List<GetListRentResponse> rentsResponse = new ArrayList<GetListRentResponse>();
        for (int i = 0; i < rents.size(); i++) {
            GetListRentResponse rent = mapper.map(rents.get(i), GetListRentResponse.class);
            rent.setBookId(rents.get(i).getBook().getId());
            rent.setReaderId(rents.get(i).getReader().getId());
            rentsResponse.add(rent);
        }

        return RestResponse.<List<GetListRentResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(rentsResponse)
                .build();
    }
}
