package prof.fortran.gui.webbrowser;

// Набор базовых пакетов Java
import java.util.List;
import java.util.ArrayList;
import java.net.URL;
import java.io.IOException;

// Пакеты расширений Java
import javax.swing.JEditorPane;

/**
 * WebBrowserPane.java
 * WebBrowserPane — простой компонент для просмотра Web-содержимого, который
 * расширяет класс JEditorPane и хранит перечень просмотренных URL
 */
public class WebBrowserPane extends JEditorPane {

    private List<URL> history = new ArrayList<>();
    private int historyIndex;

    /**
     * Конструктор WebBrowserPane
     */
    public WebBrowserPane() {
        // запрет редактирования для разрешения переходов по гиперссылкам
        setEditable(false);
    }

    /**
     * Отображение заданного URL и добавление его в журнал
     */
    public void goToURL(URL url) {
        displayPage(url);
        history.add(url);
        historyIndex = history.size() - 1;
    }

    /**
     * Отображение следующего URL из журнала в панели JEditorPane
     */
    public URL forward() {
        historyIndex++;

        // не выходить за конец журнала
        if (historyIndex >= history.size()) {
            historyIndex = history.size() - 1;
        }

        URL url = history.get(historyIndex);
        displayPage(url);

        return url;
    }

    /**
     * Отображение предыдущего URL из журнала в панели JEditorPane
     */
    public URL back() {
        historyIndex--;

        // не выходить за начало журнала
        if (historyIndex < 0) {
            historyIndex = 0;
        }

        // отображение предыдущего URL
        URL url = history.get(historyIndex);
        displayPage(url);

        return url;
    }

    /**
     * Отображение содержимого по заданному URL в панели JEditorPane
     */
    private void displayPage(URL pageURL) {
        try {
            // отображение URL
            setPage(pageURL);
        } catch (IOException ioException) {
            // обработка исключения при отображении URL
            ioException.printStackTrace();
        }
    }

}