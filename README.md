# gb-notebook
Что сделала: 
Избавилась от пакета dao: 
- перенесла финальную переменную класса __FileOperation__ *__fileName__* в класс __UserRepository__
- перенесла в класс __UserRepository__ методы __*List<String> readAll()*__ и __*void saveAll(List<String> data)*__, 
работающие со списками строк, сделав их приватными в этом классе, чтобы не были доступны извне 

Добавила в класс __UserRepository__ методы: 
- __*public User findById(Long id)*__, 
- __*public boolean delete(Long id)*__, 
- вынесла в отдельный метод __*private Long getMaxID (List<User> userList)*__ логику поиска последнего задействованного id.

В класс __User__ добавила переопределения методов: 
- __*public boolean equals(Object o)*__ и 
- __*public int hashCode()*__, 
чтобы можно было воспользоваться методом __*List.remove(Object o)*__ применительно к списку пользователей для 
удаления пользователя из БД. 
