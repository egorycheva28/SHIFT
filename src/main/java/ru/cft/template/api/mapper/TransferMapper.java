package ru.cft.template.api.mapper;

import org.springframework.stereotype.Component;
import ru.cft.template.api.dto.transfer.ListTransfersDto;
import ru.cft.template.api.dto.transfer.Pagination;
import ru.cft.template.api.dto.transfer.ResponseTransferDto;
import ru.cft.template.core.entity.Transfer;

import java.util.List;

@Component
public class TransferMapper {

    public static ResponseTransferDto responseTransferMapper(Transfer transfer)
    {
        return new ResponseTransferDto(
                transfer.getId(),
                transfer.getCreationTime(),
                transfer.getAmount(),
                transfer.getTransferType(),
                transfer.getStatus()
        );
    }

    public static ListTransfersDto listTransfersDto(List<ResponseTransferDto> responseTransferDto, Pagination pagination)
    {
        return new ListTransfersDto(
                responseTransferDto,
                pagination
        );
    }
}
