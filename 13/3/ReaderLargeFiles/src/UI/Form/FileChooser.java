package UI.Form;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileChooser {
    static private final String[][] FILE_FILTERS = {{"xml", "XML файлы (*.xml)"},
            {"html", "HTML файлы(*.html)"},
            {"txt", "TXT файлы(*.txt)"}};
    static private FileNameExtensionFilter fileFilter;
    static private File fileChosen;


    static public File chooseFile(String[][] FILE_FILTERS) {
        //создание
        JFileChooser fileChooser = new JFileChooser();
        //fileChooser.setAcceptAllFileFilterUsed(true);
        // Определяем фильтры типов файлов
        for (int i = 0; i < FILE_FILTERS[0].length + 1; i++) {
            FileNameExtensionFilter eff = new FileNameExtensionFilter(FILE_FILTERS[i][1],
                    FILE_FILTERS[i][0]);
            fileChooser.addChoosableFileFilter(eff);
        }
        //fileChooser.
        //настройка выбора: только файлы

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Choose file to read");
        //настройка фильтра файлов
        //fileChooser.setFileFilter(fileFilter);
        //вызываем диалоговое окно
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            fileChosen = fileChooser.getSelectedFile();
            return fileChosen;
        }

        return null;
    }

    public static File OpenFile() {
        File inputFile = null;
        File chosenFile = chooseFile(FILE_FILTERS);

        //Если файл не выбран - не меняем файл для сохранения
        if (chosenFile != null) inputFile = chosenFile;

        //выводим информацию о результате выбора файла

        return inputFile;
    }

    //проверяем - если на конце файла расширение, если нет - добавляем
    static private File setFileExtension() {
        return fileChosen.getPath().matches("^.*\\." + fileFilter.getExtensions()[0] + "$") ? fileChosen :
                new File(fileChosen.getPath() + "." + fileFilter.getExtensions()[0]);
    }
}