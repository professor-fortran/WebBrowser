package prof.fortran.gui.webbrowser;

// Набор базовых пакетов Java
import java.awt.*;

// Пакеты расширений Java
import javax.swing.*;

/**
 * WebBrowser.java
 * WebBrowser - приложение для просмотра Web-страниц с использованием компонентов WebToolBar и WebBrowserPane
 */
public class WebBrowser extends JFrame {

    private WebToolBar toolBar;
    private WebBrowserPane browserPane;

    /**
     * Конструктор WebBrowser
     */
    private WebBrowser() {
        super("Prof.Fortran Web Browser");

        // создание WebBrowserPane и WebToolBar для навигации
        browserPane = new WebBrowserPane();
        toolBar = new WebToolBar(browserPane);

        // размещение компонентов WebBrowser
        Container contentPane = getContentPane();
        contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(browserPane), BorderLayout.CENTER);
    }

    /**
     * Выполнение приложения
     */
    public static void main(String args[]) {
        WebBrowser browser = new WebBrowser();
        browser.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        browser.setSize(640, 480);
        browser.setVisible(true);
    }

}