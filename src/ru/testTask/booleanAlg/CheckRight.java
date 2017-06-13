package ru.testTask.booleanAlg;

/**
 * могут быть различные роли в проекте:
 *      Гость
 *      Редактор
 *      Админ
 *      Представим каждый доступ в отдельности.
 *      Доступ, соответствующий только управлению правами: 00001 (=1) (все нули кроме 1 на позиции, соответствующей этому доступу).
 *      Доступ, соответствующий только изменению товаров: 00010 (=2).
 *      Доступ, соответствующий только просмотру товаров: 00100 (=4).
 *      Доступ, соответствующий только изменению статей: 01000 (=8).
 *      Доступ, соответствующий только просмотру статей: 10000 (=16).
 */
public class CheckRight {
    final public static int ACCESS_ADMIN = 1;          // 00001
    final public static int  ACCESS_GOODS_EDIT = 2;   // 00010
    final public static int  ACCESS_GOODS_VIEW = 4;     // 00100
    final public static int  ACCESS_ARTICLE_EDIT = 8; // 01000
    final public static int  ACCESS_ARTICLE_VIEW = 16;  // 10000

    /**
     * Из этих констант получить нужную комбинацию доступов можно при помощи операции |.
     *
     * guest = ACCESS_ARTICLE_VIEW | ACCESS_GOODS_VIEW; // 10100
     * editor = guest | ACCESS_ARTICLE_EDIT | ACCESS_GOODS_EDIT; // 11110
     * admin = editor | ACCESS_ADMIN; // 11111
     * Теперь, чтобы понять, есть ли в доступе editor нужный доступ,
     * например управление правами – достаточно применить к нему побитовый оператор И (&) с соответствующей константой.
     * Ненулевой результат будет означать, что доступ есть:
     * if (editor & ACCESS_ADMIN); // 0, доступа нет
     * if (editor & ACCESS_ARTICLE_EDIT); // 8, доступ есть
     * */

    public static void main(String[] args) {
        // Допустим поле в checkRight = 11101
        int checkRight = 3;
        if ((ACCESS_ADMIN  & checkRight)>0) {
            System.out.println("ACCESS_ADMIN SUCCESS");
        } else {
            System.out.println("ACCESS_ADMIN ERROR");
        }
    }
}