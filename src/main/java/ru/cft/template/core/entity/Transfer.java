package ru.cft.template.core.entity;

import jakarta.persistence.*;
import javax.validation.constraints.*;
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
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sender_wallet_id", referencedColumnName = "number")
    private Wallet senderWallet; //кто переводит

    @ManyToOne
    @JoinColumn(name = "receive_wallet_id", referencedColumnName = "number")
    private Wallet receiveWallet; //кому переводят

    @ManyToOne
    @JoinColumn(name = "receiver_phone", referencedColumnName = "phone")
    private User receiverPhone; //кому переводят

    @NotNull(message = "CreationTime - это обязательное поле.")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_time", nullable = false)
    private Date creationTime;

    @NotNull(message = "Amount - это обязательное поле.")
    @Column(name = "amount", nullable = false)
    private Long amount;

    @NotNull(message = "TransferType - это обязательное поле.")
    @Column(name = "transfer_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransferType transferType;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusTransfer status;

}
