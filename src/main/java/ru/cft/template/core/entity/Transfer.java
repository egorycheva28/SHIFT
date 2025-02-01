package ru.cft.template.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.cft.template.api.dto.transfer.enums.StatusTransfer;
import ru.cft.template.api.dto.transfer.enums.TransferType;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "transfers")
@Getter
@Setter
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private UUID userId; //кому переводят

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransferType transferType;

    @Enumerated(EnumType.STRING)
    private StatusTransfer status;

}
