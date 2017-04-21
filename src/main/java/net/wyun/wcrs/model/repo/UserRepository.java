package net.wyun.wcrs.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import net.wyun.wcrs.model.Account;

public interface UserRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}
