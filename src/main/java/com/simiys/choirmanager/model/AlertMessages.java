package com.simiys.choirmanager.model;

public enum AlertMessages {
    TOKEN_EXPIRED("Похоже, что срок действия сслыки закончился. \n Пожалуйста запросите восстановлеият пароля еще раз"),
    TOKEN_INVALID("Похоже, что в ссылке какая-то ошибка. \n Пожалуйста запросите ссылку еще раз"),
    RECOVERY_MAIL_MESSSAGE("Письмо с ссылкой для восстановления пароля отправлено вам на почту"),
    REGISTRATION_MAIL_MESSAGE("Письмо с ссылкой для регистрации отправлено вам на почту"),
    SMTH_WRONG("Похоже, что-то пошло не так..."),
    NO_SUCH_USER_PRESENT("Такой пользователь не зарегистрирован. \n Проверьте правилность ввода email или зарегистрируйтесь."),
    REGISTRATION_COMPLETE("Регистрация успешно завершена!"),
    RECOVERY_COMPLETE("Пароль успешно изменен!"),
    JOIN("Вы успешно присоеденились к хору!");

    private String message;

    public String getMessage() {
        return message;
    }

    AlertMessages(String msg) {
        this.message = msg;
    }
}
