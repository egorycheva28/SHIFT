package ru.cft.template.api.dto.transfer;

public record Pagination(
        Long size,
        int count,
        Long current
) {
}
