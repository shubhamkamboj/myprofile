package net.codejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.model.SendMessage;

public interface SendMessageRepository extends JpaRepository<SendMessage, Long> {

}
