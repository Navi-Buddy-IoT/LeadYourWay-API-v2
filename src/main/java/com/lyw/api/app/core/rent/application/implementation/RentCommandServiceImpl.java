package com.lyw.api.app.core.rent.application.implementation;

import org.springframework.stereotype.Service;

import com.lyw.api.app.core.card.application.services.CardCommandService;
import com.lyw.api.app.core.card.domain.commands.UpdateCardAmountCommand;
import com.lyw.api.app.core.rent.application.services.RentCommandService;
import com.lyw.api.app.core.rent.domain.commands.CreateRentCommand;
import com.lyw.api.app.core.rent.domain.model.Rent;
import com.lyw.api.app.core.rent.domain.repositories.RentRepository;
import com.lyw.api.app.core.rent.infrastructure.dto.RentRequestDto;
import com.lyw.api.app.iam.identity.application.services.UserQueryService;
import com.lyw.api.app.iam.identity.domain.queries.GetUserMainCard;
import com.lyw.api.app.shared.utils.ValidationUtil;

@Service
public class RentCommandServiceImpl implements RentCommandService {

    private final RentRepository rentRepository;
    private final CardCommandService cardCommandService;
    private final UserQueryService userQueryService;
    private final ValidationUtil validationUtil;

    public RentCommandServiceImpl(RentRepository rentRepository, CardCommandService cardCommandService,
            UserQueryService userQueryService, ValidationUtil validationUtil) {
        this.rentRepository = rentRepository;
        this.cardCommandService = cardCommandService;
        this.userQueryService = userQueryService;
        this.validationUtil = validationUtil;
    }

    @Override
    public Rent handle(CreateRentCommand command) {
        Rent rent = new Rent();
        rent = setRent(rent, command.rentRequestDto());
        rent.setBicycle(validationUtil.findBicycleById(command.rentRequestDto().getBicycleId()));
        rent.setCard(validationUtil.findCardById(command.rentRequestDto().getCardId()));
        makePayment(command, rent);
        return rentRepository.save(rent);
    }

    private void makePayment(CreateRentCommand command, Rent rent) {
        Long lesseeCardId = command.rentRequestDto().getCardId();
        Long lessorCardId = userQueryService.handle(new GetUserMainCard(rent.getBicycle().getUser().getId(), true))
                .getId();

        double paymentAmount = command.rentRequestDto().getRentPrice();

        cardCommandService.handle(new UpdateCardAmountCommand(lesseeCardId, paymentAmount * -1));
        cardCommandService.handle(new UpdateCardAmountCommand(lessorCardId, paymentAmount));
    }

    private Rent setRent(Rent rent, RentRequestDto rentRequestDto) {
        rent.setRentStartDate(rentRequestDto.getRentStartDate());
        rent.setRentEndDate(rentRequestDto.getRentEndDate());
        rent.setRentPrice(rentRequestDto.getRentPrice());
        return rent;
    }
}
