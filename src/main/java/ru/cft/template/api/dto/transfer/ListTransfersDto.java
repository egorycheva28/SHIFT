package ru.cft.template.api.dto.transfer;

import java.util.List;

public record ListTransfersDto(
        List<ResponseTransferDto> transfers,
        Pagination pagination
) {
}
