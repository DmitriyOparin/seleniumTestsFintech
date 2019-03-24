# seleniumTestsFintech

1. Скачать репозиторий
2. Открыть проект в InteliJ IDEA
3. Открыть настройки конфигурации проекта
4. В строку VM options прописать:
* -Dtest=emptyFields -Dbrowser=chrome - для запуска тестов в GoogleChrome
* -Dtest=emptyFields -Dbrowser=firefox - для запуска тестов в Mozilla FireFox
* -Dtest=emptyFields -Dbrowser=opera - для запуска тестов в Opera
5. Запустить тесты из класса TestsTariffs