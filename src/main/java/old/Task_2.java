package old;

public class Task_2 {

    /*
     * Метод для конвертации из гривны в доллар
     */

    public double usdConverter(double uah, double course) {

        double usd = uah * course;

        return usd;
    }

    /*
     * Метод для увеличения элементов массива на определенный процент
     */

    public double[] increaseMassive(double[] basicMassive, int increasePercent) {

        double[] increasedMassive =  new double[basicMassive.length];

        for (int i = 0; i < basicMassive.length; i++) {
            increasedMassive[i] = basicMassive[i] + (basicMassive[i]/100*increasePercent);
        }

        return increasedMassive;
    }

    /*
     * Метод-цензор для замены определенных слов(строк) в тексте
     */

    public String stringCensor(String text, String censoredWord) {

        String censoredText = text;
        int position = text.indexOf(censoredWord);

        while (position != -1) {
            String firstPart = censoredText.substring(0,position);
            String secondPart = censoredText.substring(position + censoredWord.length(), censoredText.length() - 1);
            censoredText = firstPart + "*censored*" + secondPart + censoredText.charAt(censoredText.length() - 1);
            position = censoredText.indexOf(censoredWord);
        };

        return censoredText;
    }
}
