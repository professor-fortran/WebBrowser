package prof.fortran.gui.webbrowser;

// Набор базовых пакетов Java
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

// Пакеты расширений Java
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 * WebToolBar.java
 * WebToolBar - подкласс класса JToolBar, содержащий компоненты для навигации в панели WebBrowserPane.
 * WebToolBar содержит кнопки "Вперед", и "Назад", и текстовое поле для ввода URL
 */
public class WebToolBar extends JToolBar implements HyperlinkListener {

    private WebBrowserPane webBrowserPane;

    private JButton backButton;
    private JButton forwardButton;
    private JTextField urlTextField;

    /**
     * Коструктор WebToolBar
     */
    public WebToolBar(WebBrowserPane browser) {
        super("Web Navigation");

        // регистрация событий HyperlinkEvent
        webBrowserPane = browser;
        webBrowserPane.addHyperlinkListener(this);

        // создание объекта JTextField для ввода URL
        urlTextField = new JTextField(25);
        urlTextField.addActionListener(
                new ActionListener() {
                    /**
                     * Переход в WebBrowserPane по введенному пользователем URL
                     */
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        try {
                            // попытка загрузить страницу в WebBrowserPane
                            URL url = new URL(urlTextField.getText());
                            webBrowserPane.goToURL(url);
                        } catch (MalformedURLException urlException) {
                            // обработка ошибочного URL
                            urlException.printStackTrace();
                        }
                    }
                }
        );

        // создание объекта JButton для перехода к предыдущему URL в журнале
        backButton = new JButton(new ImageIcon(
                getClass().getResource("/images/back.png")));

        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        // переход по предыдущему URL
                        URL url = webBrowserPane.back();

                        // отображение URL в поле urlTextField
                        urlTextField.setText(url.toString());
                    }
                }
        );

        // создание объекта JButton для перехода к следующему URL в журнале
        forwardButton = new JButton(new ImageIcon(
                getClass().getResource("/images/forward.png")));

        forwardButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        // переход к следующему URL
                        URL url = webBrowserPane.forward();

                        // отображение нового URL в поле urlTextField
                        urlTextField.setText(url.toString());
                    }
                }
        );

        // добавление компонентов JButton-ов и JTextField в панель WebToolBar
        add(backButton);
        add(forwardButton);
        add(urlTextField);
    } // завершение конструктора WebToolBar

    /**
     * Отслеживание событий HyperlinkEvent в WebBrowserPane
     */
    @Override
    public void hyperlinkUpdate(HyperlinkEvent event) {
        // если гиперссылка была активирована, перейти по URL ссылки
        if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            // получение URL из HyperlinkEvent
            URL url = event.getURL();

            if (url != null) { // проверка, что URL действительно получен
                // переход пo URL и отображение URL в поле urlTextField
                webBrowserPane.goToURL(url);
                urlTextField.setText(url.toString());
            }
        }
    }

}
