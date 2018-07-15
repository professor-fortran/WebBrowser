package prof.fortran.gui.tabbedpane;

// Набор базовых пакетов Java
import java.awt.*;
import java.awt.event.ActionEvent;

// Пакеты расширений Java
import javax.swing.*;

// Пакеты prof.fortran
import prof.fortran.gui.webbrowser.WebBrowserPane;
import prof.fortran.gui.webbrowser.WebToolBar;

/**
 * TabbedPaneWebBrowser.java
 * TabbedPaneWebBrowser - приложение, которое использует класс
 * JTabbedPane для отображения нескольких окон Web-браузера
 */
public class TabbedPaneWebBrowser extends JFrame {

    /**
     * Класс JTabbedPane для одновременного отображения нескольких браузеров
     */
    private final JTabbedPane tabbedPane = new JTabbedPane();

    /**
     * Конструктор TabbedPaneWebBrowser
     */
    private TabbedPaneWebBrowser() {
        super("JTabbedPane Web Browser");

        // создание вкладки для первого браузера
        createNewTab();

        // добавление JTabbedPane в панель contentPane
        getContentPane().add(tabbedPane);

        // создание меню File (JMenu) для создания новых
        // вкладок браузеров и выхода из приложения
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new NewTabAction());
        fileMenu.addSeparator();
        fileMenu.add(new ExitAction());
        fileMenu.setMnemonic('F');

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    } // завершение конструктора TabbedPaneWebBrowser

    /**
     * Создание новой вкладки для браузера
     */
    private void createNewTab() {
        // создание панели JPanel, включающей панели WebBrowserРапе и WebToolBar
        JPanel panel = new JPanel(new BorderLayout());

        // создание панелей WebBrowserPane и WebToolBar
        WebBrowserPane browserPane = new WebBrowserPane();
        WebToolBar toolBar = new WebToolBar(browserPane);

        // добавление WebBrowserPane и WebToolBar в панель JPanel
        panel.add(toolBar, BorderLayout.NORTH);
        panel.add(new JScrollPane(browserPane), BorderLayout.CENTER);

        // добавление JPanel в JTabbedPane
        tabbedPane.addTab("Browser " + tabbedPane.getTabCount(), panel);
    }

    /**
     * Действие для создания новой вкладки для браузера
     */
    private class NewTabAction extends AbstractAction {
        /**
         * Конструктор NewTabAction
         */
        NewTabAction() {
            // задание имени, описания и "горячей” клавиши
            putValue(Action.NAME, "New Browser Tab");
            putValue(Action.SHORT_DESCRIPTION, "Create New Web Browser Tab");
            putValue(Action.MNEMONIC_KEY, (int) 'N');
        }

        /**
         * При возбуждении Action создать новую вкладку браузера
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            createNewTab();
        }
    }

    /**
     * Действие для выхода из приложения
     */
    private static class ExitAction extends AbstractAction {
        /**
         * Конструктор ExitAction
         */
        ExitAction() {
            // задание имени, описания и "горячей" клавиши
            putValue(Action.NAME, "Exit");
            putValue(Action.SHORT_DESCRIPTION, "Exit Application");
            putValue(Action.MNEMONIC_KEY, (int) 'X');
        }

        /**
         * Выход из приложения
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }

    /**
     * Выполнение приложения
     */
    public static void main(String args[]) {
        TabbedPaneWebBrowser browser = new TabbedPaneWebBrowser();
        browser.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        browser.setSize(640, 480);
        browser.setVisible(true);
    }

}