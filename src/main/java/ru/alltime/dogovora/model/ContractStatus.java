package ru.alltime.dogovora.model;

public enum ContractStatus {
    ACTIVE, // Активный
    EXPIRED, // Истек
    DRAFT, // Черновик
    IN_APPROVAL, // На согласовании
    SIGNATURE_PENDING, //
    SIGNED, // Подписан
    HOLD, // Приостановлен
    TERMINATED, // Расторгнут
    CANCELED // Аннулирован
}
