package com.sevilla.usermanager.users;

import static org.assertj.core.api.Assertions.assertThat;

import com.sevilla.usermanager.dao.UserRepository;
import com.sevilla.usermanager.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;

    @Test
    public void testCreateUser() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("pswd2023");

        User newUser = new User("0000", "NNN", "name@mail.net", password, new Date(), null, null, "token", Boolean.TRUE, null);
        User savedUser = repo.save(newUser);

        assertThat(savedUser).isNotNull();
    }
}
