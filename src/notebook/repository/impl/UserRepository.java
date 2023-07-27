package notebook.repository.impl;

import notebook.mapper.impl.UserMapper;
import notebook.model.User;
import notebook.repository.GBRepository;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements GBRepository<User, Long> {
    private final UserMapper mapper;
    private final String fileName;

    public UserRepository(String fileName) {
        this.mapper = new UserMapper();
        this.fileName = fileName;
    }

    @Override
    public List<User> findAll() {
        List<String> lines = readAll();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.toOutput(line));
        }
        return users;
    }
    private List<String> readAll() {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(fileName);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            if (line != null) {
                lines.add(line);
            }
            while (line != null) {
                // считываем остальные строки в цикле
                line = reader.readLine();
                if (line != null) {
                    lines.add(line);
                }
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    @Override
    public User create(User user) {
        List<User> users = findAll();
        long next = getMaxID(users) + 1;
        user.setId(next);
        users.add(user);
        List<String> lines = new ArrayList<>();
        for (User u: users) {
            lines.add(mapper.toInput(u));
        }
        saveAll(lines);
        return user;
    }
    private Long getMaxID (List<User> userList) {
        long max = 0L;
        for (User u : userList) {
            long id = u.getId();
            if (max < id){
                max = id;
            }
        }
        return max;
    }
    private void saveAll(List<String> data) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            for (String line : data) {
                // запись всей строки
                writer.write(line);
                // запись по символам
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public User findById(Long id) {
        List<User> users = findAll();
        User foundUser = null;
        for (User u: users) {
            if (u.getId() == id) foundUser = u;
        }
        return foundUser;
    }

    @Override
    public Optional<User> update(Long id, User user) {
        List<User> users = findAll();
        User editUser = users.stream()
                .filter(u -> u.getId()
                        .equals(id))
                .findFirst().orElseThrow(() -> new RuntimeException("USER NOT FOUND"));
        editUser.setId(id);
        editUser.setFirstName(user.getFirstName());
        editUser.setLastName(user.getLastName());
        editUser.setPhone(user.getPhone());
        write(users);
        return Optional.empty();
    }
    private void write(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User u: users) {
            lines.add(mapper.toInput(u));
        }
        saveAll(lines);
    }

    @Override
    public boolean delete(Long id) {
        boolean result = false;
        List<User> users = findAll();
        User userToDelete = (User)findById(id);
        if (userToDelete != null) {
            result = true;
            users.remove(userToDelete);
            write(users);
        }
        return result;
    }
}
