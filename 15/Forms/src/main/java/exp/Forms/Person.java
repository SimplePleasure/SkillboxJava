package exp.Forms;


import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Component
@PropertySource("${classpath:app.properties}")

public class Person{
    static DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @NotBlank(message = "Имя должно быть заполнено")
    String name;
    @NotBlank(message = "Фамилия должна быть заполнена")
    String surname;
    @NotBlank(message = "Отчество должно быть заполнено")
    String patronymic;
    @NotBlank(message = "Должен быть указан телефон для связи")
    String phone;
    @NotBlank(message = "Графа день рождения должна быть заполнена")
    String birthday;
    Integer period;
    Integer count;

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getPatronymic() {
        return patronymic;
    }
    public String getBirthday() {
        return birthday;
    }
    public String getPhone() {
        return phone;
    }
    public Integer getPeriod() {
        return period;
    }
    public Integer getCount() {
        return count;
    }



    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setPeriod(Integer period) {
        this.period = period;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
}
