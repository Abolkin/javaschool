package subsequence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SubsequenceImpl implements Subsequence {

    @Override
    public boolean find(List<String> first, List<String> second) {
        //Создаем итераторы для наших списков(ниже ссылка что это такое)
        //https://metanit.com/java/tutorial/5.10.php
        Iterator<String> firstIterator = first.iterator();
        Iterator<String> secondIterator = second.iterator();
        //Создаем список для хранения совпавших строк
        List<String> third = new ArrayList<>();
        //Создаем переменные хранящие первые значения из наших итераторов
        String fNext = firstIterator.next();
        String sNext = secondIterator.next();

        //Пока наш первый итератор имеет следующее значение продолжай цикл
        while (firstIterator.hasNext()) {
            //Пока наш второй итератор имеет следующее значение продолжай цикл
            while (secondIterator.hasNext()) {
                //Если переменные эквивалентны, то...
                if (sNext.equals(fNext)) {
                    //Добавляем в список совпавшее значение
                    third.add(fNext);
                    //Переменная fNext хранит следующее значение
                    fNext = firstIterator.next();
                    //Иначе
                } else {
                    //Переменная sNext хранит следующее значение
                    sNext = secondIterator.next();
                }
            }
        }
        //Так как наш итератор secondIterator достиг предела придется проверить последнее сохраненное значение
        if (sNext.equals(fNext)) {
            //И добавить в список если условие выполняется
            third.add(fNext);
        }
        //Если списки совпадают, то возвращаем true
        return third.equals(first);
    }
}
