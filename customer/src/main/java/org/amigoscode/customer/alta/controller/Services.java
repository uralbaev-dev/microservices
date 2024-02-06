package org.amigoscode.customer.alta.controller;

import lombok.RequiredArgsConstructor;
import org.amigoscode.customer.alta.entity.Card;
import org.amigoscode.customer.alta.entity.CardTransaction;
import org.amigoscode.customer.alta.entity.User;
import org.amigoscode.customer.alta.payload.CardDto;
import org.amigoscode.customer.alta.payload.CardTransactionDto;
import org.amigoscode.customer.alta.payload.UserDto;
import org.amigoscode.customer.alta.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: Service
 * @date: 10.01.2024
 * @author: Uralbaev Diyorbek
 */

@Service
@RequiredArgsConstructor
public class Services {

    private final UserRepository userRepository;

    public List<UserDto> getAllUsersWithCardsAndTransactions() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToUserDTO)
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDTO(User user) {
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setFio(user.getFio());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setStatus(user.getStatus());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setCards(mapToCardDtoList(user.getCards()));
        return userDTO;
    }

    private List<CardDto> mapToCardDtoList(List<Card> cards) {
        return cards.stream()
                .map(this::mapToCardDto)
                .collect(Collectors.toList());
    }

    private CardDto mapToCardDto(Card card) {
        CardDto cardDto = new CardDto();
        cardDto.setId(card.getId());
        cardDto.setType(card.getType());
        cardDto.setBalance(card.getBalance());
        cardDto.setUserId(card.getUser().getId());
        cardDto.setNumber(card.getNumber());
        cardDto.setExpire(card.getExpire());
        cardDto.setStatus(card.getStatus());
        cardDto.setCreatedAt(card.getCreatedAt());
        cardDto.setCardTransactions(mapToCardTransactionDTOList(card.getCardTransactions()));
        return cardDto;
    }

    private List<CardTransactionDto> mapToCardTransactionDTOList(List<CardTransaction> transactions) {
        return transactions.stream()
                .map(this::mapToCardTransactionDTO)
                .collect(Collectors.toList());
    }

    private CardTransactionDto mapToCardTransactionDTO(CardTransaction transaction) {
        CardTransactionDto transactionDTO = new CardTransactionDto();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setType(transaction.getType());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setOldBalance(transaction.getOld_balance());
        transactionDTO.setNewBalance(transaction.getNew_balance());
        transactionDTO.setUserId(transaction.getUser().getId()); // Added user_id
        transactionDTO.setCardId(transaction.getCard().getId());
        return transactionDTO;
    }

}
