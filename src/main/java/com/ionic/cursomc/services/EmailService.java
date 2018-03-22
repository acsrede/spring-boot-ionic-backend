package com.ionic.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.ionic.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmation(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
	
}
