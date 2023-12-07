package com.alloho.modul2task5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private TextView totalNumber;
    private TextView minNumber;
    private TextView unSort;

    public int[] workMassive() {

        String pi = "3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808651";
        String string = pi.replaceAll("\\D", "");
        String[] strings = string.split("");
        int[] result = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            result[i] = Integer.parseInt(strings[i]);

        }

        return result;
    }
    public StringBuilder totalNumber() {

        int[] mas = workMassive();
        StringBuilder result = new StringBuilder();


        Arrays.stream(mas).boxed().collect(Collectors.toMap(key -> key, val -> 1, Integer::sum))//Масив в Map ключ цифра, значение увеличивается
                .entrySet().stream()    //поток для Map
                .filter(key -> key.getKey() == 3 || key.getKey() == 5) //фильтр 3 и 5
                .forEach(x -> result.append("кол-во ").append(x.getKey()).append(" = ").append(x.getValue()).append("\n"));

        return result;

    }
    public StringBuilder minNumber() {
        StringBuilder result = new StringBuilder();
        int[] mas = workMassive();

        result.append("Цифра ")
                .append(" ")
                .append(Arrays.stream(mas).boxed()
                        .collect(Collectors.toMap(key -> key, val -> 1, Integer::sum))
                        .entrySet().stream()
                        .min(Map.Entry.comparingByValue())
                        .get()
                        .getKey())
                .append("\n Кол-во повторений равно ")
                .append(Arrays.stream(mas).boxed()
                        .collect(Collectors.toMap(key -> key, val -> 1, Integer::sum))
                        .entrySet().stream()
                        .min(Map.Entry.comparingByValue())
                        .get()
                        .getValue());
        ;

        return result;
    }
    public StringBuilder unSort(int[] massive) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < massive.length / 2; i++) {
            int tmp = massive[i];
            massive[i] = massive[massive.length - i - 1];
            massive[massive.length - i - 1] = tmp;
        }
        for (int i : massive) {
            result.append(i).append(" ");
        }

        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalNumber = findViewById(R.id.totalNumber);
        totalNumber.setText(totalNumber());
        minNumber = findViewById(R.id.minNumber);
        minNumber.setText(minNumber());
        unSort = findViewById(R.id.unSort);
        unSort.setText(unSort(workMassive()));

    }
}
