package ru.cft.template.api.mapper;

import org.springframework.stereotype.Component;
import ru.cft.template.api.dto.session.GetSessionDto;
import ru.cft.template.core.entity.Session;

@Component
public class SessionMapper {

    public static GetSessionDto getSessionMapper(Session session)
    {
        return new GetSessionDto(
                session.getId(),
                session.getUserId(),
                session.getExpirationTime(),
                session.getActive()
        );
    }
}
