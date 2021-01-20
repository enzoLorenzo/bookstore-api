package pl.rscorporation.bookstoreapi.manager;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.rscorporation.bookstoreapi.dao.AuthorRepository;
import pl.rscorporation.bookstoreapi.dao.BookRepository;
import pl.rscorporation.bookstoreapi.dao.dto.BookReadDTO;
import pl.rscorporation.bookstoreapi.dao.dto.BookWriteDTO;
import pl.rscorporation.bookstoreapi.dao.models.Author;
import pl.rscorporation.bookstoreapi.dao.models.Book;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookServiceTest {

    BookRepository mockBookRepository;
    AuthorRepository authorRepository;
    BookService bookService;

    @BeforeEach
    public void initVariables(){
        this.mockBookRepository = mock(BookRepository.class);
        this.authorRepository = mock(AuthorRepository.class);
        this.bookService = new BookService(mockBookRepository, authorRepository);
    }

    @Test
    public void findBookById_BookNotExists_ShouldThrowIllegalArgumentException(){
        //given
        when(mockBookRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when
        var resultToTest = catchThrowable(() -> bookService.findBookById(5L));

        //then
        assertThat(resultToTest).isInstanceOf(IllegalArgumentException.class).hasMessage("Book with this id not exists");
    }

    @Test
    public void findBookById_returnBookReadDTO_everythingCorrect(){
        //given
        var mockBook = mock(Book.class);
        when(mockBookRepository.findById(anyLong())).thenReturn(Optional.of(mockBook));

        //when
        var resultToTest = bookService.findBookById(5L);

        //then
        assertThat(resultToTest).isInstanceOf(BookReadDTO.class);
    }

    @Test
    public void findAll_shouldMapListBookFromRepositoryToListOfBookReadDTO_correctResult(){
        //given
        var mockBook = mock(Book.class);
        when(mockBookRepository.findAll()).thenReturn(List.of(mockBook));

        //when
        var resultToTest = bookService.findAll();

        //then
        assertThat(resultToTest.get(0)).isInstanceOf(BookReadDTO.class);
    }

    @Test
    public void addBook_authorOfBookNotExists_shouldThrowIllegalArgumentException(){
        //given
        when(authorRepository.findById(anyLong())).thenReturn(Optional.empty());
        var mockBookToAdd = mock(BookWriteDTO.class);

        //when
        var resultToTest = catchThrowable(() -> bookService.addBook(mockBookToAdd));

        //then
        assertThat(resultToTest).isInstanceOf(IllegalArgumentException.class).hasMessage("Author with given id not exists insert correct book author");
    }

//    @Test
//    public void addBook_shouldReturnBookReadDTO_everythingCorrect(){
//        //given
////        var mockBookReturnedFromRepo =
//
//        Book book = new Book();
//        BookWriteDTO bookWriteDTO = new BookWriteDTO();
//        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(new Author()));
//
//        //when
//        var resultToTest = bookService.addBook(bookWriteDTO);
//
//        //then
//        assertThat(resultToTest).isInstanceOf(BookReadDTO.class);
//    }

    @Test
    public void deleteBookById_bookWithGivenIdNotExists_shouldThrowIllegalArgumentException(){
        //given
        when(mockBookRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when
        var resultToTest = catchThrowable(() -> bookService.deleteBookById(5L));

        //then
        assertThat(resultToTest).isInstanceOf(IllegalArgumentException.class).hasMessage("Book with given id not exists");
    }

}
