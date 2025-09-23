package ru.alltime.dogovora.infrastructure.models;

import jakarta.persistence.*;
import lombok.*;
import ru.alltime.dogovora.domain.models.ContractStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contract")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ContractEntity {

    @Id
    @Column(name="id", nullable=false, updatable=false)
    private String id;

    @Column(name="contract_num", nullable=false)
    private String contractNum;

    @Column(name="active", nullable=false)
    private boolean active = true;

    // fileList — список строк-ссылок
    @ElementCollection
    @CollectionTable(name="contract_files", joinColumns = @JoinColumn(name="contract_id"))
    @Column(name="file_link")
    private List<String> fileList = new ArrayList<>();

    @Column(name="contract_date_from")
    private String contractDateFrom;

    @Column(name="contract_date_to")
    private String contractDateTo;

    @Column(name="client_id", nullable=false)
    private String clientId;

    // managerId — список id пользователей
    @ElementCollection
    @CollectionTable(name="contract_managers", joinColumns = @JoinColumn(name="contract_id"))
    @Column(name="user_id")
    private List<String> managerId = new ArrayList<>();

    @Lob
    @Column(name="description_text", columnDefinition = "text")
    private String descriptionText;

    @Enumerated(EnumType.STRING)
    @Column(name="current_status", nullable=false)
    private ContractStatus currentStatus;
}