package ru.alltime.dogovora.model;

public enum BusinessForm {

    SOLE_PROPRIETOR("ИП"),      // Индивидуальный предприниматель
    LLC("ООО"),                 // Общество с ограниченной ответственностью
    JSC("АО"),                  // Акционерное общество
    NON_PROFIT("НКО"),          // Некоммерческая организация
    LIMITED_PARTNERSHIP("ТОО"), // Товарищество с ограниченной ответственностью
    FARM_ENTERPRISE("КФХ");     // Крестьянское фермерское хозяйство

    private final String title;

    BusinessForm(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
