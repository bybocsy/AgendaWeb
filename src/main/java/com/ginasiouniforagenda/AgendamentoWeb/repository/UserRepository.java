package com.ginasiouniforagenda.AgendamentoWeb.repository;

import com.ginasiouniforagenda.AgendamentoWeb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
