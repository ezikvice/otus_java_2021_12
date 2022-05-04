# Домашние задания для курса OTUS ["Разработчик Java"](https://otus.ru/lessons/java-professional/?utm_source=github&utm_medium=free&utm_campaign=otus)

### Группа 2021-12

## Задание 1: 
Создание проекта, сборка в gradle, использование google.guava
#### NB! под линуксом джарник лучше запускать из родного, не идеевского терминала, поскольку пока что у jetBrains не все рантаймы поддерживают emoji
(["см. багу здесь"](https://youtrack.jetbrains.com/issue/JBR-410#focus=streamItem-27-4163770.0-0) )

## Задание 2 (занятие 4: Дженерики, стандартные коллекции):
Исправить классы из homework, чтобы тесты проходили

## Задание 3: 
Сделать маленький тестовый фреймворк на аннотациях

## Задание 4:
Сборщик мусора. Посмотреть зависимость времени сборки мусора от выделенной приложению памяти

## Задание 5:
Автологирование (работа с загрузчиками классов). Посмотреть как может реализовываться AOP.

## Задание 7:
Структурные паттерны. Реализовать to do:
1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
2. Сделать процессор, который поменяет местами значения field11 и field12
3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
Секунда должна определяьться во время выполнения.
Тест - важная часть задания
Обязательно посмотрите пример к паттерну Мементо!
4. Сделать Listener для ведения истории (подумайте, как сделать, чтобы сообщения не портились)
Уже есть заготовка - класс HistoryListener, надо сделать его реализацию
Для него уже есть тест, убедитесь, что тест проходит

## Задание 8:
сериализация/десериализация файлов .json
За Jackson Mix-Ins спасибо Александру Оруджеву за наводку и Henry Brown (https://github.com/hgbrown/jackson-mixin-example) за пример


## Задание 9:
Самодельный ORM, **цель:** Научиться работать с jdbc.
запуск докер образов в /docker/runDb.src