package exp.Forms;


import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person{
    static DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @NotBlank(message = "Имя должно быть заполнено")
    String name;
    @NotBlank(message = "Фамилия должна быть заполнена")
    String surname;
    @NotBlank(message = "Отчество должно быть заполнено")
    String patronymic;
    LocalDate birthday;
    @NotBlank(message = "Должен быть указан телефон для связи!")
    String phone;
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
    public LocalDate getBirthday() {
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
        this.birthday = LocalDate.parse(birthday, f);
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
