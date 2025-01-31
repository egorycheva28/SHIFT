package ru.cft.template.api.mapper;

import org.springframework.stereotype.Component;
import ru.cft.template.api.dto.ResponseTransferDto;
import ru.cft.template.entity.Transfer;

@Component
public class TransferMapper {

    public static ResponseTransferDto ResponseTransferMapper(Transfer transfer)
    {
        return new ResponseTransferDto(
                transfer.getId(),
                transfer.getUserId(),
                transfer.getCreationTime(),
                transfer.getAmount(),
                transfer.getTransferType(),
                transfer.getStatus()
        );

    }
}
