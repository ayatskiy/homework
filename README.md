Проект-обучение Otus **Java QA Automation**

Запуск авто-тестов через консоль: mvn clean test -Dtest=TestClassName#testName

Возможно использование дополнительных параметров запуска:
1. **otus_login** - погин пользователя otus для авторизации.
2. **otus_password** - пароль пользователя otus для авторизации.
4. **browser** - браузер, в котором требуется запуск. Регистр не имеет значения. Доступны на выбор: CHROME, FIREFOX, OPERA, EDGE

Пример запуска:
```bash
mvn clean test -Dtest=FourthHomeworkTest#checkOtusProfileInfo -Dotus_login="login" -Dotus_password="password" -Dbrowser="chrome"
```