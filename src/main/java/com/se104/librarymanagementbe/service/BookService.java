package com.se104.librarymanagementbe.service;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateBookRequest;
import com.se104.librarymanagementbe.dto.request.UpdateBookRequest;
import com.se104.librarymanagementbe.dto.response.*;
import com.se104.librarymanagementbe.entity.Author;
import com.se104.librarymanagementbe.entity.Book;
import com.se104.librarymanagementbe.entity.Category;
import com.se104.librarymanagementbe.repository.AuthorRepository;
import com.se104.librarymanagementbe.repository.BookRepository;
import com.se104.librarymanagementbe.repository.CategoryRepository;
import com.se104.librarymanagementbe.repository.ConfigLibraryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    @Autowired
    private final ConfigLibraryService configLibraryService;
    private final ConfigLibraryRepository configLibraryRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ModelMapper mapper;


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

    public RestResponse<CreateBookResponse> createBook(CreateBookRequest book) {
        Optional<Category> category = categoryRepository.findById(book.getCategoryId());
        if(category.isEmpty()){
            return null;
        }
        Optional<Author> author = authorRepository.findById(book.getAuthorId());
        if(author.isEmpty()){
            return null;
        }
        GetOneConfigLibraryResponse lastConfig = configLibraryService.getLastConfig();
        LocalDateTime publishDate = LocalDateTime.ofInstant(book.getPublishDate(), ZoneOffset.UTC);

        LocalDateTime publishDateValid = publishDate.plusYears(lastConfig.getYearOfPublication());

        Instant now = Instant.now();
        LocalDateTime currentDate = LocalDateTime.ofInstant(now, ZoneOffset.UTC);
        if (currentDate.isAfter(publishDateValid)) {
            return null;
        }
        Book newBook = mapper.map(book, Book.class);
        newBook.setCategory(category.get());
        newBook.setAuthor(author.get());
        bookRepository.save(newBook);
        return RestResponse.<CreateBookResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(mapper.map(newBook, CreateBookResponse.class))
                .build();
    }

    public RestResponse<UpdateBookResponse> updateBook(UpdateBookRequest book, Long id) {
        Optional<Book> oldBook = bookRepository.findById(id);
        if (oldBook.isPresent()) {
            if(book.getName() != null){
                oldBook.get().setName(book.getName());
            }
            if(book.getCategoryId() != 0){
                Optional<Category> category = categoryRepository.findById(book.getCategoryId());
                if(category.isEmpty()){
                    return null;
                } else{
                    oldBook.get().setCategory(category.get());
                }
            }
            if(book.getAuthorId() != 0){
                Optional<Author> author = authorRepository.findById(book.getCategoryId());
                if(author.isEmpty()){
                    return null;
                }else{
                    oldBook.get().setAuthor(author.get());
                }
            }
            if(book.getPublishDate() != null){
                GetOneConfigLibraryResponse lastConfig = configLibraryService.getLastConfig();
                LocalDateTime publishDate = LocalDateTime.ofInstant(book.getPublishDate(), ZoneOffset.UTC);

                LocalDateTime publishDateValid = publishDate.plusYears(lastConfig.getYearOfPublication());

                Instant now = Instant.now();
                LocalDateTime currentDate = LocalDateTime.ofInstant(now, ZoneOffset.UTC);
                if (currentDate.isAfter(publishDateValid)) {
                    return null;
                }else{
                    oldBook.get().setPublishDate(book.getPublishDate());
                }
            }
            if(book.getPublisher() != null){
                oldBook.get().setPublisher(book.getPublisher());
            }
            if(book.getPrice() != 0){
                oldBook.get().setPrice(book.getPrice());
            }
            bookRepository.save(oldBook.get());
            return RestResponse.<UpdateBookResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(mapper.map(oldBook, UpdateBookResponse.class))
                    .build();
        } else {
            return null;
        }
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }


    public RestResponse<List<GetListBookResponse>> getListBooks() {
        List<Book> books = bookRepository.findAll();

        return RestResponse.<List<GetListBookResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(books.stream()
                        .map(book -> mapper.map(book, GetListBookResponse.class))
                        .collect(Collectors.toList()))
                .build();
    }
}
