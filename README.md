Данная работа выполнена для дипломного проекте курса "Тестирования ПО" , в работе представлено тестирование веб-сервиса с использованием СУБД и API банка

1. Запустить Docker
2. Ввести в терминале `docker-compose up --build`
3. Для запуска сервиса с указанием пути к базе данных:

   3.1. Для mysql ввести в терминале `java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar`
   
   3.2. Для postgresql ввести в терминале `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar`
4. Открыть новую вкладку в терминале (не закрывая предыдущую)
5. Запуск тестов c указанием пути к базе данных:

   5.1. Для mysql ввести в терминале `./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"`
   
   5.2. Для postgresql ввести в терминале `./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"`
6. Для произведения отчета Allure  ввести в терминале `gradlew allureReport`
7. Для получения отчета Allure ввести в терминале `gradlew allureServe`