package pyramidBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PyramidBuilderImpl implements PyramidBuilder {

    @Override
    public List<List<Integer>> build(int[] arr) throws CannotBuildPyramidException {
        //Сортируем массив по возрастанию
        Arrays.sort(arr);
        //Вычисляем колличество строк нашей пирамиды
        //---------------------------------
        int n = arr.length;
        int idx = 0;
        int numRows = 0;

        while (idx < n) {
            numRows++;
            for (int numInRow = 0; numInRow < numRows; numInRow++) {
                idx++;
            }
        }
        //---------------------------------
        //Создаем список для хранения линий
        List<List<Integer>> array = new ArrayList<>();
        //Счетчик номера строки
        int rowCount = 1;
        //Счетчик аргумента нашего массива
        int arrCounter = 0;

        try {
            //Запускаем цикл чтобы заполнить список для каждой линии
            for (int i = numRows; i > 0; i--) {
                //Создаем список для хранения данных на нашей линии
                List<Integer> line = new ArrayList<>();
                //Заполняем нулями "до"
                for (int j = 1; j <= i; j++) {
                    line.add(0);
                }
                //Заполняем список числами из списка arr, добавляем нули и увеличиваем счетчик
                for (int j = 1; j <= rowCount; j++) {
                    line.add(arr[arrCounter]);
                    line.add(0);
                    arrCounter++;
                }
                //Заполняем нулями "после"
                for (int j = 1; j < i; j++) {
                    line.add(0);
                }
                //Добавляем линию в список хранящий линии
                array.add(line);
                //Инкрементируем счетчик номера строки
                rowCount++;
            }
            return array;
            //Если мы вышли за пределы массива и не можем построить пирамиду выбрасываем ошибку
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CannotBuildPyramidException();
        }
    }
}
