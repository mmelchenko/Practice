import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Task_4 {

    private LocalDate birthDay;

    public Task_4(String birthDay) {
        this.birthDay = this.convertToLocalDate(birthDay);
    }

    private LocalDate convertToLocalDate(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDate dateTime = LocalDate.parse(date, formatter);

        return dateTime;
    }
    /*
     * Метод, который определяет, сколько времени прошло со времени предидущего дня рождения.
     */

    public String timeFromBirthDay() {

        LocalDate today = LocalDate.now();

        Period period = Period.between(birthDay, today);

        String result = "There are " + period.getMonths() +
                        " months, and " + period.getDays() +
                        " days left from your last birthday.";

        return result;
    }


}
