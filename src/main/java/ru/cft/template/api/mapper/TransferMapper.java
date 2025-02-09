package ru.cft.template.api.mapper;

import org.springframework.stereotype.Component;
import ru.cft.template.api.dto.transfer.ResponseTransferDto;
import ru.cft.template.core.entity.Transfer;

@Component
public class TransferMapper {

    public static ResponseTransferDto ResponseTransferMapper(Transfer transfer)
    {
        return new ResponseTransferDto(
                transfer.getId(),
                transfer.getCreationTime(),
                transfer.getAmount(),
                transfer.getTransferType(),
                transfer.getStatus()
        );

    }
}
