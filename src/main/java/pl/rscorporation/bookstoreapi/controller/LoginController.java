package pl.rscorporation.bookstoreapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rscorporation.bookstoreapi.dao.dto.UserDTO;
import pl.rscorporation.bookstoreapi.manager.LoginService;

@RestController
public class LoginController {

    private LoginService loginService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);


    @PostMapping()
    public void login(UserDTO userDTO) {
        logger.info("Login" + userDTO.toString());


    }

}
