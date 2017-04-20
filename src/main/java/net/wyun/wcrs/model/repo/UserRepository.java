package net.wyun.wcrs.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import net.wyun.wcrs.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
