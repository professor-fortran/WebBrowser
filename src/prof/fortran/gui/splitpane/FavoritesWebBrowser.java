package prof.fortran.gui.splitpane;

// Набор базовых пакетов Java
import java.awt.*;

// Пакеты расширений Java
import javax.swing.*;

// Пакеты prof.fortran
import prof.fortran.gui.webbrowser.WebBrowserPane;
import prof.fortran.gui.webbrowser.WebToolBar;

/**
 * FavoritesWebBrowser.java
 * FavoritesWebBrowser - приложение для просмотра Web-сайтов
 * с помощью компонентов WebToolBar и WebBrowserPane и отображения
 * HTML-страницы, содержащей ссылки на избранные Web-сайты
 */
public class FavoritesWebBrowser extends JFrame {

    private WebToolBar toolBar;
    private WebBrowserPane browserPane;
    private WebBrowserPane favoritesBrowserPane;

    /**
     * Конструктор WebBrowser
     */
    private FavoritesWebBrowser() {
        super("Prof.Fortran Favorites Web Browser");

        // создание объектов WebBrowserPane и WebToolBar для навигации
        browserPane = new WebBrowserPane();
        toolBar = new WebToolBar(browserPane);

        // создание объекта WebBrowserPane
        // для отображения избранных сайтов
        favoritesBrowserPane = new WebBrowserPane();

        // добавление WebToolBar в качестве слушателя для
        // HyperlinkEvents в компонент favoritesBrowserPane
        favoritesBrowserPane.addHyperlinkListener(toolBar);

        // отображение favorites.html в панели favoritesBrowserPane
        favoritesBrowserPane.goToURL(
                getClass().getResource("/htmls/favorites.html"));

        // создание горизонтальной панели JSplitPane и добавление
        // панелей WebBrowserPanes с полосами прокрутки JScrollPane
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(favoritesBrowserPane),
                new JScrollPane(browserPane));

        // задание положения разделительной линии
        // между панелями WebBrowserPanes
        splitPane.setDividerLocation(210);

        // добавление кнопок для разворачивакия/сворачивания разделителя
        splitPane.setOneTouchExpandable(true);

        // размещение компонентов FavoritesWebBrowser
        Container contentPane = getContentPane();
        contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(splitPane, BorderLayout.CENTER);
    }

    /**
     * Выполнение приложения
     */
    public static void main(String args[]) {
        FavoritesWebBrowser browser = new FavoritesWebBrowser();
        browser.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        browser.setSize(640, 480);
        browser.setVisible(true);
    }

}