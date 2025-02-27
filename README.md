# infotecs
# Реализация SFTP клиента для работы с json

## Описание

Приложения для работы с SFTP-сервером, на котором расположен файл с информацией о доменных адресах в виде JSON, следующей структуры:
{ "addresses": [
  {"domain": "first.domain", "ip": "192.168.0.1"},
  {"domain": "second.domain", "ip": "192.168.0.2"},
  {"domain": "third.domain", "ip": "192.168.0.3"}
]}
 
Клиент принимает на вход адрес, логин, пароль и порт SFTP-сервера.


### Запуск приложения

*Чтобы запустить приложение необходимо:*
1. Склонировать репозиторий с GitHub на компьютер
   ```sh
   [https://github.com/CHIKOTILA/Infotecs](https://github.com/CHIKOTILA/infotecs.git)
    ```
2. Перейти в корневую директорию ДОПИСАТЬ
   
3. Запустить исполняемый **jar** файл проекта
   ```sh
   java -jar 
   ```
----
## Работа с приложением
При запуске приложения пользователя встречает авторизация

<img width="342" alt="Авторизация" src="https://github.com/user-attachments/assets/38c32210-9f25-49c9-8e47-6766ed81b1ac" />



Пользователю будет предложено ввести данные для подключения.
>[!IMPORTANT]
>Если данные будет введены неправильно, то программа предложет их ввести заново,
> или завершить работу

После ввода данных, пользователю откроется меню

<img width="527" alt="Меню" src="https://github.com/user-attachments/assets/0cf49145-9d96-4ae4-90a4-fd39b1e785a8" />



>[!IMPORTANT]
>Расположен файл с информацией о доменных адресах в виде JSON
>структуры уникальными значениями доменов и IP-адресов уже лежит в папке dir
>

### Структура файла

```json
{ "addresses": [
  {"domain": "first.domain", "ip": "192.168.0.1"},
  {"domain": "forth.domain", "ip": "111.111.111.111"},
  {"domain": "second.domain", "ip": "192.168.0.2"},
  {"domain": "third.domain", "ip": "192.168.0.3"}

]}
```

----

### Операции

Ниже приведены примеры 

1. Получение списка пар "домен – адрес" из файла в ***алфавитном порядке***

  <img width="332" alt="Список пар" src="https://github.com/user-attachments/assets/d3280313-ed84-4681-928e-de8d46f095b6" />



2. Получение id по его **Домену**

    <img width="527" alt="Поиск по Домену" src="https://github.com/user-attachments/assets/62fcdeb4-8286-4f2c-815d-978a3847d1d6" />



3. Получение домена по его **IP**

   <img width="527" alt="Поиск по IP" src="https://github.com/user-attachments/assets/b4c889be-cb24-487c-a427-6ad24e0a1c6e" />



4. Добавление пары **'Домен - адрес'** в файл

  <img width="523" alt="Добавление новой пары" src="https://github.com/user-attachments/assets/e96b6948-a571-4045-8dbe-5320ab81f650" />
  
 >В случаях, если пользователь ввёл несуществующий IP,
 >будет
 >отображено сообщение об этом
  <img width="281" alt="Ошибка добавления новой пары " src="https://github.com/user-attachments/assets/5077850f-2cea-496d-8b2f-2e8dc4dcae63" />



6. Удаление пары **'Домен - адрес'** по его **id** или **домену**

   <img width="453" alt="Удаление пары" src="https://github.com/user-attachments/assets/58307d90-0f53-46cd-82e6-6eb14b897f01" />


7. Завершение программы
   
<img width="374" alt="Завершение работы программы" src="https://github.com/user-attachments/assets/73123ba4-d557-4319-b915-57c1fd02d784" />


## Инструкция по запуску тестов и кратким обоснованием тестов

1.Перейти в корневую директорию проекта 
   
2. Запустить исполняемый **jar** файл проекта
   ```sh
   java -jar путь до папки/infotecs/out/artifacts/infotecs_jar/infotecs.jar
   ```
