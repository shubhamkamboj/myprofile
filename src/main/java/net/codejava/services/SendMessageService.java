package net.codejava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.model.SendMessage;
import net.codejava.repository.SendMessageRepository;

@Service
@Transactional
public class SendMessageService {

	@Autowired
	private SendMessageRepository repo;
	
	public List<SendMessage> listAll() {
		return repo.findAll();
	}
	
	public void save(SendMessage sendMessage) {
		repo.save(sendMessage);
	}
	
	public SendMessage get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
}
