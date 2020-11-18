package pl.rscorporation.bookstoreapi.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.rscorporation.bookstoreapi.dao.AuthorRepository;
import pl.rscorporation.bookstoreapi.dao.BookRepository;
import pl.rscorporation.bookstoreapi.dao.dto.AuthorReadDTO;
import pl.rscorporation.bookstoreapi.dao.dto.AuthorWriteDTO;
import pl.rscorporation.bookstoreapi.dao.dto.BookReadDTO;
import pl.rscorporation.bookstoreapi.dao.models.Author;
import pl.rscorporation.bookstoreapi.dao.models.Book;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthorServiceTest {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private AuthorService authorService;

    @BeforeEach
    public void initVariables(){
        authorRepository = mock(AuthorRepository.class);
        bookRepository = mock(BookRepository.class);
        authorService = new AuthorService(authorRepository, bookRepository);
    }

    @Test
    public void findById_authorIdNotExists_shouldThrowIllegalArgumentException(){
        //given
        when(authorRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when
        var resultToCheck = catchThrowable(() -> authorService.findAuthorById(anyLong()));

        //then
        assertThat(resultToCheck).isInstanceOf(IllegalArgumentException.class).hasMessage("Author with this id not exists");
    }

    @Test
    public void findById_returnAuthorReadDTO_everythingCorrect(){
        //given
        var mockAuthor = mock(Author.class);
        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(mockAuthor));

        //when
        var resultToTest = authorService.findAuthorById(5L);

        //then
        assertThat(resultToTest).isInstanceOf(AuthorReadDTO.class);
    }


    @Test
    public void findAll_shouldReturnListOfAuthorReadDTO(){
        //given
        var author1 = mock(Author.class);
        when(authorRepository.findAll()).thenReturn(List.of(author1));

        //when
        var listToTest = authorService.findAll();

        //then
        assertThat(listToTest.get(0)).isInstanceOf(AuthorReadDTO.class);
    }

    @Test
    public void saveAuthor_shouldAddAuthorAndReturnAuthorReadDTO(){
        //given
        var mockAuthorWriteDTO = mock(AuthorWriteDTO.class);
        var mockAuthor = mock(Author.class);
        when(authorRepository.save(any())).thenReturn(mockAuthor);

        //when
        var resultToTest = authorService.saveAuthor(mockAuthorWriteDTO);

        //then
        assertThat(resultToTest).isInstanceOf(AuthorReadDTO.class);
    }

    @Test
    public void deleteAuthorById_authorIdNotExists_shouldThrowIllegalArgumentException(){
        //given
        when(authorRepository.existsById(anyLong())).thenReturn(false);

        //when
        var resultToTest = catchThrowable(() -> authorService.deleteAuthorById(6L));

        //then
        assertThat(resultToTest).isInstanceOf(IllegalArgumentException.class).hasMessage("Author with given id not exists");
    }

    @Test
    public void findAuthorBooks_authorWithGivenIdNotExists_shouldThrowIllegalArgumentException(){
        //given
        initVariables();
        when(authorRepository.existsById(anyLong())).thenReturn(false);

        //when
        var toTest = catchThrowable(() -> authorService.findAuthorBooks(5L));

        //then
        assertThat(toTest).isInstanceOf(IllegalArgumentException.class).hasMessage("Author with given id not exists");
    }

    @Test
    public void findAuthorBooks_everythingCorrect_shouldReturnBookReadDTOList(){
        initVariables();
        List<Book> books = List.of(mock(Book.class));
        when(authorRepository.existsById(anyLong())).thenReturn(true);
        when(bookRepository.findByAuthorId(anyLong())).thenReturn(books);

        //when
        var resultToTest = authorService.findAuthorBooks(5L);

        //then
        assertThat(resultToTest.get(0)).isInstanceOf(BookReadDTO.class);
    }

    /*
    public List<BookReadDTO> findAuthorBooks(Long authorId){
        if(!authorRepository.existsById(authorId))
            throw new IllegalArgumentException("Author with given id not exists");
        return bookRepository.findByAuthorId(authorId).stream()
                .map(BookReadDTO::new)
                .collect(Collectors.toList());
    }
     */

}
