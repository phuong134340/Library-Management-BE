package com.se104.librarymanagementbe.service.impl;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateBookRequest;
import com.se104.librarymanagementbe.dto.request.UpdateBookRequest;
import com.se104.librarymanagementbe.dto.response.*;
import com.se104.librarymanagementbe.entity.Book;
import com.se104.librarymanagementbe.repository.BookRepository;
import com.se104.librarymanagementbe.service.BookService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper mapper;


    @Override
    public RestResponse<GetOneBookResponse> getOneBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            GetOneBookResponse res = mapper.map(book, GetOneBookResponse.class);
            return RestResponse.<GetOneBookResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(res)
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public RestResponse<CreateBookResponse> createBook(CreateBookRequest book) {
        Book res = bookRepository.save(mapper.map(book, Book.class));
        return RestResponse.<CreateBookResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(mapper.map(res, CreateBookResponse.class))
                .build();
    }

    @Override
    public RestResponse<UpdateBookResponse> updateBook(UpdateBookRequest book, Long id) {
        Optional<Book> oldBook = bookRepository.findById(id);
        if (oldBook.isPresent()) {
            oldBook.get()
                    .setName(book.getName());
            bookRepository.save(oldBook.get());
            return RestResponse.<UpdateBookResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(mapper.map(oldBook, UpdateBookResponse.class))
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public RestResponse<List<GetListBookResponse>> getListBooks() {
        List<Book> books = bookRepository.findAll();

        return RestResponse.<List<GetListBookResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(books.stream()
                        .map(user -> mapper.map(books, GetListBookResponse.class))
                        .collect(Collectors.toList()))
                .build();
    }
}