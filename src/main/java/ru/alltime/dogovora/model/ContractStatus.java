package ru.alltime.dogovora.model;

public enum ContractStatus {  //  todo Поставил единичное число, потому-что по нейминг конвенции, исправить в документации?
    ACTIVE, // Активный
    EXPIRED, // Истек
    DRAFT, // Черновик
    IN_APPROVAL, // На согласовании
    SIGNATURE_PENDING, //
    SIGNED, // Подписан
    HOLD, // Приостановлен
    TERMINATED, // Расторгнут
    CANCELED // Аннулирован todo Исправил грамматическую ошибку в документации, надо справить в документации её
}
