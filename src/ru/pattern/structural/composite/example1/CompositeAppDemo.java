package ru.pattern.structural.composite.example1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 12.11.2016.
 */

/**
 * Component — это интерфейс, определяющий методы, которые должны быть
 доступными всем частям древовидной структуры. В тех случаях, когда требует-
 ся обеспечить стандартное поведение всех подтипов, можно выполнить
 Component в виде абстрактного класса. Обычно в приложении не создаются
 экземпляры этого класса, а используются экземпляры его подклассов или реа-
 лизующие классы, называемые узлами, из которых и образуется древовидная
 структура.
 * */
interface Сomponent {
    void draw();
}


/**
 * Классы Node
 * Node — класс, реализующий интерфейс Component и предоставляющий реализа-
 цию для каждого метода Component. Различие между классами Composite и Node
 состоит в том, что последний не содержит никаких ссылок на другие экземпляры
 класса Component. Иными словами, совокупность объектов Node представляет
 собой самый нижний, или пограничный, уровень всей структуры в целом.
 * */


class Square implements  Сomponent{
    public void draw() {
        System.out.println("Я квадрат!");
    }
}

class Circle implements  Сomponent{
    public void draw() {
        System.out.println("Я круг!");
    }
}

class Triangle implements  Сomponent{
    public void draw() {
        System.out.println("Я треугольник!");
    }
}
/**
 * Composite— это класс, представляющий собой композицию своих компонен-
 тов, т.е. функциональность его определяется содержащимися в нем компонен-
 тами. Поскольку класс Composite должен предоставлять возможность получе-
 ния динамических групп объектов Component, в нем имеются методы добавле-
 ния экземпляров класса Component в коллекцию и методы их удаления из нее.
 Методы, определенные в классе Component, реализуются таким образом, что-
 бы, во-первых, они выполняли функции, характерные для содержащего экзем-
 пляры компонентов объекта Composite, а во-вторых, чтобы они вызывали та-
 кие же методы для каждого из своих узлов. Такие классы Composite часто на-
 зывают классами ветвей или контейнеров.
 * */
class Composite implements  Сomponent {
    private List<Сomponent> components = new ArrayList<Сomponent>();

    public void addComponents(Сomponent component) {
        components.add(component);
    }

    public void removeComponents(Сomponent component) {
        components.remove(component);
    }
    @Override
    public void draw() {
        for (Сomponent component : components) {
            component.draw();
        }
    }
}

public class CompositeAppDemo {
    public static void main(String[] args) {
        //Набор фигур 1
        Circle circle1 = new Circle();
        Circle circle2 = new Circle();
        Square Square1 = new Square();

        //Набор фигур 2
        Square Square2 = new Square();
        Square Square3 = new Square();
        Triangle triangle1 = new Triangle();


        Composite compose1 = new Composite();
        compose1.addComponents(circle1);
        compose1.addComponents(circle2);
        compose1.addComponents(Square1);

        Composite compose2 = new Composite();
        compose2.addComponents(Square2);
        compose2.addComponents(Square3);
        compose2.addComponents(triangle1);

        Composite compose = new Composite();
        compose.addComponents(compose1);
        compose.addComponents(compose2);

        compose.draw();
    }
}