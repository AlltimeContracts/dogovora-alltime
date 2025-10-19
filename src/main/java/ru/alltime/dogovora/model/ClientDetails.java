package ru.alltime.dogovora.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "client_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private String ogrnOgrnip; // ОГРН/ОГРНИП

    @Column(nullable = false)
    private String inn; // ИНН

    private String kpp; // КПП

    private String legalAddress; // Юридический адрес

    private String actualAddress; // Фактический адрес

    private String currentAccount; // Расчетные реквизиты

    private String correspondentAccount; // Корреспондентский счет

}
